package nl.bioinf.fooddiary.control;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Locale;

/**
 * @author Hans Zijlstra
 */

@Controller
public class DiaryEntryController {

    @RequestMapping(value = "/diary-entry")
        public String diaryWithoutLocale(Locale locale) {
        return "redirect:" + locale.getLanguage() + "/diary-entry";

        }

    @RequestMapping(value = "/{locale}/diary-entry")
    public String  diaryWithLocale(HttpServletRequest request, Authentication authentication, Model model, Principal principal) {

        return "/diary-entry";
    }

    }
