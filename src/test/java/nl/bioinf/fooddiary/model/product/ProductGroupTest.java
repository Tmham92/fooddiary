package nl.bioinf.fooddiary.model.product;

import nl.bioinf.fooddiary.model.csvparser.ProductCsvParser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;

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
        int expectedGroupCode = 5;
        String expectedGroupCodeDescription = "description";

        ProductGroup productGroup = ProductGroup.builder("5", "description")
                .build();

        int actualGroupCode = productGroup.getGroupCode();
        String actualGroupCodeDescription = productGroup.getGroupCodeDescription();

        assertEquals(expectedGroupCode, actualGroupCode);
        assertEquals(expectedGroupCodeDescription, actualGroupCodeDescription);

    }

    @Test
    void productGroupNullInput() {

        Throwable exception = assertThrows(NumberFormatException.class,
                () -> ProductGroup.builder(null, "Aardappelen en knolgewassen")
                        .build());

        String expected = "Please don't provide a null value for group code!";

        assertEquals(expected, exception.getMessage());
    }


    @ParameterizedTest
    void productGroupBoundaryCases() {

        Throwable exception = assertThrows(NumberFormatException.class,
                () -> ProductGroup.builder(null, "Aardappelen en knolgewassen")
                        .build());

        String expected = "Please don't provide a null value for group code!";

        assertEquals(expected, exception.getMessage());


    }
}