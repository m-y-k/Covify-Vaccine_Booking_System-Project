package com.example.covify.repository;

import com.example.covify.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByEmailId(String emailId);

    @Query(value = "select * from user where is_dose1_taken = false and is_dose2_taken = false;", nativeQuery = true)
    List<User> getAllUsersWhoHaveNotTakenAnyDose();

    @Query(value = "select * from user where is_dose1_taken = true and is_dose2_taken = false;", nativeQuery = true)
    List<User> getAllUsersWhoHaveNotTakenDose1ButNotDose2();

    @Query(value = "select * from user where is_dose1_taken = true and is_dose2_taken = true;", nativeQuery = true)
    List<User> getAllUsersWhoAreFullyVaccinated();

    @Query(value = " select * from user where gender = \"male\" and is_dose1_taken = false and is_dose2_taken = false;", nativeQuery = true)
    List<User> getAllMalesWhoHaveNotTakenAnyDose();

    @Query(value = " select * from user where gender = \"female\" and is_dose1_taken = true and is_dose2_taken = true;", nativeQuery = true)
    List<User> getAllFemalesWhoAreFullyVaccinated();

}
