package nl.bioinf.fooddiary.control;

import nl.bioinf.fooddiary.FooddiaryApplication;
import nl.bioinf.fooddiary.dao.jdbc.ProductDAO;
import nl.bioinf.fooddiary.model.newproduct.NewProduct;
import nl.bioinf.fooddiary.model.product.*;
import nl.bioinf.fooddiary.service.NewProductService;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.Locale;

@Controller
public class VerifyProductController {

    private static final Logger logger = LoggerFactory.getLogger(FooddiaryApplication.class);
    ProductDescription productDescription = new ProductDescription();
    ProductGroup productGroup = new ProductGroup();
    ProductInfoExtra productInfoExtra = new ProductInfoExtra();
    ProductMeasurement productMeasurement = new ProductMeasurement();


    @RequestMapping(value = {"/verifyproducts"}, method = RequestMethod.GET)
    public String verifyproductWithoutLocale(Locale locale, Model model) {
        logger.info("/verifyproduct url has been called, Open /verifyproducts in requested language.");

        model.addAttribute("productgroup", productGroup);
        model.addAttribute("productdescription", productDescription);
        model.addAttribute("productmeasurement", productMeasurement);
        model.addAttribute("productinfoextra", productInfoExtra);
        return "redirect:" + locale.getLanguage() + "/verifyproducts";
    }

    @RequestMapping(value = "/{locale}/verifyproducts", method = RequestMethod.GET)
    public String  contactWithLocale(Model model) {
        logger.info("/verifyproduct url has been called, Open /verifyproducts in requested language.");
        model.addAttribute("productGroup", productGroup);
        model.addAttribute("productdescription", productDescription);
        model.addAttribute("productmeasurement", productMeasurement);
        model.addAttribute("productinfoextra", productInfoExtra);
        return "verifyproducts";
    }

    @PostMapping("/verifyproducts/description")
    public String injectNewProduct(@ModelAttribute ProductDescription productDescription, Model model) {
        model.addAttribute("productGroup", productGroup);
        model.addAttribute("productdescription", productDescription);
        model.addAttribute("productmeasurement", productMeasurement);
        model.addAttribute("productinfoextra", productInfoExtra);
        System.out.println("THIS IS WORKING");
        return "results";
    }
}
