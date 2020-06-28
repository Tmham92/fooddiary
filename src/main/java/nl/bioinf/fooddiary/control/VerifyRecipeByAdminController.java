package nl.bioinf.fooddiary.control;

import nl.bioinf.fooddiary.FooddiaryApplication;
import nl.bioinf.fooddiary.model.recipe.CombinedRecipeProducts;
import nl.bioinf.fooddiary.service.VerifyRecipeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Locale;

/**
 * @author Tom Wagenaar
 * Date: 05-06-2020
 *
 * This class handles the requests and responses for verify-recipe-by-admin page. On that page there is a table that
 * needs to be filled with data. This data is a list of CombinedRecipeProducts, such a object contains a recipe name,
 * a list of products in that recipe from the recipe table in the database and a list of products from the product table
 * in the database that correspond to the products in the other list.
 */
@Controller
public class VerifyRecipeByAdminController {

    private static final Logger logger = LoggerFactory.getLogger(FooddiaryApplication.class);

    @Autowired
    private VerifyRecipeService verifyRecipeService;

    /**
     * Shows the Verify recipe by admin page on request
     * @param locale loads the web page in the current language setting
     * @return returns the locale + verify-recipe-by-admin.html page
     */
    @RequestMapping(value = {"/verify-recipe-by-admin"}, method = RequestMethod.GET)
    public String VerifyRecipeByAdminWithoutLocale(Locale locale) {
        logger.info("/verify-recipe-by-admin url has been called, showing verify-recipe-by-admin page.");
        return "redirect:" + locale.getLanguage() + "/verify-recipe-by-admin";
    }

    /**
     * Shows the verify-recipe-by-admin page on request
     * @return returns the verify-recipe-by-admin.html page
     */
    @RequestMapping(value = "/{locale}/verify-recipe-by-admin")
    public String VerifyRecipeByAdminWithLocale() {
        logger.info("/verify-recipe-by-admin url has been called, showing verify-recipe-by-admin page.");
        return "verify-recipe-by-admin";
    }

    /**
     * Fill the table on the verify-recipe-by-admin page with data, this data is a list of CombinedRecipeProducts objects.
     * These objects contain a recipe name, a list of products in that recipe from the recipe table in the database and
     * a list of products from the product table in the database that correspond to the products in the other list.
     * @return
     */
    @RequestMapping(value = "/verify-recipe-by-admin/fill-datatable", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<CombinedRecipeProducts> fillVerifyRecipeTable() {
        logger.info("/verify-recipe-by-admin/fill-datatable url has been called, retrieving recipe data to fill table!");
        return verifyRecipeService.processRecipeData();
    }
}
