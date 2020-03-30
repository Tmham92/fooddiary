package nl.bioinf.fooddiary.control;

import nl.bioinf.fooddiary.model.NewProduct;
import nl.bioinf.fooddiary.service.NewProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Locale;
/*
@author Tobias Ham
 */

@Controller
public class NewProductController {

    @Autowired
    NewProductService newProductService;
    Principal principal;

    @RequestMapping(value = {"/newproductform"}, method = RequestMethod.GET)
    public String newProductFormWithoutLocale(Locale locale, Model model) {
        System.out.println("Loading page without Locale");
        NewProduct newProduct = new NewProduct();
        model.addAttribute("newproductform", newProduct);
        model.addAttribute("page_name", "newproductform");
        return "redirect:" + locale.getLanguage() + "/newproductform";
    }

    @RequestMapping(value = "/{locale}/newproductform")
    public String  newProductFormWithLocale(Model model) {
        System.out.println("Loading page with Locale");
        NewProduct newProduct = new NewProduct();
        model.addAttribute("newproductform", newProduct);
        model.addAttribute("page_name", "newproductform");
        return "/newproductform";
    }


    @RequestMapping(value= {"/getnewproducts"}, method = RequestMethod.GET)
    public String getAllNewProductsWithoutLocale(Locale locale, Model model) {
        List<NewProduct> newProducts = newProductService.getAllNewProducts();
        model.addAttribute("getNewProducts", newProducts);
        return "redirect:" + locale.getLanguage() + "/getnewproducts";
    }

    @RequestMapping(value = "/{locale}/getnewproducts")
    public String getAllNewProductsWithLocale(Model model) {
        List<NewProduct> newProducts = newProductService.getAllNewProducts();
        model.addAttribute("getNewProducts", newProducts);
        return "/getnewproducts";
    }

    @RequestMapping(value = "/addednewproduct", method = RequestMethod.POST)
    public String injectNewProduct(Model model, @ModelAttribute("newproductform")
                                   @Validated NewProduct newProduct, BindingResult bindingResult) {
        System.out.println("Trying to inject data");
        newProductService.addNewProduct(newProduct);
        return "redirect:/addednewproduct";
    }

    @RequestMapping(value = {"/addednewproduct"}, method = RequestMethod.GET)
    public String addedNewProductWithoutLocale(Locale locale, Model model) {
        model.addAttribute("page_name", "addednewproduct");
        return "redirect:" + locale.getLanguage() + "/addednewproduct";
    }

    @RequestMapping(value = "/{locale}/addednewproduct")
    public String  addedNewProductWithLocale(Model model) {
        model.addAttribute("page_name", "addednewproduct");
        return "/addednewproduct";
    }
}
