package com.FireService.PrathamFireService.Dao;

import com.FireService.PrathamFireService.Model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InvoiceRepo extends JpaRepository<Invoice,Integer> {
    @Override
    Optional<Invoice> findById(Integer integer);
}
