package nl.bioinf.fooddiary.newproductcontrollertests;

import nl.bioinf.fooddiary.control.NewProductController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class NewProductControllerTests {

    @Autowired
    private NewProductController newProductController;

    @Test
    void contextLoads() throws Exception {
        assertThat(newProductController).isNotNull();
    }
}
