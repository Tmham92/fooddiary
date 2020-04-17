package nl.bioinf.fooddiary.service;

import nl.bioinf.fooddiary.model.newproduct.NewProduct;

import java.util.List;
/**
 * @author Tobias ham
 * @version 0.0.1
 * date 16-03-2020
 *
 * Interface for the new product service.
 */
public interface INewProductService {
    List<NewProduct> getAllNewProducts();

    NewProduct getNewProductById(int newProductId);

    void addNewProduct(NewProduct newProduct);

    void deleteNewProduct(int newProductId);

}
