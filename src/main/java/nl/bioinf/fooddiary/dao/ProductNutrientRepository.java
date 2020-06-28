package nl.bioinf.fooddiary.dao;

import nl.bioinf.fooddiary.model.nutrient.ProductNutrient;

/**
 * @author Tom Wagenaar
 *
 * Interface that support the ProductNutrientDAO, this interface is used to insert the product nutrient data into the
 * database.
 */
public interface ProductNutrientRepository {

    int insertProductNutrientData(ProductNutrient productNutrient);
}
