package nl.bioinf.fooddiary.service;

import nl.bioinf.fooddiary.model.project.NewProject;
import nl.bioinf.fooddiary.model.project.NewProjectUser;

public interface IProjectService {
    void addNewProject(NewProject newProject);
    void addProjectUser(NewProjectUser newProjectUser);
}
