package com.example.maawa.service;

import com.example.maawa.exception.apiException;
import com.example.maawa.model.MyUser;
import com.example.maawa.model.Picture;

import com.example.maawa.model.Product;
import com.example.maawa.repository.pictureRepository;
import com.example.maawa.repository.productRepository;
import com.example.maawa.repository.serviceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class pictureService {
    private final pictureRepository pictureRepository;
    private final productRepository productRepository;
    private final serviceRepository serviceRepository;


    public List<Picture> getPicture() {
        return pictureRepository.findAll();
    }


    public void addPicture(Picture picture, MyUser user){
        Product product=productRepository.findProductById(picture.getProductId());
        if (product==null) {
            throw new apiException("Wrong product ID!");
        }
        if (product.getStoreId()!= user.getId()) {
            throw new apiException("Sorry , You do not have the authority to add picture to this product!");
        }
            pictureRepository.save(picture);
        }


    public void deletePicture(Integer id , MyUser user) {
        Picture picture=pictureRepository.findPictureById(id);
        if(picture==null){
            throw new apiException("Wrong picture ID!");
        }
        if(productRepository.findProductById(picture.getProductId()).getStoreId()!=user.getId()){
            throw new apiException("Sorry , You do not have the authority to delete picture from this product!");
        }
        pictureRepository.delete(picture);
    }

    public void updatePicture(Integer id, Picture picture , MyUser user) {
        Picture picture1=pictureRepository.findPictureById(id);
        if(picture1==null){
            throw new apiException("Wrong picture ID!");
        }
        if(productRepository.findProductById(picture1.getProductId()).getStoreId()!=user.getId()){
            throw new apiException("Sorry , You do not have the authority to update picture in this product!");
        }
        picture1.setPictureUlr(picture.getPictureUlr());
        pictureRepository.save(picture1);
    }

    public List<Picture> getPicturesByProduct(Integer productId){
        if(productRepository.findProductById(productId)==null){
            throw new apiException("Wrong productId");
        }
        return pictureRepository.findAllByProductId(productId);
    }


}
