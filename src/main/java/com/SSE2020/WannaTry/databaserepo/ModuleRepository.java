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
    ArrayList<Modules> getModules(long id);

    @Query(value= "SELECT * FROM modules as m INNER JOIN staff_module as sm ON sm.module_id = m.module_id INNER JOIN user as u ON sm.staff_id = u.id where s.staff_id = ?1",nativeQuery=true)
    ArrayList<Modules> getStaffModules(long id);


    @Query(value = "SELECT student_id FROM module_enrolement as me\n" +
            "    INNER JOIN modules as m ON me.module_id = m.module_id\n" +
            "    INNER JOIN staff_module as sm ON sm.module_id = m.module_id" +
            " INNER JOIN user as u ON u.id = sm.staff_id" +
            "                                               \n" +
            "    where m.module_id=?1 AND s.staff_id = ?2",nativeQuery = true)
    ArrayList<Long> getStudentsInSpecificModuleStaff(String module,long staff);

    @Query(value= "SELECT sum(m.price) FROM modules as m INNER JOIN module_enrolement as en ON m.module_id = en.module_id\n" +
            "\n" +
            "WHERE en.student_id = ?1",nativeQuery=true)
    Double getFees(long id);

    @Query(value = "SELECT DISTINCT * FROM modules ",nativeQuery = true)
    ArrayList<Modules> getAllModules();

    @Query(value = "SELECT percentage FROM student_modules_grades WHERE module_id = ?1",nativeQuery = true)
    ArrayList<Double> getModuleSpecificGrades(String m_id);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM module_enrolement where student_id = ?1 and module_id = ?2",nativeQuery = true)
    void unEnrolStudent(long s_id,String m_id);
}
