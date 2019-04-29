package com.gms.xms.common.utils;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.*;

import java.io.FileOutputStream;
import java.io.IOException;

public class PdfScaleUtils {

    public class ScaleEvent extends PdfPageEventHelper {

        protected float scale = 1;
        protected PdfDictionary pageDict;

        public ScaleEvent(float scale) {
            this.scale = scale;
        }

        public void setPageDict(PdfDictionary pageDict) {
            this.pageDict = pageDict;
        }

        @Override
        public void onStartPage(PdfWriter writer, Document document) {
            writer.addPageDictEntry(PdfName.ROTATE, pageDict.getAsNumber(PdfName.ROTATE));
            writer.addPageDictEntry(PdfName.MEDIABOX, scaleDown(pageDict.getAsArray(PdfName.MEDIABOX), scale));
            writer.addPageDictEntry(PdfName.CROPBOX, scaleDown(pageDict.getAsArray(PdfName.CROPBOX), scale));
        }
    }

    public void manipulatePdf(String pdfIn, String pdfOut, Float scale) throws IOException, DocumentException {
        PdfReader reader = new PdfReader(pdfIn);
        ScaleEvent event = new ScaleEvent(scale);
        event.setPageDict(reader.getPageN(1));

        int n = reader.getNumberOfPages();
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(pdfOut));
        writer.setPageEvent(event);
        document.open();
        Image page;
        for (int p = 1; p <= n; p++) {
            page = Image.getInstance(writer.getImportedPage(reader, p));
            page.setAbsolutePosition(0, 0);
            page.scalePercent(scale * 100);
            document.add(page);
            if (p < n) {
                event.setPageDict(reader.getPageN(p + 1));
            }
            document.newPage();
        }
        document.close();
    }

    public PdfArray scaleDown(PdfArray original, float scale) {
        if (original == null)
            return null;
        float width = original.getAsNumber(2).floatValue() - original.getAsNumber(0).floatValue();
        float height = original.getAsNumber(3).floatValue() - original.getAsNumber(1).floatValue();
        return new PdfRectangle(width * scale, height * scale);
    }

}
