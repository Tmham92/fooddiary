package nl.bioinf.fooddiary.model.nutrient;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NutrientTest {

    @Test
    void nutrientSunnyInput() {
        Nutrient nutrient = Nutrient.builder("ENERCJ", "Energie",
                "energy kJ, total metabolisable", "KJ").build();

        String expectedNutrientCode = "ENERCJ";
        String expectedNameDutch = "Energie";
        String expectedNameEnglish = "energy kJ, total metabolisable";
        String expectedUnit = "KJ";

        assertEquals(expectedNutrientCode, nutrient.getNutrientCode());
        assertEquals(expectedNameDutch, nutrient.getNameDutch());
        assertEquals(expectedNameEnglish, nutrient.getNameEnglish());
        assertEquals(expectedUnit, nutrient.getMeasurementUnit());
    }



}