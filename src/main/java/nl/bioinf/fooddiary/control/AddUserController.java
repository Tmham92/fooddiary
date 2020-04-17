package nl.bioinf.fooddiary.control;

import nl.bioinf.fooddiary.model.newuser.NewUser;
import nl.bioinf.fooddiary.service.NewUserService;
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

    @Autowired
    NewUserService newUserService;

    @RequestMapping(value = {"/adduser"}, method = RequestMethod.GET)
    public String newUserFormWithoutLocale(Locale locale, Model model) {
        NewUser newUser = new NewUser();
        model.addAttribute("newuserform", newUser);
        return "redirect:" + locale.getLanguage() + "/adduser";
    }

    @RequestMapping(value = "/{locale}/adduser", method = RequestMethod.GET)
    public String newUserFormWithLocale(Model model) {
        NewUser newUser = new NewUser();
        model.addAttribute("newuserform", newUser);
        return "/adduser";
    }

    @RequestMapping(value = "/adduser", method = RequestMethod.POST)
    public String injectNewUser(@ModelAttribute("newuserform")
                                   @Validated NewUser newUser, BindingResult bindingResult) {
        newUserService.addNewUser(newUser);
        return "redirect:/adduser";
    }
}
