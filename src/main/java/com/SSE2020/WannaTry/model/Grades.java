package com.SSE2020.WannaTry.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="grades")
public class Grades {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotNull
    private String module_id;
    @NotNull
    private int student_id;
    @NotNull
    private Double percentage;
    @NotNull
    private String Letter;

    private String feedback;

    public Grades() {
    }
    public Grades(String module_id, int student_id, Double percentage, String letter_grade,String feedback) {
        this.module_id=module_id;
        this.student_id=student_id;
        this.percentage=percentage;
        this.Letter=letter_grade;
        this.feedback = feedback;
    }

    public String getModule_id() {
        return module_id;
    }

    public void setModule_id(String module_id) {
        this.module_id = module_id;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }

    public String getLetter_grade() {
        return Letter;
    }

    public void setLetter_grade(String letter_grade) {
        this.Letter = letter_grade;
    }

    public long getRelation_id() { return id; }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
