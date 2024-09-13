package vn.hoidanit.laptopshop.controller.admin;

import java.util.List;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.repository.UserRepository;
import vn.hoidanit.laptopshop.service.UploadService;
import vn.hoidanit.laptopshop.service.UserService;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;





@Controller
public class UserController {
        private final UserService userService;
        private final UploadService uploadService;
        private PasswordEncoder passwordEncoder;

    public UserController(UserService userService, UserRepository userRepository, UploadService uploadService, PasswordEncoder passwordEncoder ) {
        this.userService = userService;
        this.uploadService = uploadService;
        this.passwordEncoder = passwordEncoder;
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
        return "admin/user/show";
    }
    @RequestMapping("/admin/user/{id}")
    public String getUserDetailPage(Model model, @PathVariable long id) {
        User user = this.userService.getInfoUserById(id);
        model.addAttribute("user", user);
        model.addAttribute("id", id);
        
        return "admin/user/detail";
        
    }

    @GetMapping("/admin/user/create")
    public String getUser(Model model){
        model.addAttribute("newUser", new User());
        return "admin/user/create";

    }
    


@PostMapping(value = "/admin/user/create")
public String createUserPage(Model model, @ModelAttribute("newUser") User hoidanit,
@RequestParam("hoidanitFile") MultipartFile file){ 
    String avatar = this.uploadService.handleSaveUploadFile(file, "avatar");
    String hashPassword = this.passwordEncoder.encode(hoidanit.getPassword());
    hoidanit.setAvatar(avatar);
    hoidanit.setPassword(hashPassword);
    hoidanit.setRole(this.userService.getRoleByName(hoidanit.getRole().getName()));
    this.userService.handleSaveUser(hoidanit);
    return "redirect:/admin/user";

}
@RequestMapping("/admin/user/update-user/{id}")
    public String getUpdateUser(Model model, @PathVariable long id){
        User currentUser = this.userService.getInfoUserById(id);
        model.addAttribute("newUser", currentUser);
        return "admin/user/update-user";

    }


@PostMapping("/admin/user/update-user")
    public String postUpdateUser(Model model, @ModelAttribute("newUser") User hoidanit, @RequestParam(value = "hoidanitFile", required = false) MultipartFile file) {
        User currentUser = this.userService.getInfoUserById(hoidanit.getId());
        
        if (currentUser != null) {
            // Cập nhật thông tin cơ bản
            currentUser.setAddress(hoidanit.getAddress());
            currentUser.setFullName(hoidanit.getFullName());
            currentUser.setPhone(hoidanit.getPhone());
            
            // Cập nhật avatar nếu có file mới
            if (file != null && !file.isEmpty()) {
                String avatar = this.uploadService.handleSaveUploadFile(file, "avatar");
                currentUser.setAvatar(avatar);
            }
    
            // Cập nhật role nếu có
            if (hoidanit.getRole() != null && hoidanit.getRole().getName() != null) {
                currentUser.setRole(this.userService.getRoleByName(hoidanit.getRole().getName()));
            }
            
            // Lưu user cập nhật
            this.userService.handleSaveUser(currentUser);
        }
        
        return "redirect:/admin/user";
    }
    
@GetMapping("/admin/user/delete-user/{id}")
    public String getDeleteUserPage(Model model, @PathVariable long id){
        model.addAttribute("id", id);
        User user = new User();
        user.setId(id);
        model.addAttribute("newUser", user);
        return "admin/user/delete-user";
    }
    @PostMapping("/admin/user/delete-user")
    public String postDeleteUser(Model model, @ModelAttribute("newUser") User hoidanit){
        this.userService.deleteUserById(hoidanit.getId());
        return "redirect:/admin/user";
    }

}