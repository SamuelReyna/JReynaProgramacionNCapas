package com.programacionNCapas.SReynaProgramacionNCapas.JPA;

import com.programacionNCapas.SReynaProgramacionNCapas.ML.DireccionML;
import com.programacionNCapas.SReynaProgramacionNCapas.ML.UsuarioML;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(name = "Usuario")
@Table(name = "usuarios")
public class UsuarioJPA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iduser")
    private int IdUser;
    @Column(name = "username", unique = true)
    private String Username;
    @Column(name = "nombre")
    private String NombreUsuario;
    @Column(name = "apellidomaterno")
    private String ApellidoMaterno;
    @Column(name = "apellidopaterno")
    private String ApellidoPaterno;
    @Column(name = "password")
    private String Password;
    @Column(name = "fechanacimiento")
    private Date FechaNacimiento;
    @Column(name = "email", unique = true)
    private String Email;
    @Column(name = "telefono")
    private String Telefono;
    @Column(name = "celular")
    private String Celular;
    @Column(name = "curp")
    private String Curp;
    @Column(name = "sexo")
    private String Sexo;
    @Lob
    @Column(name = "img")
    private String Img;
    @ManyToOne
    @JoinColumn(name = "idrol")
    public RolJPA Rol = new RolJPA();

    @OneToMany(mappedBy = "Usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<DireccionJPA> Direcciones = new ArrayList<>();

    public UsuarioJPA() {
    }

    public UsuarioJPA(UsuarioML usuarioML) {
        this.NombreUsuario = usuarioML.getNombreUsuario();
        this.ApellidoPaterno = usuarioML.getApellidoPaterno();
        this.ApellidoMaterno = usuarioML.getApellidoMaterno();
        this.Username = usuarioML.getUsername();
        this.Telefono = usuarioML.getTelefono();
        this.Celular = usuarioML.getCelular();
        this.FechaNacimiento = usuarioML.getFechaNacimiento();
        this.Curp = usuarioML.getCurp();
        this.Sexo = usuarioML.getSexo();
        this.Email = usuarioML.getEmail();
        this.Password = usuarioML.getPassword();
        this.Img = usuarioML.getImg();
        this.Rol = new RolJPA();
        this.Rol.setIdRol(usuarioML.Rol.getIdRol());
        for (DireccionML direccione : usuarioML.direcciones) {
            DireccionJPA direccion = new DireccionJPA();
            direccion.setCalle(direccione.getCalle());
            direccion.setNumeroExterior(direccione.getNumeroExterior());
            direccion.setNumeroInterior(direccione.getNumeroInterior());
            direccion.Colonia = new ColoniaJPA();
            direccion.Colonia.setIdColonia(direccione.Colonia.getIdColonia());
            direccion.Usuario = this;

            Direcciones.add(direccion);
        }
    }

    public int getIdUser() {
        return IdUser;
    }

    public void setIdUser(int IdUser) {
        this.IdUser = IdUser;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public String getNombreUsuario() {
        return NombreUsuario;
    }

    public void setNombreUsuario(String NombreUsuario) {
        this.NombreUsuario = NombreUsuario;
    }

    public String getApellidoMaterno() {
        return ApellidoMaterno;
    }

    public void setApellidoMaterno(String ApellidoMaterno) {
        this.ApellidoMaterno = ApellidoMaterno;
    }

    public String getApellidoPaterno() {
        return ApellidoPaterno;
    }

    public void setApellidoPaterno(String ApellidoPaterno) {
        this.ApellidoPaterno = ApellidoPaterno;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public Date getFechaNacimiento() {
        return FechaNacimiento;
    }

    public void setFechaNacimiento(Date FechaNacimiento) {
        this.FechaNacimiento = FechaNacimiento;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }

    public String getCelular() {
        return Celular;
    }

    public void setCelular(String Celular) {
        this.Celular = Celular;
    }

    public String getCurp() {
        return Curp;
    }

    public void setCurp(String Curp) {
        this.Curp = Curp;
    }

    public String getSexo() {
        return Sexo;
    }

    public void setSexo(String Sexo) {
        this.Sexo = Sexo;
    }

    public String getImg() {
        return Img;
    }

    public void setImg(String Img) {
        this.Img = Img;
    }

    public RolJPA getRol() {
        return Rol;
    }

    public void setRol(RolJPA Rol) {
        this.Rol = Rol;
    }

    public UsuarioJPA(int IdUser, String Username, String NombreUsuario, String ApellidoMaterno, String ApellidoPaterno, String Password, Date FechaNacimiento, String Email, String Telefono, String Celular, String Curp, String Sexo, String Img) {
        this.IdUser = IdUser;
        this.Username = Username;
        this.NombreUsuario = NombreUsuario;
        this.ApellidoMaterno = ApellidoMaterno;
        this.ApellidoPaterno = ApellidoPaterno;
        this.Password = Password;
        this.FechaNacimiento = FechaNacimiento;
        this.Email = Email;
        this.Telefono = Telefono;
        this.Celular = Celular;
        this.Curp = Curp;
        this.Sexo = Sexo;
        this.Img = Img;
    }

}
