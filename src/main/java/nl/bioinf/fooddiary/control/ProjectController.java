package nl.bioinf.fooddiary.control;

import nl.bioinf.fooddiary.FooddiaryApplication;
import nl.bioinf.fooddiary.model.project.NewProject;
import nl.bioinf.fooddiary.model.project.NewProjectUser;
import nl.bioinf.fooddiary.service.ProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.Locale;

/**
 * @Author Hugo Donkerbroek
 */

@Controller
public class ProjectController {
    private static final Logger logger = LoggerFactory.getLogger(FooddiaryApplication.class);

    @Autowired
    ProjectService projectService;

    /**
     * Shows the newprojectform and the newprojectuserform on the /project url page.
     * @param locale loads the web page in the current language setting
     * @param model newproject- and newprojectuserform used for filling in information for projects
     * @return returns the locale + project.html page
     */
    @RequestMapping(value = {"/project"}, method = RequestMethod.GET)
    public String projectWithoutLocale(Locale locale, Model model) {
        logger.info("/project url has been called");
        NewProject newProject = new NewProject();
        logger.info("/adding the new project form to the attributes");
        model.addAttribute("newprojectform", newProject);

        NewProjectUser newProjectUser = new NewProjectUser();
        model.addAttribute("newprojectuserform", newProjectUser);
        return "redirect:" + locale.getLanguage() + "/project";
    }

    /**
     * Shows the newprojectform and the newprojectuserform on the /project url page.
     * @param model newproject- and newprojectuserform used for filling in information for projects
     * @return returns the project.html page
     */
    @RequestMapping(value = "/{locale}/project", method = RequestMethod.GET)
    public String projectWithLocale(Model model) {
        logger.info("/project url has been called");
        NewProject newProject = new NewProject();
        logger.info("/adding the new project form to the attributes");
        model.addAttribute("newprojectform", newProject);

        NewProjectUser newProjectUser = new NewProjectUser();
        model.addAttribute("newprojectuserform", newProjectUser);
        return "/project";
    }

    /**
     * Handles the page post request. The data sent with the post request is saved in the NewProject class
     * @param newProject the new project data from the newprojectform
     * @return redirects to /project to reload the page
     */
    @RequestMapping(value = "/new-project", method = RequestMethod.POST)
    public String injectNewProject(@ModelAttribute("newprojectform")
                                @Validated NewProject newProject) {
        logger.info("submitted the new-user-form");
        projectService.addNewProject(newProject);
        logger.info("redirect to /project url");
        return "redirect:/project";
    }

    /**
     * Handles the page post request. The data sent with the post request is saved in the NewProjectUser class
     * @param newProjectUser the project and user data from the newprojectuserform
     * @return redirects to /project to reload the page
     */
    @RequestMapping(value = "/new-project-user", method = RequestMethod.POST)
    public String test(@ModelAttribute("newprojectuserform")
                                   @Validated NewProjectUser newProjectUser) {
        logger.info("submitted the new-user-form");
        projectService.addProjectUser(newProjectUser);
        logger.info("redirect to /project url");
        return "redirect:/project";
    }
}
