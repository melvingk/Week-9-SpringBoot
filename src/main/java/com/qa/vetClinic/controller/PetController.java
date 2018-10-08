package com.qa.vetClinic.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
import com.qa.vetClinic.model.PetModel;
import com.qa.vetClinic.repository.OwnerRepo;
import com.qa.vetClinic.repository.PetRepo;

@RequestMapping("/api")
@RestController
public class PetController {

	
	
	public class personController {
		
		@Autowired
		private PetRepo petRepository;
		
		@Autowired
		private OwnerRepo mySpringBootRepository;
		
		@GetMapping("/owner/{ownerId}/pet")
		public Page<PetModel> getAllPetsByOwnersId(@PathVariable(value="ownerId")Long ownerId, Pageable pageable){
		return petRepository.findByOwnerId(ownerId, pageable);
		}
		
		@PostMapping("/owner/{ownerId}/pet")
		public PetModel createComment(@PathVariable(value= "ownerId") Long ownerId, 
									@Valid@RequestBody PetModel pet) {
			return mySpringBootRepository.findById(ownerId).map(mySpringBootDataModel->
			{pet.setOwner(mySpringBootDataModel);
			return petRepository.save(pet);
			}).orElseThrow(() -> new ResourceNotFoundException("Owner", "id", pet));
			
			}
		@PutMapping("/owner/{ownerId}/pet/{petId}")
		public PetModel updateOrder(@PathVariable(value="ownerId")Long ownerId,
								 @PathVariable(value="petId") Long petId,
								 @Valid@RequestBody PetModel petRequest) {
		if (!mySpringBootRepository.existsById(ownerId)) {
			throw new ResourceNotFoundException("Owner", "id", petRequest);
		}
		
		return petRepository.findById(petId).map(pet -> {
			pet.setPetName(petRequest.getPetName());
			return petRepository.save(pet);
		}).orElseThrow(() -> new ResourceNotFoundException("OrderId", "id", petRequest));

		}
		
		@DeleteMapping("/owner/{ownerId}/pet/{petId}")
		public ResponseEntity<?> deleteComment(@PathVariable(value = "ownerId") Long ownerId,
											   @PathVariable(value = "petId") Long petId){
			if (!mySpringBootRepository.existsById(ownerId)) {
				throw new ResourceNotFoundException("Owner", "id", ownerId);
			}
			
			return petRepository.findById(petId).map(pet -> {
				petRepository.delete(pet);
				return ResponseEntity.ok().build();
			}).orElseThrow(() -> new ResourceNotFoundException("PetId", petId.toString(),null));
			

			}
			
}
}
