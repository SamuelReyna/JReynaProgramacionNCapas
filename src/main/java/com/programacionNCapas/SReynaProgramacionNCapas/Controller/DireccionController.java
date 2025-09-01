package com.programacionNCapas.SReynaProgramacionNCapas.Controller;

import com.programacionNCapas.SReynaProgramacionNCapas.DAO.ColoniaDAOImplementation;
import com.programacionNCapas.SReynaProgramacionNCapas.DAO.EstadoDAOImplementation;
import com.programacionNCapas.SReynaProgramacionNCapas.DAO.MunicipioDAOImplementation;
import com.programacionNCapas.SReynaProgramacionNCapas.DAOJPA.ColoniaDAOJPAImplementation;
import com.programacionNCapas.SReynaProgramacionNCapas.DAOJPA.DireccionDAOJPAImplementation;
import com.programacionNCapas.SReynaProgramacionNCapas.DAOJPA.EstadoDAOJPAImplementation;
import com.programacionNCapas.SReynaProgramacionNCapas.DAOJPA.MunicipioDAOJPAImplementation;
import com.programacionNCapas.SReynaProgramacionNCapas.ML.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("direccion")
public class DireccionController {

    @Autowired
    private EstadoDAOJPAImplementation estadoDAOJPAImplementation;

    @Autowired
    private DireccionDAOJPAImplementation direccionDAOJPAImplementation;
    @Autowired
    private MunicipioDAOJPAImplementation municipioDAOJPAImplementation;

    @Autowired
    private ColoniaDAOJPAImplementation coloniaDAOJPAImplementation;

//    @GetMapping("GetEstados/{IdPais}")
//    @ResponseBody
//    public Result EstadoByPais(@PathVariable int IdPais) {
//        return estadoDAOJPAImplementation.GetByPais(IdPais);
//    }
//
//    @GetMapping("GetMunicipios/{IdEstado}")
//    @ResponseBody
//    public Result MunicipioByEstado(@PathVariable int IdEstado) {
//        return municipioDAOJPAImplementation.GetByEstado(IdEstado);
//    }
//
//    @GetMapping("GetColonias/{IdMunicipio}")
//    @ResponseBody
//    public Result ColoniaByMunicipio(@PathVariable int IdMunicipio) {
//        return coloniaDAOJPAImplementation.GetByMunicipio(IdMunicipio);
//    }

    @GetMapping("GetColonias")
    @ResponseBody
    public Result Colonias() {
        return coloniaDAOJPAImplementation.GetAll();
    }

    @GetMapping("GetEstados")
    @ResponseBody
    public Result Estados() {
        return estadoDAOJPAImplementation.GetAll();
    }

    @GetMapping("GetMunicipios")
    @ResponseBody
    public Result Municipios() {
        return municipioDAOJPAImplementation.GetAll();
    }

    @GetMapping("DeleteDireccion")
    @ResponseBody
    public ResponseEntity<Object> DeleteDireccion(@RequestParam int IdDireccion) {

        Result result = direccionDAOJPAImplementation.Delete(IdDireccion);
        if (result.correct) {
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500 si falla
        }
    }

}
