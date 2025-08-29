package com.programacionNCapas.SReynaProgramacionNCapas.DAOJPA;

import com.programacionNCapas.SReynaProgramacionNCapas.JPA.ColoniaJPA;
import com.programacionNCapas.SReynaProgramacionNCapas.JPA.DireccionJPA;
import com.programacionNCapas.SReynaProgramacionNCapas.JPA.UsuarioJPA;
import com.programacionNCapas.SReynaProgramacionNCapas.ML.DireccionML;
import com.programacionNCapas.SReynaProgramacionNCapas.ML.Result;
import com.programacionNCapas.SReynaProgramacionNCapas.ML.UsuarioML;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DireccionDAOJPAImplementation implements IDireccionDAOJPA {

    @Autowired
    EntityManager entityManager;

    @Transactional
    @Override
    public Result Delete(int IdDireccion) {
        Result result = new Result();
        try {
            DireccionJPA direccion = entityManager.find(DireccionJPA.class, IdDireccion);
            entityManager.remove(direccion);
            result.correct = true;

        } catch (Exception ex) {
            result.ex = ex;
            result.errMessage = ex.getLocalizedMessage();
            result.correct = false;
        }
        return result;
    }

    @Transactional
    @Override
    public Result Add(UsuarioML usuario) {

        Result result = new Result();
        try {
            DireccionJPA direccionJPA = new DireccionJPA(usuario);
            direccionJPA.Usuario = new UsuarioJPA();
            direccionJPA.Usuario.setIdUser(usuario.getIdUser());
            direccionJPA.Colonia = new ColoniaJPA();
            direccionJPA.Colonia.setIdColonia(usuario.Direccion.Colonia.getIdColonia());
            entityManager.persist(direccionJPA);
            result.correct = true;
        } catch (Exception ex) {
            result.correct = false;
            result.ex = ex;
            result.errMessage = ex.getLocalizedMessage();
        }
        return result;
    }

    @Override
    public Result Update(DireccionML direccion, int IdDireccion) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Result GetOne(int IdDireccion) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
