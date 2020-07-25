package com.FireService.PrathamFireService.Model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "invoice")
@Getter
@Setter
public class Invoice {

    @Id
    @Column(name = "Invid")
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "Bank")
    @NotEmpty(message = "is required")
    private String bank;

    @Column(name = "Client")
    @NotEmpty(message = "is required")
    private String client;

    @Column(name = "Item")
    @NotEmpty(message = "is required")
    private String item;

    @Column(name = "Code")
    @NotEmpty(message = "is required")
    private String code;

    @Column(name = "Capacity")
    @NotNull(message = "is required")
    private Integer capacity;

    @Column(name = "Rate")
    @NotNull(message = "is required")
    private Double rate;

    @Column(name = "NOS")
    @NotNull(message = "is required")
    private Integer nos;

    @Column(name = "Amount")
    @NotNull(message = "is required")
    private Double amount;

    @Column(name = "Paymentstatus")
    @NotEmpty(message = "is required")
    private String payment;

    @Column(name = "invoicedate")
    @NotEmpty(message = "is required")
    private String date;

    public Integer getId() {
        return id;
    }

    public String getBank() {
        return bank;
    }

    public String getClient() {
        return client;
    }

    public String getItem() {
        return item;
    }

    public String getCode() {
        return code;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public Double getRate() {
        return rate;
    }

    public Integer getNos() {
        return nos;
    }

    public Double getAmount() {
        return amount;
    }

    public String getPayment() {
        return payment;
    }

    public String getDate() {
        return date;
    }
}
