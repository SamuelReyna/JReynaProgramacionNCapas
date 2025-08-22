package com.programacionNCapas.SReynaProgramacionNCapas.DAO;

import com.programacionNCapas.SReynaProgramacionNCapas.ML.Result;
import com.programacionNCapas.SReynaProgramacionNCapas.ML.UsuarioML;

public interface IDireccionDAO {

    Result updateDireccion(int IdDireccion, UsuarioML usuario);

    Result DeleteDireccion(int IdDireccion);
    
    Result AddDireccion (int IdUsuario, UsuarioML usuario);
    
    Result GetDireccion(int IdDireccion);
    
    
}
