package nl.bioinf.fooddiary.control;

import nl.bioinf.fooddiary.FooddiaryApplication;
import nl.bioinf.fooddiary.model.NewProduct;
import nl.bioinf.fooddiary.service.NewProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(FooddiaryApplication.class);

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
        logger.info("/newproductform url has been called without a known Locale." +
                "Requesting Locale and opening web page with requested language");
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
        logger.info("/newproductform url has been called with a known Locale." +
                "Opening web page with requested language");
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
        logger.info("Submitting NewProduct from newProductForm to database." +
                "Redirecting user to /addednewproduct url.");
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
        logger.info("/addednewproduct url has been called without known Locale." +
                "Requesting Locale and opening web page in right language.");
        return "redirect:" + locale.getLanguage() + "/addednewproduct";
    }

    /**
     * Method that opens the addednewproduct web page when Locale is known.
     * @return (String)
     */
    @RequestMapping(value = "/{locale}/addednewproduct")
    public String  addedNewProductWithLocale() {
        logger.info("/addednewproduct url has been called with known Locale." +
                "Opening web page in right language.");
        return "/addednewproduct";
    }
}
