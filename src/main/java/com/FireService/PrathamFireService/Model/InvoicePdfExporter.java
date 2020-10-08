package com.FireService.PrathamFireService.Model;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.*;
import com.sun.xml.internal.org.jvnet.mimepull.MIMEMessage;
import org.apache.catalina.Context;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.jsf.FacesContextUtils;
import sun.font.FontFamily;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sound.sampled.Line;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


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
        font.setSize(9.5f);

        Font font_data=FontFactory.getFont(FontFactory.HELVETICA);
        font_data.setColor(Color.BLACK);
        font_data.setSize(9.5f);


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
        newPdfPCell.setPaddingLeft(5f);
        pdfPTable.addCell(newPdfPCell);



        Paragraph paragraph2=new Paragraph();
        paragraph2.add(new Phrase("seller detail:\n".toUpperCase(),font));
        paragraph2.setSpacingAfter(8f);
        paragraph2.setSpacingBefore(4f);



        Phrase phrase3=new Phrase();
        phrase3.add(paragraph2);
        phrase3.add(new Phrase(Chunk.NEWLINE+"Name:\t".toUpperCase(),font));
        phrase3.add(new Phrase("pratham fire service".toUpperCase(),font_data));
        phrase3.add(new Phrase("\nemail:\t".toUpperCase(),font));
        phrase3.add(new Phrase("prathamfireservice@gmail.com",font_data));
        phrase3.add(new Phrase("\ngst no:\t".toUpperCase(),font));
        phrase3.add(new Phrase("24ABZPT8058P1ZA",font_data));
        phrase3.add(new Phrase("\npan no:\t".toUpperCase(),font));
        phrase3.add(new Phrase("ABZPT8058P",font_data));


        PdfPCell newPdfPCell3=new PdfPCell();
        newPdfPCell3.addElement(phrase3);
        newPdfPCell3.setPaddingBottom(8f);
        newPdfPCell3.setPaddingLeft(5.2f);
        newPdfPCell3.setPaddingTop(5f);
        pdfPTable.addCell(newPdfPCell3);


        Paragraph paragraph3=new Paragraph();
        paragraph3.add(new Phrase("seller banking detail:\n".toUpperCase(),font));
        paragraph3.setSpacingAfter(8f);
        paragraph3.setSpacingBefore(4f);



        Phrase phrase2=new Phrase();
        phrase2.add(paragraph3);
        phrase2.add(new Phrase(Chunk.NEWLINE+"Bank Name:\t".toUpperCase(),font));
        phrase2.add(new Phrase("The Gandevi People's Co-op Bank Ltd",font_data));
        phrase2.add(new Phrase("\nA/C No:\t",font));
        phrase2.add(new Phrase("802171503000973",font_data));
        phrase2.add(new Phrase("\nIFSC Code:\t".toUpperCase(),font));
        phrase2.add(new Phrase("IBKL0068GP1",font_data));

        PdfPCell newPdfPCell1=new PdfPCell();
        newPdfPCell1.addElement(phrase2);
        newPdfPCell1.setPaddingTop(5f);
        newPdfPCell1.setPaddingBottom(8f);
        newPdfPCell1.setPaddingLeft(5f);
        pdfPTable.addCell(newPdfPCell1);









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

    public void add_header(PdfPTable pdfPTable) throws IOException {



        Image image=Image.getInstance("src/main/resources/static/Images/logopfs.jpg");
        image.scaleAbsolute(90,50);


//        document.add(image);

        Font font_header=FontFactory.getFont(FontFactory.TIMES_ITALIC);
        font_header.setSize(9);



        Paragraph paragraph=new Paragraph("Mo. : 9724100675, 9537431801",font_header);
        paragraph.setIndentationLeft(20f);
        paragraph.setAlignment(Element.ALIGN_LEFT);

//        paragraph.setFont(font_header);
//        document.add(paragraph);

       // PdfPTable pdfPTable=new PdfPTable(3);

        PdfPCell pdfPCell=new PdfPCell();
        pdfPCell.setBorder(0);

        Paragraph paragraph1=new Paragraph();
        paragraph1.add(new Chunk(image,2,0));



        pdfPCell.addElement(paragraph1);

        pdfPTable.addCell(pdfPCell);
        PdfPCell pdfPCell1=new PdfPCell();
        pdfPCell1.addElement(paragraph);

        Paragraph paragraph2=new Paragraph("972514610",font_header);
        paragraph2.setIndentationLeft(50f);
        paragraph2.setAlignment(Element.ALIGN_MIDDLE);
        pdfPCell1.addElement(paragraph2);
        pdfPCell1.setPaddingTop(20);
        pdfPCell1.setBorder(0);

        pdfPTable.addCell(pdfPCell1);


//        Image image_name=Image.getInstance("src/main/resources/static/Images/LogoName.jpg");
//        image_name.scaleAbsolute(100,50);
//
        PdfPCell pdfPCell2=new PdfPCell();
//        Paragraph paragraph3=new Paragraph();
//        paragraph3.add(new Chunk(image_name,0,0));
//        pdfPCell2.addElement(paragraph3);
//        pdfPTable.addCell(pdfPCell2);

        Font font=FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setColor(Color.RED);
        font.setSize(20);

        Phrase phrase=new Phrase("PRATHAM FIRE SERVICE".toUpperCase(),font);
        Paragraph  paragraph3=new Paragraph(phrase);
        pdfPCell2.addElement(paragraph3);
        pdfPCell2.setPaddingTop(-15f);
        pdfPCell2.setPaddingLeft(17f);

//        pdfPTable.addCell(pdfPCell2);

//        Phrase phrase=new Phrase("PRATHAM FIRE SERVICE".toUpperCase(),font);
        Font font1=FontFactory.getFont(FontFactory.HELVETICA);
        font1.setColor(Color.BLUE);
        font1.setSize(10);
        Paragraph  paragraph4=new Paragraph(new Phrase("Govt. Approved. Fire Extinguishers",font1));
        Paragraph  paragraph5=new Paragraph(new Phrase("Shop No. 537,Surya Complex,",font1));
        Paragraph  paragraph6=new Paragraph(new Phrase("Opp. Mamlatdar Kacheri Rahej, Gandevi-396360",font1));
        Paragraph  paragraph7=new Paragraph(new Phrase("email : prathamfireservice@ymail.com",font1));
        pdfPCell2.addElement(paragraph4);
        pdfPCell2.addElement(paragraph5);
        pdfPCell2.addElement(paragraph6);
        pdfPCell2.addElement(paragraph7);
        pdfPCell2.setBorder(0);

        pdfPTable.addCell(pdfPCell2);







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

    public void export_new(HttpServletResponse response) throws IOException, MessagingException, ParseException {

        Document document=new Document(PageSize.A4);

        PdfWriter pdfWriter= PdfWriter.getInstance(document,response.getOutputStream());

        document.open();
//        HttpServlet servlet = null;
//        String path =servlet.getServletContext().getRealPath("/resources/fonts/foobar.ttf");
//        Image image=Image.getInstance(path);

        PdfPTable pdfPTable=new PdfPTable(3);
        pdfPTable.setWidthPercentage(100f);
        pdfPTable.setWidths(new float[]{0.8f,1.4f,2.5f});
        pdfPTable.setHorizontalAlignment(PdfPTable.ALIGN_LEFT);

        add_header(pdfPTable);



        //rule1
//        PdfContentByte cb = pdfWriter.getDirectContent();
//        cb.setLineWidth(1.5f);  // Make a bit thicker than 1.0 default
//        cb.setGrayStroke(0.95f); // 1 = black, 0 = white
//        cb.setColorStroke(Color.blue);
//        cb.moveTo(55, 30);
//        cb.lineTo(200, 30);
//        cb.stroke();


        //rule2



        //Footer

        Font font_footer=new Font();
        font_footer.setColor(Color.RED);

        Font font_header_title=FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font_header_title.setSize(12);

        Font page_footer=FontFactory.getFont(FontFactory.HELVETICA);
        page_footer.setSize(9f);
        page_footer.setStyle(Font.ITALIC);


        //Before content
        ColumnText.showTextAligned(pdfWriter.getDirectContent(),Element.ALIGN_LEFT,new Phrase("------------------------------",font_footer),50,80,0);
        ColumnText.showTextAligned(pdfWriter.getDirectContent(),Element.ALIGN_LEFT,new Phrase("------------------------------",font_footer),50,82.5f,0);

        //content
        ColumnText.showTextAligned(pdfWriter.getDirectContent(),Element.ALIGN_LEFT,new Phrase("Fire Extinguishers & Industrail Safety Products",font_header_title),170,81,0);

        //After content
        ColumnText.showTextAligned(pdfWriter.getDirectContent(),Element.ALIGN_LEFT,new Phrase("----------------------------",font_footer),439,80,0);
        ColumnText.showTextAligned(pdfWriter.getDirectContent(),Element.ALIGN_LEFT,new Phrase("----------------------------",font_footer),439,82.5f,0);

        ColumnText.showTextAligned(pdfWriter.getDirectContent(),Element.ALIGN_LEFT,new Phrase(String.valueOf(pdfWriter.getPageNumber()),page_footer),50,60f,0);
        ColumnText.showTextAligned(pdfWriter.getDirectContent(),Element.ALIGN_LEFT,new Phrase("pratham fire service".toUpperCase(),page_footer),445f,60f,0);

        /*Image image_gas=Image.getInstance("src/main/resources/static/Images/gas.jpg");
        image_gas.scaleAbsolute(30,30);
        image_gas.setAbsolutePosition(50,50);


        PdfContentByte byte1 = pdfWriter.getDirectContent();
        PdfTemplate tp1 = byte1.createTemplate(600, 150);
        tp1.addImage(image_gas);

        byte1.addTemplate(tp1, 0, -5);

        Phrase phrase1 = new Phrase(byte1 + "", FontFactory.getFont(FontFactory.TIMES_ROMAN, 7, Font.NORMAL));

        HeaderFooter header = new HeaderFooter(phrase1, true);*/



        document.add(pdfPTable);
        Font font_first= FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font_first.setSize(10);
        font_first.setColor(Color.BLACK);

//        Paragraph paragraph_first=new Paragraph(String.valueOf("Invoice Date : "+invoice.getDate()),font_first);
////        paragraph.setFont(font);
//        paragraph_first.setAlignment(Paragraph.ALIGN_RIGHT);
//        paragraph_first.setIndentationRight(2f);
//        paragraph_first.setSpacingAfter(1);
//        paragraph_first.setSpacingBefore(30f);
//
//        document.add(paragraph_first);


        Font head_title=FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        head_title.setSize(14f);

        Paragraph  head_para=new Paragraph("Tax Invoice",head_title);
        head_para.setAlignment(Paragraph.ALIGN_CENTER);
        head_para.setSpacingBefore(10f);
        head_para.setSpacingAfter(10f);

        document.add(head_para);

        PdfPTable pdfPTable_client=new PdfPTable(2);
        pdfPTable_client.setWidthPercentage(100);
        pdfPTable_client.setSpacingBefore(20);


        add_table_header_client(pdfPTable_client);
        document.add(pdfPTable_client);


        PdfPTable pdfPTable_invoice=new PdfPTable(7);

        pdfPTable_invoice.setWidthPercentage(100);
        // pdfPTable.setWidths(new float[]{0.7f,2.5f,1.3f,1f,1.3f,1.7f,1.2f});
        pdfPTable_invoice.setSpacingBefore(20);
        add_table_header(pdfPTable_invoice);
        add_table_invoice_data(this.invoice,pdfPTable_invoice);

        document.add(pdfPTable_invoice);





        Font font2= FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font2.setSize(11);
        font2.setColor(Color.BLACK);

        Paragraph paragraph8=new Paragraph("Declaration:",font2);
//        paragraph.setFont(font);
        paragraph8.setAlignment(Paragraph.ALIGN_LEFT);
        paragraph8.setSpacingBefore(20);
        document.add(paragraph8);

        Font font3= FontFactory.getFont(FontFactory.HELVETICA);
        font3.setSize(11);
        font3.setColor(Color.BLACK);

        Paragraph paragraph9=new Paragraph("We declare that this invoice shows the actual price of the goods described and that all particulars are true and " +
                "correct.\n",font3);
//        paragraph.setFont(font);
        paragraph9.setAlignment(Paragraph.ALIGN_LEFT);
        paragraph9.setSpacingBefore(15);

        document.add(paragraph9);

        Font font4= FontFactory.getFont(FontFactory.HELVETICA);
        font4.setSize(10);
        font4.setColor(Color.BLACK);


        Paragraph paragraph10=new Paragraph("FROM PRATHAM FIRE SERVICE".toUpperCase(),font4);
//        paragraph.setFont(font);
        paragraph10.setAlignment(Paragraph.ALIGN_RIGHT);
        paragraph10.setSpacingBefore(15);
        document.add(paragraph10);

        Font font5= FontFactory.getFont(FontFactory.HELVETICA);
        font5.setSize(10);
        font5.setColor(Color.BLACK);

        Paragraph paragraph11=new Paragraph("PROPRIETOR".toUpperCase(),font5);
//        paragraph.setFont(font);
        paragraph11.setAlignment(Paragraph.ALIGN_RIGHT);
        paragraph11.setSpacingBefore(28);
        document.add(paragraph11);


        document.newPage();

        PdfPTable pdfPTable1=new PdfPTable(3);
        pdfPTable1.setWidthPercentage(100f);
        pdfPTable1.setWidths(new float[]{0.8f,1.4f,2.5f});
        pdfPTable1.setHorizontalAlignment(PdfPTable.ALIGN_LEFT);


        add_header(pdfPTable1);

        document.add(pdfPTable1);

        Font font=FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(14);

        Paragraph paragraph=new Paragraph("certifcate".toUpperCase(),font);
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);
        paragraph.setSpacingBefore(30f);
        paragraph.setSpacingAfter(15f);

        Font font1=FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font1.setSize(10f);

        Paragraph paragraph1=new Paragraph("Date: "+this.invoice.getDate(),font1);
        paragraph1.setAlignment(Paragraph.ALIGN_RIGHT);
        paragraph1.setSpacingBefore(7f);
        paragraph1.setSpacingAfter(10f);
//
        Font font6=FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font6.setSize(11f);

        Paragraph paragraph2=new Paragraph(new Phrase("To,",font6));
//        paragraph2.setFont(font6);
        paragraph2.setSpacingBefore(8f);

        Font font7=FontFactory.getFont(FontFactory.HELVETICA);
        font7.setSize(10f);

        Paragraph paragraph3=new Paragraph(new Phrase(this.client.getC_name()+".",font7));

        Paragraph paragraph4=new Paragraph(new Phrase(this.client.getC_alias_name(),font7));
        paragraph4.setSpacingAfter(20f);

        Date date=new SimpleDateFormat("yyyy-MM-dd").parse(this.invoice.getDate());

        Format format3=new SimpleDateFormat("MMMM");
        String month1=format3.format(date);

        Format format4=new SimpleDateFormat("yyyy");
        String year1=format4.format(date);

        Paragraph paragraph5=new Paragraph("\t\t\t\t\tthis is certify that we have refiled below mentioned fire extinguishers and keep them in good condition on ".toUpperCase() +String.valueOf(date.getDate())+" "+String.valueOf(month1)+" "+String.valueOf(year1)+".",font7);
        paragraph5.setSpacingBefore(30f);


        document.add(paragraph);
        document.add(paragraph1);
        document.add(paragraph2);
        document.add(paragraph3);
        document.add(paragraph4);
        document.add(paragraph5);

        PdfPTable pdfPTable2=new PdfPTable(4);
        pdfPTable2.setWidthPercentage(100f);
        pdfPTable2.setSpacingBefore(30f);


        Font table_header=FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        table_header.setSize(10f);
        PdfPCell pdfPCell=new PdfPCell();
        pdfPCell.addElement(new Phrase("SNo.",table_header));

        pdfPCell.setPaddingLeft(4f);
        pdfPCell.setPaddingBottom(7f);

        pdfPTable2.addCell(pdfPCell);


        PdfPCell pdfPCell1=new PdfPCell();
        pdfPCell1.addElement(new Phrase("item".toUpperCase(),table_header));
        pdfPCell1.setPaddingLeft(4f);
        pdfPCell1.setPaddingBottom(7f);
        pdfPTable2.addCell(pdfPCell1);

        PdfPCell pdfPCell2=new PdfPCell();
        pdfPCell2.addElement(new Phrase("capacity".toUpperCase(),table_header));
        pdfPCell2.setPaddingLeft(4f);
        pdfPCell2.setPaddingBottom(7f);
        pdfPTable2.addCell(pdfPCell2);

        PdfPCell pdfPCell3=new PdfPCell();
        pdfPCell3.addElement(new Phrase("nos".toUpperCase(),table_header));
        pdfPCell3.setPaddingLeft(4f);
        pdfPCell3.setPaddingBottom(7f);
        pdfPTable2.addCell(pdfPCell3);




        Client client=this.invoice.getClient();
        int i=0;


        for (Invoice invoice:client.getInvoices()
             ) {


            PdfPCell pdfPCell4 = new PdfPCell();
            pdfPCell4.addElement(new Phrase(String.valueOf(i+1),font7));
            pdfPCell4.setPaddingLeft(4f);
            pdfPCell4.setPaddingBottom(7f);
            pdfPTable2.addCell(pdfPCell4);

            PdfPCell pdfPCell5 = new PdfPCell();
            pdfPCell5.addElement(new Phrase(invoice.getItem(),font7));
            pdfPCell5.setPaddingLeft(4f);
            pdfPCell5.setPaddingBottom(7f);
            pdfPTable2.addCell(pdfPCell5);

            PdfPCell pdfPCell6 = new PdfPCell();
            pdfPCell6.addElement(new Phrase(String.valueOf(invoice.getCapacity()),font7));
            pdfPCell6.setPaddingLeft(4f);
            pdfPCell6.setPaddingBottom(7f);
            pdfPTable2.addCell(pdfPCell6);

            PdfPCell pdfPCell7 = new PdfPCell();
            pdfPCell7.addElement(new Phrase(String.valueOf(invoice.getNos()),font7));
            pdfPCell7.setPaddingLeft(4f);
            pdfPCell7.setPaddingBottom(7f);
            pdfPTable2.addCell(pdfPCell7);
            i++;

        }
        document.add(pdfPTable2);

        Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(this.invoice.getDate());

        Format format=new SimpleDateFormat("MMMM");
        String month=format.format(date1);

        Format format1=new SimpleDateFormat("yyyy");
        String year=format1.format(date1);
        System.out.println( "Year ==> "+String.valueOf(date1.getYear())+year);
        Paragraph paragraph6=new Paragraph("this expire will due on ".toUpperCase()+""+String.valueOf(date1.getDate()-1)+" "+String.valueOf(month)+" "+(Integer.valueOf(year)+1)+".",font7);
        paragraph6.setSpacingBefore(20f);
        document.add(paragraph6);

        Paragraph paragraph7=new Paragraph("this certificate is valid upto one year from the date of reffilling ".toUpperCase(),font7);
        paragraph7.setSpacingBefore(15f);
        document.add(paragraph7);

        Paragraph paragraph12=new Paragraph(("this certificate & also as t&c. norms each fire extinguishers require periodically maintenance. please refill your" +
                "extinguishers before date of expiry. ").toUpperCase(),font7);
        paragraph12.setSpacingBefore(17f);
        document.add(paragraph12);

        Paragraph paragraph13=new Paragraph("from pratham fire service".toUpperCase(),font7);
        paragraph13.setSpacingBefore(20f);
        paragraph13.setAlignment(Paragraph.ALIGN_RIGHT);
        document.add(paragraph13);

        Paragraph paragraph14=new Paragraph("proprietor".toUpperCase(),font7);
        paragraph14.setSpacingBefore(20f);
        paragraph14.setAlignment(Paragraph.ALIGN_RIGHT);
        document.add(paragraph14);


        //Before content
        ColumnText.showTextAligned(pdfWriter.getDirectContent(),Element.ALIGN_LEFT,new Phrase("------------------------------",font_footer),50,80,0);
        ColumnText.showTextAligned(pdfWriter.getDirectContent(),Element.ALIGN_LEFT,new Phrase("------------------------------",font_footer),50,82.5f,0);

        //content
        ColumnText.showTextAligned(pdfWriter.getDirectContent(),Element.ALIGN_LEFT,new Phrase("Fire Extinguishers & Industrail Safety Products",font_header_title),170,81,0);

        //After content


        ColumnText.showTextAligned(pdfWriter.getDirectContent(),Element.ALIGN_LEFT,new Phrase("----------------------------",font_footer),439,80,0);
        ColumnText.showTextAligned(pdfWriter.getDirectContent(),Element.ALIGN_LEFT,new Phrase("----------------------------",font_footer),439,82.5f,0);
        ColumnText.showTextAligned(pdfWriter.getDirectContent(),Element.ALIGN_LEFT,new Phrase(String.valueOf(pdfWriter.getPageNumber()),page_footer),50,60f,0);
        ColumnText.showTextAligned(pdfWriter.getDirectContent(),Element.ALIGN_LEFT,new Phrase("pratham fire service".toUpperCase(),page_footer),445f,60f,0);

//
        document.close();


    }


}
