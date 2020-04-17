package nl.bioinf.fooddiary.dao.nutrient;

import nl.bioinf.fooddiary.model.nutrient.Nutrient;

/**
 * @author Tom Wagenaar
 *
 * Interface that support the NutrientDAO, this interface is used in the data controller to call the methods in the
 * NutrientDAO. This interface supports inserting the nutrient data into the database when called.
 */
public interface NutrientRepository {

    int insertNutrientData(Nutrient nutrient);
}
