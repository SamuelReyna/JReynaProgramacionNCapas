package com.programacionNCapas.SReynaProgramacionNCapas.DAO;

import com.programacionNCapas.SReynaProgramacionNCapas.ML.MunicipioML;
import com.programacionNCapas.SReynaProgramacionNCapas.ML.Result;
import java.sql.ResultSet;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MunicipioDAOImplementation implements IMunicipioDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Result GetByEstado(int IdEstado) {

        Result result = new Result();

        try {
            result.correct = jdbcTemplate.execute("CALL getMunicipioByEstado(?,?)", (CallableStatementCallback<Boolean>) callableStatement -> {

                callableStatement.registerOutParameter(1, java.sql.Types.REF_CURSOR);
                callableStatement.setInt(2, IdEstado);
                callableStatement.execute();

                ResultSet resultSet = (ResultSet) callableStatement.getObject(1);

                result.objects = new ArrayList<>();
                while (resultSet.next()) {
                    MunicipioML municipio = new MunicipioML(
                            resultSet.getInt("IdMunicipio"),
                            resultSet.getString("Nombre")
                    );
                    result.objects.add(municipio);
                }
                return true;
            });

        } catch (Exception ex) {
            result.ex = ex;
            result.correct = false;
            result.errMessage = ex.getLocalizedMessage();
        }

        return result;
    }

}
