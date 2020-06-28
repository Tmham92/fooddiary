package nl.bioinf.fooddiary.model.project;

import javax.validation.constraints.NotNull;

public class NewProjectUser {
    private int id;
    @NotNull
    private int userId;
    @NotNull
    private int projectId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "NewProjectUser{" +
                "id=" + id +
                ", user_id=" + userId +
                ", project_id=" + projectId +
                '}';
    }
}
