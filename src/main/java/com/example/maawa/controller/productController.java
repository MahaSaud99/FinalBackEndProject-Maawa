package com.example.maawa.controller;

import com.example.maawa.dto.apiResponse;
import com.example.maawa.model.Category;
import com.example.maawa.model.MyUser;
import com.example.maawa.model.Product;
import com.example.maawa.service.productService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class productController {

    private final productService productService;


    @GetMapping("/get")
    public ResponseEntity<List> getProducts(){
        List<Product> products=productService.getProducts();
        return ResponseEntity.status(200).body(products);
    }

    @PostMapping("/add")
    public ResponseEntity<apiResponse> addProduct(@RequestBody @Valid Product product, @AuthenticationPrincipal MyUser user){
        productService.addProduct(product,user);
        return ResponseEntity.status(200).body(new apiResponse("Product Added Successfully!"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<apiResponse> deleteProduct(@PathVariable Integer id , @AuthenticationPrincipal MyUser user){
        productService.deleteProduct(id,user);
        return ResponseEntity.status(200).body(new apiResponse("Product Deleted Successfully!"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<apiResponse> updateProduct(@PathVariable Integer id ,@RequestBody @Valid Product product, @AuthenticationPrincipal MyUser user){
        productService.updateProduct(id,product,user);
        return ResponseEntity.status(200).body(new apiResponse("Product Updated Successfully!"));
    }

    @GetMapping("/myProducts")
    public ResponseEntity<List> getMyProducts(@AuthenticationPrincipal MyUser user){
        List<Product> products= productService.getMyProducts(user);
        return ResponseEntity.status(200).body(products);
    }

    @GetMapping("/byStoreId")
    public ResponseEntity<List> getProductByStore(@RequestBody Integer storeId){
        List<Product> products= productService.getProductByStore(storeId);
        return ResponseEntity.status(200).body(products);
    }


}
