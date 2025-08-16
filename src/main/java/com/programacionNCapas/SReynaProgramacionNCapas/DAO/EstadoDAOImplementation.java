package com.programacionNCapas.SReynaProgramacionNCapas.DAO;

import com.programacionNCapas.SReynaProgramacionNCapas.ML.EstadoML;
import com.programacionNCapas.SReynaProgramacionNCapas.ML.Result;
import java.sql.ResultSet;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class EstadoDAOImplementation implements IEstadoDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Result GetByPais(int IdPais) {
        Result result = new Result();

        try {
            result.correct = jdbcTemplate.execute("CALL getEstadoByPais(?,?)", (CallableStatementCallback<Boolean>) callableStatement -> {
                callableStatement.registerOutParameter(1, java.sql.Types.REF_CURSOR);
                callableStatement.setInt(2, IdPais);
                callableStatement.execute();

                ResultSet resultSet = (ResultSet) callableStatement.getObject(1);
                result.objects = new ArrayList<>();
                while (resultSet.next()) {
                    EstadoML estado = new EstadoML(
                            resultSet.getInt("IdEstado"),
                            resultSet.getString("Nombre"));
                    result.objects.add(estado);

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

    @Override
    public Result GetAll() {
        Result result = new Result();
        try {
            result.correct = jdbcTemplate.execute("CALL getAllEstado(?)", (CallableStatementCallback<Boolean>) cs -> {
                cs.registerOutParameter(1, java.sql.Types.REF_CURSOR);
                cs.execute();
                ResultSet resultSet = (ResultSet) cs.getObject(1);
                result.objects = new ArrayList<>();
                while (resultSet.next()) {
                    EstadoML estado = new EstadoML(
                            resultSet.getInt("IdEstado"),
                            resultSet.getString("Nombre")
                    );
                    result.objects.add(estado);
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
