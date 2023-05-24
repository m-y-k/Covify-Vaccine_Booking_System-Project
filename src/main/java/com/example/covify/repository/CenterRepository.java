package com.example.covify.repository;

import com.example.covify.model.Doctor;
import com.example.covify.model.VaccinationCenter;
import com.sun.source.doctree.DocRootTree;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CenterRepository extends JpaRepository<VaccinationCenter, Integer> {
    Optional<VaccinationCenter> findAllById(Integer id);
}
