package com.example.maawa.service;

import com.example.maawa.exception.apiException;
import com.example.maawa.model.MyUser;
import com.example.maawa.model.Product;
import com.example.maawa.repository.myUserRepository;
import com.example.maawa.repository.serviceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class serviceService {

    private final serviceRepository serviceRepository;
    private final myUserRepository myUserRepository;

    public List<com.example.maawa.model.Service> getServices() {
        return serviceRepository.findAll();
    }


    public void addService(com.example.maawa.model.Service service , MyUser user ) {
        service.setClinicId(user.getId());
        serviceRepository.save(service);
    }


    public void deleteService(Integer id,MyUser user) {
        com.example.maawa.model.Service service=serviceRepository.findServiceById(id);
        if(service==null){
            throw new apiException("Wrong service ID!");
        }
        if (service.getClinicId()!= user.getId()){
            throw new apiException("Sorry , You do not have the authority to delete this service");
        }
        serviceRepository.delete(service);
    }

    public void updateService(Integer id, com.example.maawa.model.Service service , MyUser user) {
        com.example.maawa.model.Service service1=serviceRepository.findServiceById(id);
        if(service1==null){
            throw new apiException("Wrong service id!");
        }
        if (service1.getClinicId()!= user.getId()){
            throw new apiException("Sorry , You do not have the authority to update this service");
        }
        service1.setName(service.getName());
        service1.setDescription(service.getDescription());
        service1.setPrice(service.getPrice());
        service1.setPicture(service.getPicture());

        serviceRepository.save(service1);
    }

    public List<com.example.maawa.model.Service> getMyProducts(MyUser user){
        return serviceRepository.findAllByClinicId(user.getId());
    }

    public List<com.example.maawa.model.Service> getServicesByClinic(Integer clinicId){
        if(myUserRepository.findMyUserById(clinicId)==null){
            throw new apiException("Wrong clinicId");
        }
        if (!myUserRepository.findMyUserById(clinicId).getRole().equals("clinic")){
            throw new apiException("Wrong clinicId");
        }
        return serviceRepository.findAllByClinicId(clinicId);
    }

}
