package com.programacionNCapas.SReynaProgramacionNCapas.DAOJPA;

import com.programacionNCapas.SReynaProgramacionNCapas.ML.DireccionML;
import com.programacionNCapas.SReynaProgramacionNCapas.ML.Result;
import com.programacionNCapas.SReynaProgramacionNCapas.ML.UsuarioML;

public interface IDireccionDAOJPA {
    
    Result Add(UsuarioML usuario);
    
    Result Update(DireccionML direccion, int IdDireccion);
    
    Result GetOne(int IdDireccion);

    Result Delete(int IdDireccion);
}
