package com.SSE2020.WannaTry.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;


@Entity
@Table(name = "staff")
public class Staff {
    @Id
    private String staff_id;
    @NotBlank
    private String staff_firstname;
    @NotBlank
    private String staff_surname;
    @NotBlank
    private String password;


    public Staff() {
        super();
    }

    public Staff(String id, String staff_firstname, String staff_surname,
                 String email, String password, String address, String phone_number) {
        super();
        this.staff_id = id;
        this.staff_firstname = staff_firstname;
        this.staff_surname = staff_surname;
        this.password = password;

    }

    public String getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(String id) {
        this.staff_id = id;
    }

    public String getStaff_firstname() {
        return staff_firstname;
    }

    public void setStaff_firstname(String staff_firstname) {
        this.staff_firstname = staff_firstname;
    }

    public String getStaff_surname() {
        return staff_surname;
    }

    public void setStaff_surname(String staff_surname) {
        this.staff_surname = staff_surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + staff_id +
                ", FN='" + staff_firstname + '\'' +
                ", SN='" + staff_surname + '\'' +
                '}';
    }


}
