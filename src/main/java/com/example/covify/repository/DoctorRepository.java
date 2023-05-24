package com.example.covify.repository;

import com.example.covify.dto.responseDTO.DoctorResponseDto;
import com.example.covify.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {

    DoctorResponseDto deleteByEmailId(String emailId);

    Doctor findByEmailId(String emailId);
    @Query(value = "SELECT * FROM doctor WHERE gender = :gender AND age > :age", nativeQuery = true)
    List<Doctor> getAllDoctorsWithAgeMoreThanX(String gender, Integer age);

    @Query(value = "SELECT * FROM doctor WHERE gender = :gender", nativeQuery = true)
    List<Doctor> getAllDoctorsWithGender(String gender);

    List<Doctor> findAllByGenderAndVaccinationCenterId(String gender, Integer id);

    List<Doctor> findAllByGenderAndAgeAndVaccinationCenterId(String gender, Integer age, Integer id);

}
