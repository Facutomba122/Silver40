
package com.example.noCountry.Services;

import com.example.noCountry.DTO.EmployerDTO;
import com.example.noCountry.Entity.Employer;
import com.example.noCountry.Repository.EmployerRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployerService {

    @Autowired
    private EmployerRepository employerRepo;
    
    public void save(EmployerDTO employerDTO) throws Exception {
        if (validateInput(employerDTO)){            
            Employer newEmployer = new Employer(
                employerDTO.getEnterpriseName(),
                employerDTO.getFiscalCondition(),
                employerDTO.getCuit(),
                employerDTO.getTaxResidence(),
                employerDTO.getIndustryType(),
                employerDTO.getIsVerified(),
                new ArrayList<>(),
                employerDTO.getEmail(),
                employerDTO.getPassword(),
                employerDTO.getFirstname(),
                employerDTO.getLastname(),
                employerDTO.getContactNum(),
                employerDTO.getRole(),
                employerDTO.getLocation(),
                employerDTO.getCountry()
            );
            employerRepo.save(newEmployer);
        } else {
            throw new Exception("El usuario empleador no pudo crearse correctamente");
        }
    }
    
    public List<Employer> findAll(){
        List<Employer> responseEmployerList = employerRepo.findAll();
        return responseEmployerList;
    }
    
    private boolean validateInput(EmployerDTO employerDTO){
        if (employerDTO.getIsVerified() == null){
            return false;
        }
        if (employerDTO.getContactNum() == null){
            return false;
        }
        if (employerDTO.getCountry() == null){
            return false;
        }
        if (employerDTO.getCuit() == null){
            return false;
        }
        if (employerDTO.getEmail() == null || employerRepo.findByUsername(employerDTO.getEmail()).isPresent()){
            return false;
        }
        if (employerDTO.getEnterpriseName() == null){
            return false;
        }
        if (employerDTO.getFirstname() == null){
            return false;
        }
        if (employerDTO.getFiscalCondition() == null){
            return false;
        }
        if (employerDTO.getIndustryType() == null){
            return false;
        }
        if (employerDTO.getLastname() == null){
            return false;
        }
        if (employerDTO.getLocation() == null){
            return false;
        }
        if (employerDTO.getPassword() == null){
            return false;
        }
        if (employerDTO.getRole() == null){
            return false;
        }
        return true; 
    }
                    
}
