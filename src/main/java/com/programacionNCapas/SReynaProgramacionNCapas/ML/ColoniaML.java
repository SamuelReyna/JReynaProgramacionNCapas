package com.programacionNCapas.SReynaProgramacionNCapas.ML;

import com.programacionNCapas.SReynaProgramacionNCapas.JPA.ColoniaJPA;

public class ColoniaML {

    private int IdColonia;
    private String Nombre;
    private String CodigoPostal;
    public MunicipioML Municipio;

    public ColoniaML() {
    }

    public ColoniaML(int IdColonia, String Nombre, String CodigoPostal) {
        this.IdColonia = IdColonia;
        this.Nombre = Nombre;
        this.CodigoPostal = CodigoPostal;
    }

    public ColoniaML(ColoniaJPA coloniaJPA) {
        this.IdColonia = coloniaJPA.getIdColonia();
        this.CodigoPostal = coloniaJPA.getCodigoPostal();
        this.Nombre = coloniaJPA.getNombre();
    }

    public int getIdColonia() {
        return IdColonia;
    }

    public void setIdColonia(int IdColonia) {
        this.IdColonia = IdColonia;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getCodigoPostal() {
        return CodigoPostal;
    }

    public void setCodigoPostal(String CodigoPostal) {
        this.CodigoPostal = CodigoPostal;
    }

    public MunicipioML getMunicipio() {
        return Municipio;
    }

    public void setMunicipio(MunicipioML Municipio) {
        this.Municipio = Municipio;
    }

}
