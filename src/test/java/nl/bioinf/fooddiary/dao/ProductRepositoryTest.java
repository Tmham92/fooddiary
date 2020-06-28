package nl.bioinf.fooddiary.dao;

import nl.bioinf.fooddiary.dao.jdbc.ProductDAO;
import nl.bioinf.fooddiary.model.product.ProductEntry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLDataException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;


/**
 * @author Hans Zijlstra
 */
@RunWith(SpringRunner.class)
class ProductRepositoryTest {
    private static String language = "nl";

    @Mock
    private ProductDAO mockProductDAO;

    @BeforeEach
    public void setUp() {
        // needed for inititalizytion of @Mock
        MockitoAnnotations.initMocks(this);
        mockProductDAO = Mockito.mock(ProductDAO.class);
    }

    @Test
    public void getProductSunnyTest() {
      String product = "Aardappelen rauw";
      Mockito.when(mockProductDAO.getProductId(language,product)).thenReturn(1);
      int retrievedId = mockProductDAO.getProductId(language,product);
      assertEquals(retrievedId, 1);
    }

    @Test
    public void getProductIdWithEmptyString() {
        String product = "%%%%$$$$";
        Mockito.when(mockProductDAO.getProductId(language,product)).thenThrow(new IllegalArgumentException("Illegal argument"));
        try {
             int id = mockProductDAO.getProductId(language,product);
        } catch (IllegalArgumentException e) {
            String msg = "Illegal argument";
            assertEquals(e.getMessage(), msg);
        }
    }

    @Test
    public void getProductIdWithNull() {
        String product = null;
        Mockito.when(mockProductDAO.getProductId(language,product)).thenThrow(new IllegalArgumentException("Illegal argument"));
        try {
            int id = mockProductDAO.getProductId(language,product);
        } catch (IllegalArgumentException e) {
            String msg = "Illegal argument";
            assertEquals(e.getMessage(), msg);
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {0, -1, -1.5, -100})
    public void InsertProductWithInvalidQuantityValue(double quantity) {
        ProductEntry productEntry = ProductEntry.builder().productDescription("Aardappelen rauw").quantity(quantity).unit("g").date("31-01-2020").time("12:01").mealtime("ontbijt").build();
        mockProductDAO.insertProductIntoDiary(language,1,1, productEntry);
        Mockito.when(mockProductDAO.insertProductIntoDiary(language,1, 1, productEntry)).thenThrow(new IllegalArgumentException("Illegal argument"));
        try {
            mockProductDAO.insertProductIntoDiary(language,1, 1, ProductEntry.builder().productDescription("Aardappelen rauw").quantity(quantity).unit("g").date("31-01-2020").time("12:01").mealtime("ontbijt").build());
        } catch (IllegalArgumentException e) {
            String msg = "Illegal argument";
            assertEquals(e.getMessage(), msg);

        }
    }

    @Test
    public void addToDiaryReturnsError() {
        int userId = 1;
        String date = "31-01-2020";
        mockProductDAO.getDiaryEntriesByDate(language, userId, date);
        try {
            mockProductDAO.getDiaryEntriesByDate(language, userId, date);
        } catch (IllegalArgumentException e) {
            String msg = "Cannot add to database";
            assertEquals(e.getMessage(), msg);
        }

    }

    @Test
    public void addToDiaryWithNull() {
        int userId = 1;
        String date = null;
        mockProductDAO.getDiaryEntriesByDate(language, userId, date);
        try {
            mockProductDAO.getDiaryEntriesByDate(language, userId, date);
        } catch (IllegalArgumentException e) {
            String msg = "Cannot add to database";
            assertEquals(e.getMessage(), msg);
        }

    }






}