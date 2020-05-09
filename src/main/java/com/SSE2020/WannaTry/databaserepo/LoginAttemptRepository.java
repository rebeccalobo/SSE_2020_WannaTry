package com.SSE2020.WannaTry.databaserepo;

import com.SSE2020.WannaTry.model.FailedAttempts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;

public interface LoginAttemptRepository extends JpaRepository<FailedAttempts,String> {

    @Modifying
    @Query(value = "REPLACE INTO wannatryschema.failed_attempts(IP_ADDRESS, ATTEMPTS, LAST_ATTEMPT) VALUE (?1,?2,?3) ",nativeQuery = true)
    void updateAttempt(String IP,int a, Date date);

}
