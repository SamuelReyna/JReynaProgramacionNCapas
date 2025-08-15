package com.programacionNCapas.SReynaProgramacionNCapas.ML;

public class EstadoML {

    private int IdEstado;
    private String Nombre;
    public PaisML pais;

    public EstadoML() {
    }

    public EstadoML(int idEstado, String nombre) {
        this.IdEstado = idEstado;
        this.Nombre = nombre;
    }
    
    public void setIdEstado (int idEstado){
        this.IdEstado = idEstado;
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
