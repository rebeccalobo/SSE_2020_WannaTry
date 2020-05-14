package com.SSE2020.WannaTry.databaserepo;

import com.SSE2020.WannaTry.model.IP_Blacklist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


public interface BlacklistRepository extends JpaRepository<IP_Blacklist, String> {
    @Modifying
    @Query(value = "REPLACE INTO wannatryschema.ip_blacklist(IP_ADDRESS) value(?1)",nativeQuery = true)
    void blockIP(String ip);


}
