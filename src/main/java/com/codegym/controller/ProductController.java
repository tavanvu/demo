package com.codegym.controller;

import com.codegym.model.Product;
import com.codegym.model.ProductForm;
import com.codegym.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private IProductService productService;

    @Value("${file-upload}")
    private String fileUpload;

    @GetMapping
    public ModelAndView showAll(@RequestParam(name = "q", required = false) String name) {
        ModelAndView modelAndView = new ModelAndView("/product/list");
        List<Product> products;
        if (name == null) {
            products = productService.findAll();
        } else {
            products = productService.findByName(name);
        }
        modelAndView.addObject("products", products);
        return modelAndView;
    }

    @GetMapping("/search")
    public ModelAndView findByName(@RequestParam(name = "q") Optional<String> name) {
        if (name.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/product/list");
            List<Product> products = productService.findByName(name.get());
            modelAndView.addObject("products", products);
            return modelAndView;
        }
        return new ModelAndView("/error-404");
    }

    @GetMapping("/create")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/product/create");
        modelAndView.addObject("product", new ProductForm());
        return modelAndView;
    }

    @PostMapping("/save")
    public String createProduct(@ModelAttribute ProductForm productForm) {
        MultipartFile multipartFile = productForm.getImage();
        String fileName = multipartFile.getOriginalFilename();
        try {
            FileCopyUtils.copy(productForm.getImage().getBytes(), new File(fileUpload + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Product product = new Product();
        product.setId(productForm.getId());
        product.setName(productForm.getName());
        product.setPrice(productForm.getPrice());
        product.setDescription(productForm.getDescription());
        product.setImage(fileName);
        productService.save(product);
        return "redirect:/product";
    }
}
