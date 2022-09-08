package com.main.classes;


import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Date;

import static com.main.classes.ClassConstants.*;


public class PdfGeneratorClass {


    private PdfWriter writer;

    public void generatePdf() throws IOException {
        try {
            Document document = new Document(PageSize.A4);
            writer = PdfWriter.getInstance(document, new FileOutputStream(PATH));
            document.open();
            addMetaData(document);
            addContents(document);
            addCategory(document);
            document.close();

        } catch (FileNotFoundException | DocumentException | MalformedURLException e) {
            e.printStackTrace();
        }
    }

    private void addMetaData(Document document) {
        document.addTitle("Pdf Generator");
        document.addSubject("Using iText");
        document.addKeywords("Java, PDF, iText");
        document.addAuthor("Abhishek");
        document.addCreator("Abhishek");
    }

    private void addContents(Document document) throws DocumentException, IOException {
        Paragraph preface = new Paragraph();

        //Setting up Logo
        Image logo = Image.getInstance(LOGOPATH);
        logo.scaleToFit(110, 110);
        logo.setAbsolutePosition(240, 720);
        document.add(logo);

        //Setting up watermark
        Image watermark = Image.getInstance(WATERMARKSRC);
        watermark.scaleToFit(220, 220);
        watermark.setAbsolutePosition(200, 300);
        document.add(watermark);

        //Setting up Title
        addEmptyLine(preface, 3);
        Paragraph mainTitle = new Paragraph(TITLE, TITLEFONT);
        mainTitle.setAlignment(Element.ALIGN_CENTER);
        preface.add(mainTitle);

        //Setting up Date-Time
        addEmptyLine(preface, 1);
        preface.add(new Paragraph( AUTHOR + System.getProperty("user.name") + "\n" + new Date(),
                SMALLBOLD));

        //Setting up Information Bar
        addEmptyLine(preface, 1);
        addPrimaryTable(preface);

        //Setting up Intro
        addEmptyLine(preface, 1);
        preface.add(new Paragraph(INTRO, SMALLBOLD));
        preface.add(new Paragraph(PARABODY, NORMALFONT));

        //Setting up table
        addEmptyLine(preface, 1);
        createTable(preface);

        //Setting up Page Numbers
        getPageNum(document);

        //Adding everything to the Document
        document.add(preface);

        document.newPage();
    }


    private void addCategory(Document document) throws DocumentException {
        Anchor anchor = new Anchor("First Chapter", SUBFONT);
        anchor.setName("First Chapter");

        Chapter catPart = new Chapter(new Paragraph(anchor), 1);

        Paragraph subPara = new Paragraph("Subcategory 1", SMALLBOLD);
        Section subCatPart = catPart.addSection(subPara);
        subCatPart.add(new Paragraph(SUBTEXT, NORMALFONT));
        addEmptyLine(subPara, 1);

        subPara = new Paragraph("Subcategory 2", SMALLBOLD);
        subCatPart = catPart.addSection(subPara);
        subCatPart.add(new Paragraph(PARA1, NORMALFONT));
        subCatPart.add(new Paragraph(PARA2, NORMALFONT));
        subCatPart.add(new Paragraph(PARA3, NORMALFONT));

        //adding a List
        createList(subCatPart);
        Paragraph paragraph = new Paragraph();
        addEmptyLine(paragraph, 5);
        subCatPart.add(paragraph);

        //Adding everything to the Document
        document.add(catPart);
    }

    private void addPrimaryTable(Paragraph document) throws DocumentException {

        float col = 280f;
        float[] colWidth = {col, col};

        PdfPTable table = new PdfPTable(colWidth);
        table.setTotalWidth(PageSize.A4.getWidth()-70);
        table.setLockedWidth(true);

        PdfPCell c1 = new PdfPCell(new Phrase(REFNo, SMALLBOLD));
        c1.setBorder(Rectangle.NO_BORDER);
        table.addCell(c1);

        PdfPCell c2 = new PdfPCell(new Phrase(DOCKETNo, SMALLBOLD));
        c2.setBorder(Rectangle.NO_BORDER);
        c2.setHorizontalAlignment(Element.ALIGN_RIGHT);
        table.addCell(c2);

        document.add(table);
    }

    private void createList(Section subCatPart) {
        List list = new List(true, false, 10);
        list.add(new ListItem(LIST1, NORMALFONT));
        list.add(new ListItem(LIST2, NORMALFONT));
        list.add(new ListItem(LIST3, NORMALFONT));

        subCatPart.add(list);
    }

    private void createTable(Paragraph document) {
        PdfPTable table = new PdfPTable(3);

        for (int i=1; i <= table.getNumberOfColumns(); i++){
            PdfPCell c = new PdfPCell(new Phrase("Table Header " + i, SMALLBOLD));
            c.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c);
        }
        table.setHeaderRows(1);

        table.addCell("1.0");
        table.addCell("1.1");
        table.addCell("1.2");
        table.addCell("2.1");
        table.addCell("2.2");
        table.addCell("2.3");

        document.add(table);
    }

    private void getPageNum(Document document){
        Rectangle pageSize = document.getPageSize();
        ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_RIGHT,
                new Phrase(String.format("Page: %s", String.valueOf(writer.getCurrentPageNumber()))),
                pageSize.getRight(30), pageSize.getTop(30), 0);
    }

    private void addEmptyLine(Paragraph paragraph, int number) {
        for (int i=0; i < number; i++)
            paragraph.add(new Paragraph(" "));
    }
}
