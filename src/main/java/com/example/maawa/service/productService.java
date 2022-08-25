package com.example.maawa.service;

import com.example.maawa.exception.apiException;
import com.example.maawa.model.*;
import com.example.maawa.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class productService {

    private final productRepository productRepository;
    private final categoryRepository categoryRepository;
    private final myUserRepository myUserRepository;


    public List<Product> getProducts() {
        return productRepository.findAll();
    }


    public void addProduct(Product product , MyUser user) {
        Category category=categoryRepository.findCategoryById(product.getCategoryId());
        if (category==null){
            throw new apiException("Wrong category id");
        }
        product.setStoreId(user.getId());
        productRepository.save(product);
    }

    public void deleteProduct(Integer id , MyUser user) {
        Product product=productRepository.findProductById(id);
        if(product==null){
            throw new apiException("Wrong product ID!");
        }
        if (product.getStoreId()!=user.getId()){
            throw new apiException("Sorry , You do not have the authority to delete the product");
        }
        productRepository.delete(product);
    }

    public void updateProduct(Integer id, Product product, MyUser user) {
        Product product1=productRepository.findProductById(id);

        if(product1==null){
            throw new apiException("Wrong product ID!");
        }
        if (product1.getStoreId()!=user.getId()){
            throw new apiException("Sorry , You do not have the authority to update the product");
        }

        product1.setName(product.getName());
        product1.setDescription(product.getDescription());
        product1.setCategoryId(product.getCategoryId());
        product1.setPrice(product.getPrice());
        product1.setQuantity(product.getQuantity());

        productRepository.save(product1);
    }

    public List<Product> getMyProducts(MyUser user){
        return productRepository.findAllByStoreId(user.getId());
    }

    public List<Product> getProductByStore(Integer storeId){
        if(myUserRepository.findMyUserById(storeId)==null){
            throw new apiException("Wrong storeId");
        }
        return productRepository.findAllByStoreId(storeId);
    }



}
