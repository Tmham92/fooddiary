package nl.bioinf.fooddiary.dao.productdoa;

import nl.bioinf.fooddiary.model.NewProduct;
import java.util.List;

public interface IProductDAO {
    List<NewProduct> getAllNewProducts();

    NewProduct getNewProductById(int newProductId);

    void deleteNewProduct(int newProductId);

    NewProduct getNewProduct(Integer id, String description);


}
