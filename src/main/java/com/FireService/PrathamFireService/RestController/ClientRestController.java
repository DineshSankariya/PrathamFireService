package com.FireService.PrathamFireService.RestController;

import com.FireService.PrathamFireService.Dao.ClientRepo;
import com.FireService.PrathamFireService.Dao.InvoiceRepo;
import com.FireService.PrathamFireService.Model.Client;
import com.FireService.PrathamFireService.Model.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientrest")
public class ClientRestController {


    @Autowired
    private ClientRepo clientRepo;

    @Autowired
    private InvoiceRepo invoiceRepo;



    @RequestMapping("find/{id}")
    public Client find(@PathVariable("id")int id){
        return clientRepo.findById(id).get();
    }

    @RequestMapping("find")
    @ResponseBody
    public ResponseEntity<List<Client>> find_all(){
        return new ResponseEntity<>(clientRepo.findAll(),HttpStatus.OK);
    }

    @RequestMapping("saveclient")
    @ResponseBody
    public String save(@RequestBody Client id){
        System.out.println(id.toString());
        clientRepo.save(id);
        return "{\"success\":\"ok\"}";
    }

    @RequestMapping(value = "updateclient",method = RequestMethod.PUT)
    @ResponseBody
    public String update(@RequestBody Client id){
        System.out.println(id.toString());
        clientRepo.save(id);
        return "{\"success\":\"ok\"}";
    }

    @RequestMapping(value = "delete_client/{id}")
    public String delete(@PathVariable("id")int id){
        clientRepo.deleteById(id);
        //return new ResponseEntity<Invoice>(invoiceRepo.deleteById(id);, HttpStatus.OK);
        return "ok";
    }

    @RequestMapping(value = "find_client/{id}")
    @ResponseBody
    public ResponseEntity<Client> client(@PathVariable("id")int id){

        Client client=null;
        Invoice invoice=invoiceRepo.findById(id).get();
//        System.out.println(invoice.toString());
        Client client1=invoice.getClient();

        return new ResponseEntity<Client>(client1,HttpStatus.OK);

    }

}
