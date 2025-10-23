package com.programacionNCapas.SReynaProgramacionNCapas;

import com.programacionNCapas.SReynaProgramacionNCapas.DAO.ColoniaDAOImplementation;
import com.programacionNCapas.SReynaProgramacionNCapas.ML.Result;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ColoniaDAOTest {

    @Autowired
    private ColoniaDAOImplementation coloniaDAOImplementation;

    @Test
    public void GetAll() {
        Result result = coloniaDAOImplementation.GetAll();

        Assertions.assertNotNull(result.objects);
        Assertions.assertTrue(result.correct);
        Assertions.assertNull(result.ex);
        Assertions.assertInstanceOf(Result.class, result);
    }

    @Test
    public void GetByMunicipio() {
        Result result = coloniaDAOImplementation.GetByMunicipio(1);

        Assertions.assertNotNull(result);
        Assertions.assertNotNull(result.objects);
        Assertions.assertTrue(result.correct);
        Assertions.assertNull(result.ex);
        Assertions.assertInstanceOf(Result.class, result);
    }

}
