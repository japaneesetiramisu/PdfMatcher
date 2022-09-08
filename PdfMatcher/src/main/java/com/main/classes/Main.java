package com.main.classes;

import com.itextpdf.text.pdf.PdfWriter;

import java.io.IOException;

public class Main {
    public  static void main(String[] args) throws IOException {
        PdfGeneratorClass pdfGeneratorClass = new PdfGeneratorClass();
        pdfGeneratorClass.generatePdf();
        //ReadFromExistingPdf pdf = new ReadFromExistingPdf();
        //pdf.pdfGen();
    }
}
