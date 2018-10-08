package com.qa.vetClinic.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.vetClinic.exception.ResourceNotFoundException;
import com.qa.vetClinic.model.OwnerModel;
import com.qa.vetClinic.repository.OwnerRepo;



@RestController
@RequestMapping("/api")
public class OwnerController {
	
		
		@Autowired
		OwnerRepo myRepository;
		

		//Method to create a person
		@PostMapping ("/owner")
		public OwnerModel createsPerson (@Valid @RequestBody OwnerModel mSDM)
		{
			return myRepository.save(mSDM);
		}
		
		//Method to get a person 
		@GetMapping ("/owner/{id}")
		public OwnerModel getOwnerbyID(@PathVariable(value = "id") Long ownerID ) {
			
			return myRepository.findById(ownerID).orElseThrow(()-> new ResourceNotFoundException("OwnerModel", "id", ownerID));
			
		}
		
		
		//Method  to get all people
		@GetMapping("/owner")
		public List<OwnerModel>getAllOwners(){
			return myRepository.findAll();
		}
		
		//Method to update a person
		
		@PutMapping("/owner/{id}")
		public OwnerModel updatePerson(@PathVariable(value = "id" ) Long ownerID,
				@Valid @RequestBody OwnerModel ownerDetails) {
			
			OwnerModel mSDM = myRepository.findById(ownerID).orElseThrow(()-> new ResourceNotFoundException("Owner", "id", ownerID));

			mSDM.setName(ownerDetails.getName());
			mSDM.setPetType(ownerDetails.getPetType());
			
			OwnerModel updateData = myRepository.save(mSDM);
			
			return updateData;
		}
		
		//Method to remove a person
		
		@DeleteMapping("/owner/{id}")
		public ResponseEntity<?> deletePerson(@PathVariable(value="id")Long ownerID){
			
			OwnerModel mSDM = myRepository.findById(ownerID).orElseThrow(()-> new ResourceNotFoundException("Owner", "id", ownerID)); 
			
			myRepository.delete(mSDM);
			return ResponseEntity.ok().build();
		}


}
