package com.programacionNCapas.SReynaProgramacionNCapas.DAOJPA;

import com.programacionNCapas.SReynaProgramacionNCapas.JPA.RolJPA;
import com.programacionNCapas.SReynaProgramacionNCapas.ML.Result;
import com.programacionNCapas.SReynaProgramacionNCapas.ML.RolML;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RolDAOJPAImplementation implements IRolDAOJPA {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Result GetAll() {
        Result result = new Result();
        try {
            TypedQuery<RolJPA> queryRol
                    = entityManager.createQuery("FROM rol", RolJPA.class);
            List<RolJPA> roles = queryRol.getResultList();

            result.objects = new ArrayList<>();
            for (RolJPA rol : roles) {
                result.objects.add(new RolML(rol));
            }
            result.correct = true;
        } catch (Exception ex) {
            result.ex = ex;
            result.correct = false;
            result.errMessage = ex.getLocalizedMessage();
        }
            return result;
    }

}
