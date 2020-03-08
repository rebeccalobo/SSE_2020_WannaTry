package com.SSE2020.WannaTry.databaserepo;

import com.SSE2020.WannaTry.model.StudentModuleGrades;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.ArrayList;

@Repository
public interface StudentModuleGradesRepository extends JpaRepository<StudentModuleGrades,String> {
    @Query(value= "SELECT gra.*, end_date FROM modules as m LEFT JOIN module_enrolments as en ON m.module_id = en.module_id\n" +
            "RIGHT JOIN student_module_grades as gra ON m.module_id = gra.module_id\n" +
            "WHERE en.student_id = ?1 AND  m.end_date <= ?2 ",nativeQuery=true)
    ArrayList<StudentModuleGrades> getGrades(String id, Date d)
            ;
}
