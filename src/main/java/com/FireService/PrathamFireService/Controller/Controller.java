package com.FireService.PrathamFireService.Controller;

import com.FireService.PrathamFireService.Dao.InvoicePageRepo;
import com.FireService.PrathamFireService.Dao.InvoiceRepo;
import com.FireService.PrathamFireService.Model.Invoice;
import com.FireService.PrathamFireService.Model.InvoicePdfExporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@org.springframework.stereotype.Controller
@RequestMapping("/fireservice")
public class Controller {

    @Autowired
    private InvoiceRepo invoiceRepo;

    @Autowired
    private InvoicePageRepo invoicePageRepo;
    @GetMapping("/home")
    public String homepage(Model model){
//        model.addAttribute("invoice",invoiceRepo.findAll());
        return "home";
    }

    @GetMapping("/invoice")
    public String invoice(Model model, @PageableDefault(size = 8) Pageable pageable){
        model.addAttribute("invoice",invoiceRepo.findAll(pageable));
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
    public void pdf(@RequestParam("id")int id, HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
        String headerkey="Content-Disposition";
        String headervalue="inline;filename=invoice_id_"+id+".pdf";
        response.setHeader(headerkey,headervalue);
        InvoicePdfExporter invoicePdfExporter=new InvoicePdfExporter(invoiceRepo.findById(id).get());
        invoicePdfExporter.export(response);
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