package com.programacionNCapas.SReynaProgramacionNCapas;

import com.programacionNCapas.SReynaProgramacionNCapas.DAO.EstadoDAOImplementation;
import com.programacionNCapas.SReynaProgramacionNCapas.ML.Result;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EstadoDAOTest {

    @Autowired
    private EstadoDAOImplementation estadoDAOImplementation;

    @Test
    public void GetAll() {
        Result result = estadoDAOImplementation.GetAll();

        Assertions.assertNull(result.ex);
        Assertions.assertNotNull(result);
        Assertions.assertInstanceOf(Result.class, result);
    }

    @Test
    public void GetByPais() {
        Result result = estadoDAOImplementation.GetByPais(3);

        Assertions.assertNull(result.ex);
        Assertions.assertNotNull(result);
        Assertions.assertInstanceOf(Result.class, result);
    }
}
