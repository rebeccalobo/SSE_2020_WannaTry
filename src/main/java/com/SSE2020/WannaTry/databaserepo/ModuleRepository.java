package com.SSE2020.WannaTry.databaserepo;

import com.SSE2020.WannaTry.model.Modules;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface ModuleRepository extends JpaRepository<Modules, String> {
    @Query(value= "SELECT * FROM modules as m INNER JOIN module_enrolments as en ON m.module_id = en.module_id WHERE en.student_id = ?1",nativeQuery=true)
    ArrayList<Modules> getModules(String id);
    @Query(value= "SELECT sum(m.price) FROM modules as m INNER JOIN module_enrolments as en ON m.module_id = en.module_id\n" +
            "\n" +
            "WHERE en.student_id = ?1",nativeQuery=true)
    Double getFees(String id);
}
