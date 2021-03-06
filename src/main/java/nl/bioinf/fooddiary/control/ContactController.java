package nl.bioinf.fooddiary.control;

import nl.bioinf.fooddiary.FooddiaryApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Locale;

/**
 * @author Hugo Donkerbroek
 */

@Controller
public class ContactController {

    private static final Logger logger = LoggerFactory.getLogger(FooddiaryApplication.class);

    /**
     * Shows the contact page on request
     * @param locale loads the web page in the current language setting
     * @return returns the locale + contact.html page
     */
    @RequestMapping(value = {"/contact"}, method = RequestMethod.GET)
    public String contactWithoutLocale(Locale locale) {
        logger.info("/contact url has been called, showing contact page.");
        return "redirect:" + locale.getLanguage() + "/contact";
    }

    /**
     * Shows the contact page on request
     * @return returns the contact.html page
     */
    @RequestMapping(value = "/{locale}/contact")
    public String  contactWithLocale() {
        logger.info("/contact url has been called, showing contact page.");
        return "contact";
    }
}
