package nl.bioinf.fooddiary.control;


import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Locale;

@Controller
public class LoginController {

<<<<<<< HEAD


    @RequestMapping(value = {"", "/", "/home"}, method = RequestMethod.GET)
    public String homeWithoutLocale(Locale locale) {
=======
    @RequestMapping(value = {"", "/", "/home"}, method = RequestMethod.GET)
    public String homeWithoutLocale(Locale locale, Model model) {
        model.addAttribute("page_name", "home");
>>>>>>> 7eaf715b9f6af0a70cc142240392fecf662949f5
        return "redirect:" + locale.getLanguage() + "/home";

    }

    @RequestMapping("/login-error.html")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "/home";
    }

    @RequestMapping(value = "/{locale}/home")
<<<<<<< HEAD
    public String  homeWithLocale() {
        return "/home";
    }


=======
    public String  homeWithLocale(Model model) {
        model.addAttribute("page_name", "home");
        return "/home";
    }
>>>>>>> 7eaf715b9f6af0a70cc142240392fecf662949f5
}
