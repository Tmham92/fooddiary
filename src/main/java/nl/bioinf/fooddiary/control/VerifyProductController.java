package nl.bioinf.fooddiary.control;

import nl.bioinf.fooddiary.FooddiaryApplication;
import nl.bioinf.fooddiary.model.newproduct.NewProduct;
import nl.bioinf.fooddiary.service.NewProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.Locale;

@Controller
public class VerifyProductController {

    private static final Logger logger = LoggerFactory.getLogger(FooddiaryApplication.class);

    @Autowired
    NewProductService newProductService;

    @RequestMapping(value = {"/verifyproducts/{id}"}, method = RequestMethod.GET)
    public String verifyproductWithoutLocale(@PathVariable("id") int id, Locale locale, Model model) {
        logger.info("/verifyproduct url has been called, Open /verifyproducts in requested language.");
        NewProduct newProduct = newProductService.getNewProductById(id);
        model.addAttribute("newproduct", newProduct);
        model.addAttribute("page_name", "verifyproducts");
        return "redirect:" + locale.getLanguage() + "/verifyproducts";
    }

    @RequestMapping(value = "/{locale}/verifyproducts/{id}", method = RequestMethod.GET)
    public String  contactWithLocale(@PathVariable("id") int id, Model model) {
        logger.info("/verifyproduct url has been called, Open /verifyproducts in requested language.");
        NewProduct newProduct = newProductService.getNewProductById(id);
        model.addAttribute("newproduct", newProduct);
        model.addAttribute("page_name", "verifyproducts");
        return "verifyproducts";
    }




}
