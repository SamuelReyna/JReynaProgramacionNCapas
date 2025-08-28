package com.programacionNCapas.SReynaProgramacionNCapas.DAO;

import com.programacionNCapas.SReynaProgramacionNCapas.ML.ColoniaML;
import com.programacionNCapas.SReynaProgramacionNCapas.ML.DireccionML;
import com.programacionNCapas.SReynaProgramacionNCapas.ML.EstadoML;
import com.programacionNCapas.SReynaProgramacionNCapas.ML.MunicipioML;
import com.programacionNCapas.SReynaProgramacionNCapas.ML.PaisML;
import com.programacionNCapas.SReynaProgramacionNCapas.ML.Result;
import com.programacionNCapas.SReynaProgramacionNCapas.ML.RolML;
import com.programacionNCapas.SReynaProgramacionNCapas.ML.UsuarioML;
import java.io.StringReader;
import java.sql.Clob;
import java.sql.Date;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioDAOImplementation implements IUserDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Result GetAll(UsuarioML user) {
        Result result = new Result();
        try {

            jdbcTemplate.execute("{CALL GetAllUsersWithAdress(?,?,?,?,?)}", (CallableStatementCallback<Integer>) callableStatement -> {

                callableStatement.registerOutParameter(1, java.sql.Types.REF_CURSOR);
                callableStatement.setString(2, (user.getNombreUsuario() != null && !"".equals(user.getNombreUsuario())) ? user.getNombreUsuario() : "");
                callableStatement.setString(3, (user.getApellidoPaterno() != null && !"".equals(user.getApellidoPaterno())) ? user.getApellidoPaterno() : "");
                callableStatement.setString(4, (user.getApellidoMaterno() != null && !"".equals(user.getApellidoMaterno())) ? user.getApellidoMaterno() : "");
                callableStatement.setInt(5, (user.Rol.getIdRol() != 0) ? user.Rol.getIdRol() : 0);
                callableStatement.execute();

                ResultSet resultSet = (ResultSet) callableStatement.getObject(1);

                List<UsuarioML> usuarios = new ArrayList<>();
                result.objects = new ArrayList<>();
                while (resultSet.next()) {

                    String username = resultSet.getString("username");
                    int idDireccion;

                    UsuarioML userExist = null;
                    for (UsuarioML u : usuarios) {
                        if (u.getUsername().equals(username)) {
                            userExist = u;
                            break;
                        }
                    }
                    if (userExist == null) {
                        UsuarioML usuario = new UsuarioML();
                        RolML rol = new RolML();

                        usuario.Rol = rol;
                        usuario.setIdUser(resultSet.getInt("idUser"));
                        usuario.setNombre(resultSet.getString("Nombre"));
                        usuario.setApellidoPaterno(resultSet.getString("ApellidoPaterno"));
                        usuario.setApellidoMaterno(resultSet.getString("ApellidoMaterno"));
                        usuario.setFechaNacimiento(usuario.getFechaNacimiento());
                        usuario.setPassword(resultSet.getString("password"));
                        usuario.setSexo(resultSet.getString("sexo"));
                        usuario.setTelefono(resultSet.getString("telefono"));
                        usuario.setCelular(resultSet.getString("celular"));
                        usuario.setCurp(resultSet.getString("Curp"));
                        usuario.setUsername(resultSet.getString("Username"));
                        usuario.setEmail(resultSet.getString("Email"));
                        usuario.setImg(resultSet.getString("Img"));
                        rol.setIdRol(resultSet.getInt("idRol"));
                        rol.setNombreRol(resultSet.getString("Rol"));

                        if ((idDireccion = resultSet.getInt("idDireccion")) != 0) {

                            usuario.direcciones = new ArrayList<>();

                            DireccionML direccion = new DireccionML();
                            direccion.setIdDireccion(idDireccion);
                            direccion.setCalle(resultSet.getString("Calle"));
                            direccion.setNumeroInterior(resultSet.getString("NumeroInterior"));
                            direccion.setNumeroExterior(resultSet.getString("NumeroExterior"));

                            ColoniaML colonia = new ColoniaML();
                            colonia.setIdColonia(resultSet.getInt("idColonia"));
                            colonia.setNombre(resultSet.getString("Colonia"));
                            colonia.setCodigoPostal(resultSet.getString("CodigoPostal"));
                            direccion.Colonia = colonia;

                            MunicipioML municipio = new MunicipioML();
                            municipio.setIdMunicipio(resultSet.getInt("idMunicipio"));
                            municipio.setNombre(resultSet.getString("Municipio"));
                            colonia.Municipio = municipio;

                            EstadoML estado = new EstadoML();
                            estado.setIdEstado(resultSet.getInt("idEstado"));
                            estado.setNombre(resultSet.getString("Estado"));
                            municipio.estado = estado;

                            PaisML pais = new PaisML();
                            pais.setIdPais(resultSet.getInt("idPais"));
                            pais.setNombre(resultSet.getString("Pais"));
                            estado.pais = pais;

                            usuario.direcciones.add(direccion);

                        }
                        usuarios.add(usuario);

                    } else {
                        if ((idDireccion = resultSet.getInt("idDireccion")) != 0) {

                            DireccionML direccion = new DireccionML();
                            direccion.setIdDireccion(idDireccion);
                            direccion.setCalle(resultSet.getString("Calle"));
                            direccion.setNumeroInterior(resultSet.getString("NumeroInterior"));
                            direccion.setNumeroExterior(resultSet.getString("NumeroExterior"));

                            ColoniaML colonia = new ColoniaML();
                            colonia.setIdColonia(resultSet.getInt("idColonia"));
                            colonia.setNombre(resultSet.getString("Colonia"));
                            colonia.setCodigoPostal(resultSet.getString("CodigoPostal"));
                            direccion.Colonia = colonia;

                            MunicipioML municipio = new MunicipioML();
                            municipio.setIdMunicipio(resultSet.getInt("idMunicipio"));
                            municipio.setNombre(resultSet.getString("Municipio"));
                            colonia.Municipio = municipio;

                            EstadoML estado = new EstadoML();
                            estado.setIdEstado(resultSet.getInt("idEstado"));
                            estado.setNombre(resultSet.getString("Estado"));
                            municipio.estado = estado;

                            PaisML pais = new PaisML();
                            pais.setIdPais(resultSet.getInt("idPais"));
                            pais.setNombre(resultSet.getString("Pais"));
                            estado.pais = pais;

                            userExist.direcciones.add(direccion);
                        }
                    }
                }
                result.objects.addAll(usuarios);
                result.correct = true;
                return 1;
            });
        } catch (Exception ex) {
            result.errMessage = ex.getLocalizedMessage();
            result.ex = ex;
            result.correct = false;
        }
        return result;
    }

    @Override
    public Result GetDetail(int idUser) {
        Result result = new Result();

        try {
            jdbcTemplate.execute("{CALL GetDireccionByIdUser(?,?)}", (CallableStatementCallback<Integer>) callableStatement -> {
                callableStatement.registerOutParameter(1, java.sql.Types.REF_CURSOR);
                callableStatement.setInt(2, idUser);
                callableStatement.execute();

                ResultSet resultSet = (ResultSet) callableStatement.getObject(1);

                if (resultSet.next()) {
                    UsuarioML usuario = new UsuarioML();

                    RolML rol = new RolML();
                    usuario.Rol = rol;
                    usuario.setIdUser(resultSet.getInt("IdUser"));
                    usuario.setUsername(resultSet.getString("Username"));
                    usuario.setNombreUsuario(resultSet.getString("Nombre"));
                    usuario.setApellidoPaterno(resultSet.getString("apellidoPaterno"));
                    usuario.setApellidoMaterno(resultSet.getString("apellidoMaterno"));
                    usuario.setFechaNacimiento(resultSet.getDate("fechaNacimiento"));
                    usuario.setSexo(resultSet.getString("sexo"));
                    usuario.setCelular(resultSet.getString("celular"));
                    usuario.setTelefono(resultSet.getString("Telefono"));
                    usuario.setCurp(resultSet.getString("curp"));
                    usuario.setEmail(resultSet.getString("email"));
                    usuario.setPassword(resultSet.getString("password"));
                    usuario.setImg(resultSet.getString("Img"));
                    usuario.Rol.setNombreRol(resultSet.getString("Rol"));

                    int idDireccion = resultSet.getInt("idDireccion");
                    if (idDireccion != 0) {
                        usuario.direcciones = new ArrayList<>();

                        do {

                            DireccionML direccion = new DireccionML();

                            direccion.setIdDireccion(resultSet.getInt("idDireccion"));
                            direccion.setCalle(resultSet.getString("Calle"));
                            direccion.setNumeroInterior(resultSet.getString("NumeroInterior"));
                            direccion.setNumeroExterior(resultSet.getString("NumeroExterior"));

                            ColoniaML colonia = new ColoniaML();

                            colonia.setIdColonia(resultSet.getInt("idColonia"));
                            colonia.setNombre(resultSet.getString("Colonia"));
                            colonia.setCodigoPostal(resultSet.getString("CodigoPostal"));
                            direccion.Colonia = colonia;

                            MunicipioML municipio = new MunicipioML();

                            municipio.setIdMunicipio(resultSet.getInt("idMunicipio"));
                            municipio.setNombre(resultSet.getString("Municipio"));
                            colonia.Municipio = municipio;

                            EstadoML estado = new EstadoML();

                            estado.setIdEstado(resultSet.getInt("idEstado"));
                            estado.setNombre(resultSet.getString("Estado"));
                            municipio.estado = estado;

                            PaisML pais = new PaisML();

                            pais.setIdPais(resultSet.getInt("idPais"));
                            pais.setNombre(resultSet.getString("Pais"));
                            estado.pais = pais;

                            usuario.direcciones.add(direccion);

                        } while (resultSet.next());
                    }

                    result.object = usuario;
                    result.correct = true;
                }

                return 1;
            });

        } catch (Exception ex) {
            result.correct = false;
            result.errMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        return result;

    }

    @Override
    public Result Add(UsuarioML usuario) {
        Result result = new Result();

        try {
            result.correct = jdbcTemplate.execute("{CALL UsuarioDireccionAdd(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}",
                    (CallableStatementCallback<Boolean>) callableStatement -> {
                        callableStatement.setString(1, usuario.getNombreUsuario());
                        callableStatement.setString(2, usuario.getApellidoPaterno());
                        callableStatement.setString(3, usuario.getApellidoMaterno());
                        callableStatement.setDate(4, (Date) usuario.getFechaNacimiento());
                        callableStatement.setString(5, usuario.getPassword());
                        callableStatement.setString(6, usuario.getSexo());
                        callableStatement.setString(7, usuario.getUsername());
                        callableStatement.setString(8, usuario.getEmail());
                        callableStatement.setString(9, usuario.getTelefono());
                        callableStatement.setString(10, usuario.getCelular());
                        callableStatement.setString(11, usuario.getCurp());
                        callableStatement.setInt(12, usuario.Rol.getIdRol());
                        callableStatement.setString(13, usuario.getDireccion().getCalle());
                        callableStatement.setString(14, usuario.getDireccion().getNumeroInterior());
                        callableStatement.setString(15, usuario.getDireccion().getNumeroExterior());
                        callableStatement.setInt(16, usuario.getDireccion().Colonia.getIdColonia());

                        Clob clob = callableStatement.getConnection().createClob();
                        clob.setString(1, usuario.getImg());
                        callableStatement.setClob(17, clob);

                        callableStatement.execute();

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

    @Override
    public Result Update(int idUser, UsuarioML usuario) {
        Result result = new Result();
        try {
            result.correct = jdbcTemplate.execute("CALL UserUpdate(?,?,?,?,?,?,?,?,?,?,?,?,?)", (CallableStatementCallback<Boolean>) cs -> {

                cs.setString(1, usuario.getNombreUsuario());
                cs.setString(2, usuario.getApellidoPaterno());
                cs.setString(3, usuario.getApellidoMaterno());
                cs.setDate(4, (Date) usuario.getFechaNacimiento());
                cs.setString(5, usuario.getPassword());
                cs.setString(6, usuario.getSexo());
                cs.setString(7, usuario.getUsername());
                cs.setString(8, usuario.getEmail());
                cs.setString(9, usuario.getTelefono());
                cs.setString(10, usuario.getCelular());
                cs.setString(11, usuario.getCurp());
                cs.setInt(12, usuario.Rol.getIdRol());
                cs.setInt(13, idUser);
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
    public Result Delete(int idUser) {
        Result result = new Result();

        try {
            result.correct = jdbcTemplate.execute("CALL DeleteUserAndDireccion(?)", (CallableStatementCallback<Boolean>) cs -> {
                cs.setInt(1, idUser);
                cs.execute();
                return true;

            });

        } catch (Exception ex) {
            result.errMessage = ex.getLocalizedMessage();
            result.correct = false;
            result.ex = ex;
        }
        return result;
    }

    @Override
    public Result GetOne(int idUser) {
        Result result = new Result();
        try {
            result.correct = jdbcTemplate.execute("CALL GetUserById(?,?)", (CallableStatementCallback<Boolean>) cs -> {
                cs.registerOutParameter(1, java.sql.Types.REF_CURSOR);
                cs.setInt(2, idUser);
                cs.execute();
                ResultSet resultSet = (ResultSet) cs.getObject(1);

                if (resultSet.next()) {
                    UsuarioML usuario = new UsuarioML();

                    RolML rol = new RolML();
                    usuario.Rol = rol;
                    usuario.Direccion = new DireccionML();
                    usuario.setIdUser(resultSet.getInt("IdUser"));
                    usuario.setUsername(resultSet.getString("Username"));
                    usuario.setNombreUsuario(resultSet.getString("Nombre"));
                    usuario.setApellidoPaterno(resultSet.getString("apellidoPaterno"));
                    usuario.setApellidoMaterno(resultSet.getString("apellidoMaterno"));
                    usuario.setFechaNacimiento(usuario.getFechaNacimiento());
                    usuario.setSexo(resultSet.getString("sexo").trim());
                    usuario.setCelular(resultSet.getString("celular"));
                    usuario.setTelefono(resultSet.getString("Telefono"));
                    usuario.setCurp(resultSet.getString("curp"));
                    usuario.setEmail(resultSet.getString("email"));
                    usuario.setPassword(resultSet.getString("password"));
                    usuario.setImg(resultSet.getString("Img"));
                    usuario.Rol.setIdRol(resultSet.getInt("idRol"));
                    usuario.Rol.setNombreRol(resultSet.getString("Rol"));
                    usuario.Direccion.setIdDireccion(-1);
                    result.object = usuario;
                }
                return true;
            });

        } catch (Exception ex) {
            result.ex = ex;
            result.errMessage = ex.getLocalizedMessage();
            result.correct = false;
        }

        return result;
    }

}
