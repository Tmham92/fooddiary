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

    @RequestMapping(value = {"", "/", "/home"}, method = RequestMethod.GET)
    public String homeWithoutLocale(Locale locale, Model model) {
        model.addAttribute("page_name", "home");
        return "redirect:" + locale.getLanguage() + "/home";

    }

    @RequestMapping("/login-error.html")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "/home";
    }

    @RequestMapping(value = "/{locale}/home")
    public String  homeWithLocale(Model model) {
        model.addAttribute("page_name", "home");
        return "/home";
    }
}
