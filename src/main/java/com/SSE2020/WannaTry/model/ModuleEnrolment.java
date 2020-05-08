package com.SSE2020.WannaTry.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "module_enrolement")
public class ModuleEnrolment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    private String module_id;
    @NotNull
    private long student_id;

    public ModuleEnrolment() {
        super();
    }

    public ModuleEnrolment(String m_id, long s_id) {
        super();
        this.module_id = m_id;
        this.student_id = s_id;

    }

    public int getRelation_id() {
        return id;
    }



    public String getModule_id() {
        return module_id;
    }

    public void setModule_id(String module_id) {
        this.module_id = module_id;
    }

    public long getStudent_id() {
        return student_id;
    }

    public void setStudent_id(long student_id) {
        this.student_id = student_id;
    }
}
