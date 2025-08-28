package com.programacionNCapas.SReynaProgramacionNCapas.DAOJPA;

import com.programacionNCapas.SReynaProgramacionNCapas.ML.Result;

public interface IColoniaDAOJPA {

    Result GetAll();

    Result GetByIdMunicipio(int IdMunicipio);
}
