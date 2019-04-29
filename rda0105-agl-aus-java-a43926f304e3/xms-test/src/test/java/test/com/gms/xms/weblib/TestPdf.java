package test.com.gms.xms.weblib;

import com.gms.xms.common.utils.AppUtils;

/**
 * Posted from TestPdf
 * <p>
 * Author HungNT Date Jul 31, 2015
 */
public class TestPdf {
    public static void main(String[] args) throws Exception {
        String htmlFilePath = "D:/tnt_dom_airbill.html";
        String pdfFilePath = "D:/tnt_dom_airbill.pdf";
        AppUtils.createPDFFromHTMLWithFont(htmlFilePath, pdfFilePath, "arial", true);
    }
}
