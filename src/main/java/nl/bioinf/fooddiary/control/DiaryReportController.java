package nl.bioinf.fooddiary.control;

import nl.bioinf.fooddiary.FooddiaryApplication;
import nl.bioinf.fooddiary.dao.DiaryReportRepository;
import nl.bioinf.fooddiary.model.newuser.NewUserReport;
import nl.bioinf.fooddiary.model.report.ReportEntry;
import nl.bioinf.fooddiary.model.report.ReportValueWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * @author Hans Zijlstra
 * This class controls the creation of food diary reports.
 * A project is taken from the database with corresponding users
 * Once the project and the user in this project are selected a csv file is created
 */
@Controller
public class DiaryReportController {

    private static final Logger logger = LoggerFactory.getLogger(FooddiaryApplication.class);


    @Autowired
    private DiaryReportRepository diaryReportRepository;

    /**
     *returns redirect to diary-reports page with local
     * @param locale locale interceptor
     * @return diary-report page
     */
    @RequestMapping(value = "/diary-reports")
    public String reportWithoutLocale(Locale locale) {
        logger.info("/diary-entry url has been called returning: redirect: + locale.getLanguage() + /diary-reports");
        return "redirect:" + locale.getLanguage() + "/diary-reports";
    }

    /**
     * @author Hans Zijlstra
     * @return diary-report
     */
    @RequestMapping(value = "{locale}/diary-reports")
    public String reportWithLocale() {
        logger.info("/diary-entry url has been called returning: /diary-reports");
        return "/diary-reports";
    }


    /**
     *return all the excisting projects in the database
     * @return ResponseEntity
     */
    @GetMapping(value = "projects")
    @ResponseBody
    public ResponseEntity getProjects() {
        try{
            List<String> projects = diaryReportRepository.getProjects();
            if (projects.size() == 0 || projects == null) {
                throw new NullPointerException();
            }
            logger.info("/projects url has been returning all projects");
            return ResponseEntity.ok(projects);
        } catch (NullPointerException e) {
            logger.error("/projects url has been called nullpointerexception has been thrown");
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No projects found");
        }

    }

    /**
     *gets all the participants within a selected project
     * @param projectName name of a project
     * @return responEntity
     */
    @GetMapping(value = "/project-users")
    @ResponseBody
    public ResponseEntity<Object> getUsersFromProject(@RequestParam String projectName ) {
        try {
            List<NewUserReport> userReports = diaryReportRepository.getUserFromProject(projectName);
            if (userReports.size() == 0) {
                throw new NullPointerException();
            }
            logger.info("/project-users url has been called getting users from selected project");
            return ResponseEntity.ok(userReports);
        }catch (NullPointerException e) {
            logger.error("/project-users url has been called and has raised a NullPointer exception");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No project participants found");
        }
    }


    /**
     * Creates a csv file with user and product information  of selected users their food diary, within a selected project
     * @param reportEntry Class containing porject and user information
     * @return responEntity
     */
    @PostMapping(value = "/fetch-report", consumes = "application/json")
    public ResponseEntity<Object> fetchReport(@RequestBody ReportEntry reportEntry) {
        try {
            List<ReportValueWrapper> reports = diaryReportRepository.getUserReport(reportEntry);
            ReportValueWrapper.createDiaryReportCsv(ReportValueWrapper.collectDiaryReports(reports));
            File[] files = new File("diary_reports/").listFiles();
            if (reportEntry == null || files.length == 0)
                throw new IOException();
            else
                logger.info("/fetch-report url has been called creating reports for selected projects and users");
                return new ResponseEntity(files, HttpStatus.OK);
        } catch (IOException e) {
            logger.error("/fetch-report url has been called which resulted in a IOException" );
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Could not create your report");
        }
    }
}
