package org.springframework.samples.petclinic.owner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;

@ContextConfiguration(classes = { PetController.class })
@DisabledInAotMode
@ExtendWith(SpringExtension.class)
class PetControllerDiffblueTest {

	@MockBean
	private OwnerRepository ownerRepository;

	@Autowired
	private PetController petController;

	/**
   * Test {@link PetController#populatePetTypes()}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link PetController#populatePetTypes()}
   */
  @Test
  @DisplayName("Test populatePetTypes(); then throw IllegalArgumentException")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Collection PetController.populatePetTypes()"})
  void testPopulatePetTypes_thenThrowIllegalArgumentException() {
    // Arrange
    when(ownerRepository.findPetTypes()).thenThrow(new IllegalArgumentException());

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> petController.populatePetTypes());
    verify(ownerRepository).findPetTypes();
  }

	/**
	 * Test {@link PetController#findOwner(int)}.
	 *
	 * <ul>
	 * <li>Given {@link Owner} (default constructor) Address is {@code 42 Main St}.
	 * <li>Then return {@link Owner} (default constructor).
	 * </ul>
	 *
	 * <p>
	 * Method under test: {@link PetController#findOwner(int)}
	 */
	@Test
	@DisplayName("Test findOwner(int); given Owner (default constructor) Address is '42 Main St'; then return Owner (default constructor)")
	@Tag("ContributionFromDiffblue")
	@ManagedByDiffblue
	@MethodsUnderTest({ "Owner PetController.findOwner(int)" })
	void testFindOwner_givenOwnerAddressIs42MainSt_thenReturnOwner() {
		// Arrange
		Owner owner = new Owner();
		owner.setAddress("42 Main St");
		owner.setCity("Oxford");
		owner.setFirstName("Jane");
		owner.setId(1);
		owner.setLastName("Doe");
		owner.setTelephone("6625550144");
		when(ownerRepository.findById(Mockito.<Integer>any())).thenReturn(owner);

		// Act
		Owner actualFindOwnerResult = petController.findOwner(1);

		// Assert
		verify(ownerRepository).findById(1);
		assertSame(owner, actualFindOwnerResult);
	}

	/**
   * Test {@link PetController#findOwner(int)}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link PetController#findOwner(int)}
   */
  @Test
  @DisplayName("Test findOwner(int); then throw IllegalArgumentException")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Owner PetController.findOwner(int)"})
  void testFindOwner_thenThrowIllegalArgumentException() {
    // Arrange
    when(ownerRepository.findById(Mockito.<Integer>any()))
        .thenThrow(new IllegalArgumentException());

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> petController.findOwner(1));
    verify(ownerRepository).findById(1);
  }

	/**
	 * Test {@link PetController#findPet(int, Integer)}.
	 *
	 * <ul>
	 * <li>Given {@link Owner} (default constructor) Address is {@code 42 Main St}.
	 * <li>When {@code null}.
	 * <li>Then Visits return {@link Set}.
	 * </ul>
	 *
	 * <p>
	 * Method under test: {@link PetController#findPet(int, Integer)}
	 */
	@Test
	@DisplayName("Test findPet(int, Integer); given Owner (default constructor) Address is '42 Main St'; when 'null'; then Visits return Set")
	@Tag("ContributionFromDiffblue")
	@ManagedByDiffblue
	@MethodsUnderTest({ "Pet PetController.findPet(int, Integer)" })
	void testFindPet_givenOwnerAddressIs42MainSt_whenNull_thenVisitsReturnSet() {
		// Arrange
		Owner owner = new Owner();
		owner.setAddress("42 Main St");
		owner.setCity("Oxford");
		owner.setFirstName("Jane");
		owner.setId(1);
		owner.setLastName("Doe");
		owner.setTelephone("6625550144");
		when(ownerRepository.findById(Mockito.<Integer>any())).thenReturn(owner);

		// Act
		Pet actualFindPetResult = petController.findPet(1, null);

		// Assert
		verify(ownerRepository).findById(1);
		Collection<Visit> visits = actualFindPetResult.getVisits();
		assertTrue(visits instanceof Set);
		assertNull(actualFindPetResult.getId());
		assertNull(actualFindPetResult.getName());
		assertNull(actualFindPetResult.getBirthDate());
		assertNull(actualFindPetResult.getType());
		assertTrue(visits.isEmpty());
		assertTrue(actualFindPetResult.isNew());
	}

	/**
	 * Test {@link PetController#findPet(int, Integer)}.
	 *
	 * <ul>
	 * <li>Given {@link Owner} (default constructor) Address is {@code 42 Main St}.
	 * <li>When one.
	 * <li>Then return {@code null}.
	 * </ul>
	 *
	 * <p>
	 * Method under test: {@link PetController#findPet(int, Integer)}
	 */
	@Test
	@DisplayName("Test findPet(int, Integer); given Owner (default constructor) Address is '42 Main St'; when one; then return 'null'")
	@Tag("ContributionFromDiffblue")
	@ManagedByDiffblue
	@MethodsUnderTest({ "Pet PetController.findPet(int, Integer)" })
	void testFindPet_givenOwnerAddressIs42MainSt_whenOne_thenReturnNull() {
		// Arrange
		Owner owner = new Owner();
		owner.setAddress("42 Main St");
		owner.setCity("Oxford");
		owner.setFirstName("Jane");
		owner.setId(1);
		owner.setLastName("Doe");
		owner.setTelephone("6625550144");
		when(ownerRepository.findById(Mockito.<Integer>any())).thenReturn(owner);

		// Act
		Pet actualFindPetResult = petController.findPet(1, 1);

		// Assert
		verify(ownerRepository).findById(1);
		assertNull(actualFindPetResult);
	}

	/**
   * Test {@link PetController#findPet(int, Integer)}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link PetController#findPet(int, Integer)}
   */
  @Test
  @DisplayName("Test findPet(int, Integer); then throw IllegalArgumentException")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Pet PetController.findPet(int, Integer)"})
  void testFindPet_thenThrowIllegalArgumentException() {
    // Arrange
    when(ownerRepository.findById(Mockito.<Integer>any()))
        .thenThrow(new IllegalArgumentException());

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> petController.findPet(1, 1));
    verify(ownerRepository).findById(1);
  }

	/**
	 * Test {@link PetController#initCreationForm(Owner, ModelMap)}.
	 *
	 * <p>
	 * Method under test: {@link PetController#initCreationForm(Owner, ModelMap)}
	 */
	@Test
	@DisplayName("Test initCreationForm(Owner, ModelMap)")
	@Tag("ContributionFromDiffblue")
	@ManagedByDiffblue
	@MethodsUnderTest({ "String PetController.initCreationForm(Owner, ModelMap)" })
	void testInitCreationForm() throws Exception {
		// Arrange
		Owner owner = new Owner();
		owner.setAddress("42 Main St");
		owner.setCity("Oxford");
		owner.setFirstName("Jane");
		owner.setId(1);
		owner.setLastName("Doe");
		owner.setTelephone("6625550144");
		when(ownerRepository.findPetTypes()).thenReturn(new ArrayList<>());
		when(ownerRepository.findById(Mockito.<Integer>any())).thenReturn(owner);

		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/owners/{ownerId}/pets/new", 1);

		// Act and Assert
		MockMvcBuilders.standaloneSetup(petController)
			.build()
			.perform(requestBuilder)
			.andExpect(status().isOk())
			.andExpect(model().size(3))
			.andExpect(model().attributeExists("owner", "pet", "types"))
			.andExpect(view().name("pets/createOrUpdatePetForm"))
			.andExpect(forwardedUrl("pets/createOrUpdatePetForm"));
	}

	/**
	 * Test
	 * {@link PetController#processCreationForm(Owner, Pet, BindingResult, ModelMap)}.
	 *
	 * <ul>
	 * <li>When {@code 2020-03-01}.
	 * </ul>
	 *
	 * <p>
	 * Method under test:
	 * {@link PetController#processCreationForm(Owner, Pet, BindingResult, ModelMap)}
	 */
	@Test
	@DisplayName("Test processCreationForm(Owner, Pet, BindingResult, ModelMap); when '2020-03-01'")
	@Tag("ContributionFromDiffblue")
	@ManagedByDiffblue
	@MethodsUnderTest({ "String PetController.processCreationForm(Owner, Pet, BindingResult, ModelMap)" })
	void testProcessCreationForm_when20200301() throws Exception {
		// Arrange
		Owner owner = new Owner();
		owner.setAddress("42 Main St");
		owner.setCity("Oxford");
		owner.setFirstName("Jane");
		owner.setId(1);
		owner.setLastName("Doe");
		owner.setTelephone("6625550144");
		when(ownerRepository.findPetTypes()).thenReturn(new ArrayList<>());
		when(ownerRepository.findById(Mockito.<Integer>any())).thenReturn(owner);

		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/owners/{ownerId}/pets/new", 1)
			.param("name", "not blank")
			.param("birthDate", "2020-03-01");

		// Act and Assert
		MockMvcBuilders.standaloneSetup(petController)
			.build()
			.perform(requestBuilder)
			.andExpect(status().isOk())
			.andExpect(model().size(3))
			.andExpect(model().attributeExists("owner", "pet", "types"))
			.andExpect(view().name("pets/createOrUpdatePetForm"))
			.andExpect(forwardedUrl("pets/createOrUpdatePetForm"));
	}

	/**
	 * Test
	 * {@link PetController#processCreationForm(Owner, Pet, BindingResult, ModelMap)}.
	 *
	 * <ul>
	 * <li>When empty string.
	 * </ul>
	 *
	 * <p>
	 * Method under test:
	 * {@link PetController#processCreationForm(Owner, Pet, BindingResult, ModelMap)}
	 */
	@Test
	@DisplayName("Test processCreationForm(Owner, Pet, BindingResult, ModelMap); when empty string")
	@Tag("ContributionFromDiffblue")
	@ManagedByDiffblue
	@MethodsUnderTest({ "String PetController.processCreationForm(Owner, Pet, BindingResult, ModelMap)" })
	void testProcessCreationForm_whenEmptyString() throws Exception {
		// Arrange
		Owner owner = new Owner();
		owner.setAddress("42 Main St");
		owner.setCity("Oxford");
		owner.setFirstName("Jane");
		owner.setId(1);
		owner.setLastName("Doe");
		owner.setTelephone("6625550144");
		when(ownerRepository.findPetTypes()).thenReturn(new ArrayList<>());
		when(ownerRepository.findById(Mockito.<Integer>any())).thenReturn(owner);

		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/owners/{ownerId}/pets/new", 1)
			.param("name", "");

		// Act and Assert
		MockMvcBuilders.standaloneSetup(petController)
			.build()
			.perform(requestBuilder)
			.andExpect(status().isOk())
			.andExpect(model().size(3))
			.andExpect(model().attributeExists("owner", "pet", "types"))
			.andExpect(view().name("pets/createOrUpdatePetForm"))
			.andExpect(forwardedUrl("pets/createOrUpdatePetForm"));
	}

	/**
	 * Test
	 * {@link PetController#processCreationForm(Owner, Pet, BindingResult, ModelMap)}.
	 *
	 * <ul>
	 * <li>When {@code not blank}.
	 * </ul>
	 *
	 * <p>
	 * Method under test:
	 * {@link PetController#processCreationForm(Owner, Pet, BindingResult, ModelMap)}
	 */
	@Test
	@DisplayName("Test processCreationForm(Owner, Pet, BindingResult, ModelMap); when 'not blank'")
	@Tag("ContributionFromDiffblue")
	@ManagedByDiffblue
	@MethodsUnderTest({ "String PetController.processCreationForm(Owner, Pet, BindingResult, ModelMap)" })
	void testProcessCreationForm_whenNotBlank() throws Exception {
		// Arrange
		Owner owner = new Owner();
		owner.setAddress("42 Main St");
		owner.setCity("Oxford");
		owner.setFirstName("Jane");
		owner.setId(1);
		owner.setLastName("Doe");
		owner.setTelephone("6625550144");
		when(ownerRepository.findPetTypes()).thenReturn(new ArrayList<>());
		when(ownerRepository.findById(Mockito.<Integer>any())).thenReturn(owner);

		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/owners/{ownerId}/pets/new", 1)
			.param("name", "not blank");

		// Act and Assert
		MockMvcBuilders.standaloneSetup(petController)
			.build()
			.perform(requestBuilder)
			.andExpect(status().isOk())
			.andExpect(model().size(3))
			.andExpect(model().attributeExists("owner", "pet", "types"))
			.andExpect(view().name("pets/createOrUpdatePetForm"))
			.andExpect(forwardedUrl("pets/createOrUpdatePetForm"));
	}

	/**
	 * Test {@link PetController#initUpdateForm(Owner, int, ModelMap)}.
	 *
	 * <p>
	 * Method under test: {@link PetController#initUpdateForm(Owner, int, ModelMap)}
	 */
	@Test
	@DisplayName("Test initUpdateForm(Owner, int, ModelMap)")
	@Tag("ContributionFromDiffblue")
	@ManagedByDiffblue
	@MethodsUnderTest({ "String PetController.initUpdateForm(Owner, int, ModelMap)" })
	void testInitUpdateForm() throws Exception {
		// Arrange
		Owner owner = new Owner();
		owner.setAddress("42 Main St");
		owner.setCity("Oxford");
		owner.setFirstName("Jane");
		owner.setId(1);
		owner.setLastName("Doe");
		owner.setTelephone("6625550144");
		when(ownerRepository.findPetTypes()).thenReturn(new ArrayList<>());
		when(ownerRepository.findById(Mockito.<Integer>any())).thenReturn(owner);

		// Act and Assert
		MockMvcBuilders.standaloneSetup(petController)
			.build()
			.perform(MockMvcRequestBuilders.get("/owners/{ownerId}/pets/{petId}/edit", 1, 1))
			.andExpect(status().isOk())
			.andExpect(model().size(3))
			.andExpect(model().attributeExists("owner", "types"))
			.andExpect(view().name("pets/createOrUpdatePetForm"))
			.andExpect(forwardedUrl("pets/createOrUpdatePetForm"));
	}

	/**
	 * Test {@link PetController#processUpdateForm(Pet, BindingResult, Owner, ModelMap)}.
	 *
	 * <ul>
	 * <li>Given {@code null}.
	 * <li>When {@link Pet} (default constructor) BirthDate is {@code null}.
	 * </ul>
	 *
	 * <p>
	 * Method under test:
	 * {@link PetController#processUpdateForm(Pet, BindingResult, Owner, ModelMap)}
	 */
	@Test
	@DisplayName("Test processUpdateForm(Pet, BindingResult, Owner, ModelMap); given 'null'; when Pet (default constructor) BirthDate is 'null'")
	@Tag("ContributionFromDiffblue")
	@ManagedByDiffblue
	@MethodsUnderTest({ "String PetController.processUpdateForm(Pet, BindingResult, Owner, ModelMap)" })
	void testProcessUpdateForm_givenNull_whenPetBirthDateIsNull() {
		// Diffblue Cover was unable to create a Spring-specific test for this Spring
		// method.
		// Run dcover create --keep-partial-tests to gain insights into why
		// a non-Spring test was created.

		// Arrange
		OwnerRepository owners = mock(OwnerRepository.class);
		doNothing().when(owners).save(Mockito.<Owner>any());
		PetController petController = new PetController(owners);

		PetType type = new PetType();
		type.setId(1);
		type.setName("Dog");

		Pet pet = new Pet();
		pet.setBirthDate(null);
		pet.setId(1);
		pet.setName("Bella");
		pet.setType(type);
		BindException result = new BindException("Target", "Object Name");

		Owner owner = new Owner();
		owner.setAddress("42 Main St");
		owner.setCity("Oxford");
		owner.setFirstName("Jane");
		owner.setId(1);
		owner.setLastName("Doe");
		owner.setTelephone("6625550144");
		ModelMap model = new ModelMap();

		// Act
		String actualProcessUpdateFormResult = petController.processUpdateForm(pet, result, owner, model);

		// Assert
		verify(owners).save(isA(Owner.class));
		assertEquals("redirect:/owners/{ownerId}", actualProcessUpdateFormResult);
		assertTrue(model.isEmpty());
	}

	/**
	 * Test {@link PetController#processUpdateForm(Pet, BindingResult, Owner, ModelMap)}.
	 *
	 * <ul>
	 * <li>Given {@code null}.
	 * <li>When {@link Pet} (default constructor) Name is {@code null}.
	 * </ul>
	 *
	 * <p>
	 * Method under test:
	 * {@link PetController#processUpdateForm(Pet, BindingResult, Owner, ModelMap)}
	 */
	@Test
	@DisplayName("Test processUpdateForm(Pet, BindingResult, Owner, ModelMap); given 'null'; when Pet (default constructor) Name is 'null'")
	@Tag("ContributionFromDiffblue")
	@ManagedByDiffblue
	@MethodsUnderTest({ "String PetController.processUpdateForm(Pet, BindingResult, Owner, ModelMap)" })
	void testProcessUpdateForm_givenNull_whenPetNameIsNull() {
		// Diffblue Cover was unable to create a Spring-specific test for this Spring
		// method.
		// Run dcover create --keep-partial-tests to gain insights into why
		// a non-Spring test was created.

		// Arrange
		OwnerRepository owners = mock(OwnerRepository.class);
		doNothing().when(owners).save(Mockito.<Owner>any());
		PetController petController = new PetController(owners);

		PetType type = new PetType();
		type.setId(1);
		type.setName("Dog");

		Pet pet = new Pet();
		pet.setBirthDate(LocalDate.of(1970, 1, 1));
		pet.setId(1);
		pet.setName(null);
		pet.setType(type);
		BindException result = new BindException("Target", "Object Name");

		Owner owner = new Owner();
		owner.setAddress("42 Main St");
		owner.setCity("Oxford");
		owner.setFirstName("Jane");
		owner.setId(1);
		owner.setLastName("Doe");
		owner.setTelephone("6625550144");
		ModelMap model = new ModelMap();

		// Act
		String actualProcessUpdateFormResult = petController.processUpdateForm(pet, result, owner, model);

		// Assert
		verify(owners).save(isA(Owner.class));
		assertEquals("redirect:/owners/{ownerId}", actualProcessUpdateFormResult);
		assertTrue(model.isEmpty());
	}

	/**
	 * Test {@link PetController#processUpdateForm(Pet, BindingResult, Owner, ModelMap)}.
	 *
	 * <ul>
	 * <li>Given {@code true}.
	 * <li>Then return {@code pets/createOrUpdatePetForm}.
	 * </ul>
	 *
	 * <p>
	 * Method under test:
	 * {@link PetController#processUpdateForm(Pet, BindingResult, Owner, ModelMap)}
	 */
	@Test
	@DisplayName("Test processUpdateForm(Pet, BindingResult, Owner, ModelMap); given 'true'; then return 'pets/createOrUpdatePetForm'")
	@Tag("ContributionFromDiffblue")
	@ManagedByDiffblue
	@MethodsUnderTest({ "String PetController.processUpdateForm(Pet, BindingResult, Owner, ModelMap)" })
	void testProcessUpdateForm_givenTrue_thenReturnPetsCreateOrUpdatePetForm() {
		// Diffblue Cover was unable to create a Spring-specific test for this Spring
		// method.
		// Run dcover create --keep-partial-tests to gain insights into why
		// a non-Spring test was created.

		// Arrange
		PetController petController = new PetController(mock(OwnerRepository.class));

		PetType type = new PetType();
		type.setId(1);
		type.setName("Dog");

		Pet pet = new Pet();
		pet.setBirthDate(LocalDate.of(1970, 1, 1));
		pet.setId(1);
		pet.setName("Bella");
		pet.setType(type);

		BeanPropertyBindingResult result = mock(BeanPropertyBindingResult.class);
		when(result.hasErrors()).thenReturn(true);

		Owner owner = new Owner();
		owner.setAddress("42 Main St");
		owner.setCity("Oxford");
		owner.setFirstName("Jane");
		owner.setId(1);
		owner.setLastName("Doe");
		owner.setTelephone("6625550144");
		ModelMap model = new ModelMap();

		// Act
		String actualProcessUpdateFormResult = petController.processUpdateForm(pet, result, owner, model);

		// Assert
		verify(result).hasErrors();
		assertEquals("pets/createOrUpdatePetForm", actualProcessUpdateFormResult);
		assertEquals(1, model.size());
		assertSame(pet, model.get("pet"));
	}

	/**
	 * Test {@link PetController#processUpdateForm(Pet, BindingResult, Owner, ModelMap)}.
	 *
	 * <ul>
	 * <li>Then return {@code redirect:/owners/{ownerId}}.
	 * </ul>
	 *
	 * <p>
	 * Method under test:
	 * {@link PetController#processUpdateForm(Pet, BindingResult, Owner, ModelMap)}
	 */
	@Test
	@DisplayName("Test processUpdateForm(Pet, BindingResult, Owner, ModelMap); then return 'redirect:/owners/{ownerId}'")
	@Tag("ContributionFromDiffblue")
	@ManagedByDiffblue
	@MethodsUnderTest({ "String PetController.processUpdateForm(Pet, BindingResult, Owner, ModelMap)" })
	void testProcessUpdateForm_thenReturnRedirectOwnersOwnerId() {
		// Diffblue Cover was unable to create a Spring-specific test for this Spring
		// method.
		// Run dcover create --keep-partial-tests to gain insights into why
		// a non-Spring test was created.

		// Arrange
		OwnerRepository owners = mock(OwnerRepository.class);
		doNothing().when(owners).save(Mockito.<Owner>any());
		PetController petController = new PetController(owners);

		PetType type = new PetType();
		type.setId(1);
		type.setName("Dog");

		Pet pet = new Pet();
		pet.setBirthDate(LocalDate.of(1970, 1, 1));
		pet.setId(1);
		pet.setName("Bella");
		pet.setType(type);
		BindException result = new BindException("Target", "Object Name");

		Owner owner = new Owner();
		owner.setAddress("42 Main St");
		owner.setCity("Oxford");
		owner.setFirstName("Jane");
		owner.setId(1);
		owner.setLastName("Doe");
		owner.setTelephone("6625550144");
		ModelMap model = new ModelMap();

		// Act
		String actualProcessUpdateFormResult = petController.processUpdateForm(pet, result, owner, model);

		// Assert
		verify(owners).save(isA(Owner.class));
		assertEquals("redirect:/owners/{ownerId}", actualProcessUpdateFormResult);
		assertTrue(model.isEmpty());
	}

	/**
	 * Test {@link PetController#processUpdateForm(Pet, BindingResult, Owner, ModelMap)}.
	 *
	 * <ul>
	 * <li>Then throw {@link IllegalArgumentException}.
	 * </ul>
	 *
	 * <p>
	 * Method under test:
	 * {@link PetController#processUpdateForm(Pet, BindingResult, Owner, ModelMap)}
	 */
	@Test
	@DisplayName("Test processUpdateForm(Pet, BindingResult, Owner, ModelMap); then throw IllegalArgumentException")
	@Tag("ContributionFromDiffblue")
	@ManagedByDiffblue
	@MethodsUnderTest({ "String PetController.processUpdateForm(Pet, BindingResult, Owner, ModelMap)" })
	void testProcessUpdateForm_thenThrowIllegalArgumentException() {
		// Diffblue Cover was unable to create a Spring-specific test for this Spring
		// method.
		// Run dcover create --keep-partial-tests to gain insights into why
		// a non-Spring test was created.

		// Arrange
		OwnerRepository owners = mock(OwnerRepository.class);
		doThrow(new IllegalArgumentException()).when(owners).save(Mockito.<Owner>any());
		PetController petController = new PetController(owners);

		PetType type = new PetType();
		type.setId(1);
		type.setName("Dog");

		Pet pet = new Pet();
		pet.setBirthDate(LocalDate.of(1970, 1, 1));
		pet.setId(1);
		pet.setName("Bella");
		pet.setType(type);
		BindException result = new BindException("Target", "Object Name");

		Owner owner = new Owner();
		owner.setAddress("42 Main St");
		owner.setCity("Oxford");
		owner.setFirstName("Jane");
		owner.setId(1);
		owner.setLastName("Doe");
		owner.setTelephone("6625550144");

		// Act and Assert
		assertThrows(IllegalArgumentException.class,
				() -> petController.processUpdateForm(pet, result, owner, new ModelMap()));
		verify(owners).save(isA(Owner.class));
	}

}
