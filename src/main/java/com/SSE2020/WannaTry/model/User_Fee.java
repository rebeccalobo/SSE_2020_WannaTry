package com.SSE2020.WannaTry.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class User_Fee {
    @Id
    private int id;
    @NotNull
    private double fees;

    public User_Fee(int id, @NotNull double fees) {
        this.id = id;
        this.fees = fees;
    }

    public User_Fee() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getFees() {
        return fees;
    }

    public void setFees(double fees) {
        this.fees = fees;
    }
}
