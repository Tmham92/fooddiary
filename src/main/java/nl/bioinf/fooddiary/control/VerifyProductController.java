package nl.bioinf.fooddiary.control;

import nl.bioinf.fooddiary.FooddiaryApplication;
import nl.bioinf.fooddiary.model.nutrient.NutrientNames;
import nl.bioinf.fooddiary.model.product.*;
import nl.bioinf.fooddiary.service.VerifyProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


@Controller
public class VerifyProductController {

    private static final Logger logger = LoggerFactory.getLogger(FooddiaryApplication.class);
    @Autowired
    private VerifyProductService verifyProductService;

    private ProductDescription productDescription = new ProductDescription();
    private ProductGroup productGroup = new ProductGroup();
    private ProductInfoExtra productInfoExtra = new ProductInfoExtra();
    private ProductMeasurement productMeasurement = new ProductMeasurement();
    private Product product = new Product();
    private List<NutrientNames> nutrientNamesList = new ArrayList<>();
    private List<String> nutrientValues = new ArrayList<>();




    @RequestMapping(value = {"/verifyproducts"}, method = RequestMethod.GET)
    public String verifyproductWithoutLocale(Locale locale, Model model) {
        logger.info("/verifyproduct url has been called, Open /verifyproducts in requested language.");
        //model.addAttribute("productgroupcode", productGroupCodes);
        model.addAttribute("verified", product);
        model.addAttribute("productgroup", productGroup);
        model.addAttribute("productdescription", productDescription);
        model.addAttribute("productmeasurement", productMeasurement);
        model.addAttribute("productinfoextra", productInfoExtra);
        model.addAttribute("nutrientvalues", nutrientValues);

        nutrientNamesList = verifyProductService.getAllNutrientNamesAndAbbr();
        model.addAttribute("nutrientnameslist", nutrientNamesList);
                return "redirect:" + locale.getLanguage() + "/verifyproducts";
    }

    @RequestMapping(value = "/{locale}/verifyproducts", method = RequestMethod.GET)
    public String  contactWithLocale(Model model) {

        logger.info("/verifyproduct url has been called, Open /verifyproducts in requested language.");
        //model.addAttribute("productgroupcode", productGroupCodes);
        model.addAttribute("verified", product);
        model.addAttribute("productgroup", productGroup);
        model.addAttribute("productdescription", productDescription);
        model.addAttribute("productmeasurement", productMeasurement);
        model.addAttribute("productinfoextra", productInfoExtra);
        model.addAttribute("nutrientvalues", nutrientValues);

        nutrientNamesList = verifyProductService.getAllNutrientNamesAndAbbr();
        model.addAttribute("nutrientnameslist", nutrientNamesList);
        return "verifyproducts";
    }

    @RequestMapping(value = "/verifyproducts/productdescription", method = RequestMethod.POST)
    public String createVerifiedProductDescription(@ModelAttribute("productdescription") ProductDescription productDescription, Model model) {
        logger.info("creating productdescription object and binding it to product object");
        if (productDescription.getSynonymous() == "") {
            productDescription.setSynonymous("_UNKNOWN_SYNONYMOUS_");
        }
        product.setProductDescription(productDescription);
        model.addAttribute("verified", product);
        return "redirect:/verifyproducts";
    }

    @RequestMapping(value = "/verifyproducts/productgroupcode", method = RequestMethod.POST)
        public String createVerifiedProductGroupCode(@ModelAttribute("productgroup") ProductGroup productGroup,
                                   Model model) {
        logger.info("creating productgroupcode object and binding it to product object.");
        productGroup.setGroupCodeDescription(verifyProductService.getGroupCodeDescription(productGroup.getGroupCode()));
        product.setProductGroup(productGroup);
        model.addAttribute("verified", product);
        return "redirect:/verifyproducts";
    }

    @RequestMapping(value= "/verifyproducts/productextrainfo", method = RequestMethod.POST)
    public String createVerifiedProductExtraInfo(@ModelAttribute("productextrainfo") ProductInfoExtra productInfoExtra,
                                                 Model model) {
        logger.info("Creating productInfoExtra object and binding it to Product object.");
        if (productInfoExtra.getTracesOf() == "") {
            productInfoExtra.setTracesOf("_UNKNOWN_INFO_");
        }
        if (productInfoExtra.getEnrichedWith() == "") {
            productInfoExtra.setEnrichedWith("_UNKNOWN_INFO_");
        }
        product.setProductInfoExtra(productInfoExtra);
        model.addAttribute("verified", product);
        return "redirect:/verifyproducts";
    }

    @RequestMapping(value= "/verifyproducts/productmeasurement", method = RequestMethod.POST)
    public String createVerifiedProductMeasurement(@ModelAttribute("productmeasurement") ProductMeasurement productMeasurement,
                                                   Model model) {
        logger.info("Creating productMeasurement object and binding it to Product object.");
        if (productMeasurement.getMeasurementUnit() == "") {
            productMeasurement.setMeasurementUnit("g");
        }
        if (productMeasurement.getMeasurementQuantity() == 0) {
            productMeasurement.setMeasurementQuantity(100);
        }
        product.setProductMeasurement(productMeasurement);
        model.addAttribute("verified", product);
        return "redirect:/verifyproducts";
    }

    @RequestMapping(value = "/verifyproducts/submitverifiedproduct", method = RequestMethod.POST)
    public String submittingVerifiedProduct(Model model) {
        logger.info("Submitting verified Product object to database.");


        product = new Product();
        model.addAttribute("verified", product);
        return "redirect:/verifyproducts";
    }
}
