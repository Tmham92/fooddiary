package nl.bioinf.fooddiary.control;

import nl.bioinf.fooddiary.FooddiaryApplication;
import nl.bioinf.fooddiary.model.newproduct.NewProduct;
import nl.bioinf.fooddiary.model.nutrient.NutrientNames;
import nl.bioinf.fooddiary.model.nutrient.NutrientValues;
import nl.bioinf.fooddiary.model.product.*;
import nl.bioinf.fooddiary.service.NutrientValuesDTO;
import nl.bioinf.fooddiary.service.VerifyProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * @author Tobias Ham
 *
 * VerifyProduct controller that serves the /verifyproducts url. When called a product object is created which
 * requires information from different forms. The forms fill up the product Object.
 * When the product is finished it can will be added to the database and removed from the unverified table.
 */

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
    private List<NutrientNames> nutrientNamesList = new ArrayList<>();
    private NutrientValuesDTO nutrientValuesDTO = new NutrientValuesDTO();


    /**
     * Get method which catches the url call.
     * It will redirect the user to the /verifyproducts page with the proper Locale.
     * @param newProduct (NewProduct)
     * @param locale (Locale)
     * @param redirectAttributes (RedirectAttributes)
     * @return
     */
    @RequestMapping(value = {"/verifyproducts"}, method = RequestMethod.GET)
    public String verifyproductWithoutLocale(@ModelAttribute("newProduct") NewProduct newProduct,
                                             Locale locale,
                                             final RedirectAttributes redirectAttributes) {
        logger.info("/verifyproduct url has been called without locale, Open /verifyproducts in requested language.");
        redirectAttributes.addFlashAttribute("newProduct", newProduct);

        return "redirect:" + locale.getLanguage() + "verify-products";
    }

    /**
     * Get method which catches the url call.
     * This method initialises all attributes needed by thymeleaf to be able to verify a product.
     * @param newProduct (NewProduct)
     * @param model (Model)
     * @return
     */
    @RequestMapping(value = "/{locale}/verifyproducts", method = RequestMethod.GET)
    public String  contactWithLocale(@ModelAttribute("newProduct") NewProduct newProduct, Model model) {
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
        model.addAttribute("newproduct", newProduct);
        if (newProduct.getId() != null) {
            //verifyProductService.getProductPicture(newProduct);
        }
        return "verify-products";
    }


    /**
     * Post method which creates a productGroup object and add this object to the Product Object.
     * @param productGroup
     * @param model
     * @return
     */
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

    /**
     * Post method which checks whether the ProductDescription object is finished and validated.
     * The ProductDiscription Object will be added to the Product Object.
     * @param productDescription
     * @param bindingResult
     * @param model
     * @return
     */
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
            return "redirect:/verifyproducts";
        }

        model.addAttribute("creatingProduct", createdProduct);
        verifiedProduct.setProductDescription(productDescription);

        return "redirect:/verifyproducts";
    }

    /**
     * Post method which checks whether the ProductExtraInfo object is finished and validated.
     * The ProductExtraInfo Object will be added to the Product Object.
     * @param productInfoExtra
     * @param model
     * @return
     */
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

    /**
     * Post method which checks whether the ProductMeasurement object is finished and validated.
     * The ProductMeasurement Object will be added to the Product Object.
     * @param productMeasurement
     * @param model
     * @return
     */
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

    /**
     * Post method which checks whether the NutrientValues object is finished and validated.
     * The NutrientValues Object will be added to the Product Object.
     * @param nutrientValuesDTO
     * @param bindingResult
     * @return
     */
    @RequestMapping(value="/verifyproducts/nutrientvalues", method = RequestMethod.POST)
    public String createVerifiedNutrientList(@ModelAttribute("nutrientDTO") @Validated NutrientValuesDTO nutrientValuesDTO,
                                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            logger.info("Validation Error in Nutrientvalues form.");
            return "redirect:/verifyproducts";
        }
        logger.info("Creating nutrient value list");
        nutrientValues = verifyProductService.createNutrientValueList(nutrientValuesDTO.getValues());
        verifiedProduct.setNutrientValues(nutrientValues);
        return "redirect:/verifyproducts";
    }

    /**
     * Post method which sets a product ID for the Product Object.
     * The Product object will be sent to the database.
     * @param product
     * @param productID
     * @param bindingResult
     * @return
     */
    @RequestMapping(value="/verifyproducts/submitproduct", method = RequestMethod.POST)
    public String addProductCodeToProduct(@ModelAttribute("verifiedproduct") Product product,
                                          @RequestParam("newProductID") Integer productID,
                                          BindingResult bindingResult) {
        int minimumNumber = 10000;
        if (bindingResult.hasErrors()) {
            logger.info("Validation Error in product form");
            return "redirect:/verifyproducts";
        }

        logger.info("Set productcode to unique value above 6000");
        int possibleCode = verifyProductService.checkHighestProductCode() + 1;
        if (possibleCode < minimumNumber) {
            possibleCode = minimumNumber;
        }

        logger.info("Check if productcode is unique.");
        if (verifyProductService.checkProductCode(possibleCode)) {
            verifiedProduct.setCode(possibleCode);
            verifyProductService.deleteVerifiedProductFromUnverified(productID);
            logger.info("Product deleted from unverified_product_entry");
            verifyProductService.submitProductToDatabase(verifiedProduct);
            logger.info("Product successfully added to database");
        }
        return "redirect:/getnewproducts";
    }

}
