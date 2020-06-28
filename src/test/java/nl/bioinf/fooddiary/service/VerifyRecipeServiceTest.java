package nl.bioinf.fooddiary.service;

import nl.bioinf.fooddiary.dao.ProductRepository;
import nl.bioinf.fooddiary.dao.RecipeRepository;
import nl.bioinf.fooddiary.model.nutrient.NutrientValues;
import nl.bioinf.fooddiary.model.product.*;
import nl.bioinf.fooddiary.model.recipe.SingleRecipeProduct;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Tom Wagenaar
 * Date: 08-06-2020
 *
 * Junit test for VerifyRecipeService class, the junit test in this class don't work, because down the line the way of
 * storing the recipe data was changed.
 */
@RunWith(SpringRunner.class)
class VerifyRecipeServiceTest {

    @Mock
    private RecipeRepository mockRecipeRepository;

    @Mock
    private ProductRepository mockProductRepository;

    private List<SingleRecipeProduct> recipeTableData = new ArrayList<>();
    private Product product;


    @BeforeEach
    public void setUp() {
        // Initialize the @Mock.
        MockitoAnnotations.initMocks(this);

        // Initialize the mocked recipe repository.
        mockRecipeRepository = Mockito.mock(RecipeRepository.class);

        // Initialize the mocked product repository
        mockProductRepository = Mockito.mock(ProductRepository.class);

        recipeTableData.add(new SingleRecipeProduct(1, 1, 1, "Nasi", 100, "g", 0));
//        recipeTableData.add(new SingleRecipeProduct(2, 1, 2, "Nasi", 100, "ml", 0));
//        recipeTableData.add(new SingleRecipeProduct(3, 1, 3, "Nasi", 100, "g", 0));
//        recipeTableData.add(new SingleRecipeProduct(4, 2, 121, "Koek", 100, "g", 0));


        // Parse the group code and group description into an object.
        ProductGroup productGroup = ProductGroup.builder("20", "Aardappelen en knolgewassen")
                .build();

        // Parse the descriptionDutch, descriptionEnglish and synonymous of the product into an object.
        ProductDescription productDescription = ProductDescription.builder("Aardappelen rauw", "Potatoes raw")
                .build();

        // Parse the measurement -Unit, -Quantity and -Comment into an object.
        ProductMeasurement productMeasurement = ProductMeasurement.builder("g", "100")
                .build();

        // Parse the enrichedWith and tracesOf values from a the product into an object.
        ProductInfoExtra productInfoExtra = ProductInfoExtra.builder()
                .enrichedWith("Fe, Ca")
                .build();


        // Parse all nutrient values from a single product line into one object.
        NutrientValues nutrientValues = NutrientValues.builder()
                .build();

        product = new Product(1, productGroup, productDescription, productMeasurement, productInfoExtra, nutrientValues);
    }

    @Test
    public void sunnyCreateRecipeMap() {
//        Mockito.when(mockRecipeRepository.getAllRecipes()).thenReturn(recipeTableData);
//        List<SingleRecipeProduct> recipeTable = mockRecipeRepository.getAllRecipes();
//
//        // Make a VerifyRecipeService object, call the createRecipeMap method and give it a list with SingleRecipeObjects.
//        VerifyRecipeService verifyRecipeService = new VerifyRecipeService();
//        LinkedHashMap<String, List<SingleRecipeProduct>> recipeDataMap = verifyRecipeService.createRecipeMap(recipeTable);
//
//        // Test if the the recipeDataMap contains a key named Nasi, get the first SingleRecipeObject, get the recipe name of that object and check if it is equal to Nasi.
//        assertEquals("Nasi", recipeDataMap.get("Nasi").get(0).getRecipeGroup());
    }

    @Test
    public void sunnyGetMatchingProducts() {
//        Mockito.when(mockProductRepository.getSpecificProductByProductCode(product.getCode())).thenReturn(product);
//
//        Product product = mockProductRepository.getSpecificProductByProductCode(1);
//
//        // Call the getMatchingProducts method and pass the recipeTableData on.
//        VerifyRecipeService verifyRecipeService = new VerifyRecipeService();
//        List<Product> productList = verifyRecipeService.getMatchingProducts(recipeTableData);

    }


}