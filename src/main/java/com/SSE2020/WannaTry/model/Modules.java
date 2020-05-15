package com.SSE2020.WannaTry.model;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.Collection;

@Entity
@Table(name = "modules")
public class Modules{
    @Id
    private String module_id;
    @NotNull
    private String module_name;
    @NotNull
    private String description;
    @NotNull
    private int lecturer_id;
    @NotNull
    private Date start_date;
    @NotNull
    private Date end_date;
    @NotNull
    private Double price;



    public Modules() {
        super();
    }

    public Modules(String module_id, String module_name, String description, int lecturer_id,Date start_date,Date end_date,Double price) {
        super();
        this.module_id = module_id;
        this.module_name = module_name;
        this.description = description;
        this.lecturer_id = lecturer_id;
        this.start_date = start_date;
        this.end_date = end_date;
        this.price = price;
    }

    public String getModule_id() {
        return module_id;
    }
    public String getModule_name() {
        return module_name;
    }
    public String getDescription() { return description; }
    public int getLecturer_id() { return lecturer_id; }

    public Date getStart_date() {
        return start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public Double getPrice() {
        return price;
    }

    public void setModule_id(String id) { this.module_id = id; }
    public void setModule_name(String module_name) {this.module_name = this.module_name; }
    public void setDescription(String description) { this.description = description; }
    public void setLecturer_id(int lecturer_id) { this.lecturer_id = lecturer_id; }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + module_id +
                ", N='" + module_name + '\'' +
                ", DESC='" + description + '\'' +
                '}';
    }
}
