package com.programacionNCapas.SReynaProgramacionNCapas.ML;

import com.programacionNCapas.SReynaProgramacionNCapas.JPA.DireccionJPA;
import com.programacionNCapas.SReynaProgramacionNCapas.JPA.UsuarioJPA;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UsuarioML {

    private int IdUser;
    @Pattern(regexp = "^(?!.*[_.]{2})[a-zA-Z0-9](?!.*[_.]{2})[a-zA-Z0-9._]{1,14}[a-zA-Z0-9]$", message = "Invalid content type")
    private String Username;
    @Size(min = 2, max = 20, message = "Texto de entre 2 y 20 letras")
    @NotEmpty(message = "Información requerida")
    private String NombreUsuario;
    @Size(min = 2, max = 20, message = "Texto de entre 2 y 20 letras")
    @NotEmpty(message = "Información requerida")
    private String ApellidoPaterno;
    @Size(min = 2, max = 20, message = "Texto de entre 2 y 20 letras")
    @NotEmpty(message = "Información requerida")
    private String ApellidoMaterno;
    @NotEmpty(message = "Información requerida")
    @Size(min = 8)
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "Invalid content type")
    private String Password;
    private Date FechaNacimiento;
    @Pattern(regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])", message = "Invalid content type")
    private String Email;
    private String Telefono;
    private String Celular;
    private String Curp;
    private String Sexo;
    private String Img;
    public RolML Rol = new RolML();
    public List<DireccionML> direcciones = new ArrayList<>();
    public DireccionML Direccion;

    public UsuarioML(UsuarioJPA usuarioJPA) throws ParseException {
        this.IdUser = usuarioJPA.getIdUser();
        this.NombreUsuario = usuarioJPA.getNombreUsuario();
        this.ApellidoPaterno = usuarioJPA.getApellidoPaterno();
        this.ApellidoMaterno = usuarioJPA.getApellidoMaterno();
        java.sql.Date fechaSql = new java.sql.Date(usuarioJPA.getFechaNacimiento().getTime());
        this.FechaNacimiento = fechaSql;
        this.Email = usuarioJPA.getEmail();
        this.Img = usuarioJPA.getImg();
        this.Curp = usuarioJPA.getCurp();
        this.Celular = usuarioJPA.getCelular();
        this.Telefono = usuarioJPA.getTelefono();
        this.Username = usuarioJPA.getUsername();
        this.Sexo = usuarioJPA.getSexo();
        this.Password = usuarioJPA.getPassword();
        this.Rol.setIdRol(usuarioJPA.Rol.getIdRol());
        this.Rol.setNombreRol(usuarioJPA.Rol.getNombre());
        if (usuarioJPA.Direcciones != null && !usuarioJPA.Direcciones.isEmpty()) {
            this.direcciones = new ArrayList<>();
            for (DireccionJPA direccione : usuarioJPA.Direcciones) {
                DireccionML direccion = new DireccionML();

                direccion.setCalle(direccione.getCalle());
                direccion.setNumeroExterior(direccione.getNumeroExterior());
                direccion.setNumeroInterior(direccione.getNumeroInterior());
                direccion.setIdDireccion(direccione.getIdDireccion());

                direccion.Colonia = new ColoniaML();
                direccion.Colonia.setIdColonia(direccione.Colonia.getIdColonia());
                direccion.Colonia.setNombre(direccione.Colonia.getNombre());
                direccion.Colonia.setCodigoPostal(direccione.Colonia.getCodigoPostal());

                direccion.Colonia.Municipio = new MunicipioML();
                direccion.Colonia.Municipio.setIdMunicipio(direccione.Colonia.Municipio.getIdMunicipio());
                direccion.Colonia.Municipio.setNombre(direccione.Colonia.Municipio.getNombre());

                direccion.Colonia.Municipio.estado = new EstadoML();
                direccion.Colonia.Municipio.estado.setIdEstado(direccione.Colonia.Municipio.Estado.getIdEstado());
                direccion.Colonia.Municipio.estado.setNombre(direccione.Colonia.Municipio.Estado.getNombre());

                direccion.Colonia.Municipio.estado.pais = new PaisML();
                direccion.Colonia.Municipio.estado.pais.setIdPais(direccione.Colonia.Municipio.Estado.Pais.getIdPais());
                direccion.Colonia.Municipio.estado.pais.setNombre(direccione.Colonia.Municipio.Estado.Pais.getNombre());

                this.direcciones.add(direccion);
            }
        }
    }

    public UsuarioML() {
    }

    public UsuarioML(
            int IdUser,
            String NombreUsuario,
            String Username,
            String ApellidoPaterno,
            String ApellidoMaterno,
            Date FechaNacimiento,
            String Password,
            String Sexo,
            String Email,
            String Telefono,
            String Celular,
            String Curp
    ) {

        this.IdUser = IdUser;
        this.NombreUsuario = NombreUsuario;
        this.Username = Username;
        this.Telefono = Telefono;
        this.Celular = Celular;
        this.Curp = Curp;
        this.ApellidoPaterno = ApellidoPaterno;
        this.ApellidoMaterno = ApellidoMaterno;
        this.FechaNacimiento = FechaNacimiento;
        this.Password = Password;
        this.Sexo = Sexo;
        this.Email = Email;
    }

    public void setIdUser(int IdUser) {
        this.IdUser = IdUser;
    }

    public void setNombre(String NombreUsuario) {
        this.NombreUsuario = NombreUsuario;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public String getUsername() {
        return Username;
    }

    public String getNombreUsuario() {
        return NombreUsuario;
    }

    public String getApellidoPaterno() {
        return ApellidoPaterno;
    }

    public String getApellidoMaterno() {
        return ApellidoMaterno;
    }

    public String getPassword() {
        return Password;
    }

    public String getEmail() {
        return Email;
    }

    public String getTelefono() {
        return Telefono;
    }

    public String getCelular() {
        return Celular;
    }

    public String getCurp() {
        return Curp;
    }

    public void setNombreUsuario(String NombreUsuario) {
        this.NombreUsuario = NombreUsuario;
    }

    public void setApellidoPaterno(String ApellidoPaterno) {
        this.ApellidoPaterno = ApellidoPaterno;
    }

    public void setApellidoMaterno(String ApellidoMaterno) {
        this.ApellidoMaterno = ApellidoMaterno;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }

    public void setCelular(String Celular) {
        this.Celular = Celular;
    }

    public void setCurp(String Curp) {
        this.Curp = Curp;
    }

    public void setFechaNacimiento(Date FechaNacimiento) {
        this.FechaNacimiento = FechaNacimiento;
    }

    public void setSexo(String Sexo) {
        this.Sexo = Sexo;
    }

    public int getIdUser() {
        return IdUser;
    }

    public Date getFechaNacimiento() {
        return FechaNacimiento;
    }

    public String getSexo() {
        return Sexo;
    }

    public RolML getRol() {
        return Rol;
    }

    public DireccionML getDireccion() {
        return Direccion;
    }

    public void setRol(RolML Rol) {
        this.Rol = Rol;
    }

    public void setDireccion(DireccionML Direccion) {
        this.Direccion = Direccion;
    }

    public void setImg(String Img) {
        this.Img = Img;
    }

    public String getImg() {
        return Img;
    }
}
