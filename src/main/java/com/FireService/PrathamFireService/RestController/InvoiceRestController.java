package com.FireService.PrathamFireService.RestController;

import com.FireService.PrathamFireService.Dao.ClientRepo;
import com.FireService.PrathamFireService.Dao.InvoiceRepo;
import com.FireService.PrathamFireService.Model.Client;
import com.FireService.PrathamFireService.Model.Invoice;
import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/invoicerest")
public class InvoiceRestController {

    @Autowired
    private InvoiceRepo invoiceRepo;

    @Autowired
    private ClientRepo clientRepo;

    @RequestMapping("find/{id}")
    @ResponseBody
    public ResponseEntity<Invoice> find(@PathVariable("id")int id){
        System.out.println(invoiceRepo.findById(id).get().getClient());
        Invoice invoice=invoiceRepo.findById(id).get();
        return new ResponseEntity<Invoice>(invoice, HttpStatus.OK);
    }

    @RequestMapping("find")
    @ResponseBody
    public ResponseEntity<List<Invoice>>  invoice_all(){

        return new ResponseEntity<>(invoiceRepo.findAll(), HttpStatus.OK);
    }


    @RequestMapping(value = "delete_invoice/{id}")
    public String delete(@PathVariable("id")int id){
        System.out.println("Deleting invoice id ===============> "+id+"=========>");
        Invoice invoice=invoiceRepo.findById(id).get();
        Client client=invoice.getClient();
        client.remove_invoice(invoice);
        System.out.println(invoice.toString());
        invoiceRepo.deleteById(Integer.valueOf(id));
//        return new ResponseEntity<Invoice>(invoiceRepo.deleteById(id), HttpStatus.OK);
        return "ok";
    }

    @RequestMapping("saveinvoice")
    @ResponseBody
    public String save(@RequestBody Invoice invoice){
//        System.out.println(invoice.getId());
//        System.out.println(invoice.getClient());
//        int clid=Integer.valueOf(id);
////        id.setClient(id.getClient());
        System.out.println(invoice.toString());
        Client client=clientRepo.findById(invoice.getClient().getId()).get();
        client.add_invoice(invoice);
        invoice.setClient(client);

        invoiceRepo.save(invoice);
        //return new ResponseEntity<Invoice>(invoiceRepo.deleteById(id);, HttpStatus.OK);
        return "{\"success\":\"ok\"}";


    }



    @PutMapping(value = "updateinvoice")
    public String update(@RequestBody Invoice invoice){
        System.out.println(invoice.toString());
        Client client=clientRepo.findById(invoice.getClient().getId()).get();

        invoice.setClient(client);
//        client.add_invoice(invoice);
        //invoice.setClient(client);

         invoiceRepo.save(invoice);
        return "{\"success\":\"ok\"}";
    }


}
