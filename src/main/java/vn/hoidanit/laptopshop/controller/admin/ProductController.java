package vn.hoidanit.laptopshop.controller.admin;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.security.crypto.password.PasswordEncoder;

import jakarta.validation.Valid;
import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.service.UploadService;
import vn.hoidanit.laptopshop.service.ProductService;



@Controller
public class ProductController {
    private final ProductService productService;
        private final UploadService uploadService;


    public ProductController(ProductService productService, UploadService uploadService) {
        this.productService = productService;
        this.uploadService = uploadService;
    }
    @GetMapping("/admin/product")
    public String getProduct(Model model)
    {
        List<Product> prs = this.productService.fetchProducts();
        model.addAttribute("products", prs);
        return "admin/product/show";
    }
     @GetMapping("/admin/product/create")
    public String getCreateProductPage(Model model){
        model.addAttribute("newProduct", new Product());

        return "admin/product/create";

    }
    @PostMapping(value = "/admin/product/create")
public String createProductPage( Model model, @ModelAttribute("newProduct") @Valid Product pr,BindingResult newProductBindingResult,
@RequestParam("hoidanitFile") MultipartFile file){ 
    
    if(newProductBindingResult.hasErrors()){
    return "/admin/product/create";
}
    String image = this.uploadService.handleSaveUploadFile(file, "product");
    pr.setImage(image);
    this.productService.createProduct(pr);
    return "redirect:/admin/product";

}
}