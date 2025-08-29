package com.programacionNCapas.SReynaProgramacionNCapas.DAOJPA;

import com.programacionNCapas.SReynaProgramacionNCapas.ML.Result;
import com.programacionNCapas.SReynaProgramacionNCapas.ML.UsuarioML;

public interface IUsuarioDAOJPA {

    Result GetAll();

    Result GetOne(int IdUser);
    
    Result Add(UsuarioML usuario);

    Result Update(UsuarioML usuario);

    Result Delete(int IdUser);
    
    Result LogicalDelete(int IdUser);

}
