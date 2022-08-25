package com.example.maawa.repository;

import com.example.maawa.model.myOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface orderRepository extends JpaRepository<myOrder,Integer> {
    myOrder findOrderById(Integer id);
    List<myOrder> findAllByStoreId(Integer id);
    List<myOrder> findAllByUserId(Integer id);


}
