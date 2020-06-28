package nl.bioinf.fooddiary.model.recipe;

/**
 * @author Tom Wagenaar
 * Date 05-06-2020
 *
 * Standard Pojo that represent a single row in the recipe table, one row equals one product in a recipe.
 */
public class SingleRecipeProduct {
    private int id;
    private int userId;
    private int productCode;
    private String recipeGroup;
    private int quantity;
    private String unit;
    private int verified;

    public SingleRecipeProduct(int id, int userId, int productCode, String recipeGroup, int quantity, String unit, int verified) {
        this.id = id;
        this.userId = userId;
        this.productCode = productCode;
        this.recipeGroup = recipeGroup;
        this.quantity = quantity;
        this.unit = unit;
        this.verified = verified;
    }

    public int getId() { return id; }

    public int getUserId() { return userId; }

    public int getProductCode() { return productCode; }

    public String getRecipeGroup() { return recipeGroup; }

    public int getQuantity() { return quantity; }

    public String getUnit() { return unit; }

    public int getVerified() { return verified; }

    @Override
    public String toString() {
        return "SingleRecipeProduct{" +
                "id=" + id +
                ", userId=" + userId +
                ", productCode=" + productCode +
                ", recipeGroup='" + recipeGroup + '\'' +
                ", quantity=" + quantity +
                ", unit='" + unit + '\'' +
                ", verified=" + verified +
                '}';
    }
}
