package nl.bioinf.fooddiary.dao;

import nl.bioinf.fooddiary.model.product.*;

import java.sql.Date;
import java.util.List;

/**
 * @author Tom Wagenaar
 *
 * Interface that support the ProductDAO, this interface is used in the data controller to call the methods in the
 * ProductDAO. This interface supports inserting the product data into the database when called.
 */
public interface ProductRepository {

    int getProductId(String lang,String description);

    int getUserIdByUsername(String username);

    int insertProductData(Product product);

    List<String> getAllDutchProductDescriptions();

    List<String> getAllEnglishProductDescriptions();

    String getMeasurementUnitByDescription(int productId);

    int insertProductIntoDiary(String lang, int userId, int productId, ProductEntry productEntry);

    List<ProductEntry> getDiaryEntriesByDate(String lang,int id, String date);

    int removeDiaryEntryById(int diaryEntryId);

    Product getSpecificProductByDescription(String productDescription);

    Product getSpecificProductByProductCode(int productCode);

    List<ProductOccurrence> getProductOccurrences(String lang);

}
