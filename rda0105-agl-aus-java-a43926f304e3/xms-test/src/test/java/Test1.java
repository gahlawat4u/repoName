import com.gms.xms.common.utils.AppUtils;

public class Test1 {
    public static void main(String[] args) throws Exception {
        String htmlPath = "/Users/hungnt/Desktop/tnt_connote_pdf.html";
        String outputPath = "/Users/hungnt/Desktop/tnt_connote_pdf.pdf";
        AppUtils.createPDFFromHTMLWithFont(htmlPath, outputPath, "arial", true);
    }
}
