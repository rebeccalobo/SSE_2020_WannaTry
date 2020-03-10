package com.SSE2020.WannaTry.databaserepo;

import com.SSE2020.WannaTry.model.Modules;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.ArrayList;

@Repository
public interface ModuleRepository extends JpaRepository<Modules, String> {
    @Query(value= "SELECT * FROM modules as m INNER JOIN module_enrolments as en ON m.module_id = en.module_id WHERE en.student_id = ?1",nativeQuery=true)
    ArrayList<Modules> getModules(String id);

    @Query(value= "SELECT * FROM modules as me INNER JOIN staff as s ON me.lecturer_id = s.staff_id where s.staff_id = ?1",nativeQuery=true)
    ArrayList<Modules> getStaffModules(String id);


    @Query(value = "SELECT student_id FROM module_enrolments as me\n" +
            "    INNER JOIN modules as m ON me.module_id = m.module_id\n" +
            "    INNER JOIN staff as s ON s.staff_id = m.lecturer_id\n" +
            "    where m.module_id=?1 AND s.staff_id = ?2",nativeQuery = true)
    ArrayList<String> getStudentsInSpecificModuleStaff(String module,String staff);

    @Query(value= "SELECT sum(m.price) FROM modules as m INNER JOIN module_enrolments as en ON m.module_id = en.module_id\n" +
            "\n" +
            "WHERE en.student_id = ?1",nativeQuery=true)
    Double getFees(String id);

}
