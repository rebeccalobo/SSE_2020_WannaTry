package com.SSE2020.WannaTry.databaserepo;

import com.SSE2020.WannaTry.model.Grades;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Repository
public interface GradesRepository extends JpaRepository<Grades,String> {
    @Query(value= "Select * from wannatryschema.grades where student_id=?1 and module_id=?2",nativeQuery=true)
    Optional<Grades> getGradeBySandM(int s, String m);

    @Query(value = "SELECT gra.*, end_date FROM modules as m LEFT JOIN module_enrolement as en ON m.module_id = en.module_id RIGHT JOIN GRADES as gra ON m.module_id = gra.module_id WHERE en.student_id = ?1 AND  m.end_date <= ?2 ",nativeQuery = true)
    Collection<Grades> getStudentsGrades(int s_id,Date date);

}
