package vn.hoidanit.laptopshop.controller.admin;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import vn.hoidanit.laptopshop.domain.Order;
import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.service.OrderService;



@Controller
public class OrderController {
    private final OrderService orderService;
    private final OrderDetailService orderDetailService;
    public OrderController(OrderService orderService, OrderDetailService orderDetailService){
        this.orderService = orderService;
        this.orderDetailService = orderDetailService;
    }

    @GetMapping("/admin/order")
    public String getOrder(Model model) {
        List<Order> ords = this.orderService.fethOrderById();
        model.addAttribute("order", ords);
        return "admin/order/show";
    }
    @GetMapping("/admin/order/{id}")
    public String getOrderDetailPage(Model model, @PathVariable long id) {
        List<Order> ord = this.orderService.fethOrderById();
        model.addAttribute("order", ord);
        model.addAttribute("id", id);
        return "admin/order/detail";
    }
    // @GetMapping("/admin/product/update/{id}")
    // public String getUpdateProductPage(Model model, @PathVariable long id) {
    //     Optional<Product> currentProduct = this.productService.fetchProductById(id);
    //     model.addAttribute("newProduct", currentProduct.get());
    //     return "admin/product/update";
    // }

    // @PostMapping("/admin/order/update")
    // public String handleUpdateProduct(@ModelAttribute("newProduct") @Valid Product pr,
    //         BindingResult newProductBindingResult,
    //         @RequestParam("hoidanitFile") MultipartFile file) {

    //     // validate
    //     if (newProductBindingResult.hasErrors()) {
    //         return "admin/product/update";
    //     }

    //     Product currentProduct = this.productService.fetchProductById(pr.getId()).get();
    //     if (currentProduct != null) {
    //         // update new image
    //         if (!file.isEmpty()) {
    //             String img = this.uploadService.handleSaveUploadFile(file, "product");
    //             currentProduct.setImage(img);
    //         }

    //         currentProduct.setName(pr.getName());
    //         currentProduct.setPrice(pr.getPrice());
    //         currentProduct.setQuantity(pr.getQuantity());
    //         currentProduct.setDetailDesc(pr.getDetailDesc());
    //         currentProduct.setShortDesc(pr.getShortDesc());
    //         currentProduct.setFactory(pr.getFactory());
    //         currentProduct.setTarget(pr.getTarget());

    //         this.productService.createProduct(currentProduct);
    //     }

    //     return "redirect:/admin/product";
    // }
}
