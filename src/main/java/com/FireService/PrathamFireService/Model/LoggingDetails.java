package com.FireService.PrathamFireService.Model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "logdetails")
public class LoggingDetails {

    @Id
    @Column(name = "username")
    private String  username;

    @Column(name="loggingtime")
    private String timestamp;



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDate() {
        return this.timestamp;
    }

    public void setDate(Date date) throws ParseException {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.timestamp = simpleDateFormat.format(date);

    }
}
