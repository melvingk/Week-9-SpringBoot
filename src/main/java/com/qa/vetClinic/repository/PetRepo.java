package com.qa.vetClinic.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.qa.vetClinic.model.OwnerModel;
import com.qa.vetClinic.model.PetModel;

@Repository
public interface PetRepo extends JpaRepository<PetModel, Long> {
	
	Page<PetModel>findByOwnerId(Long ownerId, Pageable pageable);
}
