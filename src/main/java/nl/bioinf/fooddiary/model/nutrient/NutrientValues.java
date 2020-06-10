package nl.bioinf.fooddiary.model.nutrient;

import com.sun.xml.bind.v2.TODO;
import nl.bioinf.fooddiary.model.DataInputChecker;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
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



    /**
     * Inner builder class that serves the NutrientValues class.
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

        // Serves the NutrientValues class.
        public NutrientValues build() {
            return new NutrientValues(this);
        }

    }

    /**
     * Class that receives and stores a single nutrient value and then assign it with _NO_VALUE_ or it holds it original
     * value. This object is then returned to the inner builder class.
     */
    public static class NutrientValue {
        private double value = -9999;

        public NutrientValue() {}

        // Checking the nutrient value and assigning a _NO_VALUE_ string whenever the nutrient is not known.
        public NutrientValue value(String value) {
            // Check on null input.


            //TODO replacement of comma to point --Hans Zijlstra

            // Assign a _NO_VALUE_ whenever value is a "".
            if (value.equals("")) {
                this.value = -9999; return this;
            } else {
                value = value.replace(",", ".");
                this.value = Double.parseDouble(value);
            }

            return this;
        }

        // Getter for a single value.
        public double getValue() {
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