package nl.bioinf.fooddiary.control;

import nl.bioinf.fooddiary.dao.product.ProductRepository;
import nl.bioinf.fooddiary.model.product.ProductDescription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * @author Hans Zijlstra
 */

@Controller
public class DiaryEntryController {

    @Autowired
    private ProductRepository productRepository;

    @RequestMapping(value = "/diary-entry")
        public String diaryWithoutLocale(Locale locale) {
        return "redirect:" + locale.getLanguage() + "/diary-entry";

        }

    @RequestMapping(value = "/{locale}/diary-entry")
    public String  diaryWithLocale(Model model) {
        return "/diary-entry";
    }

    @RequestMapping(value = "/product-description", method = RequestMethod.GET)
    public @ResponseBody
    List<ProductDescription> getTime() {
        return productRepository.getAllProductsByDescription();

    }



}
