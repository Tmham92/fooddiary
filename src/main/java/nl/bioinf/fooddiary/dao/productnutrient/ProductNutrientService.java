package nl.bioinf.fooddiary.dao.productnutrient;

import nl.bioinf.fooddiary.model.nutrient.ProductNutrient;
import org.springframework.stereotype.Service;

@Service
public interface ProductNutrientService {

    ProductNutrient getProductNutrientById(int idPattern);
}
