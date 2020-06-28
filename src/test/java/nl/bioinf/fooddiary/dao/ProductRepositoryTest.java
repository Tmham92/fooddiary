//package nl.bioinf.fooddiary.dao;
//
//import nl.bioinf.fooddiary.dao.jdbc.ProductDAO;
//import nl.bioinf.fooddiary.model.product.ProductEntry;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.ValueSource;
//import org.junit.runner.RunWith;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//import org.springframework.test.context.junit4.SpringRunner;
//import static org.junit.jupiter.api.Assertions.*;
//
//
///**
// * @author Hans Zijlstra
// */
//@RunWith(SpringRunner.class)
//class ProductRepositoryTest {
//
//
//
//    @Mock
//    private ProductDAO mockProductDAO;
//
//    @BeforeEach
//    public void setUp() {
//        // needed for inititalizytion of @Mock
//        MockitoAnnotations.initMocks(this);
//        mockProductDAO = Mockito.mock(ProductDAO.class);
//    }
//
//    @Test
//    public void getProductSunnyTest() {
//      String product = "Aardappelen rauw";
//      Mockito.when(mockProductDAO.getProductId(product)).thenReturn(1);
//      int retrievedId = mockProductDAO.getProductId(product);
//      assertEquals(retrievedId, 1);
//    }
//
//    @Test
//    public void getProductIdWithEmptyString() {
//        String product = "%%%%$$$$";
//        Mockito.when(mockProductDAO.getProductId(product)).thenThrow(new IllegalArgumentException("Illegal argument"));
//        try {
//             int id = mockProductDAO.getProductId(product);
//        } catch (IllegalArgumentException e) {
//            String msg = "Illegal argument";
//            assertEquals(e.getMessage(), msg);
//        }
//    }

//    @ParameterizedTest
//    @ValueSource(doubles = {0, -1, -1.5, -100})
//    public void InsertProductWithInvalidQuantityValue(double quantity) {
//        ProductEntry productEntry = new ProductEntry("Aardappelen rauw", quantity, "g", "31-01-2020", "12:01", "ontbijt", "");
//        mockProductDAO.insertProductIntoDiary(1,1, productEntry);
//        Mockito.when(mockProductDAO.insertProductIntoDiary(1, 1, productEntry)).thenThrow(new IllegalArgumentException("Illegal argument"));
//        try {
//            mockProductDAO.insertProductIntoDiary(1, 1, new ProductEntry(1, 1, 1, "Aardappelen rauw", quantity, "g", "31-01-2020", "12:01", "ontbijt", ""));
//        } catch (IllegalArgumentException e) {
//            String msg = "Illegal argument";
//            assertEquals(e.getMessage(), msg);
//
//        }
//    }

//    @Test
//    public void addToDiaryTest() {
//        mockProductDAO.insertProductIntoDiary(1, 1, new ProductEntry(1, 1, 1, "Aardappelen rauw", 200, "g", "31-01-2020", "12:01", "ontbijt", ""));
////        Mockito.when(mockProductDAO.insertProductIntoDiary(Mockito.any(Integer.TYPE), Mockito.any(Integer.TYPE), Mockito.any(ProductEntry.class))).thenReturn(pr);
//
//
//    }
//}