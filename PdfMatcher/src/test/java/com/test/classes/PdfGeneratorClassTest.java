package com.test.classes;


import com.spire.pdf.PdfDocument;
import com.spire.pdf.PdfPageBase;
import com.spire.pdf.exporting.PdfImageInfo;
import org.apache.commons.lang3.Range;
import org.apache.pdfbox.contentstream.PDFStreamEngine;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.sikuli.script.*;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;


public class PdfGeneratorClassTest extends PDFStreamEngine {
    private static String fileName = "PDFs/OUTPUTFILE/NewPdf.pdf";
    private static String snapOfLogoFromPdf = "Images\\logoSnap.png";
    PDDocument document = PDDocument.load(new File(fileName));;
    PDFTextStripper textStripper;

    public PdfGeneratorClassTest() throws IOException {
    }


    @Before
    public void setUp() throws Exception {}

    @Test
    public void verifyPdfTitle() throws IOException {
        textStripper = new PDFTextStripper();
        String pdfTitle = textStripper.getText(document);

        Assert.assertTrue(pdfTitle.contains("Demo Pdf"));
    }

    @Test
    public void verifyNumberOfPages() throws IOException {
       int numOfPages = document.getNumberOfPages();

       Assert.assertEquals(2, numOfPages);
    }

    @Test
    public void verifySpecificTextFromAnyPage() throws IOException {
        textStripper = new PDFTextStripper();
        textStripper.setStartPage(2);
        textStripper.setEndPage(2);

        String specText = textStripper.getText(document);

        Assert.assertTrue(specText.contains("Subcategory 2"));
    }

    @Test
    public void verifyIfTableIsPresent() throws IOException {
        textStripper = new PDFTextStripper();

        String tableHeader = textStripper.getText(document);
        Assert.assertTrue(tableHeader.contains("Table Header 1"));
    }

    @Test
    public void verifyLogoIsPresentInPdf() throws InterruptedException, IOException {
        boolean isValid = false;

        //opening Pdf
        openPdf();
        Thread.sleep(5000);

        try {
            Screen screen = new Screen();
            Pattern image = new Pattern(snapOfLogoFromPdf);
            try {
                screen.wait(image, 100);
            }catch (FindFailed e1){
                e1.printStackTrace();
            }
            if (screen.exists(image) != null){
                isValid = true;
                Assert.assertTrue("Logo verified", isValid);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        document.close();
    }


    @Test
    public void verifyPositionOfLogoInMainPage() throws InterruptedException {
        String logoPosition = "";

        Rectangle2D rectangle;
        PdfDocument doc = new PdfDocument();
        doc.loadFromFile(fileName);
        PdfPageBase page = doc.getPages().get(0);
        PdfImageInfo[] imageInfo = page.getImagesInfo();

        rectangle = imageInfo[1].getBounds();
        //System.out.println(rectangle.getX() + " " + rectangle.getY());
        System.out.print("Logo Position is: ");

        Range<Double> xCoVal = Range.between(200.00, 408.00);
        Range<Double> yCoVal = Range.between(528.00, 792.00);

        if (xCoVal.contains(rectangle.getX()) || yCoVal.contains(rectangle.getY())){
            logoPosition = "Top-Middle";
            System.out.println(logoPosition);
        }
        Assert.assertEquals(logoPosition, "Top-Middle");
    }

    @After
    public void tearDown() throws Exception {
        document.close();
    }

    private void getImageCoordinates(){
        PdfDocument doc = new PdfDocument();
        doc.loadFromFile(fileName);
        PdfPageBase page = doc.getPages().get(0);
        PdfImageInfo[] imageInfo = page.getImagesInfo();

        for (int i=0; i<imageInfo.length; i++){
            Rectangle2D rect = imageInfo[i].getBounds();
            System.out.println(String.format("The coordinate of image %d:（%f, %f）", i+1, rect.getX(), rect.getY()));
        }
    }

    private void openPdf() {
        try {

            File pdfFile = new File(fileName);
            if (pdfFile.exists()) {

                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().open(pdfFile);
                } else {
                    System.out.println("Desktop is not supported.");
                }

            } else {
                System.out.println("File does not exist.");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}