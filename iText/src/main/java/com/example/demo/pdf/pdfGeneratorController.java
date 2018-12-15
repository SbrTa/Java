package com.example.demo.pdf;

import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.Document;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.itextpdf.text.*;

import java.io.FileOutputStream;
import java.io.IOException;


@Controller
public class pdfGeneratorController {


    @GetMapping("/start")
    public void pdfGenerator()throws IOException, DocumentException{
        System.out.println("ready ... 1...... 2....... 3...... go.......");
        String dest = "C:\\Users\\Subrata Roy\\Documents\\iTextPdf/okk.pdf";
        com.itextpdf.text.Document pdf = new com.itextpdf.text.Document();
        PdfWriter writer = PdfWriter.getInstance(pdf, new FileOutputStream(dest));
        //PdfContentByte canvas = writer.getDirectContent();
        //canvas.rectangle(new Rectangle(5,5,100,100));
        pdf.open();
        pdf.add(new Paragraph("hllow polapains...."));
        pdf.close();

        System.out.println("PDF generated...............");
    }
}
