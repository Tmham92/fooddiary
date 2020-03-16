//package nl.bioinf.fooddiary.control;
//
//import nl.bioinf.fooddiary.dao.IUserService;
//import nl.bioinf.fooddiary.model.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import java.util.List;
//
//@Controller
//@RequestMapping("home")
//public class UserController {
//
//
//    @Autowired
//    private IUserService userService;
//
//    @PostMapping("/login")
//    public String getUser(@RequestParam(name="username") String username, @RequestParam(name = "pwd") String password) {
//        User user = userService.getUser(username, password);
//        if (validateLogin(user, username, password)) {
//            return "redirect:/contact";
//
//        } else {
//            return "home";
//        }
//    }
//
//    public boolean validateLogin(User user, String username, String password) {
//            return user.getUser_name().equals(username) && user.getPassword().equals(password);
//        }
//
//
//    }
//
//
//
