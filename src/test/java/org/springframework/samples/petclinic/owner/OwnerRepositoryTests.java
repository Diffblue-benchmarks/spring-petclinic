package org.springframework.samples.petclinic.owner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = { OwnerRepository.class })
@DataJpaTest
@EnableAutoConfiguration
@EntityScan(basePackages = { "org.springframework.samples.petclinic.owner" })
class OwnerRepositoryTests {

	@Autowired
	private OwnerRepository ownerRepository;

	/**
	 * Test {@link OwnerRepository#findPetTypes()}.
	 *
	 * <p>
	 * Method under test: {@link OwnerRepository#findPetTypes()}
	 */
	@Test
	@DisplayName("Test findPetTypes()")
	@Tag("ContributionFromDiffblue")
	@ManagedByDiffblue
	@MethodsUnderTest({ "List OwnerRepository.findPetTypes()" })
	void testFindPetTypes() {
		// Arrange
		Owner owner = new Owner();
		owner.setAddress("42 Main St");
		owner.setCity("Oxford");
		owner.setFirstName("Jane");
		owner.setLastName("Doe");
		owner.setTelephone("6625550144");

		Owner owner2 = new Owner();
		owner2.setAddress("17 High St");
		owner2.setCity("London");
		owner2.setFirstName("John");
		owner2.setLastName("Smith");
		owner2.setTelephone("8605550118");
		ownerRepository.save(owner);
		ownerRepository.save(owner2);

		// Act
		List<PetType> actualFindPetTypesResult = ownerRepository.findPetTypes();

		// Assert
		assertEquals(6, actualFindPetTypesResult.size());
		PetType getResult = actualFindPetTypesResult.get(0);
		assertEquals("bird", getResult.getName());
		PetType getResult2 = actualFindPetTypesResult.get(1);
		assertEquals("cat", getResult2.getName());
		PetType getResult3 = actualFindPetTypesResult.get(2);
		assertEquals("dog", getResult3.getName());
		PetType getResult4 = actualFindPetTypesResult.get(3);
		assertEquals("hamster", getResult4.getName());
		PetType getResult5 = actualFindPetTypesResult.get(4);
		assertEquals("lizard", getResult5.getName());
		PetType getResult6 = actualFindPetTypesResult.get(5);
		assertEquals("snake", getResult6.getName());
		assertEquals(1, getResult2.getId().intValue());
		assertEquals(2, getResult3.getId().intValue());
		assertEquals(3, getResult5.getId().intValue());
		assertEquals(4, getResult6.getId().intValue());
		assertEquals(5, getResult.getId().intValue());
		assertEquals(6, getResult4.getId().intValue());
		assertFalse(getResult.isNew());
		assertFalse(getResult2.isNew());
		assertFalse(getResult3.isNew());
		assertFalse(getResult4.isNew());
		assertFalse(getResult5.isNew());
		assertFalse(getResult6.isNew());
	}

	/**
	 * Test {@link OwnerRepository#findByLastName(String, Pageable)}.
	 *
	 * <p>
	 * Method under test: {@link OwnerRepository#findByLastName(String, Pageable)}
	 */
	@Test
	@DisplayName("Test findByLastName(String, Pageable)")
	@Tag("ContributionFromDiffblue")
	@ManagedByDiffblue
	@MethodsUnderTest({ "Page OwnerRepository.findByLastName(String, Pageable)" })
	void testFindByLastName() {
		// Arrange
		Owner owner = new Owner();
		owner.setAddress("42 Main St");
		owner.setCity("Oxford");
		owner.setFirstName("Jane");
		owner.setLastName("Doe");
		owner.setTelephone("6625550144");

		Owner owner2 = new Owner();
		owner2.setAddress("17 High St");
		owner2.setCity("London");
		owner2.setFirstName("John");
		owner2.setLastName("Smith");
		owner2.setTelephone("8605550118");
		ownerRepository.save(owner);
		ownerRepository.save(owner2);

		// Act
		Page<Owner> actualFindByLastNameResult = ownerRepository.findByLastName("Doe", Pageable.unpaged());

		// Assert
		assertTrue(actualFindByLastNameResult instanceof PageImpl);
		List<Owner> toListResult = actualFindByLastNameResult.toList();
		assertEquals(1, toListResult.size());
		assertSame(owner, toListResult.get(0));
	}

	/**
	 * Test {@link OwnerRepository#findById(Integer)}.
	 *
	 * <p>
	 * Method under test: {@link OwnerRepository#findById(Integer)}
	 */
	@Test
	@DisplayName("Test findById(Integer)")
	@Tag("ContributionFromDiffblue")
	@ManagedByDiffblue
	@MethodsUnderTest({ "Owner OwnerRepository.findById(Integer)" })
	void testFindById() {
		// Arrange
		Owner owner = new Owner();
		owner.setAddress("42 Main St");
		owner.setCity("Oxford");
		owner.setFirstName("Jane");
		owner.setLastName("Doe");
		owner.setTelephone("6625550144");
		ownerRepository.save(owner);

		Owner owner2 = new Owner();
		owner2.setAddress("17 High St");
		owner2.setCity("London");
		owner2.setFirstName("John");
		owner2.setLastName("Smith");
		owner2.setTelephone("8605550118");
		ownerRepository.save(owner2);

		Owner owner3 = new Owner();
		owner3.setAddress("42 Main St");
		owner3.setCity("Oxford");
		owner3.setFirstName("Jane");
		owner3.setLastName("Doe");
		owner3.setTelephone("6625550144");
		ownerRepository.save(owner3);

		// Act and Assert
		assertSame(owner3, ownerRepository.findById(owner3.getId()));
	}

	/**
	 * Test {@link OwnerRepository#save(Owner)}.
	 *
	 * <p>
	 * Method under test: {@link OwnerRepository#save(Owner)}
	 */
	@Test
	@DisplayName("Test save(Owner)")
	@Tag("ContributionFromDiffblue")
	@ManagedByDiffblue
	@MethodsUnderTest({ "void OwnerRepository.save(Owner)" })
	void testSave() {
		// Arrange
		Owner owner = new Owner();
		owner.setAddress("42 Main St");
		owner.setCity("Oxford");
		owner.setFirstName("Jane");
		owner.setLastName("Doe");
		owner.setTelephone("6625550144");

		// Act
		ownerRepository.save(owner);

		// Assert
		List<PetType> findPetTypesResult = ownerRepository.findPetTypes();
		assertEquals(6, findPetTypesResult.size());
		PetType getResult = findPetTypesResult.get(0);
		assertEquals("bird", getResult.getName());
		PetType getResult2 = findPetTypesResult.get(1);
		assertEquals("cat", getResult2.getName());
		PetType getResult3 = findPetTypesResult.get(2);
		assertEquals("dog", getResult3.getName());
		PetType getResult4 = findPetTypesResult.get(3);
		assertEquals("hamster", getResult4.getName());
		PetType getResult5 = findPetTypesResult.get(4);
		assertEquals("lizard", getResult5.getName());
		PetType getResult6 = findPetTypesResult.get(5);
		assertEquals("snake", getResult6.getName());
		assertEquals(1, getResult2.getId().intValue());
		assertEquals(2, getResult3.getId().intValue());
		assertEquals(3, getResult5.getId().intValue());
		assertEquals(4, getResult6.getId().intValue());
		assertEquals(5, getResult.getId().intValue());
		assertEquals(6, getResult4.getId().intValue());
		assertFalse(getResult.isNew());
		assertFalse(getResult2.isNew());
		assertFalse(getResult3.isNew());
		assertFalse(getResult4.isNew());
		assertFalse(getResult5.isNew());
		assertFalse(getResult6.isNew());
	}

	/**
	 * Test {@link OwnerRepository#findAll(Pageable)}.
	 *
	 * <p>
	 * Method under test: {@link OwnerRepository#findAll(Pageable)}
	 */
	@Test
	@DisplayName("Test findAll(Pageable)")
	@Tag("ContributionFromDiffblue")
	@ManagedByDiffblue
	@MethodsUnderTest({ "Page OwnerRepository.findAll(Pageable)" })
	void testFindAll() {
		// Arrange
		Owner owner = new Owner();
		owner.setAddress("42 Main St");
		owner.setCity("Oxford");
		owner.setFirstName("Jane");
		owner.setLastName("Doe");
		owner.setTelephone("6625550144");

		Owner owner2 = new Owner();
		owner2.setAddress("17 High St");
		owner2.setCity("London");
		owner2.setFirstName("John");
		owner2.setLastName("Smith");
		owner2.setTelephone("8605550118");
		ownerRepository.save(owner);
		ownerRepository.save(owner2);

		// Act
		Page<Owner> actualFindAllResult = ownerRepository.findAll(Pageable.unpaged());

		// Assert
		assertTrue(actualFindAllResult instanceof PageImpl);
		List<Owner> toListResult = actualFindAllResult.toList();
		assertEquals(12, toListResult.size());
		assertSame(owner, toListResult.get(10));
		assertSame(owner2, toListResult.get(11));
	}

}
