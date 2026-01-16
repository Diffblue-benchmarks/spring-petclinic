package org.springframework.samples.petclinic.owner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

class PetValidatorTests {

	/**
	 * Test {@link PetValidator#validate(Object, Errors)}.
	 *
	 * <p>
	 * Method under test: {@link PetValidator#validate(Object, Errors)}
	 */
	@Test
	@DisplayName("Test validate(Object, Errors)")
	@Tag("ContributionFromDiffblue")
	@ManagedByDiffblue
	@MethodsUnderTest({ "void PetValidator.validate(Object, Errors)" })
	void testValidate() {
		// Arrange
		PetValidator petValidator = new PetValidator();

		PetType type = new PetType();
		type.setId(1);
		type.setName("Dog");

		Pet pet = new Pet();
		pet.setBirthDate(LocalDate.of(1970, 1, 1));
		pet.setId(1);
		pet.setName("Bella");
		pet.setType(type);
		BindException errors = new BindException(pet, "org.springframework.samples.petclinic.owner.Pet");

		// Act
		petValidator.validate(pet, errors);

		// Assert that nothing has changed
		assertEquals("org.springframework.validation.BeanPropertyBindingResult: 0 errors",
				errors.getLocalizedMessage());
		assertEquals("org.springframework.validation.BeanPropertyBindingResult: 0 errors", errors.getMessage());
	}

	/**
	 * Test {@link PetValidator#validate(Object, Errors)}.
	 *
	 * <p>
	 * Method under test: {@link PetValidator#validate(Object, Errors)}
	 */
	@Test
	@DisplayName("Test validate(Object, Errors)")
	@Tag("ContributionFromDiffblue")
	@ManagedByDiffblue
	@MethodsUnderTest({ "void PetValidator.validate(Object, Errors)" })
	void testValidate2() {
		// Arrange
		PetValidator petValidator = new PetValidator();

		PetType type = new PetType();
		type.setId(1);
		type.setName("Dog");

		Pet pet = new Pet();
		pet.setBirthDate(null);
		pet.setId(1);
		pet.setName("Bella");
		pet.setType(type);
		BindException errors = new BindException(pet, "org.springframework.samples.petclinic.owner.Pet");

		// Act
		petValidator.validate(pet, errors);

		// Assert
		FieldError fieldError = errors.getFieldError();
		assertEquals("birthDate", fieldError.getField());
		assertEquals("org.springframework.validation.BeanPropertyBindingResult: 1 errors\n"
				+ "Field error in object 'org.springframework.samples.petclinic.owner.Pet' on field 'birthDate': rejected"
				+ " value [null]; codes [required.org.springframework.samples.petclinic.owner.Pet.birthDate,required"
				+ ".birthDate,required.java.time.LocalDate,required]; arguments []; default message [required]",
				errors.getLocalizedMessage());
		assertEquals("org.springframework.validation.BeanPropertyBindingResult: 1 errors\n"
				+ "Field error in object 'org.springframework.samples.petclinic.owner.Pet' on field 'birthDate': rejected"
				+ " value [null]; codes [required.org.springframework.samples.petclinic.owner.Pet.birthDate,required"
				+ ".birthDate,required.java.time.LocalDate,required]; arguments []; default message [required]",
				errors.getMessage());
		String[] codes = fieldError.getCodes();
		assertEquals("required.birthDate", codes[1]);
		assertEquals("required.java.time.LocalDate", codes[2]);
		assertEquals("required.org.springframework.samples.petclinic.owner.Pet.birthDate", codes[0]);
		assertEquals(4, codes.length);
	}

	/**
	 * Test {@link PetValidator#validate(Object, Errors)}.
	 *
	 * <p>
	 * Method under test: {@link PetValidator#validate(Object, Errors)}
	 */
	@Test
	@DisplayName("Test validate(Object, Errors)")
	@Tag("ContributionFromDiffblue")
	@ManagedByDiffblue
	@MethodsUnderTest({ "void PetValidator.validate(Object, Errors)" })
	void testValidate3() {
		// Arrange
		PetValidator petValidator = new PetValidator();

		PetType type = new PetType();
		type.setId(1);
		type.setName("Dog");

		Pet pet = new Pet();
		pet.setBirthDate(LocalDate.of(1970, 1, 1));
		pet.setId(1);
		pet.setName(null);
		pet.setType(type);
		BindException errors = new BindException(pet, "org.springframework.samples.petclinic.owner.Pet");

		// Act
		petValidator.validate(pet, errors);

		// Assert
		FieldError fieldError = errors.getFieldError();
		assertEquals("name", fieldError.getField());
		assertEquals("org.springframework.validation.BeanPropertyBindingResult: 1 errors\n"
				+ "Field error in object 'org.springframework.samples.petclinic.owner.Pet' on field 'name': rejected value"
				+ " [null]; codes [required.org.springframework.samples.petclinic.owner.Pet.name,required.name,required"
				+ ".java.lang.String,required]; arguments []; default message [required]",
				errors.getLocalizedMessage());
		assertEquals("org.springframework.validation.BeanPropertyBindingResult: 1 errors\n"
				+ "Field error in object 'org.springframework.samples.petclinic.owner.Pet' on field 'name': rejected value"
				+ " [null]; codes [required.org.springframework.samples.petclinic.owner.Pet.name,required.name,required"
				+ ".java.lang.String,required]; arguments []; default message [required]", errors.getMessage());
		String[] codes = fieldError.getCodes();
		assertEquals("required.java.lang.String", codes[2]);
		assertEquals("required.name", codes[1]);
		assertEquals("required.org.springframework.samples.petclinic.owner.Pet.name", codes[0]);
		assertEquals(4, codes.length);
	}

	/**
	 * Test {@link PetValidator#validate(Object, Errors)}.
	 *
	 * <ul>
	 * <li>When {@link Pet} (default constructor) Id is {@code null}.
	 * </ul>
	 *
	 * <p>
	 * Method under test: {@link PetValidator#validate(Object, Errors)}
	 */
	@Test
	@DisplayName("Test validate(Object, Errors); when Pet (default constructor) Id is 'null'")
	@Tag("ContributionFromDiffblue")
	@ManagedByDiffblue
	@MethodsUnderTest({ "void PetValidator.validate(Object, Errors)" })
	void testValidate_whenPetIdIsNull() {
		// Arrange
		PetValidator petValidator = new PetValidator();

		PetType type = new PetType();
		type.setId(1);
		type.setName("Dog");

		Pet pet = new Pet();
		pet.setBirthDate(LocalDate.of(1970, 1, 1));
		pet.setId(null);
		pet.setName("Bella");
		pet.setType(type);
		BindException errors = new BindException(pet, "org.springframework.samples.petclinic.owner.Pet");

		// Act
		petValidator.validate(pet, errors);

		// Assert that nothing has changed
		assertEquals("org.springframework.validation.BeanPropertyBindingResult: 0 errors",
				errors.getLocalizedMessage());
		assertEquals("org.springframework.validation.BeanPropertyBindingResult: 0 errors", errors.getMessage());
	}

	/**
	 * Test {@link PetValidator#supports(Class)}.
	 *
	 * <ul>
	 * <li>When {@code Object}.
	 * <li>Then return {@code false}.
	 * </ul>
	 *
	 * <p>
	 * Method under test: {@link PetValidator#supports(Class)}
	 */
	@Test
	@DisplayName("Test supports(Class); when 'java.lang.Object'; then return 'false'")
	@Tag("ContributionFromDiffblue")
	@ManagedByDiffblue
	@MethodsUnderTest({ "boolean PetValidator.supports(Class)" })
	void testSupports_whenJavaLangObject_thenReturnFalse() {
		// Arrange
		PetValidator petValidator = new PetValidator();
		Class<Object> clazz = Object.class;

		// Act and Assert
		assertFalse(petValidator.supports(clazz));
	}

	/**
	 * Test {@link PetValidator#supports(Class)}.
	 *
	 * <ul>
	 * <li>When {@code Pet}.
	 * <li>Then return {@code true}.
	 * </ul>
	 *
	 * <p>
	 * Method under test: {@link PetValidator#supports(Class)}
	 */
	@Test
	@DisplayName("Test supports(Class); when 'org.springframework.samples.petclinic.owner.Pet'; then return 'true'")
	@Tag("ContributionFromDiffblue")
	@ManagedByDiffblue
	@MethodsUnderTest({ "boolean PetValidator.supports(Class)" })
	void testSupports_whenOrgSpringframeworkSamplesPetclinicOwnerPet_thenReturnTrue() {
		// Arrange
		PetValidator petValidator = new PetValidator();
		Class<Pet> clazz = Pet.class;

		// Act and Assert
		assertTrue(petValidator.supports(clazz));
	}

}
