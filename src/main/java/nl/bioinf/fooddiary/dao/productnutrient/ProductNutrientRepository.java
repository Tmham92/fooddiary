package nl.bioinf.fooddiary.dao.productnutrient;


import nl.bioinf.fooddiary.model.nutrient.ProductNutrient;
import org.springframework.stereotype.Repository;

public interface ProductNutrientRepository {

    ProductNutrient getProductNutrientById(int idPattern);
}
