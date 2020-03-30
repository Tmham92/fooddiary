package nl.bioinf.fooddiary.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.Locale;

@Controller
public class AddUserController {

    @RequestMapping(value = {"/adduser"}, method = RequestMethod.GET)
    public String contactWithoutLocale(Locale locale) {
        return "redirect:" + locale.getLanguage() + "/adduser";
    }

    @RequestMapping(value = "/{locale}/adduser")
    public String  contactWithLocale() {
        return "adduser";
    }
}
