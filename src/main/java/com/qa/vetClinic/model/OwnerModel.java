package com.qa.vetClinic.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



@Entity
@Table(name= "owner")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value= {"creationDate", "lastModified"}, allowGetters= true)
public class OwnerModel implements Serializable {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private long id;
		
		@NotBlank
		private String name;
		
		@NotBlank
		private String petType;
		
		
		@Column (nullable = false, updatable= false)
		@Temporal(TemporalType.TIMESTAMP)
		@CreatedDate
		private Date creationDate;
		
		
		@Column (nullable = false)
		@Temporal(TemporalType.TIMESTAMP)
		@LastModifiedDate
		private Date lastModified;


		public OwnerModel(String name, String setType) {
			this.name=name;
			this.petType=petType;
			
		}
		
		public OwnerModel() {}

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getPetType() {
			return petType;
		}

		public void setPetType(String petType) {
			this.petType = petType;
		}

		public Date getCreationDate() {
			return creationDate;
		}

		public void setCreationDate(Date creationDate) {
			this.creationDate = creationDate;
		}

		public Date getLastModified() {
			return lastModified;
		}

		public void setLastModified(Date lastModified) {
			this.lastModified = lastModified;
		}

}

	