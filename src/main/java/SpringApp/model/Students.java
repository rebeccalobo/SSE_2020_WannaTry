package SpringApp.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class Students {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotBlank
    private String student_firstname;
    @NotBlank
    private String student_surname;
    @NotBlank
    private String email;
    public Students(){
        super();
    }
    public Students(int id, String student_firstname, String student_surname, String email) {
        super();
        this.id = id;
        this.student_firstname = student_firstname;
        this.student_surname = student_surname;
        this.email=email;
    }
    public int getId() {
        return id;
    }
    public String getStudent_firstname() {
        return student_firstname;
    }
    public String getStudent_surname() {
        return student_surname;
    }
    public String getEmail() {
        return email;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public void setStudent_firstname(String student_firstname) {
        this.student_firstname = student_firstname;
    }
    public void setStudent_surname(String student_surname) {
        this.student_surname = student_surname;
    }
    public void setEmail(String isbn) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", FN='" + student_firstname + '\'' +
                ", SN='" + student_surname + '\'' +
                ", EMAIL='" + email + '\'' +
                '}';
    }
}