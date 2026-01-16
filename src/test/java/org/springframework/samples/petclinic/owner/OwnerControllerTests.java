/*
 * Copyright 2012-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.owner;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.assertj.core.util.Lists;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledInNativeImage;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.result.StatusResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

/**
 * Test class for {@link OwnerController}
 *
 * @author Colin But
 */
@ContextConfiguration(classes = { OwnerController.class })
@ExtendWith(SpringExtension.class)
@WebMvcTest(OwnerController.class)
@DisabledInNativeImage
@DisabledInAotMode
class OwnerControllerTests {

	private static final int TEST_OWNER_ID = 1;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private OwnerController ownerController;

	@MockBean
	private OwnerRepository owners;

	private Owner george() {
		Owner george = new Owner();
		george.setId(TEST_OWNER_ID);
		george.setFirstName("George");
		george.setLastName("Franklin");
		george.setAddress("110 W. Liberty St.");
		george.setCity("Madison");
		george.setTelephone("6085551023");
		Pet max = new Pet();
		PetType dog = new PetType();
		dog.setName("dog");
		max.setType(dog);
		max.setName("Max");
		max.setBirthDate(LocalDate.now());
		george.addPet(max);
		max.setId(1);
		return george;
	}

	@BeforeEach
	void setup() {
		Owner george = george();
		given(this.owners.findByLastName(eq("Franklin"), any(Pageable.class)))
			.willReturn(new PageImpl<Owner>(Lists.newArrayList(george)));
		given(this.owners.findAll(any(Pageable.class))).willReturn(new PageImpl<Owner>(Lists.newArrayList(george)));
		given(this.owners.findById(TEST_OWNER_ID)).willReturn(george);
		Visit visit = new Visit();
		visit.setDate(LocalDate.now());
		george.getPet("Max").getVisits().add(visit);
	}

	/**
	 * Test {@link OwnerController#findOwner(Integer)}.
	 *
	 * <ul>
	 * <li>Given {@link Owner} (default constructor) Address is {@code 42 Main St}.
	 * <li>When one.
	 * <li>Then return {@link Owner} (default constructor).
	 * </ul>
	 *
	 * <p>
	 * Method under test: {@link OwnerController#findOwner(Integer)}
	 */
	@Test
	@DisplayName("Test findOwner(Integer); given Owner (default constructor) Address is '42 Main St'; when one; then return Owner (default constructor)")
	@Tag("ContributionFromDiffblue")
	@ManagedByDiffblue
	@MethodsUnderTest({ "Owner OwnerController.findOwner(Integer)" })
	void testFindOwner_givenOwnerAddressIs42MainSt_whenOne_thenReturnOwner() {
		// Arrange
		Owner owner = new Owner();
		owner.setAddress("42 Main St");
		owner.setCity("Oxford");
		owner.setFirstName("Jane");
		owner.setId(1);
		owner.setLastName("Doe");
		owner.setTelephone("6625550144");
		when(this.owners.findById(Mockito.<Integer>any())).thenReturn(owner);

		// Act
		Owner actualFindOwnerResult = ownerController.findOwner(1);

		// Assert
		verify(this.owners).findById(1);
		assertSame(owner, actualFindOwnerResult);
	}

	/**
	 * Test {@link OwnerController#findOwner(Integer)}.
	 *
	 * <ul>
	 * <li>Given {@link OwnerRepository}.
	 * <li>When {@code null}.
	 * <li>Then return Id is {@code null}.
	 * </ul>
	 *
	 * <p>
	 * Method under test: {@link OwnerController#findOwner(Integer)}
	 */
	@Test
	@DisplayName("Test findOwner(Integer); given OwnerRepository; when 'null'; then return Id is 'null'")
	@Tag("ContributionFromDiffblue")
	@ManagedByDiffblue
	@MethodsUnderTest({ "Owner OwnerController.findOwner(Integer)" })
	void testFindOwner_givenOwnerRepository_whenNull_thenReturnIdIsNull() {
		// Arrange and Act
		Owner actualFindOwnerResult = ownerController.findOwner(null);

		// Assert
		assertNull(actualFindOwnerResult.getId());
		assertNull(actualFindOwnerResult.getFirstName());
		assertNull(actualFindOwnerResult.getLastName());
		assertNull(actualFindOwnerResult.getAddress());
		assertNull(actualFindOwnerResult.getCity());
		assertNull(actualFindOwnerResult.getTelephone());
		assertTrue(actualFindOwnerResult.isNew());
	}

	/**
	 * Test {@link OwnerController#initCreationForm(Map)}.
	 *
	 * <p>
	 * Method under test: {@link OwnerController#initCreationForm(Map)}
	 */
	@Test
	@DisplayName("Test initCreationForm(Map)")
	@Tag("ContributionFromDiffblue")
	@ManagedByDiffblue
	@MethodsUnderTest({ "String OwnerController.initCreationForm(Map)" })
	void testInitCreationForm2() throws Exception {
		// Arrange
		MockHttpServletRequestBuilder requestBuilder = get("/owners/new");

		// Act and Assert
		MockMvcBuilders.standaloneSetup(ownerController)
			.build()
			.perform(requestBuilder)
			.andExpect(status().isOk())
			.andExpect(model().size(1))
			.andExpect(model().attributeExists("owner"))
			.andExpect(view().name("owners/createOrUpdateOwnerForm"))
			.andExpect(forwardedUrl("owners/createOrUpdateOwnerForm"));
	}

	/**
	 * Test {@link OwnerController#processCreationForm(Owner, BindingResult)}.
	 *
	 * <p>
	 * Method under test:
	 * {@link OwnerController#processCreationForm(Owner, BindingResult)}
	 */
	@Test
	@DisplayName("Test processCreationForm(Owner, BindingResult)")
	@Tag("ContributionFromDiffblue")
	@ManagedByDiffblue
	@MethodsUnderTest({ "String OwnerController.processCreationForm(Owner, BindingResult)" })
	void testProcessCreationForm() throws Exception {
		// Arrange
		MockHttpServletRequestBuilder requestBuilder = post("/owners/new");

		// Act and Assert
		MockMvcBuilders.standaloneSetup(ownerController)
			.build()
			.perform(requestBuilder)
			.andExpect(status().isOk())
			.andExpect(model().size(1))
			.andExpect(model().attributeExists("owner"))
			.andExpect(view().name("owners/createOrUpdateOwnerForm"))
			.andExpect(forwardedUrl("owners/createOrUpdateOwnerForm"));
	}

	/**
	 * Test {@link OwnerController#initFindForm()}.
	 *
	 * <p>
	 * Method under test: {@link OwnerController#initFindForm()}
	 */
	@Test
	@DisplayName("Test initFindForm()")
	@Tag("ContributionFromDiffblue")
	@ManagedByDiffblue
	@MethodsUnderTest({ "String OwnerController.initFindForm()" })
	void testInitFindForm2() throws Exception {
		// Arrange
		MockHttpServletRequestBuilder requestBuilder = get("/owners/find");

		// Act and Assert
		MockMvcBuilders.standaloneSetup(ownerController)
			.build()
			.perform(requestBuilder)
			.andExpect(status().isOk())
			.andExpect(model().size(1))
			.andExpect(model().attributeExists("owner"))
			.andExpect(view().name("owners/findOwners"))
			.andExpect(forwardedUrl("owners/findOwners"));
	}

	/**
	 * Test {@link OwnerController#processFindForm(int, Owner, BindingResult, Model)}.
	 *
	 * <ul>
	 * <li>Given {@link Owner} (default constructor) Address is {@code 17 High St}.
	 * <li>Then model size five.
	 * </ul>
	 *
	 * <p>
	 * Method under test:
	 * {@link OwnerController#processFindForm(int, Owner, BindingResult, Model)}
	 */
	@Test
	@DisplayName("Test processFindForm(int, Owner, BindingResult, Model); given Owner (default constructor) Address is '17 High St'; then model size five")
	@Tag("ContributionFromDiffblue")
	@ManagedByDiffblue
	@MethodsUnderTest({ "String OwnerController.processFindForm(int, Owner, BindingResult, Model)" })
	void testProcessFindForm_givenOwnerAddressIs17HighSt_thenModelSizeFive() throws Exception {
		// Arrange
		Owner owner = new Owner();
		owner.setAddress("42 Main St");
		owner.setCity("Oxford");
		owner.setFirstName("Jane");
		owner.setId(1);
		owner.setLastName("Doe");
		owner.setTelephone("6625550144");

		Owner owner2 = new Owner();
		owner2.setAddress("17 High St");
		owner2.setCity("London");
		owner2.setFirstName("John");
		owner2.setId(2);
		owner2.setLastName("Smith");
		owner2.setTelephone("8605550118");

		ArrayList<Owner> content = new ArrayList<>();
		content.add(owner2);
		content.add(owner);
		when(this.owners.findByLastName(Mockito.<String>any(), Mockito.<Pageable>any()))
			.thenReturn(new PageImpl<>(content));

		MockHttpServletRequestBuilder requestBuilder = get("/owners").param("page", String.valueOf(1));

		// Act and Assert
		MockMvcBuilders.standaloneSetup(ownerController)
			.build()
			.perform(requestBuilder)
			.andExpect(status().isOk())
			.andExpect(model().size(5))
			.andExpect(model().attributeExists("currentPage", "listOwners", "owner", "totalItems", "totalPages"))
			.andExpect(view().name("owners/ownersList"))
			.andExpect(forwardedUrl("owners/ownersList"));
	}

	/**
	 * Test {@link OwnerController#processFindForm(int, Owner, BindingResult, Model)}.
	 *
	 * <ul>
	 * <li>Given {@link Owner} (default constructor) Address is {@code 42 Main St}.
	 * <li>Then status {@link StatusResultMatchers#isFound()}.
	 * </ul>
	 *
	 * <p>
	 * Method under test:
	 * {@link OwnerController#processFindForm(int, Owner, BindingResult, Model)}
	 */
	@Test
	@DisplayName("Test processFindForm(int, Owner, BindingResult, Model); given Owner (default constructor) Address is '42 Main St'; then status isFound()")
	@Tag("ContributionFromDiffblue")
	@ManagedByDiffblue
	@MethodsUnderTest({ "String OwnerController.processFindForm(int, Owner, BindingResult, Model)" })
	void testProcessFindForm_givenOwnerAddressIs42MainSt_thenStatusIsFound() throws Exception {
		// Arrange
		Owner owner = new Owner();
		owner.setAddress("42 Main St");
		owner.setCity("Oxford");
		owner.setFirstName("Jane");
		owner.setId(1);
		owner.setLastName("Doe");
		owner.setTelephone("6625550144");

		ArrayList<Owner> content = new ArrayList<>();
		content.add(owner);
		when(this.owners.findByLastName(Mockito.<String>any(), Mockito.<Pageable>any()))
			.thenReturn(new PageImpl<>(content));

		MockHttpServletRequestBuilder requestBuilder = get("/owners").param("page", String.valueOf(1));

		// Act and Assert
		MockMvcBuilders.standaloneSetup(ownerController)
			.build()
			.perform(requestBuilder)
			.andExpect(status().isFound())
			.andExpect(model().size(0))
			.andExpect(view().name("redirect:/owners/1"))
			.andExpect(redirectedUrl("/owners/1"));
	}

	/**
   * Test {@link OwnerController#processFindForm(int, Owner, BindingResult, Model)}.
   *
   * <ul>
   *   <li>When {@code Doe}.
   *   <li>Then model size one.
   * </ul>
   *
   * <p>Method under test: {@link OwnerController#processFindForm(int, Owner, BindingResult, Model)}
   */
  @Test
  @DisplayName(
      "Test processFindForm(int, Owner, BindingResult, Model); when 'Doe'; then model size one")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String OwnerController.processFindForm(int, Owner, BindingResult, Model)"})
  void testProcessFindForm_whenDoe_thenModelSizeOne() throws Exception {
    // Arrange
    when(this.owners.findByLastName(Mockito.<String>any(), Mockito.<Pageable>any()))
        .thenReturn(new PageImpl<>(new ArrayList<>()));

    MockHttpServletRequestBuilder requestBuilder =
        get("/owners").param("page", String.valueOf(1)).param("lastName", "Doe");

    // Act and Assert
    MockMvcBuilders.standaloneSetup(ownerController)
        .build()
        .perform(requestBuilder)
        .andExpect(status().isOk())
        .andExpect(model().size(1))
        .andExpect(model().attributeExists("owner"))
        .andExpect(view().name("owners/findOwners"))
        .andExpect(forwardedUrl("owners/findOwners"));
  }

	/**
   * Test {@link OwnerController#processFindForm(int, Owner, BindingResult, Model)}.
   *
   * <ul>
   *   <li>When {@link MockHttpServletRequestBuilder#param(String, String[])} {@code page} is
   *       valueOf one.
   *   <li>Then model size one.
   * </ul>
   *
   * <p>Method under test: {@link OwnerController#processFindForm(int, Owner, BindingResult, Model)}
   */
  @Test
  @DisplayName(
      "Test processFindForm(int, Owner, BindingResult, Model); when param(String, String[]) 'page' is valueOf one; then model size one")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String OwnerController.processFindForm(int, Owner, BindingResult, Model)"})
  void testProcessFindForm_whenParamPageIsValueOfOne_thenModelSizeOne() throws Exception {
    // Arrange
    when(this.owners.findByLastName(Mockito.<String>any(), Mockito.<Pageable>any()))
        .thenReturn(new PageImpl<>(new ArrayList<>()));

    MockHttpServletRequestBuilder requestBuilder = get("/owners").param("page", String.valueOf(1));

    // Act and Assert
    MockMvcBuilders.standaloneSetup(ownerController)
        .build()
        .perform(requestBuilder)
        .andExpect(status().isOk())
        .andExpect(model().size(1))
        .andExpect(model().attributeExists("owner"))
        .andExpect(view().name("owners/findOwners"))
        .andExpect(forwardedUrl("owners/findOwners"));
  }

	/**
	 * Test {@link OwnerController#initUpdateOwnerForm(int, Model)}.
	 *
	 * <p>
	 * Method under test: {@link OwnerController#initUpdateOwnerForm(int, Model)}
	 */
	@Test
	@DisplayName("Test initUpdateOwnerForm(int, Model)")
	@Tag("ContributionFromDiffblue")
	@ManagedByDiffblue
	@MethodsUnderTest({ "String OwnerController.initUpdateOwnerForm(int, Model)" })
	void testInitUpdateOwnerForm2() throws Exception {
		// Arrange
		Owner owner = new Owner();
		owner.setAddress("42 Main St");
		owner.setCity("Oxford");
		owner.setFirstName("Jane");
		owner.setId(1);
		owner.setLastName("Doe");
		owner.setTelephone("6625550144");
		when(this.owners.findById(Mockito.<Integer>any())).thenReturn(owner);

		MockHttpServletRequestBuilder requestBuilder = get("/owners/{ownerId}/edit", 1);

		// Act and Assert
		MockMvcBuilders.standaloneSetup(ownerController)
			.build()
			.perform(requestBuilder)
			.andExpect(status().isOk())
			.andExpect(model().size(1))
			.andExpect(model().attributeExists("owner"))
			.andExpect(view().name("owners/createOrUpdateOwnerForm"))
			.andExpect(forwardedUrl("owners/createOrUpdateOwnerForm"));
	}

	/**
	 * Test {@link OwnerController#processUpdateOwnerForm(Owner, BindingResult, int)}.
	 *
	 * <ul>
	 * <li>Given {@link Owner} (default constructor) Address is {@code 42 Main St}.
	 * <li>Then status {@link StatusResultMatchers#isFound()}.
	 * </ul>
	 *
	 * <p>
	 * Method under test:
	 * {@link OwnerController#processUpdateOwnerForm(Owner, BindingResult, int)}
	 */
	@Test
	@DisplayName("Test processUpdateOwnerForm(Owner, BindingResult, int); given Owner (default constructor) Address is '42 Main St'; then status isFound()")
	@Tag("ContributionFromDiffblue")
	@ManagedByDiffblue
	@MethodsUnderTest({ "String OwnerController.processUpdateOwnerForm(Owner, BindingResult, int)" })
	void testProcessUpdateOwnerForm_givenOwnerAddressIs42MainSt_thenStatusIsFound() throws Exception {
		// Arrange
		Owner owner = new Owner();
		owner.setAddress("42 Main St");
		owner.setCity("Oxford");
		owner.setFirstName("Jane");
		owner.setId(1);
		owner.setLastName("Doe");
		owner.setTelephone("6625550144");
		doNothing().when(this.owners).save(Mockito.<Owner>any());
		when(this.owners.findById(Mockito.<Integer>any())).thenReturn(owner);

		MockHttpServletRequestBuilder requestBuilder = post("/owners/{ownerId}/edit", 1);

		// Act and Assert
		MockMvcBuilders.standaloneSetup(ownerController)
			.build()
			.perform(requestBuilder)
			.andExpect(status().isFound())
			.andExpect(model().size(0))
			.andExpect(view().name("redirect:/owners/{ownerId}"))
			.andExpect(redirectedUrl("/owners/1"));
	}

	/**
	 * Test {@link OwnerController#processUpdateOwnerForm(Owner, BindingResult, int)}.
	 *
	 * <ul>
	 * <li>Given {@link Owner} (default constructor) Address is empty string.
	 * <li>Then status {@link StatusResultMatchers#isOk()}.
	 * </ul>
	 *
	 * <p>
	 * Method under test:
	 * {@link OwnerController#processUpdateOwnerForm(Owner, BindingResult, int)}
	 */
	@Test
	@DisplayName("Test processUpdateOwnerForm(Owner, BindingResult, int); given Owner (default constructor) Address is empty string; then status isOk()")
	@Tag("ContributionFromDiffblue")
	@ManagedByDiffblue
	@MethodsUnderTest({ "String OwnerController.processUpdateOwnerForm(Owner, BindingResult, int)" })
	void testProcessUpdateOwnerForm_givenOwnerAddressIsEmptyString_thenStatusIsOk() throws Exception {
		// Arrange
		Owner owner = new Owner();
		owner.setAddress("");
		owner.setCity("Oxford");
		owner.setFirstName("Jane");
		owner.setId(1);
		owner.setLastName("Doe");
		owner.setTelephone("6625550144");
		doNothing().when(this.owners).save(Mockito.<Owner>any());
		when(this.owners.findById(Mockito.<Integer>any())).thenReturn(owner);

		MockHttpServletRequestBuilder requestBuilder = post("/owners/{ownerId}/edit", 1);

		// Act and Assert
		MockMvcBuilders.standaloneSetup(ownerController)
			.build()
			.perform(requestBuilder)
			.andExpect(status().isOk())
			.andExpect(model().size(1))
			.andExpect(model().attributeExists("owner"))
			.andExpect(view().name("owners/createOrUpdateOwnerForm"))
			.andExpect(forwardedUrl("owners/createOrUpdateOwnerForm"));
	}

	/**
	 * Test {@link OwnerController#showOwner(int)}.
	 *
	 * <ul>
	 * <li>When one.
	 * <li>Then view name {@code owners/ownerDetails}.
	 * </ul>
	 *
	 * <p>
	 * Method under test: {@link OwnerController#showOwner(int)}
	 */
	@Test
	@DisplayName("Test showOwner(int); when one; then view name 'owners/ownerDetails'")
	@Tag("ContributionFromDiffblue")
	@ManagedByDiffblue
	@MethodsUnderTest({ "org.springframework.web.servlet.ModelAndView OwnerController.showOwner(int)" })
	void testShowOwner_whenOne_thenViewNameOwnersOwnerDetails() throws Exception {
		// Arrange
		Owner owner = new Owner();
		owner.setAddress("42 Main St");
		owner.setCity("Oxford");
		owner.setFirstName("Jane");
		owner.setId(1);
		owner.setLastName("Doe");
		owner.setTelephone("6625550144");
		when(this.owners.findById(Mockito.<Integer>any())).thenReturn(owner);

		MockHttpServletRequestBuilder requestBuilder = get("/owners/{ownerId}", 1);

		// Act and Assert
		MockMvcBuilders.standaloneSetup(ownerController)
			.build()
			.perform(requestBuilder)
			.andExpect(status().isOk())
			.andExpect(model().size(1))
			.andExpect(model().attributeExists("owner"))
			.andExpect(view().name("owners/ownerDetails"))
			.andExpect(forwardedUrl("owners/ownerDetails"));
	}

	@Test
	void testInitCreationForm() throws Exception {
		mockMvc.perform(get("/owners/new"))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("owner"))
			.andExpect(view().name("owners/createOrUpdateOwnerForm"));
	}

	@Test
	void testProcessCreationFormSuccess() throws Exception {
		mockMvc
			.perform(post("/owners/new").param("firstName", "Joe")
				.param("lastName", "Bloggs")
				.param("address", "123 Caramel Street")
				.param("city", "London")
				.param("telephone", "01316761638"))
			.andExpect(status().is3xxRedirection());
	}

	@Test
	void testProcessCreationFormHasErrors() throws Exception {
		mockMvc
			.perform(post("/owners/new").param("firstName", "Joe").param("lastName", "Bloggs").param("city", "London"))
			.andExpect(status().isOk())
			.andExpect(model().attributeHasErrors("owner"))
			.andExpect(model().attributeHasFieldErrors("owner", "address"))
			.andExpect(model().attributeHasFieldErrors("owner", "telephone"))
			.andExpect(view().name("owners/createOrUpdateOwnerForm"));
	}

	@Test
	void testInitFindForm() throws Exception {
		mockMvc.perform(get("/owners/find"))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("owner"))
			.andExpect(view().name("owners/findOwners"));
	}

	@Test
	void testProcessFindFormSuccess() throws Exception {
		Page<Owner> tasks = new PageImpl<Owner>(Lists.newArrayList(george(), new Owner()));
		Mockito.when(this.owners.findByLastName(anyString(), any(Pageable.class))).thenReturn(tasks);
		mockMvc.perform(get("/owners?page=1")).andExpect(status().isOk()).andExpect(view().name("owners/ownersList"));
	}

	@Test
	void testProcessFindFormByLastName() throws Exception {
		Page<Owner> tasks = new PageImpl<Owner>(Lists.newArrayList(george()));
		Mockito.when(this.owners.findByLastName(eq("Franklin"), any(Pageable.class))).thenReturn(tasks);
		mockMvc.perform(get("/owners?page=1").param("lastName", "Franklin"))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/owners/" + TEST_OWNER_ID));
	}

	@Test
	void testProcessFindFormNoOwnersFound() throws Exception {
		Page<Owner> tasks = new PageImpl<Owner>(Lists.newArrayList());
		Mockito.when(this.owners.findByLastName(eq("Unknown Surname"), any(Pageable.class))).thenReturn(tasks);
		mockMvc.perform(get("/owners?page=1").param("lastName", "Unknown Surname"))
			.andExpect(status().isOk())
			.andExpect(model().attributeHasFieldErrors("owner", "lastName"))
			.andExpect(model().attributeHasFieldErrorCode("owner", "lastName", "notFound"))
			.andExpect(view().name("owners/findOwners"));
	}

	@Test
	void testInitUpdateOwnerForm() throws Exception {
		mockMvc.perform(get("/owners/{ownerId}/edit", TEST_OWNER_ID))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("owner"))
			.andExpect(model().attribute("owner", hasProperty("lastName", is("Franklin"))))
			.andExpect(model().attribute("owner", hasProperty("firstName", is("George"))))
			.andExpect(model().attribute("owner", hasProperty("address", is("110 W. Liberty St."))))
			.andExpect(model().attribute("owner", hasProperty("city", is("Madison"))))
			.andExpect(model().attribute("owner", hasProperty("telephone", is("6085551023"))))
			.andExpect(view().name("owners/createOrUpdateOwnerForm"));
	}

	@Test
	void testProcessUpdateOwnerFormSuccess() throws Exception {
		mockMvc
			.perform(post("/owners/{ownerId}/edit", TEST_OWNER_ID).param("firstName", "Joe")
				.param("lastName", "Bloggs")
				.param("address", "123 Caramel Street")
				.param("city", "London")
				.param("telephone", "01616291589"))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/owners/{ownerId}"));
	}

	@Test
	void testProcessUpdateOwnerFormUnchangedSuccess() throws Exception {
		mockMvc.perform(post("/owners/{ownerId}/edit", TEST_OWNER_ID))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/owners/{ownerId}"));
	}

	@Test
	void testProcessUpdateOwnerFormHasErrors() throws Exception {
		mockMvc
			.perform(post("/owners/{ownerId}/edit", TEST_OWNER_ID).param("firstName", "Joe")
				.param("lastName", "Bloggs")
				.param("address", "")
				.param("telephone", ""))
			.andExpect(status().isOk())
			.andExpect(model().attributeHasErrors("owner"))
			.andExpect(model().attributeHasFieldErrors("owner", "address"))
			.andExpect(model().attributeHasFieldErrors("owner", "telephone"))
			.andExpect(view().name("owners/createOrUpdateOwnerForm"));
	}

	@Test
	void testShowOwner() throws Exception {
		mockMvc.perform(get("/owners/{ownerId}", TEST_OWNER_ID))
			.andExpect(status().isOk())
			.andExpect(model().attribute("owner", hasProperty("lastName", is("Franklin"))))
			.andExpect(model().attribute("owner", hasProperty("firstName", is("George"))))
			.andExpect(model().attribute("owner", hasProperty("address", is("110 W. Liberty St."))))
			.andExpect(model().attribute("owner", hasProperty("city", is("Madison"))))
			.andExpect(model().attribute("owner", hasProperty("telephone", is("6085551023"))))
			.andExpect(model().attribute("owner", hasProperty("pets", not(empty()))))
			.andExpect(model().attribute("owner", hasProperty("pets", new BaseMatcher<List<Pet>>() {
				@Override
				public boolean matches(Object item) {
					@SuppressWarnings("unchecked")
					List<Pet> pets = (List<Pet>) item;
					Pet pet = pets.get(0);
					if (pet.getVisits().isEmpty()) {
						return false;
					}
					return true;
				}

				@Override
				public void describeTo(Description description) {
					description.appendText("Max did not have any visits");
				}
			})))
			.andExpect(view().name("owners/ownerDetails"));
	}

}
