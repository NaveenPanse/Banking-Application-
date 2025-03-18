// User Repository
package com.banking.MyBankingApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.banking.MyBankingApp.entity.User;

import jakarta.transaction.Transactional;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, Long> {
	
	//for finding user by username
    Optional<User> findByUsername(String username);
    
    ////for finding user by id
    Optional<User> findById(UUID id);
    
    //delete by id
    @Modifying
    @Transactional
    @Query("DELETE FROM User u WHERE u.id = :Id")
    void deleteById(@Param("Id") UUID Id);
}