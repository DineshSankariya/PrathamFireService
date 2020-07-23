package com.FireService.PrathamFireService.Dao;

import com.FireService.PrathamFireService.Model.Invoice;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface InvoicePageRepo extends PagingAndSortingRepository<Invoice,Integer> {
}
