package cg.formatter;

import cg.model.Category;
import cg.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;

public class CategoryFormatter implements Formatter<Category> {

    private final ICategoryService categoryService;


    public CategoryFormatter(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @Override
    public Category parse(String text, Locale locale) throws ParseException {
        return categoryService.findCategoryById(Long.parseLong(text));
    }

    @Override
    public String print(Category object, Locale locale) {
        return null;
    }
}
