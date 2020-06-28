package nl.bioinf.fooddiary.dao.jdbc;

import nl.bioinf.fooddiary.dao.ProjectRepository;
import nl.bioinf.fooddiary.model.project.NewProject;
import nl.bioinf.fooddiary.model.project.NewProjectUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProjectDAO implements ProjectRepository {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ProjectDAO(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addNewProject(NewProject newProject) {
        String sql = "INSERT INTO project" +
                "(id, name, project_leader, creation_date, closing_date) values (?,?,?,?,?);";
        jdbcTemplate.update(sql, newProject.getId(), newProject.getName(), newProject.getProjectLeader(),
                newProject.getCreation_date(), newProject.getClosingDate());
    }

    @Override
    public void addProjectUser(NewProjectUser newProjectUser) {
        String sql = "INSERT INTO user_project" +
                "(id, user_id, project_id) values (?,?,?);";
        jdbcTemplate.update(sql, newProjectUser.getId(), newProjectUser.getUserId(), newProjectUser.getProjectId());
    }
}
