package com.FireService.PrathamFireService.Model;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfTable;
import com.lowagie.text.pdf.PdfWriter;
import com.sun.xml.internal.org.jvnet.mimepull.MIMEMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import sun.font.FontFamily;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;


public class InvoicePdfExporter {

    private Invoice invoice;

    private JavaMailSender javaMailSender;

    public InvoicePdfExporter(Invoice invoice) {
        this.invoice = invoice;
    }

    private void add_table_header(PdfPTable pdfPTable){
        PdfPCell cell=new PdfPCell();
        Font font=FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setColor(Color.BLACK);
        font.setSize(11);

        cell.setBackgroundColor(Color.WHITE);
        cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        cell.setPaddingLeft(5);
        cell.setPaddingTop(5);
        cell.setPaddingBottom(5);
        //cell.setPadding(10);
        //cell.setColspan(1);

        cell.setPhrase(new Phrase("SNo.",font));
        pdfPTable.addCell(cell);

//        cell.setPhrase(new Phrase("Bank",font));
//        pdfPTable.addCell(cell);

//        cell.setPhrase(new Phrase("Client",font));
//        pdfPTable.addCell(cell);

        cell.setPhrase(new Phrase("Item",font));
        pdfPTable.addCell(cell);

        cell.setPhrase(new Phrase("HSN/SAC",font));
        pdfPTable.addCell(cell);

        cell.setPhrase(new Phrase("Capacity",font));
        pdfPTable.addCell(cell);

        cell.setPhrase(new Phrase("Rate",font));
        pdfPTable.addCell(cell);

        cell.setPhrase(new Phrase("Nos",font));
        pdfPTable.addCell(cell);

        cell.setPhrase(new Phrase("Amount (Rs.)",font));
        pdfPTable.addCell(cell);

//        cell.setPhrase(new Phrase("Payment Satus",font));
//        cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
//        pdfPTable.addCell(cell);
//
//        cell.setPhrase(new Phrase("Date",font));
//        pdfPTable.addCell(cell);


    }

    private void add_table_invoice_data(Invoice invoice,PdfPTable pdfPTable){
        Font font=FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.BLACK);
        font.setSize(11);
        PdfPCell cell=new PdfPCell();
        cell.setPaddingLeft(5);
        cell.setPaddingTop(8);
        cell.setPaddingBottom(8);
        cell.setPhrase(new Phrase(String.valueOf("1"),font));
        pdfPTable.addCell(cell);
//        cell.setPhrase(new Phrase(String.valueOf(invoice.getBank()),font));
//        pdfPTable.addCell(cell);
//        cell.setPhrase(new Phrase(String.valueOf(invoice.getClient()),font));
//        pdfPTable.addCell(cell);

        cell.setPhrase(new Phrase(String.valueOf(invoice.getItem()),font));
        pdfPTable.addCell(cell);

        cell.setPhrase(new Phrase(String.valueOf(invoice.getCode()),font));
        pdfPTable.addCell(cell);

        cell.setPhrase(new Phrase(String.valueOf(invoice.getCapacity()),font));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        cell.setPaddingRight(7f);
        pdfPTable.addCell(cell);
        cell.setPhrase(new Phrase(String.valueOf(invoice.getRate()),font));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        cell.setPaddingRight(7f);
        pdfPTable.addCell(cell);

        cell.setPhrase(new Phrase(String.valueOf(invoice.getNos()),font));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        cell.setPaddingRight(3f);
        pdfPTable.addCell(cell);
        cell.setPhrase(new Phrase(String.valueOf(invoice.getAmount()),font));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        cell.setPaddingRight(7f);
        pdfPTable.addCell(cell);

        //Total Amount
        //PdfPCell cell_amount=new PdfPCell();
        cell.setPhrase(new Phrase(String.valueOf("Total Amount"),font));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        cell.setPaddingLeft(7f);
        cell.setColspan(6);
        pdfPTable.addCell(cell);

        cell.setPhrase(new Phrase(String.valueOf(invoice.getAmount()),font));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        cell.setPaddingRight(7f);
        cell.setColspan(1);
        pdfPTable.addCell(cell);

        //SGST
        cell.setPhrase(new Phrase(String.valueOf("SGST (9%)"),font));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        cell.setPaddingLeft(7f);
        cell.setColspan(6);
        pdfPTable.addCell(cell);

        int k = (int)(invoice.getAmount() *(9.0f/100.0f));


        cell.setPhrase(new Phrase(String.valueOf(k),font));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        cell.setPaddingRight(7f);
        cell.setColspan(1);
        pdfPTable.addCell(cell);



        //CGST
        cell.setPhrase(new Phrase(String.valueOf("CGST (9%)"),font));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        cell.setPaddingLeft(7f);
        cell.setColspan(6);
        pdfPTable.addCell(cell);

        cell.setPhrase(new Phrase(String.valueOf(k),font));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        cell.setPaddingRight(7f);
        cell.setColspan(1);
        pdfPTable.addCell(cell);



        //Net Amount
        cell.setPhrase(new Phrase(String.valueOf("Net Amount"),font));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        cell.setPaddingLeft(7f);
        cell.setColspan(6);
        pdfPTable.addCell(cell);


        cell.setPhrase(new Phrase(String.valueOf(k+invoice.getAmount()),font));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        cell.setPaddingRight(7f);
        cell.setColspan(1);
        pdfPTable.addCell(cell);


//        cell.setPhrase(new Phrase(String.valueOf(invoice.getPayment()),font));
//        pdfPTable.addCell(cell);

//        cell.setPhrase(new Phrase(String.valueOf(invoice.getDate()),font));
//        pdfPTable.addCell(cell);
//        cell.setPhrase(new Phrase(String.valueOf(invoice.getId()),font));
//        pdfPTable.addCell(cell);


        /*pdfPTable.addCell(new Phrase(String.valueOf(invoice.getBank()),font));
        pdfPTable.addCell(new Phrase(String.valueOf(invoice.getClient()),font));
        pdfPTable.addCell(new Phrase(String.valueOf(invoice.getItem()),font));
        pdfPTable.addCell(new Phrase(String.valueOf(invoice.getCode()),font));
        pdfPTable.addCell(new Phrase(String.valueOf(invoice.getCapacity()),font));
        pdfPTable.addCell(new Phrase(String.valueOf(invoice.getRate()),font));
        pdfPTable.addCell(new Phrase(String.valueOf(invoice.getNos()),font));
        pdfPTable.addCell(new Phrase(String.valueOf(invoice.getAmount()),font));
        pdfPTable.addCell(new Phrase(String.valueOf(invoice.getPayment()),font));
        pdfPTable.addCell(new Phrase(String.valueOf(invoice.getDate()),font));*/

        /*pdfPTable.addCell(String.valueOf(invoice.getBank()));
        pdfPTable.addCell(String.valueOf(invoice.getClient()));
        pdfPTable.addCell(String.valueOf(invoice.getItem()));
        pdfPTable.addCell(String.valueOf(invoice.getCode()));
        pdfPTable.addCell(String.valueOf(invoice.getCapacity()));
        pdfPTable.addCell(String.valueOf(invoice.getRate()));
        pdfPTable.addCell(String.valueOf(invoice.getNos()));
        pdfPTable.addCell(String.valueOf(invoice.getAmount()));
        pdfPTable.addCell(String.valueOf(invoice.getPayment()));
        pdfPTable.addCell(String.valueOf(invoice.getDate()));*/

    }



    public void export(HttpServletResponse response) throws IOException, MessagingException {

        Document document=new Document(PageSize.A4);

        PdfWriter.getInstance(document,response.getOutputStream());

        document.open();

        Font font_first= FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font_first.setSize(10);
        font_first.setColor(Color.BLACK);

        Paragraph paragraph_first=new Paragraph(String.valueOf("Invoice Date : "+invoice.getDate()),font_first);
//        paragraph.setFont(font);
        paragraph_first.setAlignment(Paragraph.ALIGN_RIGHT);
        paragraph_first.setIndentationRight(2f);
        paragraph_first.setSpacingAfter(0);

        document.add(paragraph_first);

        Font font= FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(27);
        font.setColor(Color.RED);

        Paragraph paragraph=new Paragraph("Pratham Fire Service",font);
//        paragraph.setFont(font);
        paragraph.setAlignment(Paragraph.ALIGN_LEFT);
        paragraph.setIndentationLeft(-1f);
        paragraph.setSpacingAfter(20);

        Font font1= FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font1.setSize(17);
        font1.setColor(Color.BLACK);

        Paragraph paragraph1=new Paragraph("Tax Invoice",font1);
//        paragraph.setFont(font);
        paragraph1.setAlignment(Paragraph.ALIGN_LEFT);
        paragraph1.setIndentationLeft(-1f);
        paragraph1.setSpacingAfter(2);
        document.add(paragraph);
        document.add(paragraph1);



        PdfPTable pdfPTable=new PdfPTable(7);

        pdfPTable.setWidthPercentage(100);
        //pdfPTable.setWidths(new float[]{1f,1.5f,3f,1.5f,1.5f,1.7f,1.2f,1f,3f,3f,1.5f});
        pdfPTable.setSpacingBefore(20);
        add_table_header(pdfPTable);
        add_table_invoice_data(this.invoice,pdfPTable);
        document.add(pdfPTable);

        Font font2= FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font2.setSize(11);
        font2.setColor(Color.BLACK);

        Paragraph paragraph3=new Paragraph("Declaration:",font2);
//        paragraph.setFont(font);
        paragraph3.setAlignment(Paragraph.ALIGN_LEFT);
        paragraph3.setSpacingBefore(20);
        document.add(paragraph3);

        Font font3= FontFactory.getFont(FontFactory.HELVETICA);
        font3.setSize(11);
        font3.setColor(Color.BLACK);

        Paragraph paragraph4=new Paragraph("We declare that this invoice shows the actual price of the goods described and that all particulars are true and " +
                "correct.\n",font3);
//        paragraph.setFont(font);
        paragraph4.setAlignment(Paragraph.ALIGN_LEFT);
        paragraph4.setSpacingBefore(15);

        document.add(paragraph4);

        Font font4= FontFactory.getFont(FontFactory.HELVETICA);
        font4.setSize(10);
        font4.setColor(Color.BLACK);


        Paragraph paragraph5=new Paragraph("FROM PRATHAM FIRE SERVICE".toUpperCase(),font4);
//        paragraph.setFont(font);
        paragraph5.setAlignment(Paragraph.ALIGN_RIGHT);
        paragraph5.setSpacingBefore(15);
        document.add(paragraph5);

        Font font5= FontFactory.getFont(FontFactory.HELVETICA);
        font5.setSize(10);
        font5.setColor(Color.BLACK);

        Paragraph paragraph6=new Paragraph("PROPRIETOR".toUpperCase(),font5);
//        paragraph.setFont(font);
        paragraph6.setAlignment(Paragraph.ALIGN_RIGHT);
        paragraph6.setSpacingBefore(28);
        document.add(paragraph6);
        document.close();




    }
}
