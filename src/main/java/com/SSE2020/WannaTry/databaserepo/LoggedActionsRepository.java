package com.SSE2020.WannaTry.databaserepo;

import com.SSE2020.WannaTry.model.LoggedActions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;

public interface LoggedActionsRepository extends JpaRepository<LoggedActions,String> {

    @Modifying
    @Query(value = "INSERT INTO wannatryschema.logged_actions(USER_ID, ACTION_TYPE, ACTION_DESC, IP_ADDRESS, DATE_OF_ACTION) VALUES (?1, ?2, ?3, ?4, ?5)", nativeQuery = true)
    void log(int id, String action, String desc, String ip, Date date);

}
