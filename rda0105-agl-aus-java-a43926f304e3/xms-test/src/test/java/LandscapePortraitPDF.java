import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.File;
import java.io.FileOutputStream;
import java.util.LinkedList;
import java.util.ListIterator;

public class LandscapePortraitPDF {
    public static void genPDF(String pdfFileName, LinkedList<String> htmlFiles) throws Exception {
        int currentPage = 1;
        FileOutputStream fos = new FileOutputStream(new File(pdfFileName));
        try {
            ITextRenderer render = new ITextRenderer();
            ListIterator<String> iterator = htmlFiles.listIterator();
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

    public static void main(String[] args) throws Exception {
        String pdfFileName = "D:/Working/Download/test.pdf";
        LinkedList<String> list = new LinkedList<String>();
        list.addLast("1");
        list.addLast("2");
        list.addLast("3");
        ListIterator<String> iterator = list.listIterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        list = new LinkedList<String>();
        list.addLast("D:/Working/Download/customer_summary1.html");
        list.addLast("D:/Working/Download/customer_summary.html");
        list.addLast("D:/Working/Download/customer_summary.html");
        LandscapePortraitPDF.genPDF(pdfFileName, list);
    }
}
