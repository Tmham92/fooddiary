package nl.bioinf.fooddiary.control;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

/**
 * @author Hans Zijlstra
 */

@Controller
public class RoleDefaultController {
    @RequestMapping(value = {"/default"})
    public String redirectAllocater(Locale locale, Authentication authentication) {
        authentication = SecurityContextHolder.getContext().getAuthentication();
        String allocatedPage = "";

        for (GrantedAuthority grantedAuthority : authentication.getAuthorities()) {
           if (grantedAuthority.getAuthority().equals("USER")){
               allocatedPage = "redirect:" + locale.getLanguage() + "/diary-entry";
           } else {
               allocatedPage = "redirect:" + locale.getLanguage() + "/home";
           }
        }
       return allocatedPage;
    }
}


