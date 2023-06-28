package com.career_link.kenya.repositories;

import com.career_link.kenya.entities.ApplicationUser;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@Repository
public interface AuthRepository extends JpaRepository<ApplicationUser, Long> {


    //Note that we are using classNames for Queries and not the names of the tables in the database.
    @Query("SELECT user FROM ApplicationUser user WHERE user.emailAddress = ?1")
    public ApplicationUser findByEmailAddress(String emailAddress);
}
