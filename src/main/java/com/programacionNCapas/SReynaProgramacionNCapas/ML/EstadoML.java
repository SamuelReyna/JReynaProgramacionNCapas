package com.programacionNCapas.SReynaProgramacionNCapas.ML;

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

    public EstadoML(int IdEstado, String Nombre) {
        this.IdEstado = IdEstado;
        this.Nombre = Nombre;
    }
    
    public void setIdEstado (int IdEstado){
        this.IdEstado = IdEstado;
    }
    public int getIdEstado(){
        return IdEstado;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }


}
