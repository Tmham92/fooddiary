package nl.bioinf.fooddiary.control;

import nl.bioinf.fooddiary.FooddiaryApplication;
import nl.bioinf.fooddiary.model.nutrient.NutrientNames;
import nl.bioinf.fooddiary.model.nutrient.NutrientValues;
import nl.bioinf.fooddiary.model.product.*;
import nl.bioinf.fooddiary.service.NutrientValuesDTO;
import nl.bioinf.fooddiary.service.VerifyProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    private NutrientValues nutrientValues;
    private Product createdProduct = new Product();
    private Product verifiedProduct = new Product();
    private int code;
    public boolean isUnique;
    private List<NutrientNames> nutrientNamesList = new ArrayList<>();
    private NutrientValuesDTO nutrientValuesDTO = new NutrientValuesDTO();


    @RequestMapping(value = {"/verifyproducts"}, method = RequestMethod.GET)
    public String verifyproductWithoutLocale(Locale locale, Model model) {
        logger.info("/verifyproduct url has been called, Open /verifyproducts in requested language.");
        model.addAttribute("verifiedproduct", verifiedProduct);
        model.addAttribute("creatingProduct", createdProduct);
        model.addAttribute("productgroup", productGroup);
        model.addAttribute("productdescription", productDescription);
        model.addAttribute("productmeasurement", productMeasurement);
        model.addAttribute("productinfoextra", productInfoExtra);
        nutrientNamesList = verifyProductService.getAllNutrientNamesAndAbbr();
        model.addAttribute("nutrientnameslist", nutrientNamesList);
        model.addAttribute("nutrientDTO", nutrientValuesDTO);
        return "redirect:" + locale.getLanguage() + "/verifyproducts";
    }

    @RequestMapping(value = "/{locale}/verifyproducts", method = RequestMethod.GET)
    public String  contactWithLocale(Model model) {
        logger.info("/verifyproduct url has been called, Open /verifyproducts in requested language.");
        model.addAttribute("verifiedproduct", verifiedProduct);
        model.addAttribute("creatingProduct", createdProduct);
        model.addAttribute("productgroup", productGroup);
        model.addAttribute("productdescription", productDescription);
        model.addAttribute("productmeasurement", productMeasurement);
        model.addAttribute("productinfoextra", productInfoExtra);
        nutrientNamesList = verifyProductService.getAllNutrientNamesAndAbbr();
        model.addAttribute("nutrientnameslist", nutrientNamesList);
        model.addAttribute("nutrientDTO", nutrientValuesDTO);
        return "verifyproducts";
    }

    @RequestMapping(value = "/verifyproducts/productdescription", method = RequestMethod.POST)
    public String createVerifiedProductDescription(@Valid @ModelAttribute("productdescription") ProductDescription productDescription,
                                                   BindingResult bindingResult,
                                                   Model model) {
        logger.info("creating productdescription object and binding it to product object");
        if (productDescription.getSynonymous() == "") {
            productDescription.setSynonymous("_UNKNOWN_SYNONYMOUS_");
        }
        if (bindingResult.hasErrors()) {
            logger.info("Forminput is invalid");
        }

        model.addAttribute("creatingProduct", createdProduct);
        verifiedProduct.setProductDescription(productDescription);

        return "redirect:/verifyproducts";
    }

    @RequestMapping(value = "/verifyproducts/productgroupcode", method = RequestMethod.POST)
        public String createVerifiedProductGroupCode(@ModelAttribute("productgroup") ProductGroup productGroup,
                                   Model model) {
        logger.info("creating productgroupcode object and binding it to product object.");
        productGroup.setGroupCodeDescription(verifyProductService.getGroupCodeDescription(productGroup.getGroupCode()));
        createdProduct.setProductGroup(productGroup);

        model.addAttribute("creatingProduct", createdProduct);
        model.addAttribute("productgroupverified", productGroup);
        verifiedProduct.setProductGroup(productGroup);
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
        createdProduct.setProductInfoExtra(productInfoExtra);
        model.addAttribute("creatingProduct", createdProduct);

        verifiedProduct.setProductInfoExtra(productInfoExtra);
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
        createdProduct.setProductMeasurement(productMeasurement);

        model.addAttribute("creatingProduct", createdProduct);


        verifiedProduct.setProductMeasurement(productMeasurement);
        return "redirect:/verifyproducts";
    }

    @RequestMapping(value="/verifyproducts/nutrientvalues", method = RequestMethod.POST)
    public String createVerifiedNutrientList(@ModelAttribute("nutrientDTO") @Validated  NutrientValuesDTO nutrientValuesDTO,
                                             BindingResult bindingResult,
                                             Model model) {
        if (bindingResult.hasErrors()) {
            System.out.println("ERROR FOUND");
            return "redirect:/verifyproducts";
        }
        logger.info("Creating nutrient value list");
        nutrientValues = verifyProductService.createNutrientValueList(nutrientValuesDTO.getValues());
        createdProduct.setNutrientValues(nutrientValues);
        model.addAttribute("creatingProduct", createdProduct);

        verifiedProduct.setNutrientValues(nutrientValues);
        return "redirect:/verifyproducts";
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @RequestMapping(value="/verifyproducts/productcode", method = RequestMethod.POST)
    public String addProductCodeToProduct(@ModelAttribute Product product,
                                          Model model) {
        int productCode = product.getCode();
        boolean isUnique;

        logger.info("Check whether product code is unique.");

        if (verifyProductService.checkProductCode(productCode)) {
            verifiedProduct.setCode(productCode);
            logger.info("Submitting creatingProduct Product object to database.");
            verifyProductService.submitProductToDatabase(verifiedProduct);
            verifiedProduct = new Product();
        } else {
            System.out.println("PRODUCTCODE IS NOT UNIQUE");
        }
        return "redirect:/verifyproducts";
    }

}
