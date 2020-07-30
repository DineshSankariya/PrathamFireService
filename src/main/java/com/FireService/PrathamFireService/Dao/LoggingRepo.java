package com.FireService.PrathamFireService.Dao;

import com.FireService.PrathamFireService.Model.LoggingDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoggingRepo extends JpaRepository<LoggingDetails,String> {


}
