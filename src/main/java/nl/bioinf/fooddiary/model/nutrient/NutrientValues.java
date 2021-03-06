package nl.bioinf.fooddiary.model.nutrient;

import nl.bioinf.fooddiary.model.DataInputChecker;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Tom Wagenaar
 * @version 0.0.1
 * date: 20-03-2020
 *
 * This class stores all the nutrient data from a single product in the Nevo_Online_2019_Product.csv file into a object.
 * The inner builder class receives a string array containing the nutrient data for a single product. For each nutrient
 * a NutrientValue object containing that nutrient value is made. This NutrientValue object is then added to the
 * nutrients list.
 */
public class NutrientValues {
    private List<NutrientValue> nutrients;


    /**
     * Constructor where every NutrientValue in the nutrients list in the inner builder class is added to the nutrient
     * List in this class.
     * @param builder, NutrientValuesBuilder object.
     */
    public NutrientValues(NutrientValuesBuilder builder) {
        this.nutrients = new ArrayList<>();
        this.nutrients.addAll(builder.nutrients);
    }

    /**
     * Static method that serves the NutrientValuesBuilder inner class with no parameters.
     * @return NutrientValuesBuilder object.
     */
    public static NutrientValuesBuilder builder() {return new NutrientValuesBuilder(); }

    public void setNutrients(List<NutrientValue> nutrients) {
        this.nutrients = nutrients;
    }

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
     * Inner builder class that serves the NutrientValues class.
     */
    public static class NutrientValuesBuilder {
        private List<NutrientValue> nutrients = new ArrayList<>();

        // Constructor with no required parameters.
        public NutrientValuesBuilder() {}

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
        // Serves the NutrientValues class.
        public NutrientValues build() {
            return new NutrientValues(this);
        }
    }

    /**
     * Class that receives and stores a single nutrient value and then assign it with _NO_VALUE_ or it holds it original
     * value. This object is then returned to the inner builder class.
     */
    public static class NutrientValue implements Serializable {
        private String value = "_NO_VALUE_";

        public NutrientValue() {}

        // Checking the nutrient value and assigning a _NO_VALUE_ string whenever the nutrient is not known.
        public NutrientValue value(String value) {
            // Check on null input.
            DataInputChecker.checkStringInputNull(value, "nutrientValue");

            // Trim the value.
            value = value.trim();

            // Assign a _NO_VALUE_ whenever value is a "".
            if (value.equals("")) {
                this.value = "_NO_VALUE_"; return this;
            } else {
                this.value = value;
            }

            return this;
        }

        public void setValue(String value) {
            this.value = value;
        }

        // Getter for a single value.
        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return "{" +
                    "value='" + value + '\'' +
                    '}';
        }
    }
}