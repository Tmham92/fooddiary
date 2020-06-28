package nl.bioinf.fooddiary.model.project;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;

public class NewProject {
    private int id;
    @NotNull
    private String name;
    @NotNull
    private String projectLeader;
    private Date closingDate;

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

    public String getProjectLeader() {
        return projectLeader;
    }

    public void setProjectLeader(String projectLeader) {
        this.projectLeader = projectLeader;
    }

    public LocalDate getCreation_date() {
        return LocalDate.now();
    }

    public Date getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(Date closingDate) {
        this.closingDate = closingDate;
    }
}
