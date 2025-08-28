package com.programacionNCapas.SReynaProgramacionNCapas.DAOJPA;

import com.programacionNCapas.SReynaProgramacionNCapas.JPA.ColoniaJPA;
import com.programacionNCapas.SReynaProgramacionNCapas.ML.ColoniaML;
import com.programacionNCapas.SReynaProgramacionNCapas.ML.Result;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ColoniaDAOJPAImplementation implements IColoniaDAOJPA {

    @Autowired
    EntityManager entityManager;

    @Override
    public Result GetAll() {
        Result result = new Result();

        try {
            TypedQuery<ColoniaJPA> queryColonia
                    = entityManager.createQuery("FROM colonia", ColoniaJPA.class);
            List<ColoniaJPA> colonias = queryColonia.getResultList();
            result.objects = new ArrayList<>();
            for (ColoniaJPA colonia : colonias) {
                result.objects.add(new ColoniaML(colonia));
            }

        } catch (Exception ex) {
            result.ex = ex;
            result.errMessage = ex.getLocalizedMessage();
            result.correct = false;
        }
        return result;
    }

    @Override
    public Result GetByIdMunicipio(int IdMunicipio) {
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
