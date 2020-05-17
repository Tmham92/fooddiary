package nl.bioinf.fooddiary.service;

import nl.bioinf.fooddiary.dao.jdbc.NewProjectDAO;
import nl.bioinf.fooddiary.model.project.NewProject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewProjectService implements INewProjectService{

    @Autowired
    private NewProjectDAO newProjectDAO;

    @Override
    public void addNewProject(NewProject newProject) {
        newProjectDAO.addNewProject(newProject);
    }
}
