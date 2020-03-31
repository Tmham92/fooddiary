package nl.bioinf.fooddiary.model;

import nl.bioinf.fooddiary.model.csvparser.NutrientCsvParser;
import nl.bioinf.fooddiary.model.csvparser.ProductCsvParser;
import nl.bioinf.fooddiary.model.nutrient.Nutrient;
import nl.bioinf.fooddiary.model.nutrient.ProductNutrient;
import nl.bioinf.fooddiary.model.product.Product;

import java.util.ArrayList;
import java.util.List;

public class PrepareDataInjection {

    public List<List<ProductNutrient>> startRearrangeData() {
        ProductCsvParser productParser = new ProductCsvParser();
        List<Product> productList = productParser.readCsvFile();

        NutrientCsvParser nutrientParser = new NutrientCsvParser();
        List<Nutrient> nutrientList = nutrientParser.readCsvFile();

        List<List<ProductNutrient>> productNutrientData = rearrangeDataLists(productList, nutrientList);
        return productNutrientData;
    }

    public List<List<ProductNutrient>> rearrangeDataLists(List<Product> productList, List<Nutrient> nutrientList) {
        List<List<ProductNutrient>> productNutrientData = new ArrayList<>();

        for (Product product:productList) {
            List<ProductNutrient> productNutrientList = mergeNutrientData(product, nutrientList);
            productNutrientData.add(productNutrientList);
        }

        return productNutrientData;
    }

    public static List<ProductNutrient> mergeNutrientData(Product product, List<Nutrient> nutrientList) {
        List<ProductNutrient> productNutrientList = new ArrayList<>();

        for (int index = 0; index < nutrientList.size(); index++) {
            String nutrientCode = nutrientList.get(index).getNutrientCode();
            String nutrientValue = product.getNutrientValues().getNutrients().get(index).getValue();

            ProductNutrient productNutrient = ProductNutrient.builder(product.getCode(), nutrientCode, nutrientValue)
                    .build();

            productNutrientList.add(productNutrient);
        }
        return productNutrientList;
    }
}
