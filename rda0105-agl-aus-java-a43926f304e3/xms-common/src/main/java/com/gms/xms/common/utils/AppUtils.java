package com.gms.xms.common.utils;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.context.ContextBase;
import com.google.gson.reflect.TypeToken;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.*;
import com.itextpdf.tool.xml.XMLWorkerHelper;

import freemarker.template.Configuration;
import freemarker.template.Template;
import net.sf.jxls.transformer.XLSTransformer;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.SystemUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.ss.usermodel.Workbook;
import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;
import org.springframework.core.io.ClassPathResource;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xhtmlrenderer.pdf.ITextRenderer;
import org.xml.sax.InputSource;

import javax.imageio.ImageIO;
import javax.mail.*;
import javax.mail.Message.RecipientType;
import javax.mail.internet.*;
import javax.xml.bind.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.lang.reflect.Type;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;
import java.util.List;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Posted from AppUtils.java
 * <p>
 * Author Toantq Date Mar 15, 2015 Time: 08:17:05 AM
 */
public class AppUtils {
    protected static Log log = LogFactory.getLog(AppUtils.class);

    private static final int PAD_LIMIT = 8192;

    private static final String TEMPLATES_PDF_FONTS_PATH = "templates/pdf/fonts/";

    private static final String GMS_BASE_TMP_LINUX = "/opt/gms/xms-au/tmp/";
    private static final String GMS_BASE_TMP_WINDOWS = "C:/gms/xms-au/tmp/";

    /**
     * Return a correct array of email addresses
     *
     * @param address - String of address
     * @return InternetAddress[]
     * @throws AddressException
     */
    private static InternetAddress[] buildInternetAddressArray(String address) throws AddressException {
        InternetAddress[] internetAddressArray = null;
        try {
            internetAddressArray = InternetAddress.parse(address);
        } catch (AddressException ae) {
            log.error(ae.getMessage());
            throw ae;
        }
        return internetAddressArray;
    }

    public static Number parseNumber(String strNumber) throws Exception {
        if (StringUtils.isBlank(strNumber)) {
            return null;
        }
        NumberFormat numberFormat = new DecimalFormat(AppConstants.APP_SETTINGS.getDefaultNumberFormat());
        return numberFormat.parse(strNumber);
    }

    /**
     * Load configuration file to Object
     *
     * @param filePath - path of setting file
     * @param type     - Type of Object
     * @return Object
     * @throws JAXBException
     */
    @SuppressWarnings("unchecked")
    public static <T> T loadConfig(String filePath, Class<T> type) throws JAXBException {
        URL url = type.getResource(filePath);
        JAXBContext jaxbContext = JAXBContext.newInstance(type);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        T t = (T) jaxbUnmarshaller.unmarshal(new File(url.getPath()));
        log.info(filePath + " loaded.| settings:" + t.toString());
        return t;
    }

    public static <T> T loadConfigFromExternalFileSystem(String filePath, Class<T> type) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(type);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        File file = new File(filePath);
        if (file.exists()) {
            T t = (T) jaxbUnmarshaller.unmarshal(file);
            log.info(filePath + " loaded.| settings:" + t.toString());
            return t;
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    public static <T> T xmlString2Object(String strXml, Class<T> type) throws JAXBException {
        StringReader reader = new StringReader(strXml);
        JAXBContext jaxbContext = JAXBContext.newInstance(type);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        T t = (T) jaxbUnmarshaller.unmarshal(reader);
        log.info(strXml + " loaded.| content:" + t.toString());
        return t;
    }

    public static String Object2XmlString(Object object, @SuppressWarnings("rawtypes") Class type) throws JAXBException {
        return Object2XmlString(object, type, true);
    }

    public static String Object2XmlString(Object object, @SuppressWarnings("rawtypes") Class type, boolean includeVersion) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(type);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        if (!includeVersion) {
            jaxbMarshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
            // jaxbMarshaller.setProperty("com.sun.xml.bind.xmlDeclaration", Boolean.FALSE);
        }
        StringWriter sw = new StringWriter();
        jaxbMarshaller.marshal(object, sw);
        return sw.toString();
    }

    /**
     * @param from        - email sender
     * @param pass        - password of email account
     * @param to          - receiver address
     * @param host        - host of email server
     * @param port        - port of email server
     * @param cc          - cc address
     * @param bcc         - bcc address
     * @param subject     - subject of email
     * @param content     - content of email
     * @param attachFiles - file attachments
     * @throws Exception
     */
    public static void sendEmail(String host, int port, final String fromName, final String fromEmail, final String username, final String password, String toEmail, String cc, String bcc, String subject, String content, String[] attachFiles) throws Exception {

        if (StringUtils.isBlank(fromEmail))
            throw new Exception("Not found from address in request.");

        if (StringUtils.isBlank(toEmail))
            throw new Exception("Not found to address in request.");

        // Sets SMTP server properties
        Properties props = new Properties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.socketFactory.port", port);
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");

        // creates a new session with an authenticator
        Authenticator auth = new Authenticator() {
            @Override
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        };
        Session mailSession = Session.getInstance(props, auth);
        // Creates a new email message
        Message message = new MimeMessage(mailSession);

        message.setFrom(new InternetAddress(fromEmail, fromName));

        String toAddresses = AppConstants.APP_SETTINGS.getDevelopmentEmail();
        String ccAddresses = "";
        String bccAddresses = "";

        if (AppConstants.PRODUCTION_MODE.equalsIgnoreCase(AppConstants.APP_SETTINGS.getAppMode())) {
            toAddresses = toEmail;
            ccAddresses = cc;
            bccAddresses = bcc;
        }

        if (StringUtils.isNotBlank(toAddresses)) {
            toAddresses = toAddresses.replace(";", ",");
            InternetAddress[] addressArray = buildInternetAddressArray(toAddresses);
            if ((addressArray != null) && (addressArray.length > 0)) {
                message.setRecipients(RecipientType.TO, addressArray);
            } else
                throw new Exception("Invalid to address in request.");
        } else
            throw new Exception("Invalid to address in request.");

        if (StringUtils.isNotBlank(ccAddresses)) {
            ccAddresses = ccAddresses.replace(";", ",");
            InternetAddress[] addressArray = buildInternetAddressArray(ccAddresses);
            if ((addressArray != null) && (addressArray.length > 0)) {
                message.setRecipients(RecipientType.CC, addressArray);
            }
        }

        if (StringUtils.isNotBlank(bccAddresses)) {
            bccAddresses = bccAddresses.replace(";", ",");
            InternetAddress[] addressArray = buildInternetAddressArray(bccAddresses);
            if ((addressArray != null) && (addressArray.length > 0)) {
                message.setRecipients(RecipientType.BCC, addressArray);
            }
        }

        message.setSubject(subject);
        message.setSentDate(new Date());

        // Creates a message part
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(content, "text/html; charset=UTF-8");

        // Creates multi-part
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);

        // Adds attachments
        if (attachFiles != null && attachFiles.length > 0) {
            for (String filePath : attachFiles) {
                MimeBodyPart attachPart = new MimeBodyPart();
                try {
                    attachPart.attachFile(filePath);
                } catch (IOException ex) {
                    log.error(ex.getMessage());
                }
                multipart.addBodyPart(attachPart);
            }
        }

        // Sets the multi-part as email's content
        message.setContent(multipart);

        // Sends the email
        Transport.send(message);
    }

    public static void sendEDIDownloadMail(String host, int port, final String fromName, final String fromEmail, final String username, final String password, String toEmail, String cc, String bcc, String subject, String content, String[] attachFiles) throws Exception {

        if (StringUtils.isBlank(fromEmail))
            throw new Exception("Not found from address in request.");

        if (StringUtils.isBlank(toEmail))
            throw new Exception("Not found to address in request.");

        // Sets SMTP server properties
        Properties props = new Properties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.socketFactory.port", port);
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");

        // creates a new session with an authenticator
        Authenticator auth = new Authenticator() {
            @Override
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        };
        Session mailSession = Session.getInstance(props, auth);
        // Creates a new email message
        Message message = new MimeMessage(mailSession);

        message.setFrom(new InternetAddress(fromEmail, fromName));

        String toAddresses = AppConstants.APP_SETTINGS.getDevelopmentEmail();
        String ccAddresses = "";
        String bccAddresses = "";

        if (AppConstants.PRODUCTION_MODE.equalsIgnoreCase(AppConstants.APP_SETTINGS.getAppMode())) {
            toAddresses = toEmail;
            ccAddresses = cc;
            bccAddresses = bcc;
        } else {
            subject += " Testing mail send to " + toEmail;
        }

        if (StringUtils.isNotBlank(toAddresses)) {
            toAddresses = toAddresses.replace(";", ",");
            InternetAddress[] addressArray = buildInternetAddressArray(toAddresses);
            if ((addressArray != null) && (addressArray.length > 0)) {
                message.setRecipients(RecipientType.TO, addressArray);
            } else
                throw new Exception("Invalid to address in request.");
        } else
            throw new Exception("Invalid to address in request.");

        if (StringUtils.isNotBlank(ccAddresses)) {
            ccAddresses = ccAddresses.replace(";", ",");
            InternetAddress[] addressArray = buildInternetAddressArray(ccAddresses);
            if ((addressArray != null) && (addressArray.length > 0)) {
                message.setRecipients(RecipientType.CC, addressArray);
            }
        }

        if (StringUtils.isNotBlank(bccAddresses)) {
            bccAddresses = bccAddresses.replace(";", ",");
            InternetAddress[] addressArray = buildInternetAddressArray(bccAddresses);
            if ((addressArray != null) && (addressArray.length > 0)) {
                message.setRecipients(RecipientType.BCC, addressArray);
            }
        }

        message.setSubject(subject);
        message.setSentDate(new Date());

        // Creates a message part
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        content = "<div style='font: normal 12pt Arial, sans-serif; color: #142b71 !important;'>" + content + "</div>";
        messageBodyPart.setContent(content, "text/html; charset=UTF-8");

        // Creates multi-part
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);

        // Adds attachments
        if (attachFiles != null && attachFiles.length > 0) {
            for (String filePath : attachFiles) {
                MimeBodyPart attachPart = new MimeBodyPart();
                try {
                    attachPart.attachFile(filePath);
                } catch (IOException ex) {
                    log.error(ex.getMessage());
                }
                multipart.addBodyPart(attachPart);
            }
        }

        // Sets the multi-part as email's content
        message.setContent(multipart);

        // Sends the email
        Transport.send(message);
    }

    /**
     * Return utf8 string
     *
     * @param text - string to remove non utf8 characters
     * @return - utf8 string
     */
    public static String reconcileUTF8String(String text) {
        if (StringUtils.isEmpty(text)) {
            return text;
        }

        String utf8Text = "";
        try {
            byte[] utf8Bytes = text.getBytes("UTF-8");
            utf8Text = new String(utf8Bytes, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.info("Unsupported Encoding for " + text + "");
            return "";
        }
        try {
            if (utf8Text != null) {
                Pattern unicodeOutliers = Pattern.compile("[^\\x00-\\x7F]", Pattern.UNICODE_CASE | Pattern.CANON_EQ | Pattern.CASE_INSENSITIVE);
                Matcher unicodeOutlierMatcher = unicodeOutliers.matcher(utf8Text);
                utf8Text = unicodeOutlierMatcher.replaceAll(" ");

            }
        } catch (Exception e) {
            log.info("Error when clean up UTF8 String " + text);
        }
        return utf8Text;
    }

    /**
     * Create file content from freemarker template
     *
     * @param outputFilePath - path of file result
     * @param append         - append to file (true: append, false: create new)
     * @param tmpName        - template file
     * @param data           - data to fill in template
     * @return - boolean value (true : success, false: error)
     */
    public static boolean template2File(String outputFilePath, boolean append, String tmpName, Map<String, Object> data) {
        @SuppressWarnings("deprecation")
        Configuration cfg = new Configuration();
        try {
            cfg.setClassForTemplateLoading(AppUtils.class, "/");
            cfg.setDefaultEncoding("UTF-8");
            // File tmpFile = new File(outputFilePath);
            // Writer fw = new FileWriter(tmpFile, false);

            Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFilePath, append), "UTF-8"));
            Template template = cfg.getTemplate(tmpName);
            template.process(data, writer);
            writer.flush();
            writer.close();
        } catch (Exception e) {
            log.error("Error when render file from template.| outputFilePath: " + outputFilePath + "| tmpName: " + tmpName + "| data: " + data, e);
            return false;
        }
        return true;
    }

    /**
     * Return a string from freemarker template
     *
     * @param tempLoadPath - directory of template
     * @param tmpName      - template file
     * @param data         - data to fill in template
     * @return - String
     * @throws Exception - on error
     */
    public static String template2Text(String tempLoadPath, String tmpName, HashMap<String, Object> data) throws Exception {
        @SuppressWarnings("deprecation")
        Configuration cfg = new Configuration();
        Writer writer = new StringWriter();
        cfg.setDirectoryForTemplateLoading(new File(tempLoadPath));
        Template template = cfg.getTemplate("header.ftl");
        template.process(data, writer);
        return writer.toString();
    }

    /**
     * <p>
     * Left pad a String with spaces (' ').
     * </p>
     * <p>
     * <p>
     * The String is padded to the size of <code>size<code>.</p>
     * <p>
     * <pre>
     * StringUtils.leftPad(null, *)   = null
     * StringUtils.leftPad("", 3)     = "   "
     * StringUtils.leftPad("bat", 3)  = "bat"
     * StringUtils.leftPad("bat", 5)  = "  bat"
     * StringUtils.leftPad("bat", 1)  = "bat"
     * StringUtils.leftPad("bat", -1) = "bat"
     * </pre>
     * <p>
     * &#64;param str  the String to pad out, may be null
     * &#64;param size  the size to pad to
     * &#64;return left padded String or original String if no padding is necessary,
     * <code>null</code> if null String input
     */
    public static String leftPad(String str, int size) {
        return leftPad(str, size, ' ');
    }

    /**
     * <p>
     * Left pad a String with a specified character.
     * </p>
     * <p>
     * <p>
     * Pad to a size of <code>size</code>.
     * </p>
     * <p>
     * <pre>
     * StringUtils.leftPad(null, *, *)     = null
     * StringUtils.leftPad("", 3, 'z')     = "zzz"
     * StringUtils.leftPad("bat", 3, 'z')  = "bat"
     * StringUtils.leftPad("bat", 5, 'z')  = "zzbat"
     * StringUtils.leftPad("bat", 1, 'z')  = "bat"
     * StringUtils.leftPad("bat", -1, 'z') = "bat"
     * </pre>
     *
     * @param str     the String to pad out, may be null
     * @param size    the size to pad to
     * @param padChar the character to pad with
     * @return left padded String or original String if no padding is necessary, <code>null</code> if null String input
     * @since 2.0
     */
    public static String leftPad(String str, int size, char padChar) {
        if (str == null) {
            return null;
        }
        int pads = size - str.length();
        if (pads <= 0) {
            return str; // returns original String when possible
        }
        if (pads > PAD_LIMIT) {
            return leftPad(str, size, String.valueOf(padChar));
        }
        return padding(pads, padChar).concat(str);
    }

    /**
     * <p>
     * Left pad a String with a specified String.
     * </p>
     * <p>
     * <p>
     * Pad to a size of <code>size</code>.
     * </p>
     * <p>
     * <pre>
     * StringUtils.leftPad(null, *, *)      = null
     * StringUtils.leftPad("", 3, "z")      = "zzz"
     * StringUtils.leftPad("bat", 3, "yz")  = "bat"
     * StringUtils.leftPad("bat", 5, "yz")  = "yzbat"
     * StringUtils.leftPad("bat", 8, "yz")  = "yzyzybat"
     * StringUtils.leftPad("bat", 1, "yz")  = "bat"
     * StringUtils.leftPad("bat", -1, "yz") = "bat"
     * StringUtils.leftPad("bat", 5, null)  = "  bat"
     * StringUtils.leftPad("bat", 5, "")    = "  bat"
     * </pre>
     *
     * @param str    the String to pad out, may be null
     * @param size   the size to pad to
     * @param padStr the String to pad with, null or empty treated as single space
     * @return left padded String or original String if no padding is necessary, <code>null</code> if null String input
     */
    public static String leftPad(String str, int size, String padStr) {
        if (str == null) {
            return null;
        }
        if (isEmpty(padStr)) {
            padStr = " ";
        }
        int padLen = padStr.length();
        int strLen = str.length();
        int pads = size - strLen;
        if (pads <= 0) {
            return str; // returns original String when possible
        }
        if (padLen == 1 && pads <= PAD_LIMIT) {
            return leftPad(str, size, padStr.charAt(0));
        }

        if (pads == padLen) {
            return padStr.concat(str);
        } else if (pads < padLen) {
            return padStr.substring(0, pads).concat(str);
        } else {
            char[] padding = new char[pads];
            char[] padChars = padStr.toCharArray();
            for (int i = 0; i < pads; i++) {
                padding[i] = padChars[i % padLen];
            }
            return new String(padding).concat(str);
        }
    }

    /**
     * <p>
     * Returns padding using the specified delimiter repeated to a given length.
     * </p>
     * <p>
     * <pre>
     * StringUtils.padding(0, 'e')  = ""
     * StringUtils.padding(3, 'e')  = "eee"
     * StringUtils.padding(-2, 'e') = IndexOutOfBoundsException
     * </pre>
     * <p>
     * <p>
     * Note: this method doesn't not support padding with <a href="http://www.unicode.org/glossary/#supplementary_character"> Unicode Supplementary Characters</a> as they require a pair of <code>char</code>s to be represented. If you are needing to support full I18N of your applications consider using {@link #repeat(String, int)} instead.
     * </p>
     *
     * @param repeat  number of times to repeat delim
     * @param padChar character to repeat
     * @return String with repeated character
     * @throws IndexOutOfBoundsException if <code>repeat &lt; 0</code>
     * @see #repeat(String, int)
     */
    private static String padding(int repeat, char padChar) throws IndexOutOfBoundsException {
        if (repeat < 0) {
            throw new IndexOutOfBoundsException("Cannot pad a negative amount: " + repeat);
        }
        final char[] buf = new char[repeat];
        for (int i = 0; i < buf.length; i++) {
            buf[i] = padChar;
        }
        return new String(buf);
    }

    // Empty checks
    // -----------------------------------------------------------------------

    /**
     * <p>
     * Checks if a String is empty ("") or null.
     * </p>
     * <p>
     * <pre>
     * StringUtils.isEmpty(null)      = true
     * StringUtils.isEmpty("")        = true
     * StringUtils.isEmpty(" ")       = false
     * StringUtils.isEmpty("bob")     = false
     * StringUtils.isEmpty("  bob  ") = false
     * </pre>
     * <p>
     * <p>
     * NOTE: This method changed in Lang version 2.0. It no longer trims the String. That functionality is available in isBlank().
     * </p>
     *
     * @param str the String to check, may be null
     * @return <code>true</code> if the String is empty or null
     */
    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    /**
     * Read a file into byte array
     *
     * @param file - file to read
     * @return - a byte array
     * @throws Exception - on error
     */
    public static byte[] readContentIntoByteArray(File file) throws Exception {
        FileInputStream fileInputStream = null;
        byte[] bFile = new byte[(int) file.length()];
        fileInputStream = new FileInputStream(file);
        fileInputStream.read(bFile);
        fileInputStream.close();
        return bFile;
    }

    public static <T> T getObjectFromContext(ContextBase context, String attrName, Class<T> clazz) {
        String value = context.getString(attrName);
        return GsonUtils.fromGson(value, clazz);
    }

    public static <T> T getObjectFromContext(ContextBase context, String attrName, Type type) {
        String value = context.getString(attrName);
        return GsonUtils.fromGson(value, type);
    }

    public static <T> List<T> getListObjectFromContext(ContextBase context, String attrName) {
        Type type = new TypeToken<List<T>>() {
        }.getType();
        String value = context.getString(attrName);
        return GsonUtils.fromGson(value, type);
    }

    public static void createPDFFromHTML(String htmlFilePath, String pdfFilePath) throws Exception {
        FileOutputStream fos = new FileOutputStream(new File(pdfFilePath));
        ITextRenderer render = new ITextRenderer();
        render.setDocument(new File(htmlFilePath));
        render.layout();
        render.createPDF(fos);
        fos.close();
    }

    public static void createPDFFromHTMLWithFont(String htmlFilePath, String pdfFilePath, String font) throws Exception {
        FileOutputStream fos = new FileOutputStream(new File(pdfFilePath));
        ITextRenderer render = new ITextRenderer();
        String fontPath = TEMPLATES_PDF_FONTS_PATH + font + ".ttf";

        render.getFontResolver().addFont(fontPath, true);
        render.setDocument(new File(htmlFilePath));
        render.layout();
        render.createPDF(fos);
        fos.close();
    }

    public static void createPDFFromHTMLWithFont(String htmlFilePath, String pdfFilePath, String font, Boolean bold) throws Exception {
    	
        FileOutputStream fos = new FileOutputStream(new File(pdfFilePath));
        ITextRenderer render = new ITextRenderer();
        String fontPath = TEMPLATES_PDF_FONTS_PATH + font + ".ttf";
        String fontBoldPath = TEMPLATES_PDF_FONTS_PATH + font + "bd.ttf";
        render.getFontResolver().addFont(fontPath, true);

        if (bold) {
            render.getFontResolver().addFont(fontBoldPath, true);
        }
               
        render.setDocument(new File(htmlFilePath));   //previous code 
        render.layout();
        render.createPDF(fos);
        fos.close();  
        
    	/*FileOutputStream fos = new FileOutputStream(new File(pdfFilePath));
        ITextRenderer render = new ITextRenderer();
        String fontPath = TEMPLATES_PDF_FONTS_PATH + font + ".ttf";
        String fontBoldPath = TEMPLATES_PDF_FONTS_PATH + font + "bd.ttf";
        render.getFontResolver().addFont(fontPath, true);
        if (bold) {
            render.getFontResolver().addFont(fontBoldPath, true);
        }

        com.itextpdf.text.Document document = new com.itextpdf.text.Document();
        document.setMargins(50,50, 10, 10);
        PdfWriter writer = PdfWriter.getInstance(document, fos);
        document.open();
        FileInputStream inputStream = new FileInputStream(htmlFilePath);
        XMLWorkerHelper.getInstance().parseXHtml(writer, document, inputStream);
        document.close();
        fos.close();*/
    }

    public static void createPDFFromHTMLWithFont(LinkedList<String> htmlFilePaths, String pdfFilePath, String font, Boolean bold) throws Exception {
        int currentPage = 1;
        FileOutputStream fos = new FileOutputStream(new File(pdfFilePath));
        try {
            ITextRenderer render = new ITextRenderer();
            String fontPath = TEMPLATES_PDF_FONTS_PATH + font + ".ttf";
            String fontBoldPath = TEMPLATES_PDF_FONTS_PATH + font + "bd.ttf";
            render.getFontResolver().addFont(fontPath, true);

            if (bold) {
                render.getFontResolver().addFont(fontBoldPath, true);
            }
            ListIterator<String> iterator = htmlFilePaths.listIterator();
            while (iterator.hasNext()) {
                render.setDocument(new File(iterator.next()));
                render.layout();
                if (currentPage == 1) {
                    render.createPDF(fos, false);
                } else {
                    render.writeNextDocument(currentPage);
                }
                currentPage += render.getRootBox().getLayer().getPages().size();
            }
            render.finishPDF();
        } catch (Exception e) {
            throw (e);
        } finally {
            fos.close();
        }
    }

    public static void createPDFFromBarCode(String pdfFilePath, String barCode) throws Exception {
        try {
        	
        	int index = pdfFilePath.lastIndexOf("/");
            String  wri_value = pdfFilePath.substring(0, index);
            File file = new File(wri_value);
            if(!file.exists()){
            	file.mkdir();
            }
        	
            FileOutputStream writer = new FileOutputStream(new File(pdfFilePath));
            barCode = barCode.replaceAll("\r\n", "");
            byte[] data = DatatypeConverter.parseBase64Binary(barCode);
            writer.write(data);
            writer.close();
        } catch (Exception ex) {
            throw ex;
        }
    }

    public static String replaceStringByMap(Map<String, String> map, String content) {

        String body = content;
        String key;
        String value;

        if (map == null || body == null) {
            return body;
        }
    /* log.info("Replacement body: " + body + "| Map: " + map); */
        for (Entry<String, String> entry : map.entrySet()) {
            key = entry.getKey();
            value = entry.getValue();
            if (key != null && value != null) {
                body = StringUtils.replace(body, key, value);
            }
        }
        return body;
    }

    public static boolean checkPermission(String userLevel, String path, Map<String, List<String>> permissionMap, Map<String, List<String>> actionMap) {

        if (path == null || permissionMap == null || actionMap == null || userLevel == null) {
            return false;
        }
        String action;
        try {
            int end = path.lastIndexOf(".ix");
            action = path.substring(0, end);
            action = action.substring(action.lastIndexOf("/"), action.length()).replace("/", "");
        } catch (Exception e) {
            action = path;
        }

        List<String> groupList = actionMap.get(action);
        List<String> userPermissionGroups = permissionMap.get(userLevel);
        if (groupList == null || groupList.size() == 0 || userPermissionGroups == null || userPermissionGroups.size() == 0) {
            return false;
        }
        for (String group : groupList) {
            if (group != null && userPermissionGroups.contains(group)) {
                return true;
            }
        }
        return false;
    }

    // This is building function
    public static String formatXmlString(String xml) {
        return xml;
    }

    /**
     * Create xls file from input
     *
     * @param outPutFilePath - path of output file
     * @param xlsTemplPath   - path of xls template file
     * @param data           - map of datas
     * @return {@link Boolean} true is success, false is error.
     */
    public static Boolean generateXLSFile(String outPutFilePath, String xlsTemplPath, Map<String, Object> data) {
        try {
            InputStream inputStreamOfTemplate = new ClassPathResource(xlsTemplPath).getInputStream();
            XLSTransformer transformer = new XLSTransformer();
            Workbook workbook = transformer.transformXLS(inputStreamOfTemplate, data);
            FileOutputStream outputStream = new FileOutputStream(new File(outPutFilePath));
            workbook.write(outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            log.error(e);
            return false;
        }
        return true;
    }

    public static Boolean generateXLSFile(String outPutFilePath, String templatePath, Context data) {
        try {
            InputStream inputStreamOfTemplate = new ClassPathResource(templatePath).getInputStream();
            OutputStream os = new FileOutputStream(new File(outPutFilePath));
            JxlsHelper.getInstance().processTemplate(inputStreamOfTemplate, os, data);
        } catch (Exception e) {
            log.error(e);
            return false;
        }
        return true;
    }

    public static String removeHtmlTags(String strings) {
        if (!StringUtils.isBlank(strings)) {
            strings = strings.replace("<br>", "/n");
            strings = strings.replace("<br/>", "/n");
            strings = strings.replace("<br />", "/n");
            strings = strings.replaceAll("\\<.*?>", "");
            return strings;
        }
        return null;
    }

    public static void createBarCode(String code, String fileExtension, String outPutFilePath, Integer width, Integer height) throws IOException {
        Barcode128 barcode = new Barcode128();
        barcode.setCode(code);
        barcode.setBarHeight(Float.valueOf(height));
        Image img = barcode.createAwtImage(Color.BLACK, Color.WHITE);
       BufferedImage bffImg = new BufferedImage(500, 500, BufferedImage.TYPE_4BYTE_ABGR);
        bffImg.createGraphics().drawImage(img, 0, 0, 500, 500, null);
        
       
    ImageIO.write(bffImg, fileExtension, new File(outPutFilePath));
    }

    public static List<Double> convertStringArrayToDoubleList(String[] string) {
        List<Double> result = new LinkedList<>();
        for (String s : string) {
            Double d = Double.parseDouble(s);
            result.add(d);
        }
        return result;
    }

    public static String generateSessionKey(int length) {
        String alphabet = new String("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"); // 9
        int n = alphabet.length(); // 10

        String result = new String();
        Random r = new Random(); // 11

        for (int i = 0; i < length; i++) // 12
            result = result + alphabet.charAt(r.nextInt(n)); // 13

        return result;
    }

    /**
     * Get Hashmap Timezone
     *
     * @return HashMap<String, String> : "Kwajalein", "(GMT-12:00) International Date Line West";
     */
    public static HashMap<String, String> getTimeZoneList() {
        HashMap<String, String> timeZoneList = new LinkedHashMap<String, String>();
        timeZoneList.put("Kwajalein", "(GMT-12:00) International Date Line West");
        timeZoneList.put("Pacific/Midway", "(GMT-11:00) Midway Island");
        timeZoneList.put("Pacific/Samoa", "(GMT-11:00) Samoa");
        timeZoneList.put("Pacific/Honolulu", "(GMT-10:00) Hawaii");
        timeZoneList.put("America/Anchorage", "(GMT-09:00) Alaska");
        timeZoneList.put("America/Los_Angeles", "(GMT-08:00) Pacific Time (US &amp; Canada)");
        timeZoneList.put("America/Tijuana", "(GMT-08:00) Tijuana, Baja California");
        timeZoneList.put("America/Denver", "(GMT-07:00) Mountain Time (US &amp; Canada)");
        timeZoneList.put("America/Chihuahua", "(GMT-07:00) Chihuahua");
        timeZoneList.put("America/Mazatlan", "(GMT-07:00) Mazatlan");
        timeZoneList.put("America/Phoenix", "(GMT-07:00) Arizona");
        timeZoneList.put("America/Regina", "(GMT-06:00) Saskatchewan");
        timeZoneList.put("America/Tegucigalpa", "(GMT-06:00) Central America");
        timeZoneList.put("America/Chicago", "(GMT-06:00) Central Time (US &amp; Canada)");
        timeZoneList.put("America/Mexico_City", "(GMT-06:00) Mexico City");
        timeZoneList.put("America/Monterrey", "(GMT-06:00) Monterrey");
        timeZoneList.put("America/New_York", "(GMT-05:00) Eastern Time (US &amp; Canada)");
        timeZoneList.put("America/Bogota", "(GMT-05:00) Bogota");
        timeZoneList.put("America/Lima", "(GMT-05:00) Lima");
        timeZoneList.put("America/Rio_Branco", "(GMT-05:00) Rio Branco");
        timeZoneList.put("America/Indiana/Indianapolis", "(GMT-05:00) Indiana (East)");
        timeZoneList.put("America/Caracas", "(GMT-04:30) Caracas");
        timeZoneList.put("America/Halifax", "(GMT-04:00) Atlantic Time (Canada)");
        timeZoneList.put("America/Manaus", "(GMT-04:00) Manaus");
        timeZoneList.put("America/Santiago", "(GMT-04:00) Santiago");
        timeZoneList.put("America/La_Paz", "(GMT-04:00) La Paz");
        timeZoneList.put("America/St_Johns", "(GMT-03:30) Newfoundland");
        timeZoneList.put("America/Argentina/Buenos_Aires", "(GMT-03:00) Georgetown");
        timeZoneList.put("America/Sao_Paulo", "(GMT-03:00) Brasilia");
        timeZoneList.put("America/Godthab", "(GMT-03:00) Greenland");
        timeZoneList.put("America/Montevideo", "(GMT-03:00) Montevideo");
        timeZoneList.put("Atlantic/South_Georgia", "(GMT-02:00) Mid-Atlantic");
        timeZoneList.put("Atlantic/Azores", "(GMT-01:00) Azores");
        timeZoneList.put("Atlantic/Cape_Verde", "(GMT-01:00) Cape Verde Is.");
        timeZoneList.put("Europe/Dublin", "(GMT) Dublin");
        timeZoneList.put("Europe/Lisbon", "(GMT) Lisbon");
        timeZoneList.put("Europe/London", "(GMT) London");
        timeZoneList.put("Africa/Monrovia", "(GMT) Monrovia");
        timeZoneList.put("Atlantic/Reykjavik", "(GMT) Reykjavik");
        timeZoneList.put("Africa/Casablanca", "(GMT) Casablanca");
        timeZoneList.put("Europe/Belgrade", "(GMT+01:00) Belgrade");
        timeZoneList.put("Europe/Bratislava", "(GMT+01:00) Bratislava");
        timeZoneList.put("Europe/Budapest", "(GMT+01:00) Budapest");
        timeZoneList.put("Europe/Ljubljana", "(GMT+01:00) Ljubljana");
        timeZoneList.put("Europe/Prague", "(GMT+01:00) Prague");
        timeZoneList.put("Europe/Sarajevo", "(GMT+01:00) Sarajevo");
        timeZoneList.put("Europe/Skopje", "(GMT+01:00) Skopje");
        timeZoneList.put("Europe/Warsaw", "(GMT+01:00) Warsaw");
        timeZoneList.put("Europe/Zagreb", "(GMT+01:00) Zagreb");
        timeZoneList.put("Europe/Brussels", "(GMT+01:00) Brussels");
        timeZoneList.put("Europe/Copenhagen", "(GMT+01:00) Copenhagen");
        timeZoneList.put("Europe/Madrid", "(GMT+01:00) Madrid");
        timeZoneList.put("Europe/Paris", "(GMT+01:00) Paris");
        timeZoneList.put("Africa/Algiers", "(GMT+01:00) West Central Africa");
        timeZoneList.put("Europe/Amsterdam", "(GMT+01:00) Amsterdam");
        timeZoneList.put("Europe/Berlin", "(GMT+01:00) Berlin");
        timeZoneList.put("Europe/Rome", "(GMT+01:00) Rome");
        timeZoneList.put("Europe/Stockholm", "(GMT+01:00) Stockholm");
        timeZoneList.put("Europe/Vienna", "(GMT+01:00) Vienna");
        timeZoneList.put("Europe/Minsk", "(GMT+02:00) Minsk");
        timeZoneList.put("Africa/Cairo", "(GMT+02:00) Cairo");
        timeZoneList.put("Europe/Helsinki", "(GMT+02:00) Helsinki");
        timeZoneList.put("Europe/Riga", "(GMT+02:00) Riga");
        timeZoneList.put("Europe/Sofia", "(GMT+02:00) Sofia");
        timeZoneList.put("Europe/Tallinn", "(GMT+02:00) Tallinn");
        timeZoneList.put("Europe/Vilnius", "(GMT+02:00) Vilnius");
        timeZoneList.put("Europe/Athens", "(GMT+02:00) Athens");
        timeZoneList.put("Europe/Bucharest", "(GMT+02:00) Bucharest");
        timeZoneList.put("Europe/Istanbul", "(GMT+02:00) Istanbul");
        timeZoneList.put("Asia/Jerusalem", "(GMT+02:00) Jerusalem");
        timeZoneList.put("Asia/Amman", "(GMT+02:00) Amman");
        timeZoneList.put("Asia/Beirut", "(GMT+02:00) Beirut");
        timeZoneList.put("Africa/Windhoek", "(GMT+02:00) Windhoek");
        timeZoneList.put("Africa/Harare", "(GMT+02:00) Harare");
        timeZoneList.put("Asia/Kuwait", "(GMT+03:00) Kuwait");
        timeZoneList.put("Asia/Riyadh", "(GMT+03:00) Riyadh");
        timeZoneList.put("Asia/Baghdad", "(GMT+03:00) Baghdad");
        timeZoneList.put("Africa/Nairobi", "(GMT+03:00) Nairobi");
        timeZoneList.put("Asia/Tbilisi", "(GMT+03:00) Tbilisi");
        timeZoneList.put("Europe/Moscow", "(GMT+03:00) Moscow");
        timeZoneList.put("Europe/Volgograd", "(GMT+03:00) Volgograd");
        timeZoneList.put("Asia/Tehran", "(GMT+03:30) Tehran");
        timeZoneList.put("Asia/Muscat", "(GMT+04:00) Muscat");
        timeZoneList.put("Asia/Baku", "(GMT+04:00) Baku");
        timeZoneList.put("Asia/Yerevan", "(GMT+04:00) Yerevan");
        timeZoneList.put("Asia/Yekaterinburg", "(GMT+05:00) Ekaterinburg");
        timeZoneList.put("Asia/Karachi", "(GMT+05:00) Karachi");
        timeZoneList.put("Asia/Tashkent", "(GMT+05:00) Tashkent");
        timeZoneList.put("Asia/Kolkata", "(GMT+05:30) Calcutta");
        timeZoneList.put("Asia/Colombo", "(GMT+05:30) Sri Jayawardenepura");
        timeZoneList.put("Asia/Katmandu", "(GMT+05:45) Kathmandu");
        timeZoneList.put("Asia/Dhaka", "(GMT+06:00) Dhaka");
        timeZoneList.put("Asia/Almaty", "(GMT+06:00) Almaty");
        timeZoneList.put("Asia/Novosibirsk", "(GMT+06:00) Novosibirsk");
        timeZoneList.put("Asia/Rangoon", "(GMT+06:30) Yangon (Rangoon)");
        timeZoneList.put("Asia/Krasnoyarsk", "(GMT+07:00) Krasnoyarsk");
        timeZoneList.put("Asia/Bangkok", "(GMT+07:00) Bangkok");
        timeZoneList.put("Asia/Jakarta", "(GMT+07:00) Jakarta");
        timeZoneList.put("Asia/Brunei", "(GMT+08:00) Beijing");
        timeZoneList.put("Asia/Chongqing", "(GMT+08:00) Chongqing");
        timeZoneList.put("Asia/Hong_Kong", "(GMT+08:00) Hong Kong");
        timeZoneList.put("Asia/Urumqi", "(GMT+08:00) Urumqi");
        timeZoneList.put("Asia/Irkutsk", "(GMT+08:00) Irkutsk");
        timeZoneList.put("Asia/Ulaanbaatar", "(GMT+08:00) Ulaan Bataar");
        timeZoneList.put("Asia/Kuala_Lumpur", "(GMT+08:00) Kuala Lumpur");
        timeZoneList.put("Asia/Singapore", "(GMT+08:00) Singapore");
        timeZoneList.put("Asia/Taipei", "(GMT+08:00) Taipei");
        timeZoneList.put("Australia/Perth", "(GMT+08:00) Perth");
        timeZoneList.put("Asia/Seoul", "(GMT+09:00) Seoul");
        timeZoneList.put("Asia/Tokyo", "(GMT+09:00) Tokyo");
        timeZoneList.put("Asia/Yakutsk", "(GMT+09:00) Yakutsk");
        timeZoneList.put("Australia/Darwin", "(GMT+09:30) Darwin");
        timeZoneList.put("Australia/Adelaide", "(GMT+09:30) Adelaide");
        timeZoneList.put("Australia/Canberra", "(GMT+10:00) Canberra");
        timeZoneList.put("Australia/Melbourne", "(GMT+10:00) Melbourne");
        timeZoneList.put("Australia/Sydney", "(GMT+10:00) Sydney");
        timeZoneList.put("Australia/Brisbane", "(GMT+10:00) Brisbane");
        timeZoneList.put("Australia/Hobart", "(GMT+10:00) Hobart");
        timeZoneList.put("Asia/Vladivostok", "(GMT+10:00) Vladivostok");
        timeZoneList.put("Pacific/Guam", "(GMT+10:00) Guam");
        timeZoneList.put("Pacific/Port_Moresby", "(GMT+10:00) Port Moresby");
        timeZoneList.put("Asia/Magadan", "(GMT+11:00) Magadan");
        timeZoneList.put("Pacific/Fiji", "(GMT+12:00) Fiji");
        timeZoneList.put("Asia/Kamchatka", "(GMT+12:00) Kamchatka");
        timeZoneList.put("Pacific/Auckland", "(GMT+12:00) Auckland");
        timeZoneList.put("Pacific/Tongatapu", "(GMT+13:00) Nukualofa");

        return timeZoneList;
    }

    public static String readUTF8File2String(String fileName) {
        StringBuffer stringBuffer = new StringBuffer();
        try {
            String str;
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "UTF8"));
            while ((str = in.readLine()) != null) {
                stringBuffer.append(str);
            }
            in.close();
        } catch (Exception e) {
            log.error("Fail to read file" + fileName, e);
        }
        return stringBuffer.toString();
    }

    public static Document loadXMLFromString(String xml) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputSource is = new InputSource(new StringReader(xml));
        return builder.parse(is);
    }

    public static boolean checkXmlTagValue(String xml, String tagName, String value) {
        try {
            Document doc = loadXMLFromString(xml);
            NodeList list = doc.getElementsByTagName(tagName);
            if (list != null && list.getLength() > 0) {
                NodeList subList = list.item(0).getChildNodes();
                if (subList != null && subList.getLength() > 0) {
                    if (value.equalsIgnoreCase(subList.item(0).getNodeValue())) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            log.error("Failt to checkXmlTagValue", e);
            return false;
        }
        return false;
    }

    public static void writeToFile(String xml, String fileName) {
        try {
            File fileDir = new File(fileName);
            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileDir), "UTF8"));
            out.append(xml);
            out.flush();
            out.close();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    public static void writeToFileByDate(String xmlContent, String fileName) {
        try {
            String basePath = AppConstants.APP_SETTINGS.getAppTmpPath();
            if (SystemUtils.IS_OS_LINUX) {
                basePath = GMS_BASE_TMP_LINUX;
            } else if (SystemUtils.IS_OS_WINDOWS) {
                basePath = GMS_BASE_TMP_WINDOWS;
            }
            String folderPath = basePath
                    + "/" + Calendar.getInstance().get(Calendar.YEAR)
                    + "/" + (Calendar.getInstance().get(Calendar.MONTH) + 1)
                    + "/" + Calendar.getInstance().get(Calendar.DATE);
            Files.createDirectories(Paths.get(folderPath));
            String filePath = folderPath + "/" + fileName;
            File fileDir = new File(filePath);
            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileDir), "UTF8"));
            out.append(xmlContent);
            out.flush();
            out.close();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    public static String byteArray2String(byte[] byteArray) {
        String result = DatatypeConverter.printBase64Binary(byteArray);
        return result;
    }

    public static byte[] string2ByteArray(String data) throws IOException {
        data = data.replaceAll("\r\n", "");
        byte[] byteArray = DatatypeConverter.parseBase64Binary(data);
        return byteArray;
    }

    public static String readXml4Example(String dirPath, String fileName) throws Exception {
        File f = new File(dirPath + "/bypass.txt");
        if (f.exists() && !f.isDirectory()) {
            return null;
        }
        String content = new String(Files.readAllBytes(Paths.get(dirPath + "/" + fileName)));
        return content;
    }

    public static boolean checkTagName(String xml, String tagName) {
        String firstTagElement = "<" + tagName;
        String secondTagElement = ":" + tagName;
        if (xml.indexOf(firstTagElement) == -1 || xml.indexOf(secondTagElement) == -1) {
            return false;
        }
        return true;
    }

    public static String createMessageReference() {
        return String.valueOf(System.nanoTime()) + String.valueOf(System.nanoTime()) + String.valueOf(System.nanoTime());
    }

    public static double roundDouble(double value, int places) {
        if (places < 0)
            throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    public static String array2String(List<String> arrays) {
        if (arrays == null || arrays.size() == 0) {
            return "";
        }
        String result = "";
        for (String s : arrays) {
            result += s + ",";
        }
        result = result.substring(0, result.length() - 1);
        return result;
    }

    public static void cropPdf(String inputFile, String ouputFile, float cropLeftPercentOfWidth, float percentOfWidth) throws DocumentException, IOException {
        PdfReader reader = new PdfReader(inputFile);
        try {
            PdfArray media;
            PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(ouputFile));
            try {
                for (int i = 1; i <= reader.getNumberOfPages(); i++) {
                    PdfDictionary pdfDictionary = reader.getPageN(i);
                    PdfArray cropArray = new PdfArray();
                    media = pdfDictionary.getAsArray(PdfName.CROPBOX);
                    if (media == null) {
                        media = pdfDictionary.getAsArray(PdfName.MEDIABOX);
                    }
                    float llx = media.getAsNumber(0).floatValue();
                    float lly = media.getAsNumber(1).floatValue();
                    float w = media.getAsNumber(2).floatValue();
                    float h = media.getAsNumber(3).floatValue();
                    float llx1 = llx + w * cropLeftPercentOfWidth;
                    float w1 = llx + w * percentOfWidth;
                    float h1 = lly + h;
                    cropArray.add(new PdfNumber(llx1));
                    cropArray.add(new PdfNumber(lly));
                    cropArray.add(new PdfNumber(w1));
                    cropArray.add(new PdfNumber(h1));
                    pdfDictionary.put(PdfName.CROPBOX, cropArray);
                    pdfDictionary.put(PdfName.MEDIABOX, cropArray);
                    pdfDictionary.put(PdfName.TRIMBOX, cropArray);
                    pdfDictionary.put(PdfName.BLEEDBOX, cropArray);
                }
            } finally {
                stamper.close();
            }
        } finally {
            reader.close();
        }
    }

    public static String image2String(String imgPath) {
        String img = "";
        try {
            File imgFile = new File(imgPath);
            byte[] imgBytes = AppUtils.readContentIntoByteArray(imgFile);
            byte[] imgBytesAsBase64 = Base64.encodeBase64(imgBytes);
            String imgDataAsBase64 = new String(imgBytesAsBase64);
            img = "data:image/png;base64," + imgDataAsBase64;
        } catch (Exception e) {
        }
        return img;
    }

    public static String formatNumber(final String text, final int point) {
        String numberFormat = "##0";
        if (point != 0) {
            numberFormat += ".";
        }
        for (int i = 0; i < point; i++) {
            numberFormat += "0";
        }
        DecimalFormat df = new DecimalFormat(numberFormat);
        double number = Double.valueOf(text);
        return df.format(number);
    }

    public static String wordWrap(String s, int width, String br, boolean cut) {
        String result = "", temp = "";
        int len = 0;
        for (int i = 0; i < s.length(); i++) {
            temp += s.charAt(i);
            len++;
            if (len == width && i < s.length() - 1) {
                if (s.charAt(i) == ' ' || s.charAt(i + 1) == ' ') {
                    result += temp + br;
                } else if (cut) {
                    result += temp + br;
                } else {
                    result += temp;
                }
                len = 0;
                temp = "";
            }
        }
        if (cut && len < width && temp.length() > 0) {
            result += temp + br;
        }
        return result;
    }

    public static String wordWrap(String s, int width, String br) {
        return wordWrap(s, width, br, false);
    }
    
    public static void saveFile(File file, String fileName, String filesDirectory) throws IOException{
		FileInputStream in = null;
        FileOutputStream out = null;
        
        File dir = new File (filesDirectory);
        if ( !dir.exists() )
           dir.mkdirs();
        
        String targetPath =  dir.getPath() + File.separator + fileName;
        System.out.println("source file path ::"+file.getAbsolutePath());
        System.out.println("saving file to ::" + targetPath);
        File destinationFile = new File ( targetPath);
        try {
            in = new FileInputStream( file );
            out = new FileOutputStream( destinationFile );
            int c;

            while ((c = in.read()) != -1) {
                out.write(c);
            }

        }finally {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        }
        
	}

    
    
}