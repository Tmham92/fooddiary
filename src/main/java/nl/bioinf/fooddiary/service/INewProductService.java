package nl.bioinf.fooddiary.service;

import nl.bioinf.fooddiary.model.NewProduct;

import java.util.List;

public interface INewProductService {
    List<NewProduct> getAllNewProducts();

    NewProduct getNewProductById(int newProductId);

    boolean addNewProduct(NewProduct newProduct);

    void deleteNewProduct(int newProductId);


}
