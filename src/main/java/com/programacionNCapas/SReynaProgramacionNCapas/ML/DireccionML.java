package com.programacionNCapas.SReynaProgramacionNCapas.ML;

import com.programacionNCapas.SReynaProgramacionNCapas.JPA.DireccionJPA;

public class DireccionML {

    private int IdDireccion;
    private String Calle;
    private String NumeroInterior;
    private String NumeroExterior;
    public ColoniaML Colonia;

    public DireccionML() {
    }

    public DireccionML(DireccionJPA direccionJPA) {
        this.IdDireccion = direccionJPA.getIdDireccion();
        this.Calle = direccionJPA.getCalle();
        this.NumeroInterior = direccionJPA.getNumeroInterior();
        this.NumeroExterior = direccionJPA.getNumeroExterior();
        
        this.Colonia = new ColoniaML();
        this.Colonia.Municipio = new MunicipioML();
        this.Colonia.Municipio.estado = new EstadoML();
        this.Colonia.Municipio.estado.pais = new PaisML();
        this.Colonia.Municipio.setIdMunicipio(direccionJPA.Colonia.Municipio.getIdMunicipio());
        this.Colonia.setIdColonia(direccionJPA.Colonia.getIdColonia());
        this.Colonia.Municipio.estado.setIdEstado(direccionJPA.Colonia.Municipio.Estado.getIdEstado());
        this.Colonia.Municipio.estado.pais.setIdPais(direccionJPA.Colonia.Municipio.Estado.Pais.getIdPais());
        this.Colonia.setCodigoPostal(direccionJPA.Colonia.getCodigoPostal());
    }

    public DireccionML(
            int IdDireccion,
            String Calle,
            String NumeroInterior,
            String NumeroExterior,
            ColoniaML Colonia) {
        this.IdDireccion = IdDireccion;
        this.Calle = Calle;
        this.NumeroExterior = NumeroExterior;
        this.NumeroInterior = NumeroInterior;
        this.Colonia = Colonia;
    }

    public DireccionML(int IdDireccion) {
        this.IdDireccion = IdDireccion;
    }

    public void setIdDireccion(int IdDireccion) {
        this.IdDireccion = IdDireccion;
    }

    public void setCalle(String Calle) {
        this.Calle = Calle;
    }

    public void setNumeroExterior(String NumeroExterior) {
        this.NumeroExterior = NumeroExterior;
    }

    public void setNumeroInterior(String NumeroInterior) {
        this.NumeroInterior = NumeroInterior;
    }

    public int getIdDireccion() {
        return IdDireccion;
    }

    public String getCalle() {
        return Calle;
    }

    public String getNumeroExterior() {
        return NumeroExterior;
    }

    public String getNumeroInterior() {
        return NumeroInterior;
    }

    public ColoniaML getColonia() {
        return Colonia;
    }

    public void setColonia(ColoniaML Colonia) {
        this.Colonia = Colonia;
    }

}
