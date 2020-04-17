package nl.bioinf.fooddiary.model.product;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Tom Wagenaar
 * @version 0.0.1
 * date: 14-04-2020
 *
 * Junit test for the ProductDescription class.
 */
class ProductDescriptionTest {

    // Sunny day scenario for the ProductDescription class.
    @Test
    void productDescriptionSunny() {
        ProductDescription productDescription = ProductDescription.builder("Aardappelen rauw", "Potatoes raw")
                .synonymous("                                ")
                .build();

        String expectedDescriptionDutch = "Aardappelen rauw";
        String expectedDescriptionEnglish = "Potatoes raw";
        String expectedSynonymous = "_UNKNOWN_SYNONYMOUS_";

        String actualDescriptionDutch = productDescription.getDescriptionDutch();
        String actualDescriptionEnglish = productDescription.getDescriptionEnglish();
        String actualSynonymous = productDescription.getSynonymous();

        assertEquals(expectedDescriptionDutch, actualDescriptionDutch);
        assertEquals(expectedDescriptionEnglish, actualDescriptionEnglish);
        assertEquals(expectedSynonymous, actualSynonymous);
    }

    // Test for null input for both the descriptionDutch and descriptionEnglish. MethodSource annotation is used to get the
    // arguments from the generateNullInput method. AssertTrue to check if the error message is one of two messages.
    @ParameterizedTest
    @MethodSource("generatedNullInput")
    void productDescriptionNullInput(String descriptionDutch, String descriptionEnglish, String synonymous) {

        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> ProductDescription.builder(descriptionDutch, descriptionEnglish)
                        .synonymous(synonymous)
                        .build());

        String descriptionDutchError = "descriptionDutch can't be a null value!";
        String descriptionEnglishError = "descriptionEnglish can't be a null value!";
        String descriptionSynonymousError = "synonymous can't be a null value!";

        assertTrue(exception.getMessage().equals(descriptionDutchError) ||
                exception.getMessage().equals(descriptionEnglishError) ||
                exception.getMessage().equals(descriptionSynonymousError));
    }

    static Stream<Arguments> generatedNullInput() {
        return Stream.of(
                Arguments.of(null, "Potatoes raw", ""),
                Arguments.of("Aardappelen rauw", null, "Aardappelen"),
                Arguments.of("Aardappelen rauw", "Potatoes raw", null),
                Arguments.of(null, null, null),
                Arguments.of(null, "Potatoes raw", null)
        );
    }

    // Test the group description Dutch on boundary cases.
    @ParameterizedTest
    @ValueSource(strings = {
            "                                          ",
            "The description of this product is more then 255 characters long and therefore isn’t a valid value. Most " +
                    "usually a 255 characters can be 51 words if we consider making a word with 5 characters each. At the " +
                    "same time, if we make each word with 4 characters then there can be 64 words approximately. Like that," +
                    " there is no fixed terms to define how longer can be a 255 characters but on an average it can range" +
                    " between 30 to 60 words if we consider generally framing of the words.",
            "    "
    })
    void productDescriptionDutchBoundaryCases(String descriptionDutch) {

        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> ProductDescription.builder(descriptionDutch, "Potatoes raw")
                        .build());

        String groupDescriptionDutchError = "descriptionDutch length must be between 0 and or the same as 255";

        assertEquals(groupDescriptionDutchError, exception.getMessage());
    }

    // Test the group description English on boundary cases.
    @ParameterizedTest
    @ValueSource(strings = {
            "                                         ",
            "The description of this product is more then 255 characters long and therefore isn’t a valid value. Most " +
                    "usually a 255 characters can be 51 words if we consider making a word with 5 characters each. At the " +
                    "same time, if we make each word with 4 characters then there can be 64 words approximately. Like that," +
                    " there is no fixed terms to define how longer can be a 255 characters but on an average it can range" +
                    " between 30 to 60 words if we consider generally framing of the words.",
            "    "
    })
    void productDescriptionEnglishBoundaryCases(String descriptionEnglish) {

        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> ProductDescription.builder("Aardappelen rauw",  descriptionEnglish)
                        .build());

        String groupDescriptionEnglishError = "descriptionEnglish length must be between 0 and or the same as 255";

        assertEquals(groupDescriptionEnglishError, exception.getMessage());
    }

    // Test the group description English on boundary cases.
    @ParameterizedTest
    @ValueSource(strings = {
            "__UNKOWN__SYNONYMOUS__ The description of this product is more then 255 characters long and therefore isn’t a valid value. Most " +
                    "usually a 255 characters can be 51 words if we consider making a word with 5 characters each. At the " +
                    "same time, if we make each word with 4 characters then there can be 64 words approximately. Like that," +
                    " there is no fixed terms to define how longer can be a 255 characters but on an average it can range" +
                    " between 30 to 60 words if we consider generally framing of the words.",
    })
    void productDescriptionSynonymousBoundaryCases(String synonymous) {

        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> ProductDescription.builder("Aardappelen rauw",  "Potatoes raw")
                        .synonymous(synonymous)
                        .build());

        String groupDescriptionSynonymousError = "synonymous length must be between 0 and or the same as 255";

        assertEquals(groupDescriptionSynonymousError, exception.getMessage());
    }
}