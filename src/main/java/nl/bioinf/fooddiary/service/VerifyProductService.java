package nl.bioinf.fooddiary.service;

import nl.bioinf.fooddiary.dao.jdbc.VerifyProductDAO;
import nl.bioinf.fooddiary.model.nutrient.NutrientNames;
import nl.bioinf.fooddiary.model.product.Product;
import nl.bioinf.fooddiary.model.product.ProductDescription;
import nl.bioinf.fooddiary.model.product.ProductGroup;
import nl.bioinf.fooddiary.model.product.ProductInfoExtra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        System.out.println(product);
        return null;
    }

    @Override
    public List<NutrientNames> getAllNutrientNamesAndAbbr() {
        return verifyProductDAO.getNutrientNamesAndAbbr();
    }

//    @Override
//    public List<String> nutrientAbbreviations() {
//        var nutrientAbbrList = verifyProductDAO.getAllNutrientAbbreviations();
//        return nutrientAbbrList;
//    }
//
//    @Override
//    public List<String> nutrientNames() {
//        var nutrientNameList = verifyProductDAO.getAllNutrientNames();
//        return nutrientNameList;
//    }


}
