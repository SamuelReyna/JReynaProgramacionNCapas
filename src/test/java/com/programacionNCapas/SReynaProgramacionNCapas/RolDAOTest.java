package com.programacionNCapas.SReynaProgramacionNCapas;

import com.programacionNCapas.SReynaProgramacionNCapas.DAO.RolDAOImplementation;
import com.programacionNCapas.SReynaProgramacionNCapas.ML.Result;
import com.programacionNCapas.SReynaProgramacionNCapas.ML.RolML;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RolDAOTest {

    @Autowired
    private RolDAOImplementation rolDAOImplementation;

    @Test
    public void GetAll() {
        Result result = rolDAOImplementation.GetAll();

        Assertions.assertNull(result.ex);
        List<RolML> expected = List.of(new RolML("admin", 1), new RolML("user", 2));

        List<RolML> roles = ((List<?>) result.objects)
                .stream()
                .map(o -> (RolML) o)
                .collect(Collectors.toList());

        Assertions.assertEquals(expected, roles);
        Assertions.assertNotNull(result);
        Assertions.assertInstanceOf(Result.class, result);
    }
}
