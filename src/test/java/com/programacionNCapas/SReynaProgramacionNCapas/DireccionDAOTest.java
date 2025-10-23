package com.programacionNCapas.SReynaProgramacionNCapas;

import com.programacionNCapas.SReynaProgramacionNCapas.DAO.DireccionDAOImplementation;
import com.programacionNCapas.SReynaProgramacionNCapas.ML.ColoniaML;
import com.programacionNCapas.SReynaProgramacionNCapas.ML.DireccionML;
import com.programacionNCapas.SReynaProgramacionNCapas.ML.Result;
import com.programacionNCapas.SReynaProgramacionNCapas.ML.UsuarioML;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DireccionDAOTest {

    @Autowired
    private DireccionDAOImplementation direccionDAOImplementation;

    @Test
    public void GetOne() {
        Result result = direccionDAOImplementation.GetDireccion(122);

        Assertions.assertNotNull(result);
        Assertions.assertInstanceOf(UsuarioML.class, result.object);
        Assertions.assertNull(result.ex);
    }

    @Test
    public void Add() {
        UsuarioML usuario = new UsuarioML();

        DireccionML direccion = new DireccionML();
        direccion.setCalle("Calle de prueba");
        direccion.setNumeroInterior("123");
        direccion.setNumeroExterior("654");
        direccion.Colonia = new ColoniaML();
        direccion.Colonia.setIdColonia(1);
        usuario.Direccion = direccion;
        Result result = direccionDAOImplementation.AddDireccion(350, usuario);
        Assertions.assertNotNull(result);
        Assertions.assertTrue(result.correct);
        Assertions.assertNull(result.ex);
    }

    @Test
    public void Update() {
        UsuarioML usuario = new UsuarioML();
        usuario.setIdUser(350);
        DireccionML direccion = new DireccionML();
        direccion.setCalle("Calle de prueba");
        direccion.setNumeroInterior("123");
        direccion.setNumeroExterior("654");
        direccion.Colonia = new ColoniaML();
        direccion.Colonia.setIdColonia(1);
        usuario.Direccion = direccion;

        Result result = direccionDAOImplementation.updateDireccion(361, usuario);
        Assertions.assertNotNull(result);
        Assertions.assertTrue(result.correct);
        Assertions.assertNull(result.ex);
    }

    @Test
    public void Delete() {
        Result result = direccionDAOImplementation.DeleteDireccion(361);
        Assertions.assertNotNull(result);
        Assertions.assertTrue(result.correct);
        Assertions.assertNull(result.ex);
    }

}
