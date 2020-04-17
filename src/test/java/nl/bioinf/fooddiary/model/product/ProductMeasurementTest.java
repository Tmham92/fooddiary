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
 * date: 15-04-2020
 *
 * Junit test for the ProductMeasurement class.
 */
class ProductMeasurementTest {

    @Test
    void productMeasurementSunnyInput() {
        ProductMeasurement productMeasurement = ProductMeasurement.builder("g", "100")
                .build();

        String expectedUnit = "g";
        int expectedQuantity = 100;
        String expectedComment = "_UNKNOWN_COMMENT_";

        assertEquals(expectedUnit, productMeasurement.getMeasurementUnit());
        assertEquals(expectedQuantity, productMeasurement.getMeasurementQuantity());
        assertEquals(expectedComment, productMeasurement.getMeasurementComment());
    }

    // Test for null input.
    @ParameterizedTest
    @MethodSource("generateNullInput")
    void productMeasurementNullTest(String measurementUnit, String measurementQuantity, String measurementComment) {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> ProductMeasurement.builder(measurementUnit, measurementQuantity)
                        .measurementComment(measurementComment)
                        .build());



        String unitError = "measurementUnit can't be a null value!";
        String quantityError = "measurementQuantity can't be a null value!";
        String commentError = "measurementComment can't be a null value!";

        assertTrue(exception.getMessage().equals(unitError) ||
                exception.getMessage().equals(quantityError) ||
                exception.getMessage().equals(commentError));
    }

    static Stream<Arguments> generateNullInput() {
        return Stream.of(
                Arguments.of(null, "100", "per 100 gram"),
                Arguments.of("ml", null, "per 10 milliliter"),
                Arguments.of("ml", "10", null),
                Arguments.of(null, "50", null),
                Arguments.of(null, null, null)
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "1234, gram, gram",
            "     "
    })
    void measurementUnitBoundaryCases(String measurementUnit) {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> ProductMeasurement.builder(measurementUnit, "100").build());

        String unitError = "measurementUnit length must be between 0 and or the same as 10";

        assertEquals(unitError, exception.getMessage());
    }



}