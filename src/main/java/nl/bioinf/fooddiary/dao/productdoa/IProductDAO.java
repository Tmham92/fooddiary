package nl.bioinf.fooddiary.dao.productdoa;

import nl.bioinf.fooddiary.model.NewProduct;
import java.util.List;

public interface IProductDAO {
    List<NewProduct> getAllNewProducts();

    NewProduct getNewProduct(Integer id, String description);


}
