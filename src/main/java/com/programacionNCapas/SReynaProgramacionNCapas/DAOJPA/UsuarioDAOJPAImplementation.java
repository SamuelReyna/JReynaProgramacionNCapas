package com.programacionNCapas.SReynaProgramacionNCapas.DAOJPA;

import com.programacionNCapas.SReynaProgramacionNCapas.JPA.UsuarioJPA;
import com.programacionNCapas.SReynaProgramacionNCapas.ML.Result;
import com.programacionNCapas.SReynaProgramacionNCapas.ML.UsuarioML;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
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
                    = entityManager.createQuery("FROM Usuario", UsuarioJPA.class);
            List<UsuarioJPA> usuarios = queryUsuario.getResultList();

            result.objects = new ArrayList<>();

            for (UsuarioJPA usuario : usuarios) {
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

    @Override
    public Result GetOne(int IdUser) {
        Result result = new Result();
        try {
            UsuarioJPA usuarioJPA = entityManager.find(UsuarioJPA.class, IdUser);
            result.object = new UsuarioML(usuarioJPA);
            result.correct = true;

        } catch (Exception ex) {
            result.correct = false;
            result.errMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        return result;
    }

    @Transactional
    @Override
    public Result Add(UsuarioML usuario) {
        Result result = new Result();

        try {
            UsuarioJPA usuarioJPA = new UsuarioJPA(usuario);
            entityManager.persist(usuarioJPA);
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
    public Result Update(UsuarioML usuario) {
        Result result = new Result();
        try {
            UsuarioJPA usuarioJPA = new UsuarioJPA(usuario);
            UsuarioJPA usuarioBD = entityManager.find(UsuarioJPA.class, usuario.getIdUser());
            usuarioJPA.Direcciones = usuarioBD.Direcciones;
            entityManager.merge(usuarioJPA);
            result.correct = true;
        } catch (Exception ex) {
            result.correct = false;
            result.ex = ex;
            result.errMessage = ex.getLocalizedMessage();
        }

        return result;

    }

    @Transactional
    @Override
    public Result Delete(int IdUser) {
        Result result = new Result();
        try {
            UsuarioJPA usuario = entityManager.find(UsuarioJPA.class, IdUser);
            entityManager.remove(usuario);
            result.correct = true;
        } catch (Exception ex) {
            result.correct = false;
            result.errMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        return result;
    }

    @Override
    public Result LogicalDelete(int IdUser) {
        Result result = new Result();

        try {
            UsuarioJPA usuarioJPA = entityManager.find(UsuarioJPA.class, IdUser);
            usuarioJPA.setEstatus(usuarioJPA.getEstatus() == 1 ? 0 : 1);
            entityManager.merge(usuarioJPA);
            result.correct = true;
        } catch (Exception ex) {
            result.correct = false;
            result.errMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        return result;
    }

}
