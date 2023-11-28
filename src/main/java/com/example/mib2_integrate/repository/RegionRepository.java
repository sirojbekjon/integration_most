package com.example.mib2_integrate.repository;

import com.example.mib2_integrate.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RegionRepository extends JpaRepository<Region,Long> {

    @Query(value = "SELECT * FROM region WHERE id = :id", nativeQuery = true)
    Optional<Region> findByIdRegion(@Param("id") Long id);


}
