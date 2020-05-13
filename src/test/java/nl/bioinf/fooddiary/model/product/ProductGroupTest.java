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
 * date: 19-03-2020
 *
 * JUnit testing for the ProductGroup class and its inner class ProductGroupBuilder.
 */
class ProductGroupTest {

    // Sunny day scenario for the ProductGroup class.
    @Test
    void productGroupSunny() {
        ProductGroup productGroup = ProductGroup.builder("20", "Aardappelen en knolgewassen")
                .build();

        double expectedGroupCode = 20;
        String expectedGroupCodeDescription = "Aardappelen en knolgewassen";


        double actualGroupCode = productGroup.getGroupCode();
        String actualGroupCodeDescription = productGroup.getGroupCodeDescription();

        assertEquals(expectedGroupCode, actualGroupCode);
        assertEquals(expectedGroupCodeDescription, actualGroupCodeDescription);
    }

    // Testing an exception is thrown when input fo the groupCode can't be changed to an integer.
    @Test
    void groupCodeStringInput() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> ProductGroup.builder(null, "Aardappelen en knolgewassen")
                        .build());

        String expected = "groupCode isn't an integer!";

        assertEquals(expected, exception.getMessage());
    }


    // Test for null input for both the groupCode and groupCodeDescription. MethodSource annotation is used to get the
    // arguments from the generateNullInput method. AssertTrue to check if the error message is one of two messages.
    @ParameterizedTest
    @MethodSource("generatedNullInput")
    void productGroupNullInput(String groupCode, String groupCodeDescription) {

        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> ProductGroup.builder(groupCode, groupCodeDescription)
                        .build());

        String groupCodeMessage = "groupCode isn't an integer!";
        String groupCodeDescriptionMessage = "groupCodeDescription can't be a null value!";

        assertTrue(exception.getMessage().equals(groupCodeMessage) || exception.getMessage().equals(groupCodeDescriptionMessage));
    }

    static Stream<Arguments> generatedNullInput() {
        return Stream.of(
                Arguments.of(null, "Aardappelen en knolgewassen"),
                Arguments.of("1", null),
                Arguments.of(null, null)
        );
    }

    // Check the groupCode on multiple values, these include values that aren't between the specified values or the
    // value isn't an integer.
    @ParameterizedTest
    @ValueSource(strings = {
            "-5",
            "2000",
            "20.03",
            "1000000000002"

    })
    void productGroupCodeBoundaryCases(String groupCode) {

        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> ProductGroup.builder(groupCode, "Aardappelen en knolgewassen")
                        .build());

        String groupCodeSizeError = "groupCode value must be between 0 and or the same as 999";
        String groupCodeDoubleError = "groupCode isn't an integer!";

        assertTrue(exception.getMessage().equals(groupCodeSizeError) || exception.getMessage().equals(groupCodeDoubleError));
    }

    // Test the group code description on boundary cases.
    @ParameterizedTest
    @ValueSource(strings = {
            "                      -                    ",
            "The description of this product is more then 255 characters long and therefore isn’t a valid value. Most " +
                    "usually a 255 characters can be 51 words if we consider making a word with 5 characters each. At the " +
                    "same time, if we make each word with 4 characters then there can be 64 words approximately. Like that," +
                    " there is no fixed terms to define how longer can be a 255 characters but on an average it can range" +
                    " between 30 to 60 words if we consider generally framing of the words."
    })
    void productGroupCodeDescriptionBoundaryCases(String groupCodeDescription) {

        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> ProductGroup.builder("10", groupCodeDescription)
                        .build());

        String groupCodeDescriptionError = "groupCodeDescription length must be between 0 and or the same as 255";

        assertEquals(groupCodeDescriptionError, exception.getMessage());
    }
}
