package com.SSE2020.WannaTry.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name="logged_actions")
public class LoggedActions {

    @Id
    @NotNull
    private int USER_ID;
    @NotNull
    private String ACTION_TYPE;
    @NotNull
    private String ACTION_DESC;
    @NotNull
    private String IP_ADDRESS;
    @NotNull
    private Date DATE_OF_ACTION;

    public LoggedActions() {
    }

    public LoggedActions(int USER_ID, String ACTION_TYPE, String ACTION_DESC, String IP_ADDRESS, Date DATE_OF_ACTION) {
        this.USER_ID = USER_ID;
        this.ACTION_TYPE = ACTION_TYPE;
        this.ACTION_DESC = ACTION_DESC;
        this.IP_ADDRESS = IP_ADDRESS;
        this.DATE_OF_ACTION = DATE_OF_ACTION;
    }

    public int getUSER_ID() {
        return USER_ID;
    }

    public void setUSER_ID(int USER_ID) {
        this.USER_ID = USER_ID;
    }

    public String getACTION_TYPE() {
        return ACTION_TYPE;
    }

    public void setACTION_TYPE(String ACTION_TYPE) {
        this.ACTION_TYPE = ACTION_TYPE;
    }

    public String getACTION_DESC() {
        return ACTION_DESC;
    }

    public void setACTION_DESC(String ACTION_DESC) {
        this.ACTION_DESC = ACTION_DESC;
    }

    public String getIP_ADDRESS() {
        return IP_ADDRESS;
    }

    public void setIP_ADDRESS(String IP_ADDRESS) {
        this.IP_ADDRESS = IP_ADDRESS;
    }

    public Date getDATE_OF_ACTION() {
        return DATE_OF_ACTION;
    }

    public void setDATE_OF_ACTION(Date DATE_OF_ACTION) {
        this.DATE_OF_ACTION = DATE_OF_ACTION;
    }
}
