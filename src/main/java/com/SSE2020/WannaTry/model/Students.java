package com.SSE2020.WannaTry.model;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Entity
@Table(name = "students")
public class Students {
    @Id
    private String student_id;
    @NotNull
    private String student_firstname;
    @NotNull
    private String student_surname;
    @NotNull
    private String email;
    @NotNull
    private String address;
    @NotNull
    private String password;
    @NotNull
    private String phone_number;
    @NotNull
    private Double amount_paid;
    @NotNull
    private String gender;
    @NotNull
    private Date dob;
    @NotNull
    private String stage;
    public Students(){
        super();
    }

    public Students(String id, String student_firstname, String student_surname, String email, String password, String address, String phone_number, Double amount_paid, String gender, Date dob, String stage) {
        super();
        this.student_id = id;
        this.email=email;
        this.address=address;
        this.phone_number=phone_number;
        this.student_firstname = student_firstname;
        this.student_surname = student_surname;
        this.password = password;
        this.amount_paid = amount_paid;
        this.gender = gender;
        this.dob = dob;
        this.stage = stage;
    }
    public String getStudent_id() {
        return student_id;
    }
    public String getStudent_firstname() {
        return student_firstname;
    }
    public String getStudent_surname() {
        return student_surname;
    }
    public String getEmail() {
        return email;
    }
    public String getAddress(){return address;}
    public String getPhone_number(){return phone_number;}
    public String getPassword(){return password;}
    public Double getAmount_paid() { return amount_paid; }
    public String getGender() { return gender; }
    public Date getDob() { return dob; }
    public String getStage() { return stage; }

    public void setStudent_id(String id) { this.student_id = id;}
    public void setStudent_firstname(String student_firstname) {
        this.student_firstname = student_firstname;
    }
    public void setStudent_surname(String student_surname) {
        this.student_surname = student_surname;
    }
    public void setEmail(String isbn) {
        this.email = isbn;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }
    public void setAmount_paid(Double amount_paid) { this.amount_paid = amount_paid; }
    public void setGender(String gender) { this.gender = gender; }
    public void setDob(Date dob) { this.dob = dob; }
    public void setStage(String stage) { this.stage = stage; }


    public double remainingPay(double d) {
        return d - getAmount_paid();
    }


    @Override
    public String toString() {
        return "Blog{" +
                "id=" + student_id +
                ", FN='" + student_firstname + '\'' +
                ", SN='" + student_surname + '\'' +
                ", EMAIL='" + email + '\'' +
                '}';
    }
}