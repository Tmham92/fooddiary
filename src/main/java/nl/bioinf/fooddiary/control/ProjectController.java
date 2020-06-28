package nl.bioinf.fooddiary.control;

import nl.bioinf.fooddiary.FooddiaryApplication;
import nl.bioinf.fooddiary.model.project.NewProject;
import nl.bioinf.fooddiary.service.NewProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Locale;

@Controller
public class ProjectController {
    private static final Logger logger = LoggerFactory.getLogger(FooddiaryApplication.class);

    @Autowired
    NewProjectService newProjectService;

    /**
     * Shows the add-user-form on the /adduser url page.
     * @param locale loads the web page in the current language setting
     * @param model New-user-form used for filling in account information for new accounts
     * @return returns the locale + add-user.html page
     */
    @RequestMapping(value = {"/project"}, method = RequestMethod.GET)
    public String projectWithoutLocale(Locale locale, Model model) {
        logger.info("/project url has been called");
        NewProject newProject = new NewProject();
        logger.info("/adding the new project form to the attributes");
        model.addAttribute("newprojectform", newProject);
        return "redirect:" + locale.getLanguage() + "/project";
    }

    /**
     * Shows the add-user-form on the /adduser url page.
     * @param model New-user-form used for filling in account information for new accounts
     * @return returns the add-user.html page
     */
    @RequestMapping(value = "/{locale}/project", method = RequestMethod.GET)
    public String projectWithLocale(Model model) {
        logger.info("/project url has been called");
        NewProject newProject = new NewProject();
        logger.info("/adding the new project form to the attributes");
        model.addAttribute("newprojectform", newProject);
        return "/project";
    }

    /**
     * Handles the page post request. The data sent with the post request is saved in the newUser class
     * @param newProject the user data from the new-user-form
     * @return redirects to /adduser to reload the page
     */
    @RequestMapping(value = "/project", method = RequestMethod.POST)
    public String injectNewProject(@ModelAttribute("newprojectform")
                                @Validated NewProject newProject, BindingResult bindingResult) {
        logger.info("submitted the new-user-form");
        newProjectService.addNewProject(newProject);
        logger.info("redirect to /project url");
        return "redirect:/project";
    }
}
