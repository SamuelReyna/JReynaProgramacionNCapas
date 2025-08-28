package com.programacionNCapas.SReynaProgramacionNCapas.ML;

import com.programacionNCapas.SReynaProgramacionNCapas.JPA.RolJPA;

public class RolML {

    private String NombreRol;
    private int IdRol;

    public RolML() {
    }

    public RolML(RolJPA rolJPA){
        this.IdRol = rolJPA.getIdRol();
        this.NombreRol = rolJPA.getNombre();
    }
    
    public RolML(
            String NombreRol,
            int IdRol) {
        this.NombreRol = NombreRol;
        this.IdRol = IdRol;
    }

    public void setNombreRol(String NombreRol) {
        this.NombreRol = NombreRol;
    }

    public String getNombreRol() {
        return NombreRol;
    }

    public void setIdRol(int IdRol) {
        this.IdRol = IdRol;
    }

    public int getIdRol() {
        return IdRol;
    }

}
