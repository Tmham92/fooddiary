package nl.bioinf.fooddiary.service;

import nl.bioinf.fooddiary.dao.jdbc.ProjectDAO;
import nl.bioinf.fooddiary.model.project.NewProject;
import nl.bioinf.fooddiary.model.project.NewProjectUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService implements IProjectService {

    @Autowired
    private ProjectDAO projectDAO;

    @Override
    public void addNewProject(NewProject newProject) {
        projectDAO.addNewProject(newProject);
    }

    @Override
    public void addProjectUser(NewProjectUser newProjectUser) {
        projectDAO.addProjectUser(newProjectUser);
    }
}
