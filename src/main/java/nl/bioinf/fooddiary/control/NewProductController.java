package nl.bioinf.fooddiary.control;

import nl.bioinf.fooddiary.model.NewProduct;
import nl.bioinf.fooddiary.model.NewProductForm;
import nl.bioinf.fooddiary.service.INewProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;


@Controller
@RequestMapping("user")

public class NewProductController {
    @Autowired
    private INewProductService newProductService;

    @RequestMapping(value="/savenewproduct")
    public void saveNewProduct() {

    }


    @RequestMapping(value = {"/newproductform"}, method = RequestMethod.GET)
    public String readNewProductForm(Model model) {
        model.addAttribute("newProduct", new NewProduct());
        return "/newproductform";
    }



    @PostMapping("/newproductform")
    public String newProductFormSubmit(@ModelAttribute NewProductForm newProductForm) {
        return "/newproductform";
    }

    @GetMapping("newproductform/{id}")
    public ResponseEntity<NewProduct> getNewProductById(@PathVariable("id") Integer id) {
        NewProduct newProduct = newProductService.getNewProductById(id);
        return new ResponseEntity<NewProduct>(newProduct, HttpStatus.OK);
    }

    @GetMapping("newProducts")
    public ResponseEntity<List<NewProduct>> getAllNewProducts() {
        List<NewProduct> list = newProductService.getAllNewProducts();
        return new ResponseEntity<List<NewProduct>>(list, HttpStatus.OK);
    }


}
