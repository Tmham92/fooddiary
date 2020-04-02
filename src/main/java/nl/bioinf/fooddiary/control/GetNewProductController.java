package nl.bioinf.fooddiary.control;

import nl.bioinf.fooddiary.model.NewProduct;
import nl.bioinf.fooddiary.service.NewProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Locale;
/*
    @Author Tobias Ham
*/

@Controller
public class GetNewProductController {
    @Autowired
    NewProductService newProductService;

    @RequestMapping(value= {"/getnewproducts"}, method = RequestMethod.GET)
    public String getAllNewProductsWithoutLocale(Locale locale, Model model) {
        List<NewProduct> newProducts = newProductService.getAllNewProducts();
        model.addAttribute("getNewProducts", newProducts);
        model.addAttribute("page_name", "getnewproducts");
        return "redirect:" + locale.getLanguage() + "/getnewproducts";
    }

    @RequestMapping(value = "/{locale}/getnewproducts")
    public String getAllNewProductsWithLocale(Model model) {
        List<NewProduct> newProducts = newProductService.getAllNewProducts();
        model.addAttribute("getNewProducts", newProducts);
        model.addAttribute("page_name", "newproductform");
        return "/getnewproducts";
    }

    @RequestMapping(value = "/getnewproducts", method = RequestMethod.POST)
    private String deleteNewProduct(@RequestParam int productID) {
        System.out.println("Passing " + productID + " to delete Entry Function");
        newProductService.deleteNewProduct(productID);
        return "redirect:/getnewproducts";
    }
}
