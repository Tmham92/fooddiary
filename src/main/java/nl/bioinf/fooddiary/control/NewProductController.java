package nl.bioinf.fooddiary.control;

import nl.bioinf.fooddiary.model.NewProductForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;


public class NewProductController {
    @GetMapping("/newProductForm")
    public String newProductForm(Model model) {
        model.addAttribute("newProductForm", new NewProductForm());
        return "newProductForm";
    }

    @PostMapping("/newProductForm")
    public String newProductFormSubmit(@ModelAttribute NewProductForm newProductForm) {
        return "/newProductForm";
    }
}
