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

    private Client client;

    private JavaMailSender javaMailSender;

    public InvoicePdfExporter(Invoice invoice) {
        this.invoice = invoice;
        this.client=invoice.getClient();
    }

    private void add_table_header(PdfPTable pdfPTable){
        PdfPCell cell=new PdfPCell();
        Font font=FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setColor(Color.BLACK);
        font.setSize(10);

        cell.setBackgroundColor(Color.WHITE);
        cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        cell.setPaddingLeft(5);
        cell.setPaddingTop(6);
        cell.setPaddingBottom(6);
        //cell.setPadding(10);
        //cell.setColspan(1);

        cell.setPhrase(new Phrase("SNo.",font));
        pdfPTable.addCell(cell);

//        cell.setPhrase(new Phrase("Bank",font));
//        pdfPTable.addCell(cell);

//        cell.setPhrase(new Phrase("Client",font));
//        pdfPTable.addCell(cell);

        cell.setPhrase(new Phrase("Item".toUpperCase(),font));
        pdfPTable.addCell(cell);

        cell.setPhrase(new Phrase("HSN/SAC".toUpperCase(),font));
        pdfPTable.addCell(cell);

        cell.setPhrase(new Phrase("Capacity".toUpperCase(),font));
        pdfPTable.addCell(cell);

        cell.setPhrase(new Phrase("Rate".toUpperCase(),font));
        pdfPTable.addCell(cell);

        cell.setPhrase(new Phrase("Nos".toUpperCase(),font));
        pdfPTable.addCell(cell);

        cell.setPhrase(new Phrase("Amount".toUpperCase()+"(Rs.)",font));
        pdfPTable.addCell(cell);

//        cell.setPhrase(new Phrase("Payment Satus",font));
//        cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
//        pdfPTable.addCell(cell);
//
//        cell.setPhrase(new Phrase("Date",font));
//        pdfPTable.addCell(cell);


    }

    private void add_table_header_client(PdfPTable pdfPTable){

        PdfPCell cell=new PdfPCell();
        Font font=FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setColor(Color.BLACK);
        font.setSize(10);

        Font font_data=FontFactory.getFont(FontFactory.HELVETICA);
        font_data.setColor(Color.BLACK);
        font_data.setSize(10);


        cell.setBackgroundColor(Color.WHITE);
        cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        cell.setPaddingLeft(5);
        cell.setPaddingTop(0);
        cell.setPaddingBottom(6);


        Paragraph paragraph=new Paragraph();
        paragraph.add(new Phrase("To,\n",font));
        paragraph.add(new Phrase(this.client.getC_name()+"\n",font_data));
        paragraph.add(new Phrase(this.client.getC_alias_name()+Chunk.NEWLINE,font_data));
        paragraph.add(new Phrase(Chunk.NEWLINE+"GST No:\t",font));
        paragraph.add(new Phrase(this.client.getGst_num(),font_data));


        Phrase phrase=new Phrase(paragraph);

        cell.addElement(phrase);
        pdfPTable.addCell(cell);

        Paragraph paragraph1=new Paragraph();
        paragraph1.add(new Phrase("Invoice: ",font));
        paragraph1.add(new Phrase(String.valueOf(this.invoice.getId()),font_data));


        Phrase phrase1=new Phrase(paragraph1);
        phrase1.add(new Phrase(Chunk.NEWLINE+"\nInvoice Date:\t",font));
        phrase1.add(new Phrase(this.invoice.getDate(),font_data));
        PdfPCell newPdfPCell=new PdfPCell();
        newPdfPCell.addElement(phrase1);
        pdfPTable.addCell(newPdfPCell);

       /* Paragraph paragraph2=new Paragraph();
        paragraph2.add(new Phrase("Seller Detail: ",font));
        paragraph2.add(new Phrase("NAME:\t",font));
        paragraph2.add(new Phrase("PRATHAM FIRE SERVICE",font_data));
        paragraph2.add(new Phrase(Chunk.NEWLINE+"EMAIL:\t",font));
        paragraph2.add(new Phrase("prathamfireservice@gmail.com",font_data));
        paragraph2.add(new Phrase(Chunk.NEWLINE+"GST NO:\t",font));
        paragraph2.add(new Phrase("24ABZPT8058P1ZA",font_data));

        Phrase phrase2=new Phrase(paragraph2);*/





    }

    private void add_table_client_seller_detail(PdfPTable pdfPTable){

        Font font=FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setColor(Color.BLACK);
        font.setSize(10);

        Font font_data=FontFactory.getFont(FontFactory.HELVETICA);
        font_data.setColor(Color.BLACK);
        font_data.setSize(10);

        Paragraph paragraph2=new Paragraph();
        paragraph2.add(new Phrase("Seller Detail: ",font));
        paragraph2.add(new Phrase("NAME:\t",font));
        paragraph2.add(new Phrase("PRATHAM FIRE SERVICE",font_data));
        paragraph2.add(new Phrase(Chunk.NEWLINE+"EMAIL:\t",font));
        paragraph2.add(new Phrase("prathamfireservice@gmail.com",font_data));
        paragraph2.add(new Phrase(Chunk.NEWLINE+"GST NO:\t",font));
        paragraph2.add(new Phrase("24ABZPT8058P1ZA",font_data));

        PdfPCell newPdfPCell=new PdfPCell();


        Phrase phrase2=new Phrase(paragraph2);
        newPdfPCell.addElement(phrase2);
        pdfPTable.addCell(newPdfPCell);

    }

    private void add_table_invoice_data(Invoice invoice,PdfPTable pdfPTable){

        Font font=FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.BLACK);
        font.setSize(10);
        PdfPCell cell=new PdfPCell();
        cell.setPaddingLeft(5);
        cell.setPaddingTop(6);
        cell.setPaddingBottom(6);
//        cell.setPhrase(new Phrase(String.valueOf("1"),font));
//        pdfPTable.addCell(cell);

//        cell.setPhrase(new Phrase(String.valueOf(invoice.getBank()),font));
//        pdfPTable.addCell(cell);
//        cell.setPhrase(new Phrase(String.valueOf(invoice.getClient()),font));
//        pdfPTable.addCell(cell);
        Client client=invoice.getClient();
        Double amount=Double.valueOf(0);
        int i=0;
        for (Invoice curr:client.getInvoices()) {

            cell.setPhrase(new Phrase(String.valueOf(i+1),font));
            cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
            pdfPTable.addCell(cell);

            cell.setPhrase(new Phrase(String.valueOf(curr.getItem()),font));
            cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
            pdfPTable.addCell(cell);

            cell.setPhrase(new Phrase(String.valueOf(curr.getCode()),font));
            cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
            pdfPTable.addCell(cell);

            cell.setPhrase(new Phrase(String.valueOf(curr.getCapacity()),font));
            cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            cell.setPaddingRight(7f);
            pdfPTable.addCell(cell);
            cell.setPhrase(new Phrase(String.valueOf(curr.getRate()),font));
            cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            cell.setPaddingRight(7f);
            pdfPTable.addCell(cell);

            cell.setPhrase(new Phrase(String.valueOf(curr.getNos()),font));
            cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            cell.setPaddingRight(3f);
            pdfPTable.addCell(cell);
            cell.setPhrase(new Phrase(String.valueOf(curr.getAmount()),font));
            cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            cell.setPaddingRight(7f);
            pdfPTable.addCell(cell);
            amount+=Double.valueOf(curr.getAmount());
            i++;
        }


        //Total Amount
        //PdfPCell cell_amount=new PdfPCell();
        cell.setPhrase(new Phrase(String.valueOf("Total Amount"),font));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        cell.setPaddingLeft(7f);
        cell.setColspan(6);
        pdfPTable.addCell(cell);

        cell.setPhrase(new Phrase(String.valueOf(amount),font));
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

        int k = (int)(amount *(9.0f/100.0f));


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


        cell.setPhrase(new Phrase(String.valueOf((k*2)+amount),font));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        cell.setPaddingRight(7f);
        cell.setColspan(1);
        pdfPTable.addCell(cell);



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

        PdfPTable pdfPTable_client=new PdfPTable(2);
        pdfPTable_client.setWidthPercentage(100);
        pdfPTable_client.setSpacingBefore(20);

        add_table_header_client(pdfPTable_client);
//        add_table_client_seller_detail(pdfPTable_client);

        PdfPTable pdfPTable=new PdfPTable(7);


        pdfPTable.setWidthPercentage(100);
       // pdfPTable.setWidths(new float[]{0.7f,2.5f,1.3f,1f,1.3f,1.7f,1.2f});
        pdfPTable.setSpacingBefore(20);
        add_table_header(pdfPTable);
        add_table_invoice_data(this.invoice,pdfPTable);
        document.add(pdfPTable_client);
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
