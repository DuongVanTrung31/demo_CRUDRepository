package cg.service;

import cg.model.Category;

import java.util.List;

public interface ICategoryService {
    List<Category> findAllCategory();
    Category findCategoryById(Long id);
}
