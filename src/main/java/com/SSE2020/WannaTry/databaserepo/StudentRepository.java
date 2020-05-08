package com.SSE2020.WannaTry.databaserepo;

import com.SSE2020.WannaTry.model.Students;
import com.SSE2020.WannaTry.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete


@Repository
public interface StudentRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT gender from wannatryschema.user",nativeQuery = true)
    ArrayList<String> getGenders();
//    @Query(value = "DELETE from wannatryschema.students where student_id = ?1;DELETE from wannatryschema.module_enrolments where student_id = ?1;DELETE from wannatryschema.student_module_grades where student_id = ?1;",nativeQuery = true)
//    ArrayList<String> deleteStudent(String id);
}