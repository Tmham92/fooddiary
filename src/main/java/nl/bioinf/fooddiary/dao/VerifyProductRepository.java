package nl.bioinf.fooddiary.dao;

import nl.bioinf.fooddiary.model.nutrient.NutrientNames;

import java.util.List;

public interface VerifyProductRepository {

    int getAllProductGroupNumbers();

    String getGroupCodeDescription(int groupcode);
//
//     List<String> getAllNutrientNames();
//
//    List<String> getAllNutrientAbbreviations();

    List<NutrientNames> getNutrientNamesAndAbbr();
}
