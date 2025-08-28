package com.programacionNCapas.SReynaProgramacionNCapas.ML;

import com.programacionNCapas.SReynaProgramacionNCapas.JPA.PaisJPA;

public class PaisML {

    private int IdPais;
    private String Nombre;

    public PaisML() {
    }

    public PaisML(PaisJPA paisJPA) {
        this.IdPais = paisJPA.getIdPais();
        this.Nombre = paisJPA.getNombre();
    }

    public PaisML(
            int IdPais, String Nombre) {
        this.IdPais = IdPais;
        this.Nombre = Nombre;
    }

    public void setIdPais(int IdPais) {
        this.IdPais = IdPais;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public int getIdPais() {
        return IdPais;
    }

    public String getNombre() {
        return Nombre;
    }
}
