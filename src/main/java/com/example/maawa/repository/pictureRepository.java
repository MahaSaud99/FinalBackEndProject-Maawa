package com.example.maawa.repository;

import com.example.maawa.model.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface pictureRepository extends JpaRepository<Picture,Integer> {
    Picture findPictureById(Integer id);
    List<Picture> findAllByProductId(Integer id);
}
