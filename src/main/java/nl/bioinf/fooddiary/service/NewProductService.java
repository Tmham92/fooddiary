package nl.bioinf.fooddiary.service;

import nl.bioinf.fooddiary.dao.newproduct.NewProductDAO;
import nl.bioinf.fooddiary.model.NewProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @author Tobias ham
 * @version 0.0.1
 * date 16-03-2020
 *
 * Class that receives input from the controller and sends it to the Data Access Object.
 */
@Service
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
    public void addNewProduct(NewProduct newProduct) {
        newProductDAO.addNewProduct(newProduct);
    }

    @Override
    public void deleteNewProduct(int newProductId) {
        newProductDAO.deleteNewProduct(newProductId);
    }


}
