package com.cognixia.jump.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cognixia.jump.model.Purchase;


@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long>{
	
	Optional<Purchase> findByPurchaseDate(Date purchaseDate);

	//custom SQL Query
		@Query("select a from Purchase a where a.purchaseDate = ?1 and a.cost = ?2")
		List<Purchase>allPurchasesInSameDate(Date purchaseDate, Float cost);
}
