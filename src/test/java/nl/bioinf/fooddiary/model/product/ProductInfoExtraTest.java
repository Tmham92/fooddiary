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
 * date: 16-04-2020
 *
 * Junit test for the ProductInfoExtra class.
 */
class ProductInfoExtraTest {

    @Test
    void infoExtraSunnyInput() {
        ProductInfoExtra productInfoExtra = ProductInfoExtra.builder()
                .enrichedWith("Fe, Ca")
                .tracesOf("F10:0, F14:1CIS, F15:0, F18:2TTN6, F20:2CN6, F20:2TT, F20:4CN6, F21:0, F22:3CN3, F23:0, F24:1CIS, F8:0, FAPUXR")
                .build();

        String expectedEnrichedWith = "Fe, Ca";
        String expectedTracesOf = "F10:0, F14:1CIS, F15:0, F18:2TTN6, F20:2CN6, F20:2TT, F20:4CN6, F21:0, F22:3CN3, F23:0, F24:1CIS, F8:0, FAPUXR";

        assertEquals(expectedEnrichedWith, productInfoExtra.getEnrichedWith());
        assertEquals(expectedTracesOf, productInfoExtra.getTracesOf());
    }
}