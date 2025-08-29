package com.programacionNCapas.SReynaProgramacionNCapas.DAOJPA;

import com.programacionNCapas.SReynaProgramacionNCapas.ML.Result;
import com.programacionNCapas.SReynaProgramacionNCapas.ML.UsuarioML;

public interface IDireccionDAOJPA {
    
    Result Add(UsuarioML usuario);
    
    Result Update(UsuarioML usuario);
    
    Result GetOne(int IdDireccion);

    Result Delete(int IdDireccion);
}
