package nl.bioinf.fooddiary.model.nutrient;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Tom Wagenaar
 * @version 0.0.1
 * date: 20-03-2020
 *
 * The NutrientValues class stores all the nutrient values in the nevo_online_2019_Product.csv file. This class
 * is called and the builder method return a NutrientValuesBuilder object, therefore the inner builder class can be
 * used. This class receives a String array containing all the nutrient values for a single product. Then every single
 * nutrient is passed on to the NutrientValue class, where it is passed back with the original value or a _NO_VALUE_
 * value. When this is done, the NutrientValue object is returned and added to a list, furthermore this list is
 * returned to the Product class. For more information about the builder pattern go to ProductGroup class javadoc. There
 * are only getters and no setters and the value variables are final to ensure immutability.
 */
public class NutrientValues {
    private List<NutrientValue> nutrients;

    /**
     * Constructor where every NutrientValue in the nutrients list in the inner builder class is added to the nutrient
     * List in this class.
     * @param builder, NutrientValuesBuilder object.
     */
    private NutrientValues(NutrientValuesBuilder builder) {
        this.nutrients = new ArrayList<>();
        this.nutrients.addAll(builder.nutrients);
    }

    /**
     * Static method that serves the NutrientValuesBuilder inner class with no parameters.
     * @return NutrientValuesBuilder object.
     */
    public static NutrientValuesBuilder builder() {return new NutrientValuesBuilder(); }

    // GETTER
    public List<NutrientValue> getNutrients() {
        return nutrients;
    }

    @Override
    public String toString() {
        // Use java 8 stream to get every single nutrient and parse it together for a print.
        String nutrientValueStr = nutrients.stream()
                .map(i -> i.value)
                .collect(Collectors.joining(" & "));
        return "NutrientValues{" +
                "\n\tvalue=" + nutrientValueStr +
                '}';
    }

    /**
     * Inner builder class that serves the NutrienValues class.
     */
    public static class NutrientValuesBuilder {
        private List<NutrientValue> nutrients = new ArrayList<>();

        // Constructor with no required parameters.
        private NutrientValuesBuilder() {}

        /**
         * Method that is called whenever a String array is passed on, every string in this array is then transformed
         * into a NutrientValue object and added to the nutrients list.
         * @param values (String[])
         * @return this
         */
        public NutrientValuesBuilder nutrientValue(String[] values) {
            for (String value:values) {
                NutrientValue nutrientValue = new NutrientValue();
                nutrientValue.value(value);
                this.nutrients.add(nutrientValue);
            }

            return this;
        }

        /**
         * Serves the NutrientValues class.
         * @return NutrientValues object.
         */
        public NutrientValues build() {
            return new NutrientValues(this);
        }

    }

    /**
     * Class that receives a single nutrient value and then assign it with _NO_VALUE_ or it holds it original value.
     * This object is then returned to the inner builder class.
     */
    public static class NutrientValue {
        @NotNull
        private String value = "_NO_VALUE_";

        public NutrientValue() {}

        public NutrientValue value(String value) {
            // While parsing the nevo product table the nutrients variables that aren't defined are "",
            // therefore they aren't seen as values that aren't defined.
            if (value.equals("")) {
                this.value = "_NO_VALUE_"; return this;
            }
            this.value = value; return this;
        }

        @Override
        public String toString() {
            return "{" +
                    "value='" + value + '\'' +
                    '}';
        }
    }


}
