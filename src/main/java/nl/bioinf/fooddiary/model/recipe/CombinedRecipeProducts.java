package nl.bioinf.fooddiary.model.recipe;

import nl.bioinf.fooddiary.model.product.Product;

import java.util.List;

/**
 * @author Tom Wagenaar
 * Date: 09-06-2020
 *
 * POJO that is used to store recipe info. This class contains the recipe name, a list containing the products from
 * that recipe and a list containing products from the product table that correspond to the products in the recipe list.
 */
public class CombinedRecipeProducts {
    private String recipeName;
    private List<SingleRecipeProduct> recipeTableData;
    private List<Product> productTableData;

    public CombinedRecipeProducts(String recipeName, List<SingleRecipeProduct> recipeTableData, List<Product> productTableData) {
        this.recipeName = recipeName;
        this.recipeTableData = recipeTableData;
        this.productTableData = productTableData;
    }

    public String getRecipeName() { return recipeName; }

    public List<SingleRecipeProduct> getRecipeTableData() { return recipeTableData; }

    public List<Product> getProductTableData() { return productTableData; }

    @Override
    public String toString() {
        return "CombinedRecipeProducts{" +
                "recipeName='" + recipeName + '\'' +
                ", recipeTableData=" + recipeTableData +
                ", productTableData=" + productTableData +
                '}';
    }
}
