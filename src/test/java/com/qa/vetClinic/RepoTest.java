package com.qa.vetClinic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.qa.vetClinic.model.OwnerModel;
import com.qa.vetClinic.repository.OwnerRepo;



public class RepoTest {
	@RunWith(SpringRunner.class)
	@SpringBootTest(classes= {VetClinicApplication.class})
	@DataJpaTest
	public class RepositoryTest {
		
		@Autowired
		private TestEntityManager entityManager;
		
		@Autowired
		private OwnerRepo MyRepo;
		
		@Test
		public void retrieveByIdTest() {
			OwnerModel model1 = new OwnerModel ("Bob", "Tiger");
			entityManager.persist(model1);
			entityManager.flush();
			assertTrue(MyRepo.findById(model1.getId()).isPresent());
					
		}
		@Test
		public void retrieveByIdName() {
			OwnerModel model2 = new OwnerModel ("Vinu", "crocodile");
			entityManager.persist(model2);
			entityManager.flush();
			assertEquals("Vinu", MyRepo.findByName("Vinu").getName());		
		}
		

	}


}
