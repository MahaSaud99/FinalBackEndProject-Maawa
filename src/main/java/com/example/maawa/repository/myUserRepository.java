package com.example.maawa.repository;

import com.example.maawa.model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface myUserRepository extends JpaRepository<MyUser,Integer> {
    MyUser findMyUserById(Integer id);

    MyUser findUserByUsername(String username);





//    User
//    User findUsersByMyUserId(Integer id);

//    Store
//    Store findStoreByMyUserId(Integer id);

//    Clinic
//    Clinic findStoreByMyUserId(Integer id);


}
