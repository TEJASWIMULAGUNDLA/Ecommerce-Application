package com.ashokit.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ashokit.entity.SaleReport;

public interface SaleReportRepository extends JpaRepository<SaleReport, Long> {
   
	
	@Query("SELECT s FROM SaleReport s WHERE " +
		       "(:startDate IS NULL OR s.startDate >= :startDate) AND " +
		       "(:endDate IS NULL OR s.endDate <= :endDate)")
		List<SaleReport> findByDateRange(@Param("startDate") LocalDate startDate,
		                                 @Param("endDate") LocalDate endDate);

	    @Query("SELECT s FROM SaleReport s WHERE s.status=:status" )
	    List<SaleReport> findByStatus(@Param("status") String status);
}
