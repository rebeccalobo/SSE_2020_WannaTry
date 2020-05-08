package com.SSE2020.WannaTry.databaserepo;

import com.SSE2020.WannaTry.model.Role;
import com.SSE2020.WannaTry.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "Select * from wannatryschema.user where fname = ?1 and lname = ?2",nativeQuery = true)
    Optional<User> findByName(String f, String l);

    @Query(value="CALL wannatryschema.random_id();",nativeQuery = true)
    int getRandomID();
}
