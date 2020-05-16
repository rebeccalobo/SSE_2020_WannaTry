package com.SSE2020.WannaTry.databaserepo;

import com.SSE2020.WannaTry.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query(value = "Select * from wannatryschema.user where fname = ?1 and lname = ?2",nativeQuery = true)
    Optional<User> findByName(String f, String l);

    @Query(value="CALL wannatryschema.random_id();",nativeQuery = true)
    int getRandomID();

    @Modifying
    @Transactional
    @Query(value = "CALL wannatryschema.delete_user(?1)",nativeQuery=true)
    void deleteUser(int id);
}
