package nl.bioinf.fooddiary.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Locale;

@Controller
public class ContactController {

    @RequestMapping(value = {"/contact"}, method = RequestMethod.GET)
    public String contactWithoutLocale(Locale locale, Model model) {
        model.addAttribute("page_name", "contact");
        return "redirect:" + locale.getLanguage() + "/contact";
    }

    @RequestMapping(value = "/{locale}/contact")
    public String  contactWithLocale(Model model) {
        model.addAttribute("page_name", "contact");
        return "contact";
    }
}
