package SpringApp.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
//TODO
//@Table(name = "students")
public class Students {
    @Id
    @GeneratedValue
    private Long id;
    @NotBlank
    private String student_firstname;
    @NotBlank
    private String student_surname;
    @NotBlank
    private String isbn;
    public Students(){
        super();
    }
    public Students(Long id, String student_firstname, String student_surname, String isbn) {
        super();
        this.id = id;
        this.student_firstname = student_firstname;
        this.student_surname = student_surname;
        this.isbn=isbn;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getStudent_firstname() {
        return student_firstname;
    }
    public void setStudent_firstname(String student_firstname) {
        this.student_firstname = student_firstname;
    }
    public String getStudent_surname() {
        return student_surname;
    }
    public void setStudent_surname(String student_surname) {
        this.student_surname = student_surname;
    }
    public String getIsbn() {
        return isbn;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}