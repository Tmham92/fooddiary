package nl.bioinf.fooddiary.dao;

import nl.bioinf.fooddiary.dao.jdbc.VerifyProductDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;
/**
 @Author Tobias Ham
 */
@RunWith(MockitoJUnitRunner.class)
class VerifyProductRepositoryTest {

    @Mock
    private VerifyProductDAO mockProductDao;

    @BeforeEach
    public void setUp() {
         //needed for inititalizytion of @Mock
         MockitoAnnotations.initMocks(this);
         mockProductDao = Mockito.mock(VerifyProductDAO.class);
    }

    @Test
    public void getProductSunnyTest() {
        Mockito.when(mockProductDao.getHighestProductCode()).thenReturn(100);
        int productCode = mockProductDao.getHighestProductCode();
        assertEquals(productCode, 100);
    }

    @Test
    void checkProductCode() {
        Mockito.when(mockProductDao.checkProductCode(5)).thenReturn(true);
        boolean exists = mockProductDao.checkProductCode(5);
        assertEquals(exists, true);
    }
}