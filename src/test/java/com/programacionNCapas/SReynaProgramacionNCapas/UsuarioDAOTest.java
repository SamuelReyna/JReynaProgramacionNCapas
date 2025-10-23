package com.programacionNCapas.SReynaProgramacionNCapas;

import com.programacionNCapas.SReynaProgramacionNCapas.DAO.UsuarioDAOImplementation;
import com.programacionNCapas.SReynaProgramacionNCapas.ML.ColoniaML;
import com.programacionNCapas.SReynaProgramacionNCapas.ML.DireccionML;
import com.programacionNCapas.SReynaProgramacionNCapas.ML.Result;
import com.programacionNCapas.SReynaProgramacionNCapas.ML.RolML;
import com.programacionNCapas.SReynaProgramacionNCapas.ML.UsuarioML;
import java.time.LocalDate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UsuarioDAOTest {

    @Autowired
    private UsuarioDAOImplementation usuarioDAOImplementation;

    @Test
    public void GetAll() {
        UsuarioML user = new UsuarioML();
        user.setNombre("Sa");
        Result result = usuarioDAOImplementation.GetAll(user);

        Assertions.assertTrue(result.correct);
        Assertions.assertNotNull(result.objects);
        Assertions.assertInstanceOf(Result.class, result);
    }

    @Test
    public void GetDetail() {
        Assertions.assertInstanceOf(UsuarioML.class, usuarioDAOImplementation.GetDetail(350).object);
        Assertions.assertNotNull(usuarioDAOImplementation.GetDetail(350).object);
    }

    @Test
    public void Add() {
        UsuarioML user = new UsuarioML();
        user.setNombre("Samuel");
        user.setApellidoPaterno("Reyna");
        user.setApellidoMaterno("González");
        user.setCelular("6183100616");
        user.setTelefono("6183100616");
        user.setCurp("SamuelRG03062005");
        user.setUsername("SamuelRG03062005");
        user.setEmail("SamuelRG03062005@example.com");
        user.setSexo("M");
        user.setEstatus(1);
        user.setFechaNacimiento(LocalDate.of(2003, 8, 10));
        user.Rol = new RolML();
        user.Rol.setIdRol(1);
        DireccionML direccion = new DireccionML();
        direccion.setCalle("Calle 21");
        direccion.setNumeroExterior("124");
        direccion.setNumeroInterior("203");
        direccion.Colonia = new ColoniaML();
        direccion.Colonia.setIdColonia(1);
        user.Direccion = direccion;

        Result result = usuarioDAOImplementation.Add(user);
        Assertions.assertInstanceOf(Result.class, result);  
        Assertions.assertNotNull(result);
        Assertions.assertTrue(result.correct);
        Assertions.assertNull(result.errMessage);
    }

    @Test
    public void Update() {
        UsuarioML user = new UsuarioML();
        user.setNombre("Samuel");
        user.setApellidoPaterno("Reyna");
        user.setApellidoMaterno("González");
        user.setCelular("6183100616");
        user.setTelefono("6183100616");
        user.setCurp("REGESSAS13SFDFAS3");
        user.setUsername("SReyanasja1234");
        user.setEmail("SReyanasja1234@example.com");
        user.setSexo("M");
        user.setEstatus(1);
        user.setFechaNacimiento(LocalDate.of(2003, 8, 10));
        user.Rol = new RolML();
        user.Rol.setIdRol(1);

        Result result = usuarioDAOImplementation.Update(295, user);
        Assertions.assertInstanceOf(Result.class, result);
        Assertions.assertNotNull(result);
        Assertions.assertTrue(result.correct);
        Assertions.assertNull(result.errMessage);
    }

    @Test
    public void Delete() {
        Result result = usuarioDAOImplementation.Delete(295);

        Assertions.assertNotNull(result);
        Assertions.assertNull(result.errMessage);
        Assertions.assertInstanceOf(Result.class, result);
        Assertions.assertTrue(result.correct);
    }

    @Test
    public void GetOne() {
        Result result = usuarioDAOImplementation.GetOne(391);

        Assertions.assertInstanceOf(UsuarioML.class, result.object);
        Assertions.assertNotNull(result.object);
        Assertions.assertNull(result.ex);
        Assertions.assertTrue(result.correct);   
    }

}
