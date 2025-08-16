
package com.programacionNCapas.SReynaProgramacionNCapas.DAO;

import com.programacionNCapas.SReynaProgramacionNCapas.ML.Result;

public interface IMunicipioDAO {
    
    Result GetByEstado(int IdEstado);
    
    Result GetAll();
    
}
