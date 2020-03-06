package com.SSE2020.WannaTry.databaserepo;

import com.SSE2020.WannaTry.model.StudentModuleGrades;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentModuleGradesRepository extends JpaRepository<StudentModuleGrades,String> {
    @Query(value = "REPLACE INTO wannatryschema.student_module_grades (module_id, student_id, percentage, letter_grade) value (?1,?2,?3,?4)",nativeQuery = true)
    void updateGrade(String m_id,String s_id,String p,String l);

}
