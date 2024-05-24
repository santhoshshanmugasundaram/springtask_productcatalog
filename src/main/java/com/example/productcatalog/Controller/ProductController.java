package com.example.productcatalog.Controller;

import com.example.productcatalog.entity.Product;
import com.example.productcatalog.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/product") // Changed to lowercase
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/add")
    public String showAddProductForm() {
        return "addProduct";
    }

    @PostMapping("/add")
    public String addProduct(@RequestParam String name, @RequestParam double price, @RequestParam String category) {
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setCategory(category);
        productService.saveProduct(product);
        return "redirect:/product/"; // Ensure consistency with URL casing
    }

    @GetMapping("/list")
    public String showProductList(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "displayProducts";
    }
}
