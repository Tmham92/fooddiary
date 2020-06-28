package nl.bioinf.fooddiary.control;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import nl.bioinf.fooddiary.FooddiaryApplication;
import nl.bioinf.fooddiary.dao.ProductRepository;
import nl.bioinf.fooddiary.model.product.ProductEntry;
import nl.bioinf.fooddiary.model.product.ProductOccurrence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

/**
 * @author Hans Zijlstra
 * This class handels the entry of product to the user his food diary
 * The description of the product are taken from the database and served to the view
 * Once the user submits his a product entry into the food diary a post request submits
 * this to the database and json response views the product entry on the page
 */

@Controller
public class DiaryEntryController {
    private static final Logger logger = LoggerFactory.getLogger(FooddiaryApplication.class);
    private final LocalDate currentDate = LocalDate.now();

    @Autowired
    private ProductRepository productRepository;

    /**
     * Method handling that redirects to the page based on the users browsers region settings
     * takes locale Locale as arguments
     * returns a string sending a redirect to the page with the right language settings
     * @param locale (Locale)
     * @return {local/diary-entry} String
     */

    @RequestMapping(value = "/diary-entry")
        public String diaryWithoutLocale(Locale locale) {
        logger.info("/diary-entry url has been called returning: redirect: + locale.getLanguage() + /diary-entry");
        return "redirect:" + locale.getLanguage() + "/diary-entry";

        }

    /**
     * Method that listens to the local interceptor and serves the food diary to the visitor
     * returns the page in english or dutch with the current time fand date
     * @return diary-entry (String)
     */

    @RequestMapping(value = "/{locale}/diary-entry")
    public String  diaryWithLocale(Model model) {
        logger.info("/{locale}/diary-entry url has been called returning /diary-entry");
        model.addAttribute("ldt", currentDate);
        return "/diary-entry";
    }

    /**
     * Gets all the product descriptions from the database users can choose from
     * returns a list with all product descriptions
     * @return diary-entry (String)
     */

    @RequestMapping(value = "/product-description", method = RequestMethod.GET)
    public @ResponseBody
    List<String> getProductDescription(Locale locale) {
        logger.info("/product-description url has been called returning all productDescription in JSON FORMAT");
        if (locale.getLanguage().equals("nl")) {
            return productRepository.getAllDutchProductDescriptions();

        } else return productRepository.getAllEnglishProductDescriptions();
    }

    /*
    * Returns the measurement unit from the database
     * @param ProductDescription name of the product
     * @param locale locale interceptor
     * @return measurement unit
    */
    @PostMapping(value = "/diary-entry/product-measurement", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public String getProductMeasurement(@RequestParam String productDescription, Locale locale) throws JsonProcessingException {
        logger.info("/diary-entry/product-measurement url has been called returning measurement unit in JSON FORMAT");
        Map<String, Object> params = new HashMap<>();
        params.put("productUnit", productRepository.getMeasurementUnitByDescription(productRepository.getProductId(locale.getLanguage(),productDescription)));
        return new ObjectMapper().writeValueAsString(params);
    }

    /**
     * adds a product of a user to their food diary
     * @param productEntry a product with product information
     * @param locale locale interceptor
     * @return Responseentity holding productEntry
     */
    @PostMapping(value = "/diary-entry/addtodiary", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<Object> addProductToDiary(@RequestBody ProductEntry productEntry, Locale locale) {

        try {
            logger.info("/diary-entry/addtodiary url has been called adding product to ");
            String language = locale.getLanguage();
            int productId = productRepository.getProductId(language,productEntry.getProductDescription());
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            productRepository.insertProductIntoDiary(language, getUserID(authentication), productId, productEntry);
            logger.info("/diary-entry/addtodiary url has been called adding product to " + authentication + "diary");
            return ResponseEntity.ok(productEntry);

        } catch (IllegalArgumentException e) {
            logger.error("/diary-entry/addtodiary url has been called resulted in bad request ");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Illegal quantity ");
    }

    }

    /**
     * removes a product from the diary
     * @param entry id of product to be removed
     */
    @PostMapping(value = "/remove/diary-entry", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public void deleteFromDiary(@RequestParam int entry) {
        logger.info("/remove/diary-entry url has been called removing entry with id: " + entry);
        productRepository.removeDiaryEntryById(entry);
    }

    /**
     * Collects all the products in a users food diary for the currentdate
     * @param locale locale interceptor
     * @return List<ProductEntry> all products for the given date
     */
    @GetMapping(value = "/product-entries-by-date", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<ProductEntry> getProductEntriesByDate(Locale locale) {

        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(new Date());
        logger.info("/product-entries-by-date url has been called getting diary entries for date" + date);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return productRepository.getDiaryEntriesByDate(locale.getLanguage(),getUserID(authentication), date) ;
    }

    /**
     * Collects all the products in a users food diary for a given date
     * @param date date to retrieve
     * @param locale locale interceptor
     * @return List<ProductEntry> all products for the given date
     */
    @GetMapping(value = "/diary-by-date")
    @ResponseBody
    public List<ProductEntry> getProductEntriesBySelectedDate(@RequestParam String date, Locale locale) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        logger.info("/diary-by-date url has been called getting diary entries for date" + date);
        return productRepository.getDiaryEntriesByDate(locale.getLanguage(),getUserID(authentication), date);
    }

    /**
     *Gets the most queried product occurence
     * @param locale locale interceptor
     * @return diary-entry (String)
     */
    @GetMapping(value = "/occurences")
    @ResponseBody
    public List<ProductOccurrence> getHistoryItems(Locale locale) {
        logger.info("/occurences url has been called getting top fiftheen queries products");
        return productRepository.getProductOccurrences(locale.getLanguage());
    }

    /**
     * retrieves the user it's id by his username
     * @param authentication
     * @return int id of user
     */
    public int getUserID(Authentication authentication) {
        logger.info("/occurences url has been called getting top fiftheen queries products");
        return productRepository.getUserIdByUsername(authentication.getName());
    }

    /**
     * Checks if a given number is equal to zero and throws Illegalargument exception
     * @param quantity
     * @return quantity
     */
    public static double checkQuantityForNull(double quantity) {
        if (quantity <= 0) {
            logger.error("Quantity not equals 0");
            throw new IllegalArgumentException();
        } else {
            return quantity;
        }
    }
}
