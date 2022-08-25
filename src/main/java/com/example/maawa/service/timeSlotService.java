package com.example.maawa.service;

import com.example.maawa.exception.apiException;
import com.example.maawa.model.MyUser;
import com.example.maawa.model.timeSlot;
import com.example.maawa.repository.myUserRepository;
import com.example.maawa.repository.serviceRepository;
import com.example.maawa.repository.timeSlotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class timeSlotService {

    private final timeSlotRepository timeSlotRepository;
    private final myUserRepository myUserRepository;
    private final serviceRepository serviceRepository;


    public List<timeSlot> getTimeSlot() {
        return timeSlotRepository.findAll();
    }


    public void addTimeSlot(timeSlot timeSlot , MyUser user) {
        if(serviceRepository.findServiceById(timeSlot.getServiceId())==null){
            throw new apiException("Wrong serviceId!");
        }
        timeSlot.setClinicId(user.getId());
        timeSlotRepository.save(timeSlot);
    }

    public void deleteTimeSlot(Integer id , MyUser user) {
        timeSlot timeSlot=timeSlotRepository.findTimeSlotById(id);
        if(timeSlot==null){
            throw new apiException("Wrong time slot ID!");
        }
        if(timeSlot.getClinicId()!=user.getId()){
            throw new apiException("Sorry , You do not have the authority to delete this time slot");
        }
        timeSlotRepository.delete(timeSlot);
    }

    public void updateTimeSlot(Integer id, timeSlot timeSlot , MyUser user) {
        timeSlot timeSlot1=timeSlotRepository.findTimeSlotById(id);
        if(timeSlot1==null){
            throw new apiException("Wrong time slot ID!");
        }
        if(timeSlot1.getClinicId()!=user.getId()){
            throw new apiException("Sorry , You do not have the authority to update this time slot");
        }

        timeSlot1.setTime(timeSlot.getTime());
        timeSlot1.setIsAvailable(timeSlot.getIsAvailable());
        timeSlotRepository.save(timeSlot1);
    }

    public List<timeSlot> getTimeSlotByService(Integer serviceId){
        if(serviceRepository.findServiceById(serviceId)==null){
            throw new apiException("Wrong serviceId!");
        }
        return timeSlotRepository.findAllByServiceId(serviceId);
    }
}
