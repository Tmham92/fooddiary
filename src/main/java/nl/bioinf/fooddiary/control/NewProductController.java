package nl.bioinf.fooddiary.control;

import nl.bioinf.fooddiary.model.NewProduct;
import nl.bioinf.fooddiary.service.NewProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
/*
@author Tobias Ham
 */

@Controller
public class NewProductController {

    @Autowired
    NewProductService newProductService;

/*
    @RequestMapping(value="newproductform", method = RequestMethod.GET)
    public ModelAndView newProducts(Model model) {
        ModelAndView mav = new ModelAndView("newproductform");
        mav.addObject("newProducts", new NewProduct());
        return mav;
    }
*/
    @RequestMapping(value="/newproductform", method = RequestMethod.POST)
    public String processRequest(@ModelAttribute("newProduct") NewProduct newProduct) {
        System.out.println(newProduct);
        newProductService.addNewProduct(newProduct);
        List<NewProduct> newProducts = newProductService.getAllNewProducts();
        ModelAndView model = new ModelAndView("getAllNewProducts");
        model.addObject("newProducts", newProducts);
        return "newproductform";
    }

    @RequestMapping("/getnewproducts")
    public ModelAndView getNewProducts() {
        List<NewProduct> newProducts = newProductService.getAllNewProducts();
        ModelAndView model = new ModelAndView("getnewproducts");
        model.addObject("newProducts", newProducts);
        return model;
    }

    @GetMapping("/newproductform")
    public String newProductForm(Model model) {
        model.addAttribute("newproductform", new NewProduct());
        return "newproductform";
    }

/*
    @PostMapping("/newproductform")
    public String newProductFormSubmit(@ModelAttribute NewProduct newProduct) {
        return "newproductform";
    }
*/
}
