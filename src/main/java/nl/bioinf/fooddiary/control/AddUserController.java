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

    @RequestMapping(value = {"/adduser"}, method = RequestMethod.GET)
    public String newUserFormWithoutLocale(Locale locale, Model model) {
        logger.info("/adduser url has been called");
        NewUser newUser = new NewUser();
        logger.info("/adding the new-user-form to the attributes");
        model.addAttribute("newuserform", newUser);
        return "redirect:" + locale.getLanguage() + "/adduser";
    }

    @RequestMapping(value = "/{locale}/adduser", method = RequestMethod.GET)
    public String newUserFormWithLocale(Model model) {
        logger.info("/adduser url has been called");
        NewUser newUser = new NewUser();
        logger.info("/adding the new-user-form to the attributes");
        model.addAttribute("newuserform", newUser);
        return "/adduser";
    }

    @RequestMapping(value = "/adduser", method = RequestMethod.POST)
    public String injectNewUser(@ModelAttribute("newuserform")
                                   @Validated NewUser newUser, BindingResult bindingResult) {
        logger.info("submitted the new-user-form");
        newUserService.addNewUser(newUser);
        logger.info("redirect to /adduser url");
        return "redirect:/adduser";
    }
}
