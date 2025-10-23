package com.programacionNCapas.SReynaProgramacionNCapas;

import com.programacionNCapas.SReynaProgramacionNCapas.DAO.PaisDAOImplementation;
import com.programacionNCapas.SReynaProgramacionNCapas.ML.Result;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PaisDAOTest {

    @Autowired
    private PaisDAOImplementation paisDAOImplementation;

    @Test
    public void GetAll() {
        Result result = paisDAOImplementation.GetAll();

        Assertions.assertNull(result.ex);
        Assertions.assertNotNull(result);
        Assertions.assertInstanceOf(Result.class, result);
    }
}
