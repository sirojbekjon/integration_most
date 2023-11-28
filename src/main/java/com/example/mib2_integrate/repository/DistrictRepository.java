package com.example.mib2_integrate.repository;

import com.example.mib2_integrate.entity.District;
import com.example.mib2_integrate.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DistrictRepository extends JpaRepository<District,Long> {

    @Query(value = "SELECT * FROM district WHERE id = :id", nativeQuery = true)
    Optional<District> findByIdDistrict(@Param("id") Long id);
}
