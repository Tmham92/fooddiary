package nl.bioinf.fooddiary.control;

import nl.bioinf.fooddiary.FooddiaryApplication;
import nl.bioinf.fooddiary.model.newuser.NewUser;
import nl.bioinf.fooddiary.service.NewUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Locale;

/**
 * @author Hugo Donkerbroek
 */

@Controller
public class AddUserController {

    private static final Logger logger = LoggerFactory.getLogger(FooddiaryApplication.class);

    @Autowired
    NewUserService newUserService;

    /**
     * Shows the add-user-form on the /adduser url page.
     * @param locale loads the web page in the current language setting
     * @param model New-user-form used for filling in account information for new accounts
     * @return returns the locale + add-user.html page
     */
    @RequestMapping(value = {"/add-user"}, method = RequestMethod.GET)
    public String newUserFormWithoutLocale(Locale locale, Model model) {
        logger.info("/add-user url has been called");
        NewUser newUser = new NewUser();
        logger.info("/adding the new-user-form to the attributes");
        model.addAttribute("newuserform", newUser);
        return "redirect:" + locale.getLanguage() + "/add-user";
    }

    /**
     * Shows the add-user-form on the /adduser url page.
     * @param model New-user-form used for filling in account information for new accounts
     * @return returns the add-user.html page
     */
    @RequestMapping(value = "/{locale}/add-user", method = RequestMethod.GET)
    public String newUserFormWithLocale(Model model) {
        logger.info("/add-user url has been called");
        NewUser newUser = new NewUser();
        logger.info("/adding the new-user-form to the attributes");
        model.addAttribute("newuserform", newUser);
        return "/add-user";
    }

    /**
     * Handles the page post request. The data sent with the post request is saved in the newUser class
     * @param newUser the user data from the new-user-form
     * @return redirects to /adduser to reload the page
     */
    @RequestMapping(value = "/add-user", method = RequestMethod.POST)
    public String injectNewUser(@ModelAttribute("newuserform")
                                   @Validated NewUser newUser, BindingResult bindingResult) {
        logger.info("submitted the new-user-form");
        newUserService.addNewUser(newUser);
        logger.info("redirect to /adduser url");
        return "redirect:/add-user";
    }
}
