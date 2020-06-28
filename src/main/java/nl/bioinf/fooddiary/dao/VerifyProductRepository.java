package nl.bioinf.fooddiary.dao;

import nl.bioinf.fooddiary.model.nutrient.NutrientNames;
import nl.bioinf.fooddiary.model.product.Product;

import java.util.List;

/**
 @Author Tobias Ham
 */
public interface VerifyProductRepository {

    String getGroupCodeDescription(int groupcode);

    List<NutrientNames> getNutrientNamesAndAbbr();

    boolean checkProductCode(int code);

    void submitProductInfoToDatabase(Product product);

    List<String> getNutrientCodes();

    void submitProductNutrientsToDatabase(Product product);

    int getHighestProductCode();

    void deleteVerifiedProductFromUnverified(Integer id);

    String getProductPicture(int productId);
}
