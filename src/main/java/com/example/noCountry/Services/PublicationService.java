
package com.example.noCountry.Services;

import com.example.noCountry.DTO.PublicationDTO;
import com.example.noCountry.Entity.Publication;
import com.example.noCountry.Repository.PublicationRepository;
import jakarta.transaction.Transactional;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PublicationService {

    @Autowired
    private PublicationRepository publicationRepository;
    
    public ArrayList<Publication> findAll() {
        return (ArrayList<Publication>) publicationRepository.findAll();
    }

    public Optional<Publication> findById(UUID id) {
        return publicationRepository.findById(id);
    }

    public Optional<ArrayList<Publication>> findByKeywords(String keywords) {
        return publicationRepository.viewByKeywords(keywords);
    }

    @Transactional
    public void insertNewPublication(PublicationDTO newPublication){
        try {
            Publication auxPublication = new Publication(
                newPublication.getName(),
                newPublication.getBody(),
                newPublication.getOwner(),
                newPublication.getKeywords(),
                newPublication.getLocation(),
                newPublication.getSchedule(),
                newPublication.getVacancies(),
                newPublication.getIndustry(),
                newPublication.getContractType(),
                newPublication.getModalityWork(),
                newPublication.getSalary(),
                newPublication.getCity(),
                newPublication.getSeniority(),
                newPublication.getDescription(),
                newPublication.getChores(),
                newPublication.getRequirements(),
                newPublication.getBenefit()
            );
            
            publicationRepository.save(auxPublication);
        } catch (Exception e){
            throw e; 
        }
    }

    @Transactional
    public void updatePublication(Publication oldPublication, PublicationDTO updatePublication) {
        try {
            if (updatePublication.getBody() != null){
                oldPublication.setBody(updatePublication.getBody());
            }
            if (updatePublication.getKeywords() != null){
                oldPublication.setKeywords(updatePublication.getKeywords());
            }
            if (updatePublication.getLocation() != null){
                oldPublication.setLocation(updatePublication.getLocation());
            }
            if (updatePublication.getLocation() != null){
                oldPublication.setLocation(updatePublication.getLocation());
            }
            publicationRepository.save(oldPublication);
        } catch (Exception e){
            throw e;
        }
    }

    @Transactional
    public void deleteById(UUID id) {
        try {
            publicationRepository.deleteById(id);
        } catch (Exception e) {
            throw e;
        }
    }
    
    public boolean validateFields(PublicationDTO validatePublication) throws IllegalArgumentException, Exception{
        
        Class<?> classPublication = validatePublication.getClass();
        Field[] publicationFields =  classPublication.getDeclaredFields();
        for (Field auxField : publicationFields){
            auxField.setAccessible(true);
            try {
                if (auxField.get(validatePublication) == null){
                    return false;
                }
            } catch (IllegalAccessException | IllegalArgumentException e){
                throw e;
            }
        }
        return true;
    }
}
