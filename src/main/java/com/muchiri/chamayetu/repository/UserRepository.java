package com.muchiri.chamayetu.repository;

import com.muchiri.chamayetu.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u.username, u.password, u.role FROM User u WHERE u.username = :username ")
    Optional<User> findByUsername(@Param("username") String username);
}
