package nl.bioinf.fooddiary.dao.jdbc;

import nl.bioinf.fooddiary.model.project.NewProject;

public interface NewProjectRepository {
    void addNewProject(NewProject newProject);
}
