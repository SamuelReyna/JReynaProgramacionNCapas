package com.programacionNCapas.SReynaProgramacionNCapas.DAO;

import com.programacionNCapas.SReynaProgramacionNCapas.ML.ColoniaML;
import com.programacionNCapas.SReynaProgramacionNCapas.ML.DireccionML;
import com.programacionNCapas.SReynaProgramacionNCapas.ML.EstadoML;
import com.programacionNCapas.SReynaProgramacionNCapas.ML.MunicipioML;
import com.programacionNCapas.SReynaProgramacionNCapas.ML.PaisML;
import com.programacionNCapas.SReynaProgramacionNCapas.ML.Result;
import com.programacionNCapas.SReynaProgramacionNCapas.ML.UsuarioML;
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
    public Result updateDireccion(int IdDireccion, UsuarioML usuario) {
        Result result = new Result();

        try {

            result.correct = jdbcTemplate.execute("CALL UpdateDireccion(?,?,?,?,?,?)", (CallableStatementCallback<Boolean>) cs -> {
                cs.setString(1, usuario.getDireccion().getCalle());
                cs.setString(2, usuario.getDireccion().getNumeroExterior());
                cs.setString(3, usuario.getDireccion().getNumeroExterior());
                cs.setInt(4, usuario.getDireccion().Colonia.getIdColonia());
                cs.setInt(5, usuario.getIdUser());
                cs.setInt(6, IdDireccion);
                cs.executeUpdate();
                return true;
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
            result.correct = jdbcTemplate.execute("CALL DeleteDireccion(?)", (CallableStatementCallback<Boolean>) cs -> {
                cs.setInt(1, IdDireccion);
                cs.executeUpdate();
                return true;
            });
        } catch (Exception ex) {
            result.correct = false;
            result.ex = ex;
            result.errMessage = ex.getLocalizedMessage();
        }

        return result;
    }

    @Override
    public Result AddDireccion(int IdUsuario, UsuarioML usuario) {
        Result result = new Result();
        try {

            result.correct = jdbcTemplate.execute("CALL AddDireccion(?,?,?,?,?)", (CallableStatementCallback<Boolean>) cs -> {
                cs.setString(1, usuario.Direccion.getCalle());
                cs.setString(2, usuario.Direccion.getNumeroInterior());
                cs.setString(3, usuario.Direccion.getNumeroExterior());
                cs.setInt(4, usuario.Direccion.Colonia.getIdColonia());
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
            UsuarioML usuario = new UsuarioML();
            DireccionML direccion = new DireccionML();
            usuario.Direccion = direccion;

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
                    usuario.setIdUser(resultSet.getInt("idUser"));
                    direccion.setIdDireccion(resultSet.getInt("idDireccion"));
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

                result.object = usuario;
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
