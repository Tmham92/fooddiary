package nl.bioinf.fooddiary.model.product;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Hans Zijlstra
 */
public class Mealtimes {
    public static void main(String[] args) {
        System.out.println(mealtimes);
        for (Map.Entry<String, String> entry : Mealtimes.getMealtimes().entrySet()) {
            if (entry.getValue().equals("Avondeten")) {
                System.out.println(entry.getKey());
            }

        }
    }
    private static Map<String, String> mealtimes = new HashMap<>();

    static {
        mealtimes.put("Breakfast", "Ontbijt");
        mealtimes.put("Snack", "Tussendoortje");
        mealtimes.put("Lunch", "Middageten");
        mealtimes.put("Dinner", "Avondeten");

    }

    public static Map<String, String> getMealtimes() {
        return Collections.unmodifiableMap(mealtimes);
    }
}
