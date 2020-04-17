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

/**
 * @author Tobias Ham
 * @version 0.0.1
 * date 17-03-2020
 *
 * Controller class that deals with web based requests for adding new unknown product into the database.
 */
@Controller
public class NewProductController {

    @Autowired
    NewProductService newProductService;

    /**
     * Method that opens the web page based on the value attribute without a known Locale.
     * @param locale (Locale)
     * @param model (Model)
     * @return (String)
     */
    @RequestMapping(value = {"/newproductform"}, method = RequestMethod.GET)
    public String newProductFormWithoutLocale(Locale locale, Model model) {
        NewProduct newProduct = new NewProduct();
        model.addAttribute("newproductform", newProduct);
        return "redirect:" + locale.getLanguage() + "/newproductform";
    }

    /**
     * Method that opens the web page based on the value attribute with a known Locale.
     * @param model (Model)
     * @return (String)
     */
    @RequestMapping(value = "/{locale}/newproductform")
    public String  newProductFormWithLocale(Model model) {
        NewProduct newProduct = new NewProduct();
        model.addAttribute("newproductform", newProduct);
        return "/newproductform";
    }


    /**
     * Method that injects a valid NewProduct form submission into the database and redirect the user to a new web page.
     * @param newProduct (NewProduct object)
     * @param bindingResult (BindingResult object)
     * @return (String)
     */
    @RequestMapping(value = "/addednewproduct", method = RequestMethod.POST)
    public String injectNewProduct(@ModelAttribute("newproductform")
                                   @Validated NewProduct newProduct, BindingResult bindingResult) {
        newProductService.addNewProduct(newProduct);
        return "redirect:/addednewproduct";
    }

    /**
     * Method that opens the addednewproduct web page when locale is unknown.
     * @param locale (Locale)
     * @return (String)
     */
    @RequestMapping(value = {"/addednewproduct"}, method = RequestMethod.GET)
    public String addedNewProductWithoutLocale(Locale locale) {
        return "redirect:" + locale.getLanguage() + "/addednewproduct";
    }

    /**
     * Method that opens the addednewproduct web page when Locale is known.
     * @return (String)
     */
    @RequestMapping(value = "/{locale}/addednewproduct")
    public String  addedNewProductWithLocale() {
        return "/addednewproduct";
    }
}
