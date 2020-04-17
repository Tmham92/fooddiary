package nl.bioinf.fooddiary.control;


import nl.bioinf.fooddiary.FooddiaryApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.Locale;

/**
 * @author Hans Zijlstra & Hugo Donkerbroek
 */

@Controller
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(FooddiaryApplication.class);

    @RequestMapping(value = {"", "/", "/home"}, method = RequestMethod.GET)
    public String homeWithoutLocale(Locale locale, Model model) {
        logger.info("/home url has been called, showing home page");
        return "redirect:" + locale.getLanguage() + "/home";
    }

    @RequestMapping(value = "/{locale}/home")
    public String  homeWithLocale(Model model, Authentication authentication) {
        logger.info("/home url has been called, showing home page");
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
