package nl.bioinf.fooddiary.dao.productnutrient;

import nl.bioinf.fooddiary.model.nutrient.ProductNutrient;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
class ProductNutrientRepositoryTest {
    // Instance variable declaration
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ProductNutrientRepository repository;

    @Before
    public void setUp() {
        // given
        ProductNutrient productNutrientObject = ProductNutrient.builder(10, "ENERG", "0,256")
                .build();


        entityManager.persist(productNutrientObject);
    }

}