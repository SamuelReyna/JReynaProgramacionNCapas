package com.programacionNCapas.SReynaProgramacionNCapas.ML;

public class DireccionML {

    private int IdDireccion;
    private String Calle;
    private String NumeroInterior;
    private String NumeroExterior;
    public ColoniaML Colonia;
    public UsuarioML Usuario;

    public DireccionML() {
    }

    public DireccionML(
            int idDireccion,
            String calle,
            String numeroInterior,
            String numeroExterior) {
        this.IdDireccion = idDireccion;
        this.Calle = calle;
        this.NumeroExterior = numeroExterior;
        this.NumeroInterior = numeroInterior;
    }

    public void setIdDirecion(int idDireccion) {
        this.IdDireccion = idDireccion;
    }

    public void setCalle(String calle) {
        this.Calle = calle;
    }

    public void setNumeroExterior(String numeroExterior) {
        this.NumeroExterior = numeroExterior;
    }

    public void setNumeroInterior(String numeroInterior) {
        this.NumeroInterior = numeroInterior;
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
