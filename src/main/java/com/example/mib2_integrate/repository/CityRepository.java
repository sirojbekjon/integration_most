package com.example.mib2_integrate.repository;

import com.example.mib2_integrate.entity.City;
import com.example.mib2_integrate.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CityRepository extends JpaRepository<City,Long> {
    @Query(value = "SELECT * FROM city WHERE id = :id", nativeQuery = true)
    Optional<City> findByIdCity(@Param("id") Long id);




}
