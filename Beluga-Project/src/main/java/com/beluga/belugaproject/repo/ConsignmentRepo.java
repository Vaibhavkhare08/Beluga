package com.beluga.belugaproject.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.beluga.belugaproject.model.Consignment;

@Repository
public interface ConsignmentRepo extends JpaRepository<Consignment, Long> {
	
}
