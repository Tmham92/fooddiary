package nl.bioinf.fooddiary.model.product;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Hans Zijlstra
 * Class that returns mealtimes within a hashmap the key corresponts with the english translation
 * the value correspond with the dutch translation.
 */
public class Mealtimes {
    private static Map<String, String> mealtimes = new HashMap<>();

    static {
        mealtimes.put("Before Breakfast", "Voor Ontbijt");
        mealtimes.put("Breakfast", "Ontbijt");
        mealtimes.put("After Breakfast", "Na Ontbijt");
        mealtimes.put("Before Lunch", "Voor Lunch");
        mealtimes.put("Lunch", "Lunch");
        mealtimes.put("After Lunch", "Na lunch");
        mealtimes.put("Before Diner", "Voor Dinner");
        mealtimes.put("Dinner", "Dinner");
        mealtimes.put("After Diner", "Na Dinner");
    }

    public static Map<String, String> getMealtimes() {
        return Collections.unmodifiableMap(mealtimes);
    }
}
