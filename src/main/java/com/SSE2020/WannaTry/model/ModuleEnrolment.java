package com.SSE2020.WannaTry.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "module_enrolments")
public class ModuleEnrolment {
    @Id @NotNull
    private String relation_id;
    @NotNull
    private String module_id;
    @NotNull
    private String student_id;

    public ModuleEnrolment() {
        super();
    }

    public ModuleEnrolment(String m_id, String s_id) {
        super();
        this.module_id = m_id;
        this.student_id = s_id;
    }

    public String getRelation_id() {
        return relation_id;
    }

    public void setRelation_id(String relation_id) {
        this.relation_id = relation_id;
    }

    public String getModule_id() {
        return module_id;
    }

    public void setModule_id(String module_id) {
        this.module_id = module_id;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }
}
