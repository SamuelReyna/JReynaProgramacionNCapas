package com.programacionNCapas.SReynaProgramacionNCapas.DAOJPA;

import com.programacionNCapas.SReynaProgramacionNCapas.JPA.PaisJPA;
import com.programacionNCapas.SReynaProgramacionNCapas.ML.PaisML;
import com.programacionNCapas.SReynaProgramacionNCapas.ML.Result;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PaisDAOJPAImplementation implements IPaisDAOJPA {

    @Autowired
    EntityManager entityManager;

    @Override
    public Result GetAll() {
        Result result = new Result();
        try {
            TypedQuery<PaisJPA> paisQuery
                    = entityManager.createQuery("FROM pais", PaisJPA.class);
            List<PaisJPA> paises = paisQuery.getResultList();
            result.objects = new ArrayList<>();
            for (PaisJPA pais : paises) {
                result.objects.add(new PaisML(pais));
            }
            result.correct = true;
        } catch (Exception ex) {
            result.correct = false;
            result.errMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        return result;

    }

}
