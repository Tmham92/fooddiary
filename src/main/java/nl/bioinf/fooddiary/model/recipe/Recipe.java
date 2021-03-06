package nl.bioinf.fooddiary.model.recipe;

import nl.bioinf.fooddiary.model.DataInputChecker;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

/**
 * @author Tom Wagenaar
 * @version 0.0.2
 * date 22-04-2020
 *
 * Class that is used to store data that the user provides in the recipe form, this object is then used to insert
 * data into the recipe table from the fooddiary database. This class doesn't have any setters and the variables are
 * final to ensure immutability, except verified. In this class there is an inner builder class, this class implemented
 * in such a way to give the class atomic construction, easy combination of parameters and very readable client code. In
 * the builder method the userID and productDescriptionsList aren't checker, because they are foreign keys to other tables.
 */
public class Recipe {
    @NotNull
    @Min(0)
    private int userID;

    @NotNull
    private List<String> productDescriptionList;

    @NotNull
    @Size(min = 3, max = 255)
    private String recipeGroup;

    @NotNull
    private List<Integer> quantity;

    @NotNull
    private List<String> quantityUnit;

    @NotNull
    @Min(0)
    @Max(1)
    private int verified;


    /**
     * Constructor that uses the inner builder class that is made in te builder method, this RecipeBuilder object
     * contains the required variable values.
     * @param builder RecipeBuilder object
     */
    public Recipe(RecipeBuilder builder) {
        this.userID = builder.userID;
        this.productDescriptionList = builder.productDescriptionList;
        this.recipeGroup = builder.recipeGroup;
        this.quantity = builder.quantity;
        this.quantityUnit = builder.quantityUnit;
        this.verified = builder.verified;
    }

    public Recipe() {}

    /**
     * Method that receives the required values for the instance variables, makes a new inner builder class and passing
     * those values on to the class. Before that check the recipeGroup, quantity and verified input.
     * @param userID (int)
     * @param productDescriptionsList (List<String>)
     * @param recipeGroup (String)
     * @param quantity (int)
     * @param verified (int)
     * @return RecipeBuilder object that consists out of the required instance variables.
     */
    public static RecipeBuilder builder(int userID, List<String> productDescriptionsList, String recipeGroup, List<Integer> quantity, List<String> unit, int verified) {
        // Check the recipeGroup on null input and length, in between trim it.
        DataInputChecker.checkStringInputNull(recipeGroup, "recipeGroup");
        recipeGroup = recipeGroup.trim();
        DataInputChecker.checkInputLength(recipeGroup, 255, "recipeGroup");

        return new RecipeBuilder(userID, productDescriptionsList, recipeGroup, quantity, unit, verified);
    }

    // Getters
    public int getUserID() { return userID; }

    public List<String> getProductDescriptionList() { return productDescriptionList; }

    public String getRecipeGroup() { return recipeGroup; }

    public List<Integer> getQuantity() { return quantity; }

    public List<String> getQuantityUnit() {
        return quantityUnit;
    }

    public int getVerified() { return verified; }

    public void setUserID(int userID) { this.userID = userID; }

    public void setVerified(int verified) { this.verified = verified; }

    @Override
    public String toString() {
        return "Recipe{" +
                "userID=" + userID +
                ", productDescriptionList=" + productDescriptionList +
                ", recipeGroup='" + recipeGroup + '\'' +
                ", quantity=" + quantity +
                ", unit=" + quantityUnit +
                ", verified=" + verified +
                '}';
    }

    /**
     * Inner builder class that is used for atomic construction, easy combination of parameters and very readable client
     * code. A object of this class is made in the builder method, that method also passes on the values for the
     * variables that are required.
     */
    public static class RecipeBuilder {
        // Required instance variables.
        private final int userID;
        private final List<String> productDescriptionList;
        private final String recipeGroup;
        private final List<Integer> quantity;
        private final List<String> quantityUnit;
        private int verified;

        public RecipeBuilder(int userID, List<String> productDescriptionList, String recipeGroup, List<Integer> quantity, List<String> quantityUnit, int verified) {
            this.userID = userID;
            this.productDescriptionList = productDescriptionList;
            this.recipeGroup = recipeGroup;
            this.quantity = quantity;
            this.quantityUnit = quantityUnit;
            this.verified = verified;
        }

        /**
         * Serves the Recipe class.
         * @return Recipe object with the required parameters that correspond to the instance variables.
         */
        public Recipe build() {return new Recipe(this);}
    }
}
