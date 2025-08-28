package com.programacionNCapas.SReynaProgramacionNCapas.DAOJPA;

import com.programacionNCapas.SReynaProgramacionNCapas.ML.Result;

public interface IEstadoDAOJPA {

    Result GetAll();

    Result GetByIdPais(int idPais);

}
