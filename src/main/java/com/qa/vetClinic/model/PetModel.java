package com.qa.vetClinic.model;


	import javax.persistence.Entity;
	import javax.persistence.FetchType;
	import javax.persistence.GeneratedValue;
	import javax.persistence.GenerationType;
	import javax.persistence.Id;
	import javax.persistence.JoinColumn;
	import javax.persistence.Lob;
	import javax.persistence.ManyToOne;
	import javax.persistence.Table;
	import javax.validation.constraints.NotNull;

	import org.hibernate.annotations.OnDelete;
	import org.hibernate.annotations.OnDeleteAction;

	import com.fasterxml.jackson.annotation.JsonIgnore;

	@Entity
	@Table(name = "Pets")
	public class PetModel {
		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private long id;
		
		@NotNull
		private String petName;
		
		@NotNull
		private String petType;
		
		@NotNull
		private String injury;
		
		@NotNull
		@Lob
		private String description;
		
		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public String getPetName() {
			return petName;
		}

		public void setPetName(String petName) {
			this.petName = petName;
		}

		public String getPetType() {
			return petType;
		}

		public void setPetType(String petType) {
			this.petType = petType;
		}

		public String getInjury() {
			return injury;
		}

		public void setInjury(String injury) {
			this.injury = injury;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public OwnerModel getOwner() {
			return owner;
		}

		public void setOwner(OwnerModel owner) {
			this.owner = owner;
		}

		@ManyToOne(fetch= FetchType.LAZY, optional = false)
		@JoinColumn(name = "owner_Id", nullable = false)
		@OnDelete(action = OnDeleteAction.CASCADE)
		@JsonIgnore
		private OwnerModel owner;
		
		

	}


