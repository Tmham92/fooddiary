package nl.bioinf.fooddiary.model.report;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author Hans Zijlstra
 * Class that holds data to create a report of a users food diary
 * it requires a list of users chosen from a project, a starting and end date
 * representing for which days a report has to be created.
 */
public class ReportEntry {

    @NotBlank(message = "You need to select atleast one user ")
    private List<String> users;
    @NotBlank(message = "You need to select a start date")
    private Date dateFrom;
    @NotBlank(message = "You need to select an end date")
    private Date dateTil;

    public ReportEntry(@NotBlank(message = "You need to select atleast one user ") List users, @NotBlank(message = "You need to select a start date") Date dateFrom, @NotBlank(message = "You need to select an end date") Date dateTil) {
        this.users = users;
        this.dateFrom = dateFrom;
        this.dateTil = dateTil;
    }

    public List getUsers() {
        return users;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    @Override
    public String toString() {
        return "ReportEntry{" +
                "users=" + users +
                ", dateFrom='" + dateFrom + '\'' +
                ", dateTil='" + dateTil + '\'' +
                '}';
    }

    public Date getDateTil() {
        return dateTil;
    }
}
