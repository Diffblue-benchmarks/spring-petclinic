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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledInNativeImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

/**
 * Test class for {@link VisitController}
 *
 * @author Colin But
 */
@WebMvcTest(VisitController.class)
@DisabledInNativeImage
@DisabledInAotMode
class VisitControllerTests {

	private static final int TEST_OWNER_ID = 1;

	private static final int TEST_PET_ID = 1;

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private OwnerRepository owners;

	@BeforeEach
	void init() {
		Owner owner = new Owner();
		Pet pet = new Pet();
		owner.addPet(pet);
		pet.setId(TEST_PET_ID);
		given(this.owners.findById(TEST_OWNER_ID)).willReturn(owner);
	}

	/**
	 * Test {@link VisitController#initNewVisitForm()}.
	 *
	 * <p>
	 * Method under test: {@link VisitController#initNewVisitForm()}
	 */
	@Test
	@DisplayName("Test initNewVisitForm()")
	@Tag("ContributionFromDiffblue")
	@ManagedByDiffblue
	@MethodsUnderTest({ "String VisitController.initNewVisitForm()" })
	void testInitNewVisitForm2() {
		// Diffblue Cover was unable to create a Spring-specific test for this Spring
		// method.
		// Run dcover create --keep-partial-tests to gain insights into why
		// a non-Spring test was created.

		// Arrange, Act and Assert
		assertEquals("pets/createOrUpdateVisitForm",
				new VisitController(mock(OwnerRepository.class)).initNewVisitForm());
	}

	/**
	 * Test {@link VisitController#processNewVisitForm(Owner, int, Visit, BindingResult)}.
	 *
	 * <ul>
	 * <li>Given {@code true}.
	 * <li>Then return {@code pets/createOrUpdateVisitForm}.
	 * </ul>
	 *
	 * <p>
	 * Method under test:
	 * {@link VisitController#processNewVisitForm(Owner, int, Visit, BindingResult)}
	 */
	@Test
	@DisplayName("Test processNewVisitForm(Owner, int, Visit, BindingResult); given 'true'; then return 'pets/createOrUpdateVisitForm'")
	@Tag("ContributionFromDiffblue")
	@ManagedByDiffblue
	@MethodsUnderTest({ "String VisitController.processNewVisitForm(Owner, int, Visit, BindingResult)" })
	void testProcessNewVisitForm_givenTrue_thenReturnPetsCreateOrUpdateVisitForm() {
		// Diffblue Cover was unable to create a Spring-specific test for this Spring
		// method.
		// Run dcover create --keep-partial-tests to gain insights into why
		// a non-Spring test was created.

		// Arrange
		VisitController visitController = new VisitController(mock(OwnerRepository.class));

		Owner owner = new Owner();
		owner.setAddress("42 Main St");
		owner.setCity("Oxford");
		owner.setFirstName("Jane");
		owner.setId(1);
		owner.setLastName("Doe");
		owner.setTelephone("6625550144");

		Visit visit = new Visit();
		visit.setDate(LocalDate.of(1970, 1, 1));
		visit.setDescription("The characteristics of someone or something");
		visit.setId(1);

		BeanPropertyBindingResult result = mock(BeanPropertyBindingResult.class);
		when(result.hasErrors()).thenReturn(true);

		// Act
		String actualProcessNewVisitFormResult = visitController.processNewVisitForm(owner, 1, visit, result);

		// Assert
		verify(result).hasErrors();
		assertEquals("pets/createOrUpdateVisitForm", actualProcessNewVisitFormResult);
	}

	@Test
	void testInitNewVisitForm() throws Exception {
		mockMvc.perform(get("/owners/{ownerId}/pets/{petId}/visits/new", TEST_OWNER_ID, TEST_PET_ID))
			.andExpect(status().isOk())
			.andExpect(view().name("pets/createOrUpdateVisitForm"));
	}

	@Test
	void testProcessNewVisitFormSuccess() throws Exception {
		mockMvc
			.perform(post("/owners/{ownerId}/pets/{petId}/visits/new", TEST_OWNER_ID, TEST_PET_ID)
				.param("name", "George")
				.param("description", "Visit Description"))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/owners/{ownerId}"));
	}

	@Test
	void testProcessNewVisitFormHasErrors() throws Exception {
		mockMvc
			.perform(post("/owners/{ownerId}/pets/{petId}/visits/new", TEST_OWNER_ID, TEST_PET_ID).param("name",
					"George"))
			.andExpect(model().attributeHasErrors("visit"))
			.andExpect(status().isOk())
			.andExpect(view().name("pets/createOrUpdateVisitForm"));
	}

}
