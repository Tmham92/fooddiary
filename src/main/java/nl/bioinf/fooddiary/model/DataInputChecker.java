package nl.bioinf.fooddiary.model;

/**
 * @author Tom Wagenaar
 * date: 14-04-2020
 *
 * Class that consists out of checks for the Product, Nutrient, ProductNutrient recipe class values, these include a
 * check for null input, input length (input must be of specific length), input size (input must be between a specific
 * range of integers and a method that changes the input string to an integer. All methods have exception handling.
 */
public class DataInputChecker {

    /**
     * Method that checks if the input is a null value, furthermore receives a column name to make a proper exception.
     * @throws IllegalArgumentException when the input is a null value.
     * @param input (String)
     * @param variableName (String) Represent the name of the variable.
     */
    public static void checkStringInputNull(String input, String variableName) {
        if (input == null) {
            throw new IllegalArgumentException(variableName + " can't be a null value!");
        }
    }

    /**
     * Method that changes the input to an integer.
     * @throws IllegalArgumentException when input isn't an integer.
     * @param input (String)
     * @param variableName (String)
     * @return checkedInput (int)
     */
    public static int changeStringToInt(String input, String variableName) {
        int checkedInput;

        try {
            checkedInput = Integer.parseInt(input);
        } catch (NumberFormatException numbException) {
            throw new IllegalArgumentException(variableName + " isn't an integer!");
        }

        return checkedInput;
    }

    /**
     * Method that checks if the input string is bigger or the same as 1 and smaller or the same as the max length. This
     * max length is a parameter.
     * @throws IllegalArgumentException when the input isn't between 0 and or the same as the maxlength.
     * @param input (String)
     * @param maxLength (int)
     * @param variableName (String)
     */
    public static void checkInputLength(String input, int maxLength, String variableName) {
        if (input.length() < 1 || input.length() >= maxLength) {
            throw new IllegalArgumentException(variableName + " length must be between 0 and or the same as " + maxLength);
        }
    }

    /**
     * Method that checks if the input integer is between 0 and the max value.
     * @param input (int)
     * @param maxValue (int)
     * @param variableName (String)
     */
    public static void checkInputSize(int input, int maxValue, String variableName) {
        if (input < 0 || input >= maxValue) {
            throw new IllegalArgumentException(variableName + " value must be between 0 and or the same as " + maxValue);
        }
    }
}
