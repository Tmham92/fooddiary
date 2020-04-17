package nl.bioinf.fooddiary.control;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Locale;

@Controller
public class LoginController {

    @RequestMapping(value = {"", "/", "/home"}, method = RequestMethod.GET)
    public String homeWithoutLocale(Locale locale, Model model) {
        return "redirect:" + locale.getLanguage() + "/home";

    }

    @RequestMapping(value = "/{locale}/home")
    public String  homeWithLocale(Model model, Authentication authentication) {
        authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication.getAuthorities().toArray()[0]);
        return "/home";
    }

    @RequestMapping("/login-error.html")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "/home";
    }
}
