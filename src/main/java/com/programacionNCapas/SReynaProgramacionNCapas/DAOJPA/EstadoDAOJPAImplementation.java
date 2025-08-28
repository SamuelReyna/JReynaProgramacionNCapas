package com.programacionNCapas.SReynaProgramacionNCapas.DAOJPA;

import com.programacionNCapas.SReynaProgramacionNCapas.JPA.EstadoJPA;
import com.programacionNCapas.SReynaProgramacionNCapas.ML.EstadoML;
import com.programacionNCapas.SReynaProgramacionNCapas.ML.Result;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EstadoDAOJPAImplementation implements IEstadoDAOJPA {

    @Autowired
    EntityManager entityManager;

    @Override
    public Result GetAll() {
        Result result = new Result();

        try {
            TypedQuery<EstadoJPA> queryEstado
                    = entityManager.createQuery("FROM estado", EstadoJPA.class);
            List<EstadoJPA> estados = queryEstado.getResultList();
            result.objects = new ArrayList<>();
            for (EstadoJPA estado : estados) {
                result.objects.add(new EstadoML(estado));
            }
            result.correct = true;
        } catch (Exception ex) {
            result.ex = ex;
            result.errMessage = ex.getLocalizedMessage();
            result.correct = false;
        }
        return result;
    }

    @Override
    public Result GetByIdPais(int idPais) {
        Result result = new Result();

        try {

        } catch (Exception ex) {
            result.ex = ex;
            result.errMessage = ex.getLocalizedMessage();
            result.correct = false;
        }
        return result;
    }

}
