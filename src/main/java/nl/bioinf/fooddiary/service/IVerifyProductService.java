package nl.bioinf.fooddiary.service;

import nl.bioinf.fooddiary.model.newproduct.NewProduct;
import nl.bioinf.fooddiary.model.nutrient.NutrientNames;
import nl.bioinf.fooddiary.model.nutrient.NutrientValues;
import nl.bioinf.fooddiary.model.product.Product;

import java.util.List;
/**
 @Author Tobias Ham
 */
public interface IVerifyProductService {

    String getGroupCodeDescription(int groupcode);

   String toString(Product product);

    List<NutrientNames> getAllNutrientNamesAndAbbr();

    NutrientValues createNutrientValueList(List<String> values);

    boolean checkProductCode(int code);

    void submitProductToDatabase(Product product);

    int checkHighestProductCode();

    void deleteVerifiedProductFromUnverified(Integer id);

    String getProductPicture(NewProduct newProduct);
}
