package nl.bioinf.fooddiary.dao.jdbc;

import nl.bioinf.fooddiary.model.project.NewProject;
import nl.bioinf.fooddiary.service.INewProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class NewProjectDAO implements INewProjectService {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public NewProjectDAO(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addNewProject(NewProject newProject) {
        String sql = "INSERT INTO project" +
                "(id, name, project_leader, creation_date, closing_date) values (?,?,?,?,?);";
        jdbcTemplate.update(sql, newProject.getId(), newProject.getName(), newProject.getProject_leader(),
                newProject.getCreation_date(), newProject.getClosing_date());
    }
}
