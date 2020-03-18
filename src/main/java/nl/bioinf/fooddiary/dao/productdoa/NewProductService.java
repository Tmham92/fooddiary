package nl.bioinf.fooddiary.dao.productdoa;

import nl.bioinf.fooddiary.model.NewProduct;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class NewProductService implements IProductDAO {
    @Autowired
    private NewProductDAO newProductDAO;

    @Override
    public List<NewProduct> getAllNewProducts() {
        return newProductDAO.getAllNewProducts();
    }

    @Override
    public NewProduct getNewProduct(Integer id, String description) {
        return newProductDAO.getNewProduct(id, description);
    }
}
