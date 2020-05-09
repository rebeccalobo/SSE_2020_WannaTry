package com.SSE2020.WannaTry.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class FailedAttempts {

    @Id
    @NotNull
    @Column(unique = true)
    private String IP_ADDRESS;
    @NotNull
    private int ATTEMPTS;
    @NotNull
    private Date LAST_ATTEMPT;

    public FailedAttempts() {
    }

    public FailedAttempts(String IP_ADDRESS, int ATTEMPTS, Date LAST_ATTEMPT) {
        this.IP_ADDRESS = IP_ADDRESS;
        this.ATTEMPTS = ATTEMPTS;
        this.LAST_ATTEMPT = LAST_ATTEMPT;
    }

    public String getIP_ADDRESS() {
        return IP_ADDRESS;
    }

    public void setIP_ADDRESS(String IP_ADDRESS) {
        this.IP_ADDRESS = IP_ADDRESS;
    }

    public int getATTEMPTS() {
        return ATTEMPTS;
    }

    public void setATTEMPTS(int ATTEMPTS) {
        this.ATTEMPTS = ATTEMPTS;
    }

    public Date getLAST_ATTEMPT() {
        return LAST_ATTEMPT;
    }

    public void setLAST_ATTEMPT(Date LAST_ATTEMPT) {
        this.LAST_ATTEMPT = LAST_ATTEMPT;
    }
}
