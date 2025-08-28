package com.programacionNCapas.SReynaProgramacionNCapas.ML;

import com.programacionNCapas.SReynaProgramacionNCapas.JPA.MunicipioJPA;

public class MunicipioML {

    private int IdMunicipio;
    private String Nombre;
    public EstadoML estado;

    public EstadoML getEstado() {
        return estado;
    }

    public void setEstado(EstadoML estado) {
        this.estado = estado;
    }

    public MunicipioML() {
    }
    public MunicipioML(MunicipioJPA municipioJPA){
        this.IdMunicipio = municipioJPA.getIdMunicipio();
        this.Nombre = municipioJPA.getNombre();
    }

    public MunicipioML(int idMunicipio,
            String nombre) {
        this.IdMunicipio = idMunicipio;
        this.Nombre = nombre;
    }

    public void setIdMunicipio(int idMunicipio) {
        this.IdMunicipio = idMunicipio;
    }

    public int getIdMunicipio() {
        return IdMunicipio;
    }

    public void setNombre(String nombre) {
        this.Nombre = nombre;
    }

    public String getNombre() {
        return Nombre;
    }
}
