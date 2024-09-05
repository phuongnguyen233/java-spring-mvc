package vn.hoidanit.laptopshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.hoidanit.laptopshop.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class UserController {
        private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @RequestMapping("/")
    public String getHomePage() {
        String test = this.userService.handleHello();
        return "eric.html";
    }
}
// @RestController
// public class UserController {

//     private UserService userService;

//     public UserController(UserService userService) {
//         this.userService = userService;
//     }

//     @GetMapping("")

//     public String getHomePage() {
//         return this.userService.handleHello();
//     }
// }
