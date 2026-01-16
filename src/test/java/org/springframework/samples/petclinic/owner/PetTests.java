package org.springframework.samples.petclinic.owner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class PetTests {

	/**
	 * Test {@link Pet#addVisit(Visit)}.
	 *
	 * <p>
	 * Method under test: {@link Pet#addVisit(Visit)}
	 */
	@Test
	@DisplayName("Test addVisit(Visit)")
	@Tag("ContributionFromDiffblue")
	@ManagedByDiffblue
	@MethodsUnderTest({ "void Pet.addVisit(Visit)" })
	void testAddVisit() {
		// Arrange
		Pet pet = new Pet();

		Visit visit = new Visit();
		visit.setDate(LocalDate.of(1970, 1, 1));
		visit.setDescription("The characteristics of someone or something");
		visit.setId(1);

		// Act
		pet.addVisit(visit);

		// Assert
		Collection<Visit> visits = pet.getVisits();
		assertEquals(1, visits.size());
		assertTrue(visits instanceof Set);
	}

	/**
	 * Test getters and setters.
	 *
	 * <p>
	 * Methods under test:
	 *
	 * <ul>
	 * <li>default or parameterless constructor of {@link Pet}
	 * <li>{@link Pet#setBirthDate(LocalDate)}
	 * <li>{@link Pet#setType(PetType)}
	 * <li>{@link Pet#getBirthDate()}
	 * <li>{@link Pet#getType()}
	 * <li>{@link Pet#getVisits()}
	 * </ul>
	 */
	@Test
	@DisplayName("Test getters and setters")
	@Tag("ContributionFromDiffblue")
	@ManagedByDiffblue
	@MethodsUnderTest({ "void Pet.<init>()", "LocalDate Pet.getBirthDate()", "PetType Pet.getType()",
			"Collection Pet.getVisits()", "void Pet.setBirthDate(LocalDate)", "void Pet.setType(PetType)" })
	void testGettersAndSetters() {
		// Arrange and Act
		Pet actualPet = new Pet();
		LocalDate birthDate = LocalDate.of(1970, 1, 1);
		actualPet.setBirthDate(birthDate);
		PetType type = new PetType();
		type.setId(1);
		type.setName("Dog");
		actualPet.setType(type);
		LocalDate actualBirthDate = actualPet.getBirthDate();
		PetType actualType = actualPet.getType();

		// Assert
		assertTrue(actualPet.getVisits() instanceof Set);
		assertEquals("1970-01-01", actualBirthDate.toString());
		assertNull(actualPet.getId());
		assertNull(actualPet.getName());
		assertSame(type, actualType);
		assertSame(birthDate, actualBirthDate);
	}

}
