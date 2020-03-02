package com.SSE2020.WannaTry.model;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "students")
public class Students {
    @Id
    private String student_id;
    @NotBlank
    private String student_firstname;
    @NotBlank
    private String student_surname;
    @NotBlank
    private String email;
    @NotBlank
    private String address;
    @NotBlank
    private String password;
    @NotBlank
    private String phone_number;
    public Students(){
        super();
    }
    public Students(String id, String student_firstname, String student_surname, String email,String password,String address,String phone_number) {
        super();
        this.student_id = id;
        this.student_firstname = student_firstname;
        this.student_surname = student_surname;
        this.email=email;
        this.password = password;
        this.address=address;
        this.phone_number=phone_number;

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