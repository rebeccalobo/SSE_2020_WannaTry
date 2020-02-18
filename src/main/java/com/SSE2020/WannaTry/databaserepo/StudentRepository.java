package com.SSE2020.WannaTry.databaserepo;

import com.SSE2020.WannaTry.model.Students;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete


@Repository
public interface StudentRepository extends JpaRepository<Students, String> {
}