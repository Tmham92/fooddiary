package nl.bioinf.fooddiary.control;

import nl.bioinf.fooddiary.FooddiaryApplication;
import nl.bioinf.fooddiary.model.NewProduct;
import nl.bioinf.fooddiary.service.NewProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import java.util.Locale;
/**
 *  @Author Tobias Ham
 *  @version 0.0.1
 *  date 16-3-2020
 *
 *  Controller class that deals with web based requests for retrieving newly added products from the database.
*/

@Controller
public class GetNewProductController {
    private static final Logger logger = LoggerFactory.getLogger(FooddiaryApplication.class);

    @Autowired
    NewProductService newProductService;
    /**
     * Method that sends web request to retrieve all newly added unknown products
     * from database to the new product service when locale is unknown.
     * @param locale (Locale object)
     * @param model (Model object)
     * @return (String)
     */
    @RequestMapping(value= {"/getnewproducts"}, method = RequestMethod.GET)
    public String getAllNewProductsWithoutLocale(Locale locale, Model model) {
        logger.info("/getnewproducts is being called with unknown locale. Requesting Locale and open " +
                "/getnewproducts in requested language");

        List<NewProduct> newProducts = newProductService.getAllNewProducts();
        model.addAttribute("getNewProducts", newProducts);
        model.addAttribute("page_name", "getnewproducts");
        return "redirect:" + locale.getLanguage() + "/getnewproducts";
    }

    /**
     * Method that sends web request to retrieve all newly added unknown products
     * from database to the new product service when locale is known.
     * @param model (Model object)
     * @return (String)
     */
    @RequestMapping(value = "/{locale}/getnewproducts")
    public String getAllNewProductsWithLocale(Model model) {
        logger.info("{locale}/getnewproducts is being called. Open /getnewproducts in requested language");
        List<NewProduct> newProducts = newProductService.getAllNewProducts();
        model.addAttribute("getNewProducts", newProducts);
        model.addAttribute("page_name", "newproductform");
        return "/getnewproducts";
    }

    /**
     * Method that sends a remove request to the new product service which then deletes the entry from the database.
     * @param productID (int)
     * @return (String)
     */
    @RequestMapping(value = "/getnewproducts", method = RequestMethod.POST)
    private String deleteNewProduct(@RequestParam int productID) {
        logger.info("Newly added product removed from database. Product ID is " + productID);
        System.out.println("Passing " + productID + " to delete Entry Function");
        newProductService.deleteNewProduct(productID);
        return "redirect:/getnewproducts";
    }
}
