package com.example.noCountry.Controllers;

import com.example.noCountry.DTO.EmployerDTO;
import com.example.noCountry.Services.EmployerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employer")
public class EmployerController {
    
    @Autowired
    private EmployerService employerService;
    // ********** GET ENDPOINTS ********** //
    @GetMapping
    public ResponseEntity<?> viewAllEmployers(){
        try {
            return new ResponseEntity(employerService.findAll(), HttpStatus.OK);
        } catch (Exception e){
            throw e;
        }
    }
    
    // ********** POST ENDPOINTS ********** //   
    
    /* 
        ***
        BODY EXAMPLE REQUEST:
        ****{
                "email" : "example@123.com",
                "password" : "password1234",
                "firstname" : "mr.Example",
                "lastname" : "Example",
                "contactNum" : "123456798",
                "role" : "EMPLOYER",
                "location" : "MENDOZA",
                "country" : "Argentina",
                "enterpriseName" : "EXAMPLE ENTERPRISE",
                "fiscalCondition" : "RESPONSABLEINSCRIPTO",
                "cuit" : 23000000001,
                "taxResidence" : "example 123 Street",
                "industryType" : "TEXTILES",
                "isVerified" : "false"
        ****}
    */
    @PostMapping
    public ResponseEntity<?> createNewEmployer(@RequestBody EmployerDTO employerDTO){
        try {
            if (employerDTO == null){
                return ResponseEntity.noContent().build();
            }
            employerService.save(employerDTO);
            return new ResponseEntity("Empleador creado correctamente", HttpStatus.OK);
        } 
        catch(HttpMessageNotReadableException e) {
            return new ResponseEntity("Los datos ingresados no son validos", HttpStatus.BAD_REQUEST);
        }
        catch(Exception e){
            return new ResponseEntity("Ocurrio un error inesperado, contacte con el administrador", HttpStatus.BAD_REQUEST); 
        }
    }
    
    
}
