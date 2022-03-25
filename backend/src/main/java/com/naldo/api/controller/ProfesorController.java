package com.naldo.api.controller;

import com.naldo.api.model.Profesor;
import com.naldo.api.service.ProfesorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins="http://localhost:4200/")
public class ProfesorController {

    @Autowired
    private ProfesorService profesorService;

    @GetMapping("api/profesores")
    public List<Profesor> getAll(){
        return profesorService.getAll();
    }

    @GetMapping("api/profesores/{id}")
    public ResponseEntity<?> getProfesor(@PathVariable Integer id){
        Profesor profesor = null;
        Map<String,Object> response = new HashMap<>();
        try{
            profesor=profesorService.getProfesor(id);
        }catch (DataAccessException e){
            response.put("mensaje","Error al realizar la consulta a la base de datos");
            response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
        }
        if (profesor==null){
            response.put("mensaje","El profesor con ID :".concat(id.toString().concat(" no existe en la base de datos")));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Profesor>(profesor, HttpStatus.OK) ;
    }

    @PostMapping("api/profesores")
    public ResponseEntity<?> saveProfesor(@Valid @RequestBody Profesor profesor , BindingResult bindingResult){

        Profesor profesorNuevo=null;
        Map<String,Object> response= new HashMap<>();
        if (bindingResult.hasErrors()){
            List<String> errors = new ArrayList<>();
            for (FieldError err: bindingResult.getFieldErrors()) {
                errors.add("El campo: '"+err.getField()+ "' "+err.getDefaultMessage());
            }
            response.put("errors",errors);
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.BAD_REQUEST);
        }
        try {
            profesorNuevo= profesorService.saveProfesor(profesor);
        }catch (DataAccessException e){
            response.put("mensaje","Error al realizar el insert en la base de datos");
            response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje","El profesor ha sido creado con éxito");
        response.put("profesor",profesorNuevo);
        return new ResponseEntity<Map>(response, HttpStatus.CREATED) ;
    }

    @PutMapping("api/profesores/{id}")
    public ResponseEntity<?> updateProfesor(@Valid @RequestBody Profesor profesor,BindingResult bindingResult,@PathVariable Integer id){

        Profesor profesorActual=profesorService.getProfesor(id);
        Profesor profesorActualizado=null;

        Map<String,Object> response= new HashMap<>();
        if (bindingResult.hasErrors()){
            List<String> errors = new ArrayList<>();
            for (FieldError err: bindingResult.getFieldErrors()) {
                errors.add("El campo: '"+err.getField()+ "' "+err.getDefaultMessage());
            }
            response.put("errors",errors);
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.BAD_REQUEST);
        }
        if (profesor==null){
            response.put("mensaje","Error: no se puede editar el profesor con ID :".concat(id.toString().concat("pues, no existe en la base de datos")));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
        }
        try{
            profesorActual.setNombre(profesor.getNombre());
            profesorActual.setEmail(profesor.getEmail());
            profesorActual.setExperiencia(profesor.getExperiencia());
            profesorActualizado = profesorService.saveProfesor(profesorActual);

        }catch (DataAccessException e){
            response.put("mensaje","Error al realizar el update en la base de datos");
            response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje","El cliente ha sido actualizado con éxito");
        response.put("profesor",profesorActualizado);
        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED) ;
    }

    @DeleteMapping("api/profesores/{id}")
    public ResponseEntity<?> deleteProfesor(@PathVariable Integer id){

        Map<String,Object> response = new HashMap<>();

        try{
            profesorService.deleteProfesor(id);
        }catch (DataAccessException e){
            response.put("mensaje","Error al eliminar el cliente de la base de datos");
            response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje","El cliente ha sido eliminado con éxito");
        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
    }
}
