package com.example.maawa.service;

import com.example.maawa.exception.apiException;
import com.example.maawa.model.Category;
import com.example.maawa.model.MyUser;
import com.example.maawa.repository.categoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class categoryService {

    private final categoryRepository categoryRepository;


    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    public void addCategory(Category category, MyUser user) {
        category.setStoreId(user.getId());
        categoryRepository.save(category);
    }

    public void deleteCategory(Integer id , MyUser user) {
        Category category=categoryRepository.findCategoryById(id);
        if(category==null){
            throw new apiException("Wrong category Id");
        }
        if (category.getStoreId()!=user.getId()){
            throw new apiException("Sorry , You do not have the authority to delete the category");
        }
        categoryRepository.delete(category);
    }

    public void updateCategory(Integer id, Category category , MyUser user) {
       Category category1=categoryRepository.findCategoryById(id);
        if(category1==null){
            throw new apiException("Wrong category Id");
        }
        if (category1.getStoreId()!=user.getId()){
            throw new apiException("Sorry , You do not have the authority to update the category");
        }

        category1.setName(category.getName());
        categoryRepository.save(category1);
    }

    public List<Category> getMyCategories(MyUser user){
        return categoryRepository.findAllByStoreId(user.getId());
    }


}
