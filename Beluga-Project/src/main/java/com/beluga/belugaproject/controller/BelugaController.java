package com.beluga.belugaproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.beluga.belugaproject.model.Consignment;
import com.beluga.belugaproject.repo.ConsignmentRepo;

@RestController
@RequestMapping("beluga/v1")
public class BelugaController {
	
	@Autowired
	private ConsignmentRepo consignmentRepo;
	

	//http://localhost:8080/beluga/v1/getAllConsignment
	@GetMapping("/getAllConsignment")
	public List<Consignment>  start() {
		return consignmentRepo.findAll();
	}
	
	@PostMapping("/createConsignment")
	public ResponseEntity<Consignment> createConsignment(@RequestBody Consignment consignment) {
		return new ResponseEntity<Consignment>(consignmentRepo.save(consignment),HttpStatus.CREATED);
	}
		
	@SuppressWarnings("deprecation")
	@PostMapping("/getConsignment/{id}")
	public ResponseEntity<Consignment> getConsignment(@PathVariable long id){
		return new ResponseEntity<Consignment>(consignmentRepo.findById(id).get(),HttpStatus.FOUND);
	}
}
