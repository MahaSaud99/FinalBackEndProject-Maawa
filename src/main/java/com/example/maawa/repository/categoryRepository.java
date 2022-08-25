package com.example.maawa.repository;

import com.example.maawa.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface categoryRepository extends JpaRepository<Category,Integer> {
    Category findCategoryById(Integer id);
    List<Category> findAllByStoreId(Integer id);

}
