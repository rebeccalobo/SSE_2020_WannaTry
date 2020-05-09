package com.SSE2020.WannaTry.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class BLACKLIST {
    @Id
    @NotNull
    @Column(unique = true)
    private String IP_ADDRESS;

    public BLACKLIST() {
    }

    public BLACKLIST(String IP_ADDRESS) {
        this.IP_ADDRESS = IP_ADDRESS;
    }

    public String getIP_ADDRESS() {
        return IP_ADDRESS;
    }

    public void setIP_ADDRESS(String IP_ADDRESS) {
        this.IP_ADDRESS = IP_ADDRESS;
    }
}
