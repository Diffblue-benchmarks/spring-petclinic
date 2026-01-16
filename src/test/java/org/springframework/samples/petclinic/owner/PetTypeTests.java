package org.springframework.samples.petclinic.owner;

import static org.junit.jupiter.api.Assertions.assertNull;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class PetTypeTests {

	/**
	 * Test new {@link PetType} (default constructor).
	 *
	 * <p>
	 * Method under test: default or parameterless constructor of {@link PetType}
	 */
	@Test
	@DisplayName("Test new PetType (default constructor)")
	@Tag("ContributionFromDiffblue")
	@ManagedByDiffblue
	@MethodsUnderTest({ "void PetType.<init>()" })
	void testNewPetType() {
		// Arrange and Act
		PetType actualPetType = new PetType();

		// Assert
		assertNull(actualPetType.getId());
		assertNull(actualPetType.getName());
	}

}
