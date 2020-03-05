package com.SSE2020.WannaTry.databaserepo;

import com.SSE2020.WannaTry.model.StudentModuleGrades;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentModuleGradesRepository extends JpaRepository<StudentModuleGrades,String> {
}
