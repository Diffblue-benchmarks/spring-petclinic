package org.springframework.samples.petclinic.owner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class OwnerTests {

	@Mock
	private List<Pet> list;

	@InjectMocks
	private Owner owner;

	/**
	 * Test {@link Owner#addPet(Pet)}.
	 *
	 * <ul>
	 * <li>Given {@code null}.
	 * <li>When {@link Pet} (default constructor) Id is {@code null}.
	 * <li>Then {@link Owner} (default constructor) Pets size is one.
	 * </ul>
	 *
	 * <p>
	 * Method under test: {@link Owner#addPet(Pet)}
	 */
	@Test
	@DisplayName("Test addPet(Pet); given 'null'; when Pet (default constructor) Id is 'null'; then Owner (default constructor) Pets size is one")
	@Tag("ContributionFromDiffblue")
	@ManagedByDiffblue
	@MethodsUnderTest({ "void Owner.addPet(Pet)" })
	void testAddPet_givenNull_whenPetIdIsNull_thenOwnerPetsSizeIsOne() {
		// Arrange
		Owner owner = new Owner();

		PetType type = new PetType();
		type.setId(1);
		type.setName("Dog");

		Pet pet = new Pet();
		pet.setBirthDate(LocalDate.of(1970, 1, 1));
		pet.setId(null);
		pet.setName("Bella");
		pet.setType(type);

		// Act
		owner.addPet(pet);

		// Assert
		List<Pet> pets = owner.getPets();
		assertEquals(1, pets.size());
		assertSame(pet, pets.get(0));
	}

	/**
	 * Test {@link Owner#addPet(Pet)}.
	 *
	 * <ul>
	 * <li>Given one.
	 * <li>When {@link Pet} (default constructor) Id is one.
	 * <li>Then {@link Owner} (default constructor) Pets Empty.
	 * </ul>
	 *
	 * <p>
	 * Method under test: {@link Owner#addPet(Pet)}
	 */
	@Test
	@DisplayName("Test addPet(Pet); given one; when Pet (default constructor) Id is one; then Owner (default constructor) Pets Empty")
	@Tag("ContributionFromDiffblue")
	@ManagedByDiffblue
	@MethodsUnderTest({ "void Owner.addPet(Pet)" })
	void testAddPet_givenOne_whenPetIdIsOne_thenOwnerPetsEmpty() {
		// Arrange
		Owner owner = new Owner();

		PetType type = new PetType();
		type.setId(1);
		type.setName("Dog");

		Pet pet = new Pet();
		pet.setBirthDate(LocalDate.of(1970, 1, 1));
		pet.setId(1);
		pet.setName("Bella");
		pet.setType(type);

		// Act
		owner.addPet(pet);

		// Assert that nothing has changed
		assertTrue(owner.getPets().isEmpty());
	}

	/**
	 * Test {@link Owner#getPet(Integer)} with {@code id}.
	 *
	 * <ul>
	 * <li>Given {@link Owner} (default constructor).
	 * <li>Then return {@code null}.
	 * </ul>
	 *
	 * <p>
	 * Method under test: {@link Owner#getPet(Integer)}
	 */
	@Test
	@DisplayName("Test getPet(Integer) with 'id'; given Owner (default constructor); then return 'null'")
	@Tag("ContributionFromDiffblue")
	@ManagedByDiffblue
	@MethodsUnderTest({ "Pet Owner.getPet(Integer)" })
	void testGetPetWithId_givenOwner_thenReturnNull() {
		// Arrange, Act and Assert
		assertNull(new Owner().getPet(1));
	}

	/**
	 * Test {@link Owner#getPet(Integer)} with {@code id}.
	 *
	 * <ul>
	 * <li>Given {@link Pet} (default constructor) Id is {@code null}.
	 * <li>Then return {@code null}.
	 * </ul>
	 *
	 * <p>
	 * Method under test: {@link Owner#getPet(Integer)}
	 */
	@Test
	@DisplayName("Test getPet(Integer) with 'id'; given Pet (default constructor) Id is 'null'; then return 'null'")
	@Tag("ContributionFromDiffblue")
	@ManagedByDiffblue
	@MethodsUnderTest({ "Pet Owner.getPet(Integer)" })
	void testGetPetWithId_givenPetIdIsNull_thenReturnNull() {
		// Arrange
		PetType type = new PetType();
		type.setId(1);
		type.setName("Dog");

		Pet pet = new Pet();
		pet.setBirthDate(LocalDate.of(1970, 1, 1));
		pet.setId(null);
		pet.setName("Bella");
		pet.setType(type);

		Owner owner = new Owner();
		owner.addPet(pet);

		// Act and Assert
		assertNull(owner.getPet(1));
	}

	/**
	 * Test {@link Owner#getPet(Integer)} with {@code id}.
	 *
	 * <ul>
	 * <li>Given {@link Pet} (default constructor) Id is one.
	 * <li>Then return {@link Pet} (default constructor).
	 * </ul>
	 *
	 * <p>
	 * Method under test: {@link Owner#getPet(Integer)}
	 */
	@Test
	@DisplayName("Test getPet(Integer) with 'id'; given Pet (default constructor) Id is one; then return Pet (default constructor)")
	@Tag("ContributionFromDiffblue")
	@ManagedByDiffblue
	@MethodsUnderTest({ "Pet Owner.getPet(Integer)" })
	void testGetPetWithId_givenPetIdIsOne_thenReturnPet() {
		// Arrange
		PetType type = new PetType();
		type.setId(1);
		type.setName("Dog");

		Pet pet = new Pet();
		pet.setBirthDate(LocalDate.of(1970, 1, 1));
		pet.setId(1);
		pet.setName("Bella");
		pet.setType(type);

		ArrayList<Pet> petList = new ArrayList<>();
		petList.add(pet);
		when(list.iterator()).thenReturn(petList.iterator());

		// Act
		Pet actualPet = owner.getPet(1);

		// Assert
		verify(list).iterator();
		assertSame(pet, actualPet);
	}

	/**
	 * Test {@link Owner#getPet(Integer)} with {@code id}.
	 *
	 * <ul>
	 * <li>Given {@link PetType} (default constructor) Id is two.
	 * <li>Then return {@link Pet} (default constructor).
	 * </ul>
	 *
	 * <p>
	 * Method under test: {@link Owner#getPet(Integer)}
	 */
	@Test
	@DisplayName("Test getPet(Integer) with 'id'; given PetType (default constructor) Id is two; then return Pet (default constructor)")
	@Tag("ContributionFromDiffblue")
	@ManagedByDiffblue
	@MethodsUnderTest({ "Pet Owner.getPet(Integer)" })
	void testGetPetWithId_givenPetTypeIdIsTwo_thenReturnPet() {
		// Arrange
		PetType type = new PetType();
		type.setId(1);
		type.setName("Dog");

		Pet pet = new Pet();
		pet.setBirthDate(LocalDate.of(1970, 1, 1));
		pet.setId(1);
		pet.setName("Bella");
		pet.setType(type);

		PetType type2 = new PetType();
		type2.setId(2);
		type2.setName("Bella");

		Pet pet2 = new Pet();
		pet2.setBirthDate(LocalDate.of(1970, 1, 1));
		pet2.setId(2);
		pet2.setName("Name");
		pet2.setType(type2);

		ArrayList<Pet> petList = new ArrayList<>();
		petList.add(pet2);
		petList.add(pet);
		when(list.iterator()).thenReturn(petList.iterator());

		// Act
		Pet actualPet = owner.getPet(1);

		// Assert
		verify(list).iterator();
		assertSame(pet, actualPet);
	}

	/**
	 * Test {@link Owner#getPet(String, boolean)} with {@code name}, {@code ignoreNew}.
	 *
	 * <ul>
	 * <li>Given {@link Owner} (default constructor).
	 * <li>When {@code Bella}.
	 * <li>Then return {@code null}.
	 * </ul>
	 *
	 * <p>
	 * Method under test: {@link Owner#getPet(String, boolean)}
	 */
	@Test
	@DisplayName("Test getPet(String, boolean) with 'name', 'ignoreNew'; given Owner (default constructor); when 'Bella'; then return 'null'")
	@Tag("ContributionFromDiffblue")
	@ManagedByDiffblue
	@MethodsUnderTest({ "Pet Owner.getPet(String, boolean)" })
	void testGetPetWithNameIgnoreNew_givenOwner_whenBella_thenReturnNull() {
		// Arrange, Act and Assert
		assertNull(new Owner().getPet("Bella", true));
	}

	/**
	 * Test {@link Owner#getPet(String, boolean)} with {@code name}, {@code ignoreNew}.
	 *
	 * <ul>
	 * <li>Given {@link Pet} (default constructor) Id is {@code null}.
	 * <li>When {@code Bella}.
	 * <li>Then return {@code null}.
	 * </ul>
	 *
	 * <p>
	 * Method under test: {@link Owner#getPet(String, boolean)}
	 */
	@Test
	@DisplayName("Test getPet(String, boolean) with 'name', 'ignoreNew'; given Pet (default constructor) Id is 'null'; when 'Bella'; then return 'null'")
	@Tag("ContributionFromDiffblue")
	@ManagedByDiffblue
	@MethodsUnderTest({ "Pet Owner.getPet(String, boolean)" })
	void testGetPetWithNameIgnoreNew_givenPetIdIsNull_whenBella_thenReturnNull() {
		// Arrange
		PetType type = new PetType();
		type.setId(1);
		type.setName("Dog");

		Pet pet = new Pet();
		pet.setBirthDate(LocalDate.of(1970, 1, 1));
		pet.setId(null);
		pet.setName("Bella");
		pet.setType(type);

		Owner owner = new Owner();
		owner.addPet(pet);

		// Act and Assert
		assertNull(owner.getPet("Bella", true));
	}

	/**
	 * Test {@link Owner#getPet(String, boolean)} with {@code name}, {@code ignoreNew}.
	 *
	 * <ul>
	 * <li>Given {@link Pet} (default constructor) Id is {@code null}.
	 * <li>When {@code false}.
	 * <li>Then return {@link Pet} (default constructor).
	 * </ul>
	 *
	 * <p>
	 * Method under test: {@link Owner#getPet(String, boolean)}
	 */
	@Test
	@DisplayName("Test getPet(String, boolean) with 'name', 'ignoreNew'; given Pet (default constructor) Id is 'null'; when 'false'; then return Pet (default constructor)")
	@Tag("ContributionFromDiffblue")
	@ManagedByDiffblue
	@MethodsUnderTest({ "Pet Owner.getPet(String, boolean)" })
	void testGetPetWithNameIgnoreNew_givenPetIdIsNull_whenFalse_thenReturnPet() {
		// Arrange
		PetType type = new PetType();
		type.setId(1);
		type.setName("Dog");

		Pet pet = new Pet();
		pet.setBirthDate(LocalDate.of(1970, 1, 1));
		pet.setId(null);
		pet.setName("Bella");
		pet.setType(type);

		Owner owner = new Owner();
		owner.addPet(pet);

		// Act and Assert
		assertSame(pet, owner.getPet("Bella", false));
	}

	/**
	 * Test {@link Owner#getPet(String, boolean)} with {@code name}, {@code ignoreNew}.
	 *
	 * <ul>
	 * <li>Given {@link Pet} (default constructor) Id is one.
	 * <li>When {@code Bella}.
	 * <li>Then calls {@link List#iterator()}.
	 * </ul>
	 *
	 * <p>
	 * Method under test: {@link Owner#getPet(String, boolean)}
	 */
	@Test
	@DisplayName("Test getPet(String, boolean) with 'name', 'ignoreNew'; given Pet (default constructor) Id is one; when 'Bella'; then calls iterator()")
	@Tag("ContributionFromDiffblue")
	@ManagedByDiffblue
	@MethodsUnderTest({ "Pet Owner.getPet(String, boolean)" })
	void testGetPetWithNameIgnoreNew_givenPetIdIsOne_whenBella_thenCallsIterator() {
		// Arrange
		PetType type = new PetType();
		type.setId(1);
		type.setName("Dog");

		Pet pet = new Pet();
		pet.setBirthDate(LocalDate.of(1970, 1, 1));
		pet.setId(1);
		pet.setName("Bella");
		pet.setType(type);

		ArrayList<Pet> petList = new ArrayList<>();
		petList.add(pet);
		when(list.iterator()).thenReturn(petList.iterator());

		// Act
		Pet actualPet = owner.getPet("Bella", true);

		// Assert
		verify(list).iterator();
		assertSame(pet, actualPet);
	}

	/**
	 * Test {@link Owner#getPet(String, boolean)} with {@code name}, {@code ignoreNew}.
	 *
	 * <ul>
	 * <li>Given {@link Pet} (default constructor) Name is {@code Name}.
	 * <li>When {@code Bella}.
	 * <li>Then return {@code null}.
	 * </ul>
	 *
	 * <p>
	 * Method under test: {@link Owner#getPet(String, boolean)}
	 */
	@Test
	@DisplayName("Test getPet(String, boolean) with 'name', 'ignoreNew'; given Pet (default constructor) Name is 'Name'; when 'Bella'; then return 'null'")
	@Tag("ContributionFromDiffblue")
	@ManagedByDiffblue
	@MethodsUnderTest({ "Pet Owner.getPet(String, boolean)" })
	void testGetPetWithNameIgnoreNew_givenPetNameIsName_whenBella_thenReturnNull() {
		// Arrange
		PetType type = new PetType();
		type.setId(1);
		type.setName("Dog");

		Pet pet = new Pet();
		pet.setBirthDate(LocalDate.of(1970, 1, 1));
		pet.setId(null);
		pet.setName("Name");
		pet.setType(type);

		Owner owner = new Owner();
		owner.addPet(pet);

		// Act and Assert
		assertNull(owner.getPet("Bella", true));
	}

	/**
	 * Test {@link Owner#getPet(String, boolean)} with {@code name}, {@code ignoreNew}.
	 *
	 * <ul>
	 * <li>Given {@link Pet} (default constructor) Name is {@code null}.
	 * <li>When {@code Bella}.
	 * <li>Then return {@code null}.
	 * </ul>
	 *
	 * <p>
	 * Method under test: {@link Owner#getPet(String, boolean)}
	 */
	@Test
	@DisplayName("Test getPet(String, boolean) with 'name', 'ignoreNew'; given Pet (default constructor) Name is 'null'; when 'Bella'; then return 'null'")
	@Tag("ContributionFromDiffblue")
	@ManagedByDiffblue
	@MethodsUnderTest({ "Pet Owner.getPet(String, boolean)" })
	void testGetPetWithNameIgnoreNew_givenPetNameIsNull_whenBella_thenReturnNull() {
		// Arrange
		PetType type = new PetType();
		type.setId(1);
		type.setName("Dog");

		Pet pet = new Pet();
		pet.setBirthDate(LocalDate.of(1970, 1, 1));
		pet.setId(null);
		pet.setName(null);
		pet.setType(type);

		Owner owner = new Owner();
		owner.addPet(pet);

		// Act and Assert
		assertNull(owner.getPet("Bella", true));
	}

	/**
	 * Test {@link Owner#getPet(String)} with {@code name}.
	 *
	 * <ul>
	 * <li>Given {@link Owner} (default constructor).
	 * <li>When {@code Bella}.
	 * <li>Then return {@code null}.
	 * </ul>
	 *
	 * <p>
	 * Method under test: {@link Owner#getPet(String)}
	 */
	@Test
	@DisplayName("Test getPet(String) with 'name'; given Owner (default constructor); when 'Bella'; then return 'null'")
	@Tag("ContributionFromDiffblue")
	@ManagedByDiffblue
	@MethodsUnderTest({ "Pet Owner.getPet(String)" })
	void testGetPetWithName_givenOwner_whenBella_thenReturnNull() {
		// Arrange, Act and Assert
		assertNull(new Owner().getPet("Bella"));
	}

	/**
	 * Test {@link Owner#getPet(String)} with {@code name}.
	 *
	 * <ul>
	 * <li>Given {@link Pet} (default constructor) Name is {@code Bella}.
	 * <li>When {@code Bella}.
	 * <li>Then return {@link Pet} (default constructor).
	 * </ul>
	 *
	 * <p>
	 * Method under test: {@link Owner#getPet(String)}
	 */
	@Test
	@DisplayName("Test getPet(String) with 'name'; given Pet (default constructor) Name is 'Bella'; when 'Bella'; then return Pet (default constructor)")
	@Tag("ContributionFromDiffblue")
	@ManagedByDiffblue
	@MethodsUnderTest({ "Pet Owner.getPet(String)" })
	void testGetPetWithName_givenPetNameIsBella_whenBella_thenReturnPet() {
		// Arrange
		PetType type = new PetType();
		type.setId(1);
		type.setName("Dog");

		Pet pet = new Pet();
		pet.setBirthDate(LocalDate.of(1970, 1, 1));
		pet.setId(null);
		pet.setName("Bella");
		pet.setType(type);

		Owner owner = new Owner();
		owner.addPet(pet);

		// Act and Assert
		assertSame(pet, owner.getPet("Bella"));
	}

	/**
	 * Test {@link Owner#getPet(String)} with {@code name}.
	 *
	 * <ul>
	 * <li>Given {@link Pet} (default constructor) Name is {@code Name}.
	 * <li>When {@code Bella}.
	 * <li>Then return {@code null}.
	 * </ul>
	 *
	 * <p>
	 * Method under test: {@link Owner#getPet(String)}
	 */
	@Test
	@DisplayName("Test getPet(String) with 'name'; given Pet (default constructor) Name is 'Name'; when 'Bella'; then return 'null'")
	@Tag("ContributionFromDiffblue")
	@ManagedByDiffblue
	@MethodsUnderTest({ "Pet Owner.getPet(String)" })
	void testGetPetWithName_givenPetNameIsName_whenBella_thenReturnNull() {
		// Arrange
		PetType type = new PetType();
		type.setId(1);
		type.setName("Dog");

		Pet pet = new Pet();
		pet.setBirthDate(LocalDate.of(1970, 1, 1));
		pet.setId(null);
		pet.setName("Name");
		pet.setType(type);

		Owner owner = new Owner();
		owner.addPet(pet);

		// Act and Assert
		assertNull(owner.getPet("Bella"));
	}

	/**
	 * Test {@link Owner#getPet(String)} with {@code name}.
	 *
	 * <ul>
	 * <li>Given {@link Pet} (default constructor) Name is {@code null}.
	 * <li>When {@code Bella}.
	 * <li>Then return {@code null}.
	 * </ul>
	 *
	 * <p>
	 * Method under test: {@link Owner#getPet(String)}
	 */
	@Test
	@DisplayName("Test getPet(String) with 'name'; given Pet (default constructor) Name is 'null'; when 'Bella'; then return 'null'")
	@Tag("ContributionFromDiffblue")
	@ManagedByDiffblue
	@MethodsUnderTest({ "Pet Owner.getPet(String)" })
	void testGetPetWithName_givenPetNameIsNull_whenBella_thenReturnNull() {
		// Arrange
		PetType type = new PetType();
		type.setId(1);
		type.setName("Dog");

		Pet pet = new Pet();
		pet.setBirthDate(LocalDate.of(1970, 1, 1));
		pet.setId(null);
		pet.setName(null);
		pet.setType(type);

		Owner owner = new Owner();
		owner.addPet(pet);

		// Act and Assert
		assertNull(owner.getPet("Bella"));
	}

	/**
	 * Test {@link Owner#addVisit(Integer, Visit)}.
	 *
	 * <ul>
	 * <li>Given {@link Pet} (default constructor) Id is one.
	 * <li>When one.
	 * <li>Then calls {@link List#iterator()}.
	 * </ul>
	 *
	 * <p>
	 * Method under test: {@link Owner#addVisit(Integer, Visit)}
	 */
	@Test
	@DisplayName("Test addVisit(Integer, Visit); given Pet (default constructor) Id is one; when one; then calls iterator()")
	@Tag("ContributionFromDiffblue")
	@ManagedByDiffblue
	@MethodsUnderTest({ "void Owner.addVisit(Integer, Visit)" })
	void testAddVisit_givenPetIdIsOne_whenOne_thenCallsIterator() {
		// Arrange
		PetType type = new PetType();
		type.setId(1);
		type.setName("Dog");

		Pet pet = new Pet();
		pet.setBirthDate(LocalDate.of(1970, 1, 1));
		pet.setId(1);
		pet.setName("Bella");
		pet.setType(type);

		ArrayList<Pet> petList = new ArrayList<>();
		petList.add(pet);
		when(list.iterator()).thenReturn(petList.iterator());

		Visit visit = new Visit();
		visit.setDate(LocalDate.of(1970, 1, 1));
		visit.setDescription("The characteristics of someone or something");
		visit.setId(1);

		// Act
		owner.addVisit(1, visit);

		// Assert
		verify(list).iterator();
	}

	/**
	 * Test {@link Owner#addVisit(Integer, Visit)}.
	 *
	 * <ul>
	 * <li>Given {@link Pet} (default constructor) Id is two.
	 * <li>When one.
	 * <li>Then calls {@link List#iterator()}.
	 * </ul>
	 *
	 * <p>
	 * Method under test: {@link Owner#addVisit(Integer, Visit)}
	 */
	@Test
	@DisplayName("Test addVisit(Integer, Visit); given Pet (default constructor) Id is two; when one; then calls iterator()")
	@Tag("ContributionFromDiffblue")
	@ManagedByDiffblue
	@MethodsUnderTest({ "void Owner.addVisit(Integer, Visit)" })
	void testAddVisit_givenPetIdIsTwo_whenOne_thenCallsIterator() {
		// Arrange
		PetType type = new PetType();
		type.setId(1);
		type.setName("Dog");

		Pet pet = new Pet();
		pet.setBirthDate(LocalDate.of(1970, 1, 1));
		pet.setId(1);
		pet.setName("Bella");
		pet.setType(type);

		PetType type2 = new PetType();
		type2.setId(2);
		type2.setName("Bella");

		Pet pet2 = new Pet();
		pet2.setBirthDate(LocalDate.of(1970, 1, 1));
		pet2.setId(2);
		pet2.setName("Pet identifier must not be null!");
		pet2.setType(type2);

		ArrayList<Pet> petList = new ArrayList<>();
		petList.add(pet2);
		petList.add(pet);
		when(list.iterator()).thenReturn(petList.iterator());

		Visit visit = new Visit();
		visit.setDate(LocalDate.of(1970, 1, 1));
		visit.setDescription("The characteristics of someone or something");
		visit.setId(1);

		// Act
		owner.addVisit(1, visit);

		// Assert
		verify(list).iterator();
	}

	/**
	 * Test {@link Owner#addVisit(Integer, Visit)}.
	 *
	 * <ul>
	 * <li>Given {@link PetType} (default constructor) Id is two.
	 * <li>When one.
	 * <li>Then calls {@link List#iterator()}.
	 * </ul>
	 *
	 * <p>
	 * Method under test: {@link Owner#addVisit(Integer, Visit)}
	 */
	@Test
	@DisplayName("Test addVisit(Integer, Visit); given PetType (default constructor) Id is two; when one; then calls iterator()")
	@Tag("ContributionFromDiffblue")
	@ManagedByDiffblue
	@MethodsUnderTest({ "void Owner.addVisit(Integer, Visit)" })
	void testAddVisit_givenPetTypeIdIsTwo_whenOne_thenCallsIterator() {
		// Arrange
		PetType type = new PetType();
		type.setId(1);
		type.setName("Dog");

		Pet pet = new Pet();
		pet.setBirthDate(LocalDate.of(1970, 1, 1));
		pet.setId(1);
		pet.setName("Bella");
		pet.setType(type);

		PetType type2 = new PetType();
		type2.setId(2);
		type2.setName("Bella");

		Pet pet2 = new Pet();
		pet2.setBirthDate(LocalDate.of(1970, 1, 1));
		pet2.setId(null);
		pet2.setName("Pet identifier must not be null!");
		pet2.setType(type2);

		ArrayList<Pet> petList = new ArrayList<>();
		petList.add(pet2);
		petList.add(pet);
		when(list.iterator()).thenReturn(petList.iterator());

		Visit visit = new Visit();
		visit.setDate(LocalDate.of(1970, 1, 1));
		visit.setDescription("The characteristics of someone or something");
		visit.setId(1);

		// Act
		owner.addVisit(1, visit);

		// Assert
		verify(list).iterator();
	}

	/**
	 * Test getters and setters.
	 *
	 * <p>
	 * Methods under test:
	 *
	 * <ul>
	 * <li>default or parameterless constructor of {@link Owner}
	 * <li>{@link Owner#setAddress(String)}
	 * <li>{@link Owner#setCity(String)}
	 * <li>{@link Owner#setTelephone(String)}
	 * <li>{@link Owner#toString()}
	 * <li>{@link Owner#getAddress()}
	 * <li>{@link Owner#getCity()}
	 * <li>{@link Owner#getPets()}
	 * <li>{@link Owner#getTelephone()}
	 * </ul>
	 */
	@Test
	@DisplayName("Test getters and setters")
	@Tag("ContributionFromDiffblue")
	@ManagedByDiffblue
	@MethodsUnderTest({ "void Owner.<init>()", "String Owner.getAddress()", "String Owner.getCity()",
			"List Owner.getPets()", "String Owner.getTelephone()", "void Owner.setAddress(String)",
			"void Owner.setCity(String)", "void Owner.setTelephone(String)", "String Owner.toString()" })
	void testGettersAndSetters() {
		// Arrange and Act
		Owner actualOwner = new Owner();
		actualOwner.setAddress("42 Main St");
		actualOwner.setCity("Oxford");
		actualOwner.setTelephone("6625550144");
		actualOwner.toString();
		String actualAddress = actualOwner.getAddress();
		String actualCity = actualOwner.getCity();
		List<Pet> actualPets = actualOwner.getPets();

		// Assert
		assertEquals("42 Main St", actualAddress);
		assertEquals("6625550144", actualOwner.getTelephone());
		assertEquals("Oxford", actualCity);
		assertNull(actualOwner.getId());
		assertNull(actualOwner.getFirstName());
		assertNull(actualOwner.getLastName());
		assertTrue(actualPets.isEmpty());
	}

}
