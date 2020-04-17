package nl.bioinf.fooddiary.control;

import nl.bioinf.fooddiary.FooddiaryApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Locale;

/**
 * @author Hans Zijlstra
 * This class handles redirecting of the authenticated user to the right page
 * Users of the food diary are redirected to the food diary page
 * Admins are redirected to their own allocated page.
 */

@Controller
public class RoleDefaultController {

    private static final Logger logger = LoggerFactory.getLogger(FooddiaryApplication.class);

    /**
     * Method handling the redirect to pages based on the authentication role of the user
     * takes locale Locale and Authentication authentication  as arguments
     * @param locale (Locale)
     * @param authentication (Authentication)
     * @return allocatedPage
     */
    @RequestMapping(value = {"/default"})
    public String redirectAllocater(Locale locale, Authentication authentication) {
        logger.info("/default url has been called returning: redirect: + locale.getLanguage() + /home");
        authentication = SecurityContextHolder.getContext().getAuthentication();
        String allocatedPage = "";

        for (GrantedAuthority grantedAuthority : authentication.getAuthorities()) {
           if (grantedAuthority.getAuthority().equals("ROLE_USER")){
               logger.info("/default url has been called, authority = " +  grantedAuthority.getAuthority()+ ": redirect: + locale.getLanguage() + /home");
               allocatedPage = "redirect:" + locale.getLanguage() + "/diary-entry";
           } else {
               logger.info("/default url has been called, authority = " +  grantedAuthority.getAuthority()+ ": redirect: + locale.getLanguage() + /home");
               allocatedPage = "redirect:" + locale.getLanguage() + "/home";
           }
        }
       return allocatedPage;
    }
}


