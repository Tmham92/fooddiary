package nl.bioinf.fooddiary.service;

import nl.bioinf.fooddiary.model.NewProduct;

import java.util.List;
/*
@author Tobias Ham
 */
public interface INewProductService {
    List<NewProduct> getAllNewProducts();

    NewProduct getNewProductById(int newProductId);

    void addNewProduct(NewProduct newProduct);

    void deleteNewProduct(int newProductId);

}
