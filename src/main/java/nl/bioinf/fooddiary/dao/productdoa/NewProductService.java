package nl.bioinf.fooddiary.dao.productdoa;

import nl.bioinf.fooddiary.model.NewProduct;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class NewProductService implements INewProductService {
    @Autowired
    private NewProductDAO newProductDAO;

    @Override
    public List<NewProduct> getAllNewProducts() {
        return newProductDAO.getAllNewProducts();
    }

    @Override
    public NewProduct getNewProductById(int newProductId) {
        return null;
    }

    @Override
    public synchronized boolean addNewProduct(NewProduct newProduct) {
        if(newProductDAO.newProductExists(newProduct.getId())) {
            return false;
        } else {
            newProductDAO.addNewProduct(newProduct);
            return true;
        }
    }

    @Override
    public void deleteNewProduct(int newProductId) {

    }

}
