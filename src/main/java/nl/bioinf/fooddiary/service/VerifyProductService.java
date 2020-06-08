package nl.bioinf.fooddiary.service;

import nl.bioinf.fooddiary.dao.jdbc.VerifyProductDAO;
import nl.bioinf.fooddiary.model.nutrient.NutrientNames;
import nl.bioinf.fooddiary.model.nutrient.NutrientValues;
import nl.bioinf.fooddiary.model.nutrient.NutrientValues.NutrientValue;
import nl.bioinf.fooddiary.model.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VerifyProductService implements IVerifyProductService {

    @Autowired
    private VerifyProductDAO verifyProductDAO;

    @Override
    public String getGroupCodeDescription(int groupcode) {
        String groupCodeDescription = verifyProductDAO.getGroupCodeDescription(groupcode);
        return groupCodeDescription;
    }

    @Override
    public String toString(Product product) {
        return null;
    }

    @Override
    public List<NutrientNames> getAllNutrientNamesAndAbbr() {
        return verifyProductDAO.getNutrientNamesAndAbbr();
    }

    @Override
    public NutrientValues createNutrientValueList(List<String> values) {
        String[] valueList = values.toArray(new String[0]);
        NutrientValues.NutrientValuesBuilder builder = new NutrientValues.NutrientValuesBuilder();
        builder.nutrientValue(valueList);
        NutrientValues nutrientValues = new NutrientValues(builder);
        return nutrientValues;
    }

    public boolean checkProductCode(int code) {
        return verifyProductDAO.checkProductCode(code);
    }

    @Override
    public void submitProductToDatabase(Product product) {
        verifyProductDAO.submitProductInfoToDatabase(product);
        verifyProductDAO.submitProductNutrientsToDatabase(product);
    }
}
