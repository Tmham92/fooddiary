package nl.bioinf.fooddiary.dao.productnutrient;

import nl.bioinf.fooddiary.model.nutrient.ProductNutrient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ImplementationProductNutrientService implements ProductNutrientService{

    @Autowired
    private ProductNutrientRepository repository;

    @Transactional
    public ProductNutrient getProductNutrientById(int idPattern) {
        return repository.getProductNutrientById(idPattern);
    }
}
