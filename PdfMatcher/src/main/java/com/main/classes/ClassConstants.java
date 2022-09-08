package com.main.classes;

import com.itextpdf.text.Font;

public class ClassConstants {
    public static final String PATH = "PDFs\\OUTPUTFILE\\NewPdf.pdf";
    public static final String LOGOPATH = "Images\\random_logo.png";
    public static final String WATERMARKSRC = "Images\\bgconfidential.png";


    public static final Font TITLEFONT = new Font(Font.FontFamily.HELVETICA, 24, Font.BOLD);
    public static final Font NORMALFONT = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL);
    public static final Font SUBFONT = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
    public static final Font SMALLBOLD = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD);

    public static final String TITLE = "Demo Pdf";
    public static final String AUTHOR = "Author: ";
    public static final String INTRO = "Introduction: ";
    public static final String PARABODY = "This document gives a brief introduction about different Pdf elements.";
    public static final String REFNo = "Reference No.: 4172";
    public static final String DOCKETNo = "Docket No.: 12";
    public static final String SUBTEXT = "Portable Document Format (PDF), standardized as ISO 32000, is a file format developed by Adobe in 1992 to present documents, including text formatting and images, " +
            "in a manner independent of application software, hardware, and operating systems. " +
            "Based on the PostScript language, each PDF file encapsulates a complete description " +
            "of a fixed-layout flat document, including the text, fonts, vector graphics, raster images " +
            "and other information needed to display it. PDF has its roots in \"The Camelot Project\" " +
            "initiated by Adobe co-founder John Warnock in 1991.\n\n";
    public static final String PARA1 = "PDF files may contain a variety of content besides flat text and graphics including" +
            " logical structuring elements, interactive elements such as annotations and form-fields, layers, " +
            "rich media (including video content), three-dimensional objects using U3D or PRC, and various other data formats. \n\n";
    public static final String PARA2 = "The PDF specification also provides for encryption and digital signatures, " +
            "file attachments, and metadata to enable workflows requiring these features. \n\n";
    public static final String PARA3 = "Adobe Systems made the PDF specification available free of charge in 1993. " +
            "In the early years PDF was popular mainly in desktop publishing workflows, and competed with a variety of " +
            "formats such as DjVu, Envoy, Common Ground Digital Paper, Farallon Replica and even Adobe's own PostScript format \n\n";

    public static final String LIST1 = "Typeset text stored as content streams (i.e., not encoded in plain text.";
    public static final String LIST2 = "Vector graphics for illustrations and designs that consist of shapes and lines";
    public static final String LIST3 = "Raster graphics for photographs and other types of images";
}
