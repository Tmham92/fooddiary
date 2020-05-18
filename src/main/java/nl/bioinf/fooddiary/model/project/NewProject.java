package nl.bioinf.fooddiary.model.project;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;

public class NewProject {
    private int id;
    @NotNull
    private String name;
    @NotNull
    private String project_leader;
    private Date closing_date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProject_leader() {
        return project_leader;
    }

    public void setProject_leader(String project_leader) {
        this.project_leader = project_leader;
    }

    public LocalDate getCreation_date() {
        return LocalDate.now();
    }

    public Date getClosing_date() {
        return closing_date;
    }

    public void setClosing_date(Date closing_date) {
        this.closing_date = closing_date;
    }
}
