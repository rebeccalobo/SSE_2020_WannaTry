package com.SSE2020.WannaTry.model;

import com.SSE2020.WannaTry.service.IDGenerator;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Date;
import java.util.Collection;

@Entity
public class User implements Serializable {
    //region Instance Variables
    @Id
    @NotNull
    private int id;
    @NotNull
    private String FName;
    @NotNull
    private String LName;
    @NotNull
    private String email;
    @NotNull
    private String Address;
    @NotNull
    private String Password;
    @NotNull
    private String PhoneNumber;
    @NotNull
    private char Gender;
    @NotNull
    private Date DOB;
    @NotNull
    private String Ethnicity;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    private Collection<Role> roles;

    //endregion

    public User(){

    }
    public User(User user){
        this.id = getID();
        this.FName = getFName();
        this.LName = getLName();
        this.email = getEmail();
        this.Address = getAddress();
        this.Password = getPassword();
        this.PhoneNumber = getPhoneNumber();
        this.Gender = getGender();
        this.DOB = getDOB();
        this.Ethnicity = getEthnicity();
        this.roles = getRoles();

    }
    //region ID Getter
    public int getID() {
        return id;
    }
    //endregion
    public void setID(int id){
        this.id = id;
    }

    //region Name getters and setters
    public String getFName() {
        return FName;
    }

    public void setFName(String FName) {
        this.FName = FName;
    }

    public String getLName() {
        return LName;
    }

    public void setLName(String LName) {
        this.LName = LName;
    }
    //endregion

    //region Email Getter and Setter
    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail(){
        return email;
    }
    //endregion

    //region Address Getter and Setter
    public String getAddress() {
        return Address;
    }
    public void setAddress(String Address) {
        this.Address = Address;
    }
    //endregion

    //region Password Getter and Setter
    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }
    //endregion

    //region PhoneNumber Getter and Setter
    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.PhoneNumber = phoneNumber;
    }
    //endregion

    //region Gender Getter and Setter
    public char getGender() {
        return Gender;
    }

    public void setGender(char Gender) {
        this.Gender = Gender;
    }
    //endregion

    //region DOB Getter and Setter
    public Date getDOB() {
        return DOB;
    }

    public void setDOB(Date DOB) {
        this.DOB = DOB;
    }
    //endregion

    //region Ethnicity Getter and Setter
    public String getEthnicity() {
        return Ethnicity;
    }

    public void setEthnicity(String Ethnicity) {
        this.Ethnicity = Ethnicity;
    }
    //endregion

    //region Roles Getter and Setter
    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    //endregion


}
