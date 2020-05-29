package nl.bioinf.fooddiary.dao;

import nl.bioinf.fooddiary.model.newproduct.NewProduct;

import java.sql.Time;
import java.util.Date;
import java.util.List;
/**
    @Author Tobias Ham
 */
public interface NewProductRepository {
    List<NewProduct> getAllNewProducts();

    NewProduct getNewProduct(Integer id, String description, Date date, Time time_of_day, String mealtime, double quantity);

    NewProduct getNewProductById(int newProductId);

    void deleteNewProduct(int newProductId);

    void addNewProduct(NewProduct newProduct);

    boolean newProductExists(int newProductId);

    void addNewProductPictureLocation(String pictureLocation);

}
