package nl.bioinf.fooddiary.control;

import nl.bioinf.fooddiary.model.NewProduct;
import nl.bioinf.fooddiary.service.NewProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;
/*
@author Tobias Ham
*/
@Controller
public class NewProductController {

    @Autowired
    NewProductService newProductService;

    @RequestMapping(value = {"/newproductform"}, method = RequestMethod.GET)
    public String newProductFormWithoutLocale(Locale locale, Model model) {
        NewProduct newProduct = new NewProduct();
        model.addAttribute("newproductform", newProduct);
        return "redirect:" + locale.getLanguage() + "/newproductform";
    }

    @RequestMapping(value = "/{locale}/newproductform")
    public String  newProductFormWithLocale(Model model) {
        NewProduct newProduct = new NewProduct();
        model.addAttribute("newproductform", newProduct);
        return "/newproductform";
    }


    @RequestMapping(value = "/addednewproduct", method = RequestMethod.POST)
    public String injectNewProduct(@ModelAttribute("newproductform")
                                   @Validated NewProduct newProduct, BindingResult bindingResult) {
        newProductService.addNewProduct(newProduct);
        return "redirect:/addednewproduct";
    }

    @RequestMapping(value = {"/addednewproduct"}, method = RequestMethod.GET)
    public String addedNewProductWithoutLocale(Locale locale) {
        return "redirect:" + locale.getLanguage() + "/addednewproduct";
    }

    @RequestMapping(value = "/{locale}/addednewproduct")
    public String  addedNewProductWithLocale() {
        return "/addednewproduct";
    }
}
