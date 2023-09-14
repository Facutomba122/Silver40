package com.example.noCountry.Controllers;

import com.example.noCountry.DTO.PublicationDTO;
import com.example.noCountry.Entity.Publication;
import com.example.noCountry.Services.PublicationService;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/publication")
public class PublicationController {
    
    @Autowired
    private PublicationService publicationService;
    
    // ********** GET ENDPOINTS ********** // 
    @GetMapping()
    public ResponseEntity<?> viewAll(){
        ArrayList<Publication> allPublications = publicationService.findAll();
        return ResponseEntity.ok(allPublications);
    } 
    
    @GetMapping("/{id}")
    public ResponseEntity<?> viewById(@PathVariable("id") UUID id){
        Optional<Publication> idPublication = publicationService.findById(id);
        if (idPublication.isPresent()){
            return ResponseEntity.ok(idPublication.get());
        } else {
            return new ResponseEntity("El Id no pertenece a una publicacion", HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/{keywords}")
    public ResponseEntity<?> viewByKeywords(@PathVariable("keywords") String keywords){
        Optional<ArrayList<Publication>> responseList = publicationService.findByKeywords(keywords);
        if (responseList.isPresent()){
            ArrayList<Publication> auxList = responseList.get();
            if (auxList.isEmpty()){
                return new ResponseEntity("Lo sentimos, no encontramos publicaciones que coincidan con tu busqueda", HttpStatus.NOT_FOUND);
            }
            return ResponseEntity.ok(auxList);
        } else {
            return new ResponseEntity("Lo sentimos, no encontramos publicaciones que coincidan con tu busqueda", HttpStatus.NOT_FOUND);
        }
    }
    
    // ********** POST ENDPOINTS ********** //
    
    /*
        ----EX BODY VALIDO----
            {
                "name": "ejemplo",
                "body": "Se busca trabajdor con conocimientos en cortar fiambre y atencion al publico."
                "owner" : { 
                            owner no implementado aun, ACTUALIZAR DOCUMENTACION
                          }
                "keywords" : "carniceria#trabajo#atencionalpublico#",
                "location" : "MENDOZA"
            }
    
    
        ---- BODY ESPERADO ----
            {
            "name": "ejemplo123",
            "body": "lorem*15",
            "owner": {
                        DATOS DEL EMPLEADOR
                     },
            "keywords": "ejemplo#trabajo#desarrollador#It#",
            "location": "posibles locaciones (solo puede elegirse una):
                    MENDOZA,
                    CORDOBA,
                    SANLUIS,
                    SANJUAN,
                    BUENOSAIRES,
                    TUCUMAN,
                    SANTAFE,
                    CHUBUT,
                    RIONEGRO,
                    SANTACRUZ,
                    LAPAMPA,
                    MISIONES,
                    CATAMARCA,
                    ENTRERIOS,
                    JUJUY,
                    CHACO,
                    CORRIENTES,
                    FORMOSA,
                    LARIOJA,
                    SALTA,
                    NEUQUEN,
                    SANTIAGODELESTERO,
                    TIERRADELFUEGO  
                    "
        }
    */
    @PostMapping
    public ResponseEntity<?> insertNewPublication(@RequestBody PublicationDTO newPublication){
        try {
            
            if (newPublication == null || newPublication.getBody() == null || newPublication.getKeywords() == null 
                || newPublication.getLocation() == null || newPublication.getName() == null){ //|| newPublication.getOwner() == null){
                return new ResponseEntity("Todos los campos son obligatorios", HttpStatus.BAD_REQUEST);
            }
            publicationService.insertNewPublication(newPublication);
            return new ResponseEntity("Publicacion creada correctamente", HttpStatus.OK);
            
        } catch (Exception e){
            return new ResponseEntity("La publicacion no pudo crearse correctamente, contacte con soporte", HttpStatus.NOT_ACCEPTABLE);
        }
    }
    
    
    // ********** PUT ENDPOINTS ********** //
    
    /*
        ----EX BODY VALIDO----
            {
                "name": "ejemplo",
                "body": "Se busca trabajdor con conocimientos en cortar fiambre y atencion al publico."
                "owner" : { 
                            owner no implementado aun, ACTUALIZAR DOCUMENTACION
                          }
                "keywords" : "carniceria#trabajo#atencionalpublico#",
                "location" : "MENDOZA"
            }
    
    
        ---- BODY ESPERADO ----
            {
            "name": "ejemplo123",
            "body": "lorem*15",
            "owner": {
                        DATOS DEL EMPLEADOR
                     },
            "keywords": "ejemplo#trabajo#desarrollador#It#",
            "location": "posibles locaciones (solo puede elegirse una):
                    MENDOZA,
                    CORDOBA,
                    SANLUIS,
                    SANJUAN,
                    BUENOSAIRES,
                    TUCUMAN,
                    SANTAFE,
                    CHUBUT,
                    RIONEGRO,
                    SANTACRUZ,
                    LAPAMPA,
                    MISIONES,
                    CATAMARCA,
                    ENTRERIOS,
                    JUJUY,
                    CHACO,
                    CORRIENTES,
                    FORMOSA,
                    LARIOJA,
                    SALTA,
                    NEUQUEN,
                    SANTIAGODELESTERO,
                    TIERRADELFUEGO  
                    "
        }
    */
    @PutMapping("/{id}")
    public ResponseEntity<?> updatePublication(@RequestBody PublicationDTO updatePublication, @PathVariable("id") UUID id){
        try {
            Optional<Publication> responsePublication = publicationService.findById(id);
            if (responsePublication.isPresent()){
                Publication oldPublication = responsePublication.get();
                publicationService.updatePublication(oldPublication, updatePublication);
            }
            return new ResponseEntity("El id proporcionado no corresponde a una publicacion activa", HttpStatus.BAD_REQUEST);
        } catch (Exception e){
            return new ResponseEntity("La publicacion no pudo actualizarse correctamente, comuniquese con soporte", HttpStatus.EXPECTATION_FAILED);
        }
    }
    
    
    // ********** DELETE ENDPOINTS ********** //
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePublication(@PathVariable("id") UUID id){
        try {
            Optional<Publication> responsePublication = publicationService.findById(id);
            if (responsePublication.isPresent()){
                publicationService.deleteById(id);
                return ResponseEntity.ok("Publicacion borrada correctamente");
            }
            return new ResponseEntity("No se encontro la publicacion con ID " + id.toString(), HttpStatus.BAD_REQUEST);
        } catch (Exception e){
            return new ResponseEntity("La publicacion no pudo ser borrada correctamente, comuniquise a soporte", HttpStatus.EXPECTATION_FAILED);
        }
    }
}
