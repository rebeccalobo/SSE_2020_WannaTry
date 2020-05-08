package com.SSE2020.WannaTry.databaserepo;

import com.SSE2020.WannaTry.model.ModuleEnrolment;
import com.SSE2020.WannaTry.model.Modules;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModuleEnrolmentsRepository extends JpaRepository<ModuleEnrolment, Integer> {
}
