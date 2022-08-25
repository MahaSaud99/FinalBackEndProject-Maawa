package com.example.maawa.service;

import com.example.maawa.dto.GeocodeResult;
import com.example.maawa.exception.apiException;
import com.example.maawa.model.MyUser;
import com.example.maawa.repository.myUserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

@Service
@RequiredArgsConstructor
public class myUserService {
    private final myUserRepository myUserRepository;

    public List<MyUser> getUsers() {
        return myUserRepository.findAll();
    }

//    public MyUser getUserInfo(MyUser user) {
//        return myUserRepository.findMyUserById(user.getId());
//    }


    public void addUser(MyUser user){
        String hashedPassword=new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(hashedPassword);
//        GeocodeResult geocodeResult=getGeocode(user.getAddress());
//        if(!geocodeResult.getResults().isEmpty()) {
//            String address = geocodeResult.getResults().get(0).getFormattedAddress();
//            user.setAddress(address);
//        }
        myUserRepository.save(user);
    }

    public void deleteUser(Integer id) {
        MyUser user=myUserRepository.findMyUserById(id);
        if(user==null){
            throw new apiException("Wrong user ID!");
        }
        myUserRepository.delete(user);
    }

    public void updateUser(MyUser user , MyUser Auth) {
        MyUser myUser=myUserRepository.findUserByUsername(user.getUsername());

        if(myUser==null){
            throw new apiException("Wrong user ID!");
        }
        if (myUser.getId()!=Auth.getId()){
            throw new apiException("Sorry , You do not have the authority to update this user!");
        }

        myUser.setUsername(user.getUsername());
        myUser.setPassword(user.getPassword());
        myUser.setName(user.getName());
        myUser.setEmail(user.getEmail());
        myUser.setPhoneNumber(user.getPhoneNumber());
        myUser.setAddress(user.getAddress());

        if (user.getAge()!=null){
            myUser.setAge(user.getAge());
        }
        if (user.getDeliveryFee()!=null){
            myUser.setDeliveryFee(user.getDeliveryFee());
        }
        if (user.getPicture()!=null){
            myUser.setPicture(user.getPicture());
        }
        myUserRepository.save(myUser);
    }

     public GeocodeResult getGeocode(String address) throws IOException {
        OkHttpClient client = new OkHttpClient();
        String encodedAddress = URLEncoder.encode(address, "UTF-8");
        Request request = new Request.Builder()
                .url("https://google-maps-geocoding.p.rapidapi.com/geocode/json?language=en&address=" + encodedAddress)
                .get()
                .addHeader("x-rapidapi-host", "google-maps-geocoding.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "e236aa6413mshdc22d44aae7b45cp1405c2jsn32bdf01b636f")
                .build();
        ResponseBody responseBody = client.newCall(request).execute().body();
        ObjectMapper objectMapper = new ObjectMapper();
       return objectMapper.readValue(responseBody.string(), GeocodeResult.class);
    }

    public void makeApprovment(Boolean isApproved,Integer id){
        MyUser myUser=myUserRepository.findMyUserById(id);
        if(myUser==null){
            throw new apiException("Wrong user ID!");
        }
        myUser.setIsApproved(isApproved);
        myUserRepository.save(myUser);
    }

}




