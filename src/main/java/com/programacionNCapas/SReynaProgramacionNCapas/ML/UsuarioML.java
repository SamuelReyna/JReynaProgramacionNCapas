package com.programacionNCapas.SReynaProgramacionNCapas.ML;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.util.ArrayList;
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
    private String FechaNacimiento;
    @Pattern(regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])", message = "Invalid content type")
    private String Email;
    private String Telefono;
    private String Celular;
    private String Curp;
    private String Sexo;
    public RolML Rol;
    public List<DireccionML> direcciones = new ArrayList<>();
    private DireccionML Direccion;

    public UsuarioML() {
    }

    public UsuarioML(
            int idUser,
            String nombreUsuario,
            String username,
            String apellidoPaterno,
            String apellidoMaterno,
            String fechaNacimiento,
            String password,
            String sexo,
            String email,
            String telefono,
            String celular,
            String curp
    ) {

        this.IdUser = idUser;
        this.NombreUsuario = nombreUsuario;
        this.Username = username;
        this.Telefono = telefono;
        this.Celular = celular;
        this.Curp = curp;
        this.ApellidoPaterno = apellidoPaterno;
        this.ApellidoMaterno = apellidoPaterno;
        this.FechaNacimiento = fechaNacimiento;
        this.Password = password;
        this.Sexo = sexo;
        this.Email = email;
    }

    public void setIdUser(int idUser) {
        this.IdUser = idUser;
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

    public void setFechaNacimiento(String FechaNacimiento) {
        this.FechaNacimiento = FechaNacimiento;
    }

    public void setSexo(String Sexo) {
        this.Sexo = Sexo;
    }

    public int getIdUser() {
        return IdUser;
    }

    public String getFechaNacimiento() {
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
}
