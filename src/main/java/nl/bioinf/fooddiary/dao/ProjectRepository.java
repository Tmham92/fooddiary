package nl.bioinf.fooddiary.dao;

import nl.bioinf.fooddiary.model.project.NewProject;
import nl.bioinf.fooddiary.model.project.NewProjectUser;

public interface ProjectRepository {
    void addNewProject(NewProject newProject);
    void addProjectUser(NewProjectUser newProjectUser);
}
