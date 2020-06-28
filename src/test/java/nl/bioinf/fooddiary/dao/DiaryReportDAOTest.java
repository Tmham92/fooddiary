package nl.bioinf.fooddiary.dao;

import nl.bioinf.fooddiary.dao.jdbc.ReportDAO;
import nl.bioinf.fooddiary.model.report.ReportEntry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Hans Zijlstra
 */
@RunWith(SpringRunner.class)
class DiaryReportDAOTest {

    @Mock
    private ReportDAO mockReportDAO;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockReportDAO = Mockito.mock(ReportDAO.class);
    }

    @Test
    void getProjectsSunny() {
        List<String> expected = new ArrayList<>();
        expected.add("Hans");
        expected.add("Peter");

        Mockito.when(mockReportDAO.getProjects()).thenReturn(new ArrayList<>(Arrays.asList("Hans", "Peter")));
        List<String> result =  mockReportDAO.getProjects();
        assertEquals(expected, result);
    }

    @Test
    void getProjectNonFound() {
        Mockito.when(mockReportDAO.getProjects()).thenThrow(new NullPointerException("No projects found"));
        try{
            mockReportDAO.getProjects();
        } catch (NullPointerException e) {
            String msg = "No projects found";
            assertEquals(msg, e.getMessage());

        }
    }

    @Test
    void getUsersWithNull() {
        String project = null;
        Mockito.when(mockReportDAO.getUserFromProject(project)).thenThrow(new NullPointerException("No users found"));
        try{
            mockReportDAO.getUserFromProject(project);
        } catch (NullPointerException e) {
            String msg = "No users found";
            assertEquals(msg, e.getMessage());

        }
    }

    @Test
    void getUserFromProjectWithNoUsers() {
        String project = "testProject";
        Mockito.when(mockReportDAO.getUserFromProject(project)).thenThrow(new NullPointerException("No users found"));
        try{
            mockReportDAO.getUserFromProject(project);
        } catch (NullPointerException e) {
            String msg = "No users found";
            assertEquals(msg, e.getMessage());

        }
    }

    @Test
    void getUserReportEmpty() {
        ReportEntry reportEntry = new ReportEntry(new ArrayList(Arrays.asList("hans")), new Date(), new Date());
        Mockito.when(mockReportDAO.getUserReport(reportEntry)).thenThrow(new NullPointerException("Reports could not be found"));
        try{
            mockReportDAO.getUserReport(reportEntry);
        } catch (NullPointerException e) {
            String msg = "Reports could not be found";
            assertEquals(msg, e.getMessage());
        }
    }

    @Test
    void getUserReportWithNull() {
        ReportEntry reportEntry = null;
        Mockito.when(mockReportDAO.getUserReport(reportEntry)).thenThrow(new NullPointerException("Reports could not be found"));
        try{
            mockReportDAO.getUserReport(reportEntry);
        } catch (NullPointerException e) {
            String msg = "Reports could not be found";
            assertEquals(msg, e.getMessage());
        }
    }

}