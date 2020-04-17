package nl.bioinf.fooddiary.model.csvparser;

import nl.bioinf.fooddiary.model.product.Product;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Tom Wagenaar
 * @version 0.0.1
 * date: 18-03-2020
 *
 * JUnit testing for the methods in the ProductCsvParser class.
 */
class ProductCsvParserTest {

    @Test
    void parseCsvFileSunny() {
        String[] testLine = {"1","Aardappelen en knolgewassen","1","Aardappelen rauw","Potatoes raw","","g","100",
                            "Per 100g.","","","371","88","2",""};

        ProductCsvParser parser = new ProductCsvParser();
        Product product = parser.parseCsvFile(testLine);

        int actualCode = product.getCode();
        int expectedCode = 1;

        String actualProductDescriptionEnglish = product.getProductDescription().getDescriptionEnglish();
        String expectedProductDescriptionEnglish = "Potatoes raw";

        String actualNutrientValue = product.getNutrientValues().getNutrients().get(0).getValue();
        String expectedNutrientValue = "371";

        String actualNutrientValue2 = product.getNutrientValues().getNutrients().get(3).getValue();
        String expectedNutrientValue2 = "_NO_VALUE_";

        assertEquals(expectedCode, actualCode);
        assertEquals(expectedProductDescriptionEnglish, actualProductDescriptionEnglish);
        assertEquals(expectedNutrientValue, actualNutrientValue);
        assertEquals(expectedNutrientValue2, actualNutrientValue2);
    }


    @Test
    void parseCsvFileNullInput() {
        String[] testLine = {null,"Aardappelen en knolgewassen","4","Aardappelen rauw","Potatoes raw","","g","100",
                "Per 100g.","","","371","88","2"};

    }

}