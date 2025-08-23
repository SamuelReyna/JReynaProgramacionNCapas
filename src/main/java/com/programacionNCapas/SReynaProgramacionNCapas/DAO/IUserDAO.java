package com.programacionNCapas.SReynaProgramacionNCapas.DAO;

import com.programacionNCapas.SReynaProgramacionNCapas.ML.Result;
import com.programacionNCapas.SReynaProgramacionNCapas.ML.UsuarioML;

public interface IUserDAO {

    Result GetAll(UsuarioML usuario);

    Result GetDetail(int idUser);

    Result Add(UsuarioML usuario);

    Result Update(int idUser);
}
