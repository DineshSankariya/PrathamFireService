package com.FireService.PrathamFireService.Model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "client")
public class Client {

    @Id
    @Column(name = "cid")
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "cname")
    @NotNull(message = "is required")
    @Type(type="text")
    private String c_name;

    @Column(name = "c_al_name")
    @NotNull(message = "is required")
    @Type(type="text")
    private String c_alias_name;

    @Column(name = "p_name")
    private String p_name;

    @Column(name = "p_designation")
    private String p_designation;

    @Column(name = "b_title")
    private String b_title;

    @Column(name = "c_email")
    @NotNull(message = "is required")
    private String c_email;

    @Column(name = "c_contact")
    @NotNull(message = "is required")
    private String c_contact;

    @Column(name = "GSTNumber")
    private String gst_num;

    @Column(name = "c_address")
    @Type(type="text")
    private String c_address;

    @Column(name = "postal_code")
    private String postal_code;

    @Column(name = "city")
    @NotNull(message = "is required")
    private String city;

    @Column(name = "state")
    @NotNull(message = "is required")
    private String state;

    @Column(name = "isactive")
    private Boolean isactive;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "client")
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonManagedReference
    private List<Invoice> invoices = new ArrayList<>();

    public void add_invoice(Invoice invoice){


            this.invoices.add(invoice);

            invoice.setClient(this);

            System.out.println("####################  saved successfully #####################");

    }

    public boolean remove_invoice(Invoice invoice){

        int index=check_invoice(invoice.getId());

        if(index>=0){

            this.invoices.remove(index);
            System.out.println("Deleting invoice id ===============> "+id+" <===============");
            System.out.println("####################  Deleted successfully #####################");
            return true;

        }

        return false;
    }

    public int check_invoice(int id){

        int pos=0;
        for (Invoice invoice:this.invoices) {
            if(invoice.getId()==id){
//                System.out.println(pos);
                return pos;
            }
            pos++;
        }
        return -1;

    }

    private int check_invoice(String item){
        int pos=0;
        for (Invoice invoice:this.invoices) {
            if(invoice.getItem()==item){
//                System.out.println(pos);
                return pos;
            }
            pos++;
        }
        return -1;
    }

    public boolean isSelected(Integer id){

        if( id!=null){
            return this.getId().equals(id);
        }
        return false;
    }

    public String getC_name() {
        return c_name;
    }

    public void setC_name(String c_name) {
        this.c_name = c_name;
    }

    public String getC_alias_name() {
        return c_alias_name;
    }

    public void setC_alias_name(String c_alias_name) {
        this.c_alias_name = c_alias_name;
    }

    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

    public String getP_designation() {
        return p_designation;
    }

    public void setP_designation(String p_designation) {
        this.p_designation = p_designation;
    }

    public String getB_title() {
        return b_title;
    }

    public void setB_title(String b_title) {
        this.b_title = b_title;
    }

    public String getC_email() {
        return c_email;
    }

    public void setC_email(String c_email) {
        this.c_email = c_email;
    }

    public String getC_contact() {
        return c_contact;
    }

    public void setC_contact(String c_contact) {
        this.c_contact = c_contact;
    }

    public String getGst_num() {
        return gst_num;
    }

    public void setGst_num(String gst_num) {
        this.gst_num = gst_num;
    }

    public String getC_address() {
        return c_address;
    }

    public void setC_address(String c_address) {
        this.c_address = c_address;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Boolean getIsactive() {
        return isactive;
    }

    public void setIsactive(Boolean isactive) {
        this.isactive = isactive;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", c_name='" + c_name + '\'' +
                ", c_alias_name='" + c_alias_name + '\'' +
                ", p_name='" + p_name + '\'' +
                ", p_designation='" + p_designation + '\'' +
                ", b_title='" + b_title + '\'' +
                ", c_email='" + c_email + '\'' +
                ", c_contact='" + c_contact + '\'' +
                ", gst_num='" + gst_num + '\'' +
                ", c_address='" + c_address + '\'' +
                ", postal_code='" + postal_code + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", isactive=" + isactive +
                '}';
    }
}
