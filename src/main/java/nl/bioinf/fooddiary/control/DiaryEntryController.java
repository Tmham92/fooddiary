package nl.bioinf.fooddiary.control;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import nl.bioinf.fooddiary.FooddiaryApplication;
import nl.bioinf.fooddiary.dao.ProductRepository;
import nl.bioinf.fooddiary.model.product.ProductDescription;
import nl.bioinf.fooddiary.model.product.ProductEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
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
     * returns the page in english or dutch with the current time and date
     * @return diary-entry (String)
     */

    @RequestMapping(value = "/{locale}/diary-entry")
    public String  diaryWithLocale(Model model) {
        logger.info("/{locale}/diary-entry url has been called returning /diary-entry");
        final LocalDate currentDate = LocalDate.now();
        Date date = new Date();
        String time = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT).format(date).split("\\s+")[1];
        model.addAttribute("ldt", currentDate);
        model.addAttribute("time", time);
        return "/diary-entry";
    }

    /**
     * Gets all the product descriptions from the database users can choose from
     * returns a list with all product descriptions
     * @return diary-entry (String)
     */

    @RequestMapping(value = "/product-description", method = RequestMethod.GET)
    public @ResponseBody
    List<ProductDescription> getTime() {
        logger.info("/product-description url has been called returning all productDescription in JSON FORMAT");
        return productRepository.getAllProductDescriptions();
    }

    @PostMapping(value = "/diary-entry/product-measurement", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public String getProductMeasurement(@RequestParam String productDescription) throws JsonProcessingException {
        Map<String, Object> params = new HashMap<>();
        params.put("productUnit", productRepository.getMeasurementUnitByDescription(productDescription));
        return new ObjectMapper().writeValueAsString(params);
    }


    /**
     * Method that handles the ajax post request for a product entry into the user his food diary
     * When the fields are valid a ProductEntry object is returned and shown on the page of the food diary
     * for the particulair user.
     * returns JSON VALUE of ProductEntry object
     * @return ProductEntry
     */

    @PostMapping(value = "/diary-entry/addtodiary", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ProductEntry addProductToDiary(@RequestParam String productDescription, @RequestParam String quantity, @RequestParam String unit,
                                          @RequestParam String date, @RequestParam String time, @RequestParam String description, @RequestParam String mealtime) {
        description = validateDescription(description);
        ProductEntry productEntry = new ProductEntry(productDescription, quantity, unit, date, time, mealtime, description);
        int productId = productRepository.getProductId(productEntry.getProductDescription());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        productRepository.insertProductIntoDiary(getUserID(authentication), productId, productEntry);

        System.out.println("STARTING");





        return productEntry;

    }

    @GetMapping(value = "/product-entries-by-date", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<ProductEntry> getProductEntriesByDate() {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(new Date());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return productRepository.getDiaryEntriesByDate(getUserID(authentication), date) ;
    }

    private String validateDescription(String description) {
        return Objects.requireNonNullElse(description, " ");

    }

    private int getUserID(Authentication authentication) {
        return productRepository.getUserIdByUsername(authentication.getName());

    }


}
