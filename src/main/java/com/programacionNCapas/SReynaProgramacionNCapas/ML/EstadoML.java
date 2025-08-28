package com.programacionNCapas.SReynaProgramacionNCapas.ML;

import com.programacionNCapas.SReynaProgramacionNCapas.JPA.EstadoJPA;

public class EstadoML {

    private int IdEstado;
    private String Nombre;
    public PaisML pais;

    public PaisML getPais() {
        return pais;
    }

    public void setPais(PaisML pais) {
        this.pais = pais;
    }

    public EstadoML() {
    }

    public EstadoML(EstadoJPA estadoJPA) {
        this.IdEstado = estadoJPA.getIdEstado();
        this.Nombre = estadoJPA.getNombre();
    }

    public EstadoML(int IdEstado, String Nombre) {
        this.IdEstado = IdEstado;
        this.Nombre = Nombre;
    }

    public void setIdEstado(int IdEstado) {
        this.IdEstado = IdEstado;
    }

    public int getIdEstado() {
        return IdEstado;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

}
