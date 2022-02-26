package cg.controller;


import cg.model.Product;
import cg.service.ICategoryService;
import cg.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/product")
public class HomeController {

    @Autowired
    IProductService productService;

    @Autowired
    ICategoryService categoryService;

    @GetMapping
    public ModelAndView showList(){
        ModelAndView modelAndView = new ModelAndView("index");
        List<Product> products = productService.findAll();
        modelAndView.addObject("products",products);
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteProduct(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("index");
        productService.deleteProductById(id);
        List<Product> products = productService.findAll();
        modelAndView.addObject("products", products);
        return modelAndView;
    }



    @GetMapping("/view/{id}")
    public ModelAndView showDetail(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("detail");
        Product product = productService.findProductById(id);
        if (product != null) {
            modelAndView.addObject("product", product);
        } else {
            modelAndView.addObject("message", "Id invalid!");
        }
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView createProduct(Model model) {
        ModelAndView modelAndView = new ModelAndView("create");
        model.addAttribute("product", new Product());
        modelAndView.addObject("categories",categoryService.findAllCategory());
        return modelAndView;
    }

    @PostMapping
    public ModelAndView create(@ModelAttribute Product product) {
        ModelAndView modelAndView = new ModelAndView("create");
        Product productCreate = productService.saveProduct(product);
        modelAndView.addObject("categories",categoryService.findAllCategory());
        if (productCreate != null) {
            modelAndView.addObject("message", "Create successfully!");
        }
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editProduct(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("edit");
        Product product = productService.findProductById(id);
        modelAndView.addObject("categories",categoryService.findAllCategory());
        if (product != null) {
            modelAndView.addObject("product", product);
        } else {
            modelAndView.addObject("message", "Id invalid!");
        }
        return modelAndView;
    }

    @PostMapping("/{id}")
    public ModelAndView edit(@ModelAttribute Product product, @PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("edit");
        product.setId(id);
        Product productEdit = productService.saveProduct(product);
        modelAndView.addObject("categories",categoryService.findAllCategory());
        if (productEdit != null) {
            modelAndView.addObject("message", "Update successfully!");
        }
        return modelAndView;
    }

    @PostMapping("/search")
    public ModelAndView search(String name){
        ModelAndView modelAndView = new ModelAndView("index");
        List<Product> products = productService.findByName(name);
        modelAndView.addObject("products",products);
        return modelAndView;
    }
}
