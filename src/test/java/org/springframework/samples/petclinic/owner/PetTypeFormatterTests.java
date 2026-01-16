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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledInNativeImage;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Test class for {@link PetTypeFormatter}
 *
 * @author Colin But
 */
@ExtendWith(MockitoExtension.class)
@DisabledInNativeImage
class PetTypeFormatterTests {

	@Mock
	private OwnerRepository pets;

	private PetTypeFormatter petTypeFormatter;

	@BeforeEach
	void setup() {
		this.petTypeFormatter = new PetTypeFormatter(pets);
	}

	@Test
	void testPrint() {
		PetType petType = new PetType();
		petType.setName("Hamster");
		String petTypeName = this.petTypeFormatter.print(petType, Locale.ENGLISH);
		assertThat(petTypeName).isEqualTo("Hamster");
	}

	/**
	 * Test {@link PetTypeFormatter#print(PetType, Locale)} with {@code PetType},
	 * {@code Locale}.
	 *
	 * <p>
	 * Method under test: {@link PetTypeFormatter#print(PetType, Locale)}
	 */
	@Test
	@DisplayName("Test print(PetType, Locale) with 'PetType', 'Locale'")
	@Tag("ContributionFromDiffblue")
	@ManagedByDiffblue
	@MethodsUnderTest({ "String PetTypeFormatter.print(PetType, Locale)" })
	void testPrintWithPetTypeLocale() {
		// Arrange
		PetTypeFormatter petTypeFormatter = new PetTypeFormatter(mock(OwnerRepository.class));

		PetType petType = new PetType();
		petType.setId(1);
		petType.setName("Dog");

		// Act and Assert
		assertEquals("Dog", petTypeFormatter.print(petType, Locale.getDefault()));
	}

	@Test
	void shouldParse() throws ParseException {
		given(this.pets.findPetTypes()).willReturn(makePetTypes());
		PetType petType = petTypeFormatter.parse("Bird", Locale.ENGLISH);
		assertThat(petType.getName()).isEqualTo("Bird");
	}

	@Test
	void shouldThrowParseException() throws ParseException {
		given(this.pets.findPetTypes()).willReturn(makePetTypes());
		Assertions.assertThrows(ParseException.class, () -> {
			petTypeFormatter.parse("Fish", Locale.ENGLISH);
		});
	}

	/**
	 * Test {@link PetTypeFormatter#parse(String, Locale)}.
	 *
	 * <ul>
	 * <li>Given {@link PetType} (default constructor) Name is {@code Dog}.
	 * <li>Then return {@link PetType} (default constructor).
	 * </ul>
	 *
	 * <p>
	 * Method under test: {@link PetTypeFormatter#parse(String, Locale)}
	 */
	@Test
	@DisplayName("Test parse(String, Locale); given PetType (default constructor) Name is 'Dog'; then return PetType (default constructor)")
	@Tag("ContributionFromDiffblue")
	@ManagedByDiffblue
	@MethodsUnderTest({ "PetType PetTypeFormatter.parse(String, Locale)" })
	void testParse_givenPetTypeNameIsDog_thenReturnPetType() throws ParseException {
		// Arrange
		PetType petType = new PetType();
		petType.setId(1);
		petType.setName("Dog");

		ArrayList<PetType> petTypeList = new ArrayList<>();
		petTypeList.add(petType);

		OwnerRepository owners = mock(OwnerRepository.class);
		when(owners.findPetTypes()).thenReturn(petTypeList);

		// Act
		PetType actualParseResult = new PetTypeFormatter(owners).parse("Dog", Locale.getDefault());

		// Assert
		verify(owners).findPetTypes();
		assertSame(petType, actualParseResult);
	}

	/**
	 * Test {@link PetTypeFormatter#parse(String, Locale)}.
	 *
	 * <ul>
	 * <li>Given {@link PetType} (default constructor) Name is {@code Dog}.
	 * <li>Then return {@link PetType} (default constructor).
	 * </ul>
	 *
	 * <p>
	 * Method under test: {@link PetTypeFormatter#parse(String, Locale)}
	 */
	@Test
	@DisplayName("Test parse(String, Locale); given PetType (default constructor) Name is 'Dog'; then return PetType (default constructor)")
	@Tag("ContributionFromDiffblue")
	@ManagedByDiffblue
	@MethodsUnderTest({ "PetType PetTypeFormatter.parse(String, Locale)" })
	void testParse_givenPetTypeNameIsDog_thenReturnPetType2() throws ParseException {
		// Arrange
		PetType petType = new PetType();
		petType.setId(1);
		petType.setName("Dog");

		PetType petType2 = new PetType();
		petType2.setId(2);
		petType2.setName("Bella");

		ArrayList<PetType> petTypeList = new ArrayList<>();
		petTypeList.add(petType2);
		petTypeList.add(petType);

		OwnerRepository owners = mock(OwnerRepository.class);
		when(owners.findPetTypes()).thenReturn(petTypeList);

		// Act
		PetType actualParseResult = new PetTypeFormatter(owners).parse("Dog", Locale.getDefault());

		// Assert
		verify(owners).findPetTypes();
		assertSame(petType, actualParseResult);
	}

	/**
	 * Test {@link PetTypeFormatter#parse(String, Locale)}.
	 *
	 * <ul>
	 * <li>Then throw {@link ParseException}.
	 * </ul>
	 *
	 * <p>
	 * Method under test: {@link PetTypeFormatter#parse(String, Locale)}
	 */
	@Test
	@DisplayName("Test parse(String, Locale); then throw ParseException")
	@Tag("ContributionFromDiffblue")
	@ManagedByDiffblue
	@MethodsUnderTest({ "PetType PetTypeFormatter.parse(String, Locale)" })
	void testParse_thenThrowParseException() throws ParseException {
		// Arrange
		OwnerRepository owners = mock(OwnerRepository.class);
		when(owners.findPetTypes()).thenReturn(new ArrayList<>());

		// Act and Assert
		assertThrows(ParseException.class, () -> new PetTypeFormatter(owners).parse("Dog", Locale.getDefault()));
		verify(owners).findPetTypes();
	}

	/**
	 * Helper method to produce some sample pet types just for test purpose
	 * @return {@link Collection} of {@link PetType}
	 */
	private List<PetType> makePetTypes() {
		List<PetType> petTypes = new ArrayList<>();
		petTypes.add(new PetType() {
			{
				setName("Dog");
			}
		});
		petTypes.add(new PetType() {
			{
				setName("Bird");
			}
		});
		return petTypes;
	}

}
