package vn.hoidanit.laptopshop.controller;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.repository.UserRepository;
import vn.hoidanit.laptopshop.service.UserService;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class UserController {
        private final UserService userService;

    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
    }
    @RequestMapping("/")
    public String getHomePage(Model model) {
        List<User> arUsers = this.userService.getallUserByEmail("phuong0106@gmail.com");
        System.out.println(arUsers);
        return "hello";
    }
    @RequestMapping("/admin/user")
    public String getTableUser(Model model) {
       List<User> users = this.userService.getallUser();
       model.addAttribute("users1", users);
        return "admin/user/table";
    }
    @RequestMapping("/admin/user/{id}")
    public String getUserDetailPage(Model model, @PathVariable long id) {
        User user = this.userService.getInfoUserById(id);
        model.addAttribute("user", user);
        model.addAttribute("id", id);
        
        return "admin/user/show";
        
    }

    @RequestMapping("/admin/user/create")
    public String getUser(Model model){
        model.addAttribute("newUser", new User());
        return "admin/user/create";

    }
    


@RequestMapping(value = "/admin/user/create", method = RequestMethod.POST)
public String createUserPage(Model model, @ModelAttribute("newUser") User hoidanit){
    System.out.println("run here" + hoidanit);
    this.userService.handleSaveUser(hoidanit);
    return "redirect:/admin/user";

}
}