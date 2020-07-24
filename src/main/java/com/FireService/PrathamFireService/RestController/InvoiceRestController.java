package com.FireService.PrathamFireService.RestController;

import com.FireService.PrathamFireService.Dao.InvoiceRepo;
import com.FireService.PrathamFireService.Model.Invoice;
import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/invoicerest")
public class InvoiceRestController {

    @Autowired
    private InvoiceRepo invoiceRepo;

    @RequestMapping("find/{id}")
    public ResponseEntity<Invoice> find(@PathVariable("id")int id){
        return new ResponseEntity<Invoice>(invoiceRepo.findById(id).get(), HttpStatus.OK);
    }

    @RequestMapping("delete_invoice/{id}")
    public String delete(@PathVariable("id")int id){
        invoiceRepo.deleteById(id);
        //return new ResponseEntity<Invoice>(invoiceRepo.deleteById(id);, HttpStatus.OK);
        return "ok";
    }

    @RequestMapping("saveinvoice")
    @ResponseBody
    public String save(@RequestBody Invoice id){
        invoiceRepo.save(id);
        //return new ResponseEntity<Invoice>(invoiceRepo.deleteById(id);, HttpStatus.OK);
        return "{\"success\":\"ok\"}";


    }


}
