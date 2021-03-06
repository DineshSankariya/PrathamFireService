package com.FireService.PrathamFireService.Controller;

import com.FireService.PrathamFireService.Dao.ClientRepo;
import com.FireService.PrathamFireService.Dao.InvoicePageRepo;
import com.FireService.PrathamFireService.Dao.InvoiceRepo;
import com.FireService.PrathamFireService.Dao.LoggingRepo;
import com.FireService.PrathamFireService.Model.Invoice;
import com.FireService.PrathamFireService.Model.InvoicePdfExporter;
import com.FireService.PrathamFireService.Model.LoggingDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Controller
@RequestMapping("/fireservice")
public class InvoiceController {

    @Autowired
    private InvoiceRepo invoiceRepo;

    @Autowired
    private InvoicePageRepo invoicePageRepo;

    @Autowired
    private ClientRepo clientRepo;

    @Autowired
    private LoggingRepo loggingRepo;

    @GetMapping("/home")
    public String homepage(Model model, Principal principal) throws ParseException {
//        model.addAttribute("invoice",invoiceRepo.findAll());
        System.out.println(principal.getName()+"  "+principal.hashCode());
        LoggingDetails loggingDetails=new LoggingDetails();
        loggingDetails.setUsername(principal.getName());
        loggingDetails.setDate(new Date());
        loggingRepo.save(loggingDetails);
        model.addAttribute("user",principal.getName().toUpperCase());
        return "home";
    }

    @GetMapping("/invoice")
    public String invoice(Model model, @PageableDefault(size = 8) Pageable pageable,Principal principal){
        model.addAttribute("invoice",invoiceRepo.findAll(pageable));
        model.addAttribute("client",clientRepo.findAll());
        System.out.println(clientRepo.findAll().size());
        if(invoiceRepo.findAll().size()>0){
            model.addAttribute("selectedid",clientRepo.findAll());
        }else{
            model.addAttribute("selectedid",null);
        }
        model.addAttribute("user",principal.getName().toUpperCase());
//        model.addAttribute("selectedid",24);
        return "invoice";
    }

    @GetMapping("/invoiceform")
    @ResponseBody
    public Invoice getmodel( Invoice invoice){
       invoice=new Invoice();
       return invoice;
    }

    @PostMapping("/editinvoice")
    @ResponseBody
    public Invoice getmodel(@RequestBody int id){
        Optional<Invoice> invoice=invoiceRepo.findById(id);
        return invoice.get();
    }


    @RequestMapping("/saveinvoice")
    @ResponseBody
    public String save_invoice(@RequestBody Invoice invoice){
        System.out.println(invoice.toString());
        invoiceRepo.save(invoice);
        return "ok";
    }



    @PostMapping("/delete_invoice")
    public void getmodel(HttpServletRequest request){
        int id=Integer.valueOf(request.getParameter("delete_id"));
        System.out.println("id value ==> "+id);
        invoiceRepo.deleteById(id);

    }



    @GetMapping("/pdf")
    public void pdf(@RequestParam("id")int id, HttpServletResponse response) throws IOException, MessagingException, ParseException {
        response.setContentType("application/pdf");
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        String date=simpleDateFormat.format(new Date());


        //String newDate = new SimpleDateFormat("h:mm a").format(date);
        SimpleDateFormat simpleDateFormat1=new SimpleDateFormat("hh.mm.ss:a");

//        Date newDate=new Date();

        String date1=simpleDateFormat1.format(new Date());
        String headerkey="Content-Disposition";
        String headervalue="inline;filename="+date+"\\"+date1+" Invoice_id-"+String.valueOf(id)+".pdf";
        response.setHeader(headerkey,headervalue);
        InvoicePdfExporter invoicePdfExporter=new InvoicePdfExporter(invoiceRepo.findById(id).get());
        invoicePdfExporter.export_new(response);
    }

//    @GetMapping("/pdf_download")
//    public void pdf_download(@RequestParam("id")int id, HttpServletResponse response) throws IOException {
//        response.setContentType("application/pdf");
//        String headerkey="Content-Disposition";
//        String headervalue="attachment;filename=invoice_id-"+id+".pdf";
//        response.setHeader(headerkey,headervalue);
//        InvoicePdfExporter invoicePdfExporter=new InvoicePdfExporter(invoiceRepo.findById(id).get());
//        invoicePdfExporter.export(response);
//    }
}
