package com.naldo.api.service;

import com.naldo.api.model.Profesor;
import com.naldo.api.repository.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProfesorServiceImp implements ProfesorService{

    @Autowired
    ProfesorRepository profesorRepository;

    @Override
    public Profesor saveProfesor(Profesor profesor) {
         return profesorRepository.save(profesor) ;
    }

    @Override
    public List<Profesor> getAll() {
        return (List<Profesor>) profesorRepository.findAll();
    }

    @Override
    public void deleteProfesor(Integer id) {
        profesorRepository.deleteById(id);
    }

    @Override
    public void updateProfesor(Profesor profesor) {
        profesorRepository.save(profesor);
    }

    @Override
    public Profesor getProfesor(Integer id) {
        return profesorRepository.findById(id).orElse(null);
    }
}
