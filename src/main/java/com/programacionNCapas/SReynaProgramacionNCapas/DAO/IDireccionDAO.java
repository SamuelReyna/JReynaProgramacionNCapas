package com.programacionNCapas.SReynaProgramacionNCapas.DAO;

import com.programacionNCapas.SReynaProgramacionNCapas.ML.Result;

public interface IDireccionDAO {

    Result updateDireccion(int IdDireccion);

    Result DeleteDireccion(int IdDireccion);
    
    Result AddDireccion (int IdUsuario);
    
    Result GetDireccion(int IdDireccion);
    
    
}
