package com.programacionNCapas.SReynaProgramacionNCapas.DAOJPA;

import com.programacionNCapas.SReynaProgramacionNCapas.JPA.UsuarioJPA;
import com.programacionNCapas.SReynaProgramacionNCapas.ML.Result;
import com.programacionNCapas.SReynaProgramacionNCapas.ML.UsuarioML;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioDAOJPAImplementation implements IUsuarioDAOJPA {
    
    @Autowired
    private EntityManager entityManager;
    
    @Override
    public Result GetAll() {
        Result result = new Result();
        
        try {
            TypedQuery<UsuarioJPA> queryUsuario
                    = entityManager.createQuery("FROM usuarios", UsuarioJPA.class);
            List<UsuarioJPA> usuarios = queryUsuario.getResultList();
            
            result.objects = new ArrayList<>();
            
            for(UsuarioJPA usuario : usuarios){
                result.objects.add(new UsuarioML(usuario));
            }
            result.correct = true;
        } catch (Exception ex) {
            result.ex = ex;
            result.errMessage = ex.getLocalizedMessage();
            result.correct = false;
        }
        
        return result;
    }
    
}
