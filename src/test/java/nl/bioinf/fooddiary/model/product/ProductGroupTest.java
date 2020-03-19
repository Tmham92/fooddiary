package nl.bioinf.fooddiary.model.product;

import org.junit.jupiter.api.Test;

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

        ProductGroup productGroup = ProductGroup.builder(5, "description")
                .build();

        int actualGroupCode = productGroup.getGroupCode();
        String actualGroupCodeDescription = productGroup.getGroupCodeDescription();

        assertEquals(expectedGroupCode, actualGroupCode);
        assertEquals(expectedGroupCodeDescription, actualGroupCodeDescription);
    }

    @Test
    void productGroupBoundaryCases() {

    }
}