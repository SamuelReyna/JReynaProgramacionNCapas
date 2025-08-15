package com.programacionNCapas.SReynaProgramacionNCapas.DAO;

import com.programacionNCapas.SReynaProgramacionNCapas.ML.ColoniaML;
import com.programacionNCapas.SReynaProgramacionNCapas.ML.DireccionML;
import com.programacionNCapas.SReynaProgramacionNCapas.ML.EstadoML;
import com.programacionNCapas.SReynaProgramacionNCapas.ML.MunicipioML;
import com.programacionNCapas.SReynaProgramacionNCapas.ML.PaisML;
import com.programacionNCapas.SReynaProgramacionNCapas.ML.Result;
import java.sql.ResultSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DireccionDAOImplementation implements IDireccionDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Result updateDireccion(int IdDireccion) {
        Result result = new Result();

        try {
            DireccionML direccion = new DireccionML();
            result.correct = jdbcTemplate.execute("CALL UpdateDireccion(?,?,?,?,?,?)", (CallableStatementCallback<Boolean>) cs -> {
                cs.setString(1, direccion.getCalle());
                cs.setString(2, direccion.getNumeroExterior());
                cs.setString(3, direccion.getNumeroExterior());
                cs.setInt(4, direccion.Colonia.getIdColonia());
                cs.setInt(5, direccion.Usuario.getIdUser());
                cs.setInt(6, IdDireccion);
                int status = cs.executeUpdate();
                return status == -1;

            });
        } catch (Exception ex) {
            result.correct = false;
            result.ex = ex;
            result.errMessage = ex.getLocalizedMessage();
        }

        return result;
    }

    @Override
    public Result DeleteDireccion(int IdDireccion) {
        Result result = new Result();

        try {
            result.correct = jdbcTemplate.execute("DeleteDireccion(?)", (CallableStatementCallback<Boolean>) cs -> {
                cs.setInt(1, IdDireccion);
                int status = cs.executeUpdate();
                return status == -1;
            });
        } catch (Exception ex) {
            result.correct = false;
            result.ex = ex;
            result.errMessage = ex.getLocalizedMessage();
        }

        return result;
    }

    @Override
    public Result AddDireccion(int IdUsuario) {
        Result result = new Result();
        try {
            DireccionML direccion = new DireccionML();
            jdbcTemplate.execute("CALL AddDireccion(?,?,?,?,?)", (CallableStatementCallback<Boolean>) cs -> {
                cs.setString(1, direccion.getCalle());
                cs.setString(2, direccion.getNumeroInterior());
                cs.setString(3, direccion.getNumeroExterior());
                cs.setInt(4, direccion.Colonia.getIdColonia());
                cs.setInt(5, IdUsuario);
                int status = cs.executeUpdate();
                return status == -1;
            });
        } catch (Exception ex) {
            result.correct = false;
            result.ex = ex;
            result.errMessage = ex.getLocalizedMessage();
        }
        return result;
    }

    @Override
    public Result GetDireccion(int IdDireccion) {
        Result result = new Result();
        try {
            DireccionML direccion = new DireccionML();
            ColoniaML colonia = new ColoniaML();
            direccion.Colonia = colonia;
            MunicipioML municipio = new MunicipioML();
            colonia.Municipio = municipio;
            EstadoML estado = new EstadoML();
            municipio.estado = estado;
            PaisML pais = new PaisML();
            estado.pais = pais;
            result.correct = jdbcTemplate.execute("CALL GetDireccionById(?,?)", (CallableStatementCallback<Boolean>) cs -> {
                cs.registerOutParameter(1, java.sql.Types.REF_CURSOR);
                cs.setInt(2, IdDireccion);

                cs.execute();

                ResultSet resultSet = (ResultSet) cs.getObject(1);

                if (resultSet.next()) {
                    direccion.setCalle(resultSet.getString("Calle"));
                    direccion.setNumeroInterior(resultSet.getString("NumeroInterior"));
                    direccion.setNumeroExterior(resultSet.getString("NumeroExterior"));
                    colonia.setIdColonia(resultSet.getInt("IdColonia"));
                    colonia.setNombre(resultSet.getString("Colonia"));
                    colonia.setCodigoPostal(resultSet.getString("CodigoPostal"));
                    municipio.setIdMunicipio(resultSet.getInt("IdMunicipio"));
                    municipio.setNombre(resultSet.getString("Municipio"));
                    estado.setIdEstado(resultSet.getInt("IdEstado"));
                    estado.setNombre(resultSet.getString("Estado"));
                    pais.setIdPais(resultSet.getInt("IdPais"));
                    pais.setNombre(resultSet.getString("Pais"));
                }

                result.object = direccion;
                return true;
            });
        } catch (Exception ex) {
            result.correct = false;
            result.ex = ex;
            result.errMessage = ex.getLocalizedMessage();
        }
        return result;

    }

}
