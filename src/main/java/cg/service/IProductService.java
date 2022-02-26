package cg.service;

import cg.model.Product;

import java.util.List;


public interface IProductService {
    List<Product> findAll();
    Product saveProduct(Product product);
    void deleteProductById(Long id);
    void delete(Product product);
    Product findProductById(Long id);
    List<Product> findByName(String name);
}
