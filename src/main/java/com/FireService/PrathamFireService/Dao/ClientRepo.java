package com.FireService.PrathamFireService.Dao;

import com.FireService.PrathamFireService.Model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ClientRepo extends JpaRepository<Client,Integer>, PagingAndSortingRepository<Client,Integer> {
}
