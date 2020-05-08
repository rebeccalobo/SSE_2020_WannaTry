package com.SSE2020.WannaTry.databaserepo;

import com.SSE2020.WannaTry.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {
    @Query(value = "Select * from wannatryschema.roles where name = ?1 ",nativeQuery = true)
    Role findByName(String n);
}
