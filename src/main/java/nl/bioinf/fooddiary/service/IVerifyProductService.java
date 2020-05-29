package nl.bioinf.fooddiary.service;

import nl.bioinf.fooddiary.dao.jdbc.VerifyProductDAO;
import nl.bioinf.fooddiary.model.nutrient.NutrientNames;
import nl.bioinf.fooddiary.model.product.Product;
import nl.bioinf.fooddiary.model.product.ProductDescription;
import nl.bioinf.fooddiary.model.product.ProductGroup;
import nl.bioinf.fooddiary.model.product.ProductInfoExtra;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public interface IVerifyProductService {

    String getGroupCodeDescription(int groupcode);

   String toString(Product product);
//
//   List<String> nutrientNames();
//
//   List<String> nutrientAbbreviations();
    List<NutrientNames> getAllNutrientNamesAndAbbr();

}
