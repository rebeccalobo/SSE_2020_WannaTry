package com.SSE2020.WannaTry.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="student_module_grades")
public class StudentModuleGrades {
    @NotNull
    private String module_id;
    @NotNull
    private String student_id;
    @NotNull
    private Double percentage;
    @NotNull
    private String letter_grade;
    @Id @NotNull
    private String relation_id;


    public StudentModuleGrades() {
    }
    public StudentModuleGrades(String relation_id,String module_id,String student_id,Double percentage,String letter_grade) {
        this.module_id=module_id;
        this.student_id=student_id;
        this.percentage=percentage;
        this.letter_grade=letter_grade;
        this.relation_id=relation_id;
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

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }

    public String getLetter_grade() {
        return letter_grade;
    }

    public void setLetter_grade(String letter_grade) {
        this.letter_grade = letter_grade;
    }
}
