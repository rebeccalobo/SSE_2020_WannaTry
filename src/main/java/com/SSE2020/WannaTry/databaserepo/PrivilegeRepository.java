package com.SSE2020.WannaTry.databaserepo;

import com.SSE2020.WannaTry.model.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivilegeRepository extends JpaRepository<Privilege,Integer> {
    @Query(value = "SELECT * FROM wannatryschema.priveleges Where name = ?1",nativeQuery = true)
    Privilege findByName(String s);
}
