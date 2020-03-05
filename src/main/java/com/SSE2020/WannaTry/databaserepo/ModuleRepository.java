package com.SSE2020.WannaTry.databaserepo;

import com.SSE2020.WannaTry.model.Modules;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface ModuleRepository extends JpaRepository<Modules, String> {

}
