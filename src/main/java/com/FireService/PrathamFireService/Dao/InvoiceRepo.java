package com.FireService.PrathamFireService.Dao;

import com.FireService.PrathamFireService.Model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepo extends JpaRepository<Invoice,Integer> {

}
