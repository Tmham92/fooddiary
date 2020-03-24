package nl.bioinf.fooddiary.service;

import nl.bioinf.fooddiary.dao.newproductdao.NewProductDAO;
import nl.bioinf.fooddiary.model.NewProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/*
@author Tobias Ham
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
    }


}
