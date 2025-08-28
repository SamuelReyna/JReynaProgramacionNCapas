package com.programacionNCapas.SReynaProgramacionNCapas.DAOJPA;

import com.programacionNCapas.SReynaProgramacionNCapas.ML.Result;

public interface IMunicipioDAOJPA {

    Result GetAll();

    Result GetByIdEstado(int IdEstado);
}
