package com.programacionNCapas.SReynaProgramacionNCapas.ML;

public class RolML {

    private String NombreRol;
    private int IdRol;

    public RolML() {
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
