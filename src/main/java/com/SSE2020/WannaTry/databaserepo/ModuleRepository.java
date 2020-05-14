package com.SSE2020.WannaTry.databaserepo;

import com.SSE2020.WannaTry.model.Modules;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.ArrayList;

@Repository
public interface ModuleRepository extends JpaRepository<Modules, String> {
    @Query(value= "SELECT m.* FROM modules as m INNER JOIN module_enrolement as en ON m.module_id = en.module_id WHERE en.student_id = ?1",nativeQuery=true)
    ArrayList<Modules> getModules(int id);

    @Query(value= "SELECT distinct * from modules where lecturer_id = ?1 ",nativeQuery=true)
    ArrayList<Modules> getStaffModules(int id);


    @Query(value = "SELECT me.student_id FROM module_enrolement as me INNER JOIN modules as m ON me.module_id = m.module_id where m.module_id=?1 AND m.lecturer_id = ?2",nativeQuery = true)
    ArrayList<Integer> getStudentsInSpecificModuleStaff(String module,int id);

    @Query(value= "SELECT sum(m.price) FROM modules as m INNER JOIN module_enrolement as en ON m.module_id = en.module_id\n" +
            "\n" +
            "WHERE en.student_id = ?1",nativeQuery=true)
    Double getFees(int id);

    @Query(value = "SELECT DISTINCT * FROM modules ",nativeQuery = true)
    ArrayList<Modules> getAllModules();

    @Query(value = "SELECT percentage FROM student_modules_grades WHERE module_id = ?1",nativeQuery = true)
    ArrayList<Double> getModuleSpecificGrades(String m_id);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM module_enrolement where student_id = ?1 and module_id = ?2",nativeQuery = true)
    void unEnrolStudent(long s_id,String m_id);
}
