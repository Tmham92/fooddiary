package nl.bioinf.fooddiary.control;


import nl.bioinf.fooddiary.FooddiaryApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Locale;

/**
 * @author Hans Zijlstra
 * Login controller that serves the login page to visitor
 * This page is served when visitors visit the application page
 */

@Controller
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(FooddiaryApplication.class);

    /**
     * Method handling that redirects to the page based on the users browsers region settings
     * takes locale Locale and Authentication authentication  as arguments
     * returns a string sending a redirect to
     * @param locale (Locale)
     * @return allocatedPage
     */

    @RequestMapping(value = {"", "/", "/home"}, method = RequestMethod.GET)
    public String homeWithoutLocale(Locale locale) {
        logger.info("/home url has been called returning: redirect: + locale.getLanguage() + /home");
        return "redirect:" + locale.getLanguage() + "/home";

    }

    /**
     * Method that listens to the local interceptor and serves the homepage to the visitor
     * returns the page in english or dutch
     * @return Home (String)
     */

    @RequestMapping(value = "/{locale}/home")
    public String  homeWithLocale() {
        logger.info("/home url has been called returning: redirect: + locale.getLanguage() + /home");
        return "/home";
    }

    /**
     *returns an error when authentication on login fails
     * @return Home (String)
     */

    @RequestMapping("/login-error.html")
    public String loginError() {
        logger.info("/login-error url has been called authentication failed returning home with error message");
        return "/home";
    }
}
