package com.SSE2020.WannaTry.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
public class IP_Blacklist {
    @Id
    @NotNull
    @Column(unique = true)
    private String IP_ADDRESS;

    public IP_Blacklist() {
    }

    public IP_Blacklist(String IP_ADDRESS) {
        this.IP_ADDRESS = IP_ADDRESS;
    }

    public String getIP_ADDRESS() {
        return IP_ADDRESS;
    }

    public void setIP_ADDRESS(String IP_ADDRESS) {
        this.IP_ADDRESS = IP_ADDRESS;
    }
}
