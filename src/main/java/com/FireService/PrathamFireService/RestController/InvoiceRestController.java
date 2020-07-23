package com.FireService.PrathamFireService.RestController;

import com.FireService.PrathamFireService.Dao.InvoiceRepo;
import com.FireService.PrathamFireService.Model.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/invoicerest")
public class InvoiceRestController {

    @Autowired
    private InvoiceRepo invoiceRepo;

    @RequestMapping("find/{id}")
    public ResponseEntity<Invoice> find(@PathVariable("id")int id){
        return new ResponseEntity<Invoice>(invoiceRepo.findById(id).get(), HttpStatus.OK);
    }


}
