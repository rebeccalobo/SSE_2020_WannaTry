package com.SSE2020.WannaTry.model;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "modules")
public class Modules{
    @Id
    private String module_id;
    @NotBlank
    private String module_name;
    @NotBlank
    private String description;
    @NotBlank
    private String lecturer_id;


    public Modules() {
        super();
    }

    public Modules(String module_id, String module_name, String description, String lecturer_id) {
        super();
        this.module_id = module_id;
        this.module_name = module_name;
        this.description = description;
        this.lecturer_id = lecturer_id;
    }

    public String getModule_id() {
        return module_id;
    }
    public String getModule_name() {
        return module_name;
    }
    public String getDescription() { return description; }
    public String getLecturer_id() { return lecturer_id; }
    public void setModule_id(String id) { this.module_id = id; }
    public void setModule_name(String module_name) {this.module_name = this.module_name; }
    public void setDescription(String description) { this.description = description; }
    public void setLecturer_id(String lecturer_id) { this.lecturer_id = lecturer_id; }



    @Override
    public String toString() {
        return "Blog{" +
                "id=" + module_id +
                ", N='" + module_name + '\'' +
                ", DESC='" + description + '\'' +
                '}';
    }
}
