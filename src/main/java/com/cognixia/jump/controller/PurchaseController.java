package com.cognixia.jump.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.cognixia.jump.model.Purchase;
import com.cognixia.jump.repository.PurchaseRepository;

public class PurchaseController {

	@Autowired
	PurchaseRepository repo;
	
	@GetMapping("/purchase")
	public List<Purchase> allPurchases() {
		
		return repo.findAll();
	}
	
	@GetMapping("/purchase/{id}")
	public Purchase getPurchase(@PathVariable long id) {
		
		Optional<Purchase> found = repo.findById(id);
		
		if(found.isPresent()) {
			return found.get();
		}
		else {
			return new Purchase();
		}
	}
	
	@PostMapping("/add/purchase")
	public ResponseEntity<String> addPurchase(@Valid @RequestBody Purchase purchase) {
		
		if(repo.existsById(purchase.getId())) {
			return ResponseEntity.status(400)
					.body("Purchase with id = " + purchase.getId() + " already exists");
		}
		else {
			Purchase created = repo.save(purchase);
			return ResponseEntity.status(201)
					.body("Created: " + created);
		}
		
	}

}
