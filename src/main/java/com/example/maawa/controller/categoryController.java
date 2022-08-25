package com.example.maawa.controller;

import com.example.maawa.dto.apiResponse;
import com.example.maawa.model.Category;
import com.example.maawa.model.MyUser;
import com.example.maawa.service.categoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class categoryController {
    private final categoryService categoryService;


    @GetMapping("/get")
    public ResponseEntity<List> getCategories(){
       List<Category> categories= categoryService.getCategories();
        return ResponseEntity.status(200).body(categories);
    }

    @PostMapping("/add")
    public ResponseEntity<apiResponse> addCategory(@RequestBody @Valid Category category , @AuthenticationPrincipal MyUser user){
        categoryService.addCategory(category , user);
        return ResponseEntity.status(200).body(new apiResponse("Category added successfully!"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<apiResponse> deleteCategory(@PathVariable Integer id , @AuthenticationPrincipal MyUser user){
        categoryService.deleteCategory(id,user);
         return ResponseEntity.status(200).body(new apiResponse("Category deleted successfully!"));
        }

    @PutMapping("/update/{id}")
    public ResponseEntity<apiResponse> updateCategory(@PathVariable Integer id , @RequestBody @Valid Category category , @AuthenticationPrincipal MyUser user) {
        categoryService.updateCategory(id, category , user);
            return ResponseEntity.status(200).body(new apiResponse("Category updated successfully!"));
        }

    @GetMapping("/myCategories")
    public ResponseEntity<List> getMyCategories(@AuthenticationPrincipal MyUser user){
        List<Category> categories= categoryService.getMyCategories(user);
        return ResponseEntity.status(200).body(categories);
    }
}
