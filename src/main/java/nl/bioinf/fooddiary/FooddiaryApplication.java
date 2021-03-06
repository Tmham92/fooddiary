package nl.bioinf.fooddiary;

import nl.bioinf.fooddiary.model.newproduct.NewProductPicture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


@SpringBootApplication
@EnableConfigurationProperties({
        NewProductPicture.class
})
public class FooddiaryApplication {

    // Logger object that logs everything that from the FooddiaryApplication class.
    private static final Logger logger = LoggerFactory.getLogger(FooddiaryApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(FooddiaryApplication.class, args);
        logger.debug("--FooddiaryApplication started!--");
    }

}
