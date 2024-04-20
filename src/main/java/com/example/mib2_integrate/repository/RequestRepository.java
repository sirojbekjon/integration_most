package com.example.mib2_integrate.repository;


import com.example.mib2_integrate.entity.RequestMib;
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
                    "SELECT 'yd' AS source_table, * FROM yd WHERE yd.pnfl = :pnfll AND yd.pnfl IS NOT NULL\n"+
                    "UNION\n"+
                    "SELECT 'wareor' AS source_table, * FROM wareor WHERE wareor.pnfl = :pnfll AND wareor.pnfl IS NOT NULL\n" +
                    "UNION\n"+
                    "SELECT 'siblings' AS source_table, * FROM siblings WHERE siblings.pnfl = :pnfll AND siblings.pnfl IS NOT NULL\n" +
                    "UNION\n"+
                    "SELECT 'second_war' AS source_table, * FROM second_war WHERE second_war.pnfl = :pnfll AND second_war.pnfl IS NOT NULL\n" +
                    "UNION\n"+
                    "SELECT 'others' AS source_table, * FROM others WHERE others.pnfl = :pnfll AND others.pnfl IS NOT NULL\n" +
                    "UNION\n"+
                    "SELECT 'invalids' AS source_table, * FROM invalids WHERE invalids.pnfl = :pnfll AND invalids.pnfl IS NOT NULL\n" +
                    "UNION\n"+
                    "SELECT 'conscripts' AS source_table, * FROM conscripts WHERE conscripts.pnfl = :pnfll AND conscripts.pnfl IS NOT NULL\n" +
                    "UNION\n"+
                    "SELECT 'companion' AS source_table, * FROM companion WHERE companion.pnfl = :pnfll AND companion.pnfl IS NOT NULL\n"+
                    "UNION\n"+
                    "SELECT 'awardees' AS source_table, * FROM awardees WHERE awardees.pnfl = :pnfll AND awardees.pnfl IS NOT NULL\n",
            nativeQuery = true
    )
    Optional<Object[]> getRequestMibPinfl(@Param("pnfll") String pnfl);








}
