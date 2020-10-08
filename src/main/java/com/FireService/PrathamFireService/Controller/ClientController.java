package com.FireService.PrathamFireService.Controller;

import com.FireService.PrathamFireService.Dao.ClientRepo;
import com.FireService.PrathamFireService.Dao.InvoiceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/clientservice")
public class ClientController {

    @Autowired
    private ClientRepo clientRepo;

    @GetMapping("/client")
    public String invoice(Model model, @PageableDefault(size = 8) Pageable pageable, Principal principal){
        model.addAttribute("client",clientRepo.findAll(pageable));
        model.addAttribute("user",principal.getName().toUpperCase());
        return "client";
    }

}
