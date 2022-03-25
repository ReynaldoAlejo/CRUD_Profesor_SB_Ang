package com.naldo.api.service;

import com.naldo.api.model.Profesor;

import java.util.List;

public interface ProfesorService {

    Profesor saveProfesor(Profesor profesor);

    List<Profesor> getAll();

    void deleteProfesor(Integer id);

    void updateProfesor(Profesor profesor);

    Profesor getProfesor(Integer id);

}
