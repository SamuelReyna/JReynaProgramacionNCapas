package com.programacionNCapas.SReynaProgramacionNCapas.DAO;

import com.programacionNCapas.SReynaProgramacionNCapas.ML.ColoniaML;
import com.programacionNCapas.SReynaProgramacionNCapas.ML.Result;
import java.sql.ResultSet;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ColoniaDAOImplementation implements IColoniaDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Result GetByMunicipio(int IdMunicipio) {
        Result result = new Result();
        try {
            result.correct = jdbcTemplate.execute("CALL getColoniaByMunicipio(?,?)", (CallableStatementCallback<Boolean>) cs -> {
                cs.registerOutParameter(1, java.sql.Types.REF_CURSOR);
                cs.setInt(2, IdMunicipio);
                cs.execute();

                ResultSet resultSet = (ResultSet) cs.getObject(1);

                result.objects = new ArrayList<>();

                while (resultSet.next()) {
                    ColoniaML colonia = new ColoniaML(
                            resultSet.getInt("IdColonia"),
                            resultSet.getString("Nombre"),
                            resultSet.getString("CodigoPostal")
                    );
                    result.objects.add(colonia);
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
            result.correct = jdbcTemplate.execute(
                    "CALL getAllColonias(?)", (CallableStatementCallback<Boolean>) cs -> {
                        cs.registerOutParameter(1, java.sql.Types.REF_CURSOR);
                        cs.execute();
                        ResultSet resultSet = (ResultSet) cs.getObject(1);
                        result.objects = new ArrayList<>();
                        while (resultSet.next()) {
                            ColoniaML colonia = new ColoniaML(
                                    resultSet.getInt("idColonia"),
                                    resultSet.getString("Nombre"),
                                    resultSet.getString("CodigoPostal")
                            );
                            result.objects.add(colonia);
                        }
                        return true;
                    }
            );
        } catch (Exception ex) {
            result.ex = ex;
            result.errMessage = ex.getLocalizedMessage();
            result.correct = false;
        }
        return result;
    }

}
