package com.qa.vetClinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.qa.vetClinic.model.OwnerModel;

@Repository
public interface OwnerRepo extends JpaRepository<OwnerModel, Long> {
	
		public OwnerModel findByName(String name);
	}


