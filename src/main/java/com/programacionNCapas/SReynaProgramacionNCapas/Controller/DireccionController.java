package com.programacionNCapas.SReynaProgramacionNCapas.Controller;

import com.programacionNCapas.SReynaProgramacionNCapas.DAO.ColoniaDAOImplementation;
import com.programacionNCapas.SReynaProgramacionNCapas.DAO.EstadoDAOImplementation;
import com.programacionNCapas.SReynaProgramacionNCapas.DAO.MunicipioDAOImplementation;
import com.programacionNCapas.SReynaProgramacionNCapas.ML.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("direccion")
public class DireccionController {


    @Autowired
    private EstadoDAOImplementation estadoDAOImplementation;

    @Autowired
    private MunicipioDAOImplementation municipioDAOImplementation;

    @Autowired
    private ColoniaDAOImplementation coloniaDAOImplementation;

    @GetMapping("GetEstados/{IdPais}")
    @ResponseBody
    public Result EstadoByPais(@PathVariable int IdPais) {
        return estadoDAOImplementation.GetByPais(IdPais);
    }

    @GetMapping("GetMunicipios/{IdEstado}")
    @ResponseBody
    public Result MunicipioByEstado(@PathVariable int IdEstado) {
        return municipioDAOImplementation.GetByEstado(IdEstado);
    }

    @GetMapping("GetColonias/{IdMunicipio}")
    @ResponseBody
    public Result ColoniaByMunicipio(@PathVariable int IdMunicipio) {
        return coloniaDAOImplementation.GetByMunicipio(IdMunicipio);
    }

}
