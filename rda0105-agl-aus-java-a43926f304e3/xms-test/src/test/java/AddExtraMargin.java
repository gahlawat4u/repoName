import com.gms.xms.common.utils.PdfScaleUtils;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class AddExtraMargin {

    public static final String SRC = "d:/2060018925.pdf";
    public static final String DEST = "d:/2060018925_1.pdf";
    public static final String scaleDes = "d:/2060018925_2.pdf";

    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new AddExtraMargin().cropPdf(SRC, DEST, 0.11f, 0.43f);
        PdfScaleUtils scaleUtils = new PdfScaleUtils();
        scaleUtils.manipulatePdf(DEST, scaleDes, 0.3f);
    }

    public String cropPdf(String src, String dest, float cropLeftPercentOfWidth, float percentOfWidth) throws DocumentException, IOException {
        PdfReader reader = new PdfReader(src);
        try {
            PdfArray media;
            PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
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
                    System.out.println("before: " + llx + ":" + lly + ":" + w + ":" + h);
                    float llx1 = llx + w * cropLeftPercentOfWidth;
                    float lly1 = lly;
                    float w1 = llx + w * percentOfWidth;
                    float h1 = lly + h;
                    System.out.println("after: " + llx1 + ":" + lly1 + ":" + w1 + ":" + h1);
                    cropArray.add(new PdfNumber(llx1));
                    cropArray.add(new PdfNumber(lly));
                    cropArray.add(new PdfNumber(w1));
                    cropArray.add(new PdfNumber(h1));
                    pdfDictionary.put(PdfName.CROPBOX, cropArray);
                    pdfDictionary.put(PdfName.MEDIABOX, cropArray);
                    pdfDictionary.put(PdfName.TRIMBOX, cropArray);
                    pdfDictionary.put(PdfName.BLEEDBOX, cropArray);
                }
                return dest;
            } finally {
                stamper.close();
            }
        } finally {
            reader.close();
        }
    }
}