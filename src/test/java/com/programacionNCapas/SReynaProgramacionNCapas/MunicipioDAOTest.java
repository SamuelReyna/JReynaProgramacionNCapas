package com.programacionNCapas.SReynaProgramacionNCapas;

import com.programacionNCapas.SReynaProgramacionNCapas.DAO.MunicipioDAOImplementation;
import com.programacionNCapas.SReynaProgramacionNCapas.ML.Result;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MunicipioDAOTest {

    @Autowired
    private MunicipioDAOImplementation municipioDAOImplementation;

    @Test
    public void GetAll() {
        Result result = municipioDAOImplementation.GetAll();

        Assertions.assertNotNull(result);
        Assertions.assertNull(result.ex);
        Assertions.assertInstanceOf(Result.class, result);
    }

    public void GetByEstado() {
        Result result = municipioDAOImplementation.GetByEstado(5);

        Assertions.assertNotNull(result);
        Assertions.assertNull(result.ex);
        Assertions.assertInstanceOf(Result.class, result);
    }
}
