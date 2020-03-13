package nl.bioinf.fooddiary.control;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Locale;

@Controller
public class LoginController {

    @RequestMapping(value= {"", "/","/home"})
    public String doLoginWithoutLocale(Locale locale) {
        return "/home";
    }

}
