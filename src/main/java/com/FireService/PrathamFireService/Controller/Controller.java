package com.FireService.PrathamFireService.Controller;

import com.FireService.PrathamFireService.Dao.InvoicePageRepo;
import com.FireService.PrathamFireService.Dao.InvoiceRepo;
import com.FireService.PrathamFireService.Model.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
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

}
