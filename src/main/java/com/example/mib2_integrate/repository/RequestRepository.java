package com.example.mib2_integrate.repository;


import com.example.mib2_integrate.entity.RequestMib;
import com.example.mib2_integrate.payload.ResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RequestRepository extends JpaRepository<RequestMib, Long> {

    @Query(
            value = "SELECT 'personalform' AS source_table, * FROM personalforms WHERE personalforms.pnfl = :pnfll \n" +
                    "UNION\n" +
                    "SELECT 'dr' AS source_table, * FROM dr WHERE dr.pnfl = :pnfll AND dr.pnfl IS NOT NULL\n" +
                    "UNION\n" +
                    "SELECT 'yd' AS source_table, * FROM yd WHERE yd.pnfl = :pnfll AND yd.pnfl IS NOT NULL",
            nativeQuery = true
    )
    Optional<Object[]> getRequestMibPinfl(@Param("pnfll") String pnfl);








}
