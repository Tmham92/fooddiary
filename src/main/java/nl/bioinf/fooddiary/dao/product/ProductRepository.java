package nl.bioinf.fooddiary.dao.product;

import nl.bioinf.fooddiary.model.product.Product;
import nl.bioinf.fooddiary.model.product.ProductDescription;

import java.util.List;

public interface ProductRepository {

    int insertProductData(Product product);

    List<ProductDescription> getAllProductsByDescription();

}
