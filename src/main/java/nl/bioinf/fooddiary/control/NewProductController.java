package nl.bioinf.fooddiary.control;

import nl.bioinf.fooddiary.model.NewProduct;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class NewProductController {

    @RequestMapping(value="/addednewproduct")
    public String newAddedProduct(NewProduct newProduct) {
        //newProduct.setDescription("Dit is inderdaad Quacker Cruesli");

        return "newproductform";
    }

    @GetMapping("/newproductform")
    public String newProductForm(Model model) {
        model.addAttribute("newproductform", new NewProduct());
        return "newproductform";
    }

    @PostMapping("/newproductform")
    public String newProductFormSubmit(@ModelAttribute NewProduct newProduct) {
        return "newproductform";
    }


}
