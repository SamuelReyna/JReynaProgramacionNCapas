package com.programacionNCapas.SReynaProgramacionNCapas.Controller;

import com.programacionNCapas.SReynaProgramacionNCapas.DAO.ColoniaDAOImplementation;
import com.programacionNCapas.SReynaProgramacionNCapas.DAO.DireccionDAOImplementation;
import com.programacionNCapas.SReynaProgramacionNCapas.DAO.EstadoDAOImplementation;
import com.programacionNCapas.SReynaProgramacionNCapas.DAO.MunicipioDAOImplementation;
import com.programacionNCapas.SReynaProgramacionNCapas.DAO.PaisDAOImplementation;
import com.programacionNCapas.SReynaProgramacionNCapas.DAO.RolDAOImplementation;
import com.programacionNCapas.SReynaProgramacionNCapas.DAO.UsuarioDAOImplementation;
import com.programacionNCapas.SReynaProgramacionNCapas.ML.DireccionML;
import com.programacionNCapas.SReynaProgramacionNCapas.ML.PaisML;
import com.programacionNCapas.SReynaProgramacionNCapas.ML.Result;
import com.programacionNCapas.SReynaProgramacionNCapas.ML.UsuarioML;
import jakarta.validation.Valid;
import java.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("usuario")
public class UsuarioController {

    @Autowired
    private UsuarioDAOImplementation usuarioDAOImplementation;

    @Autowired
    private RolDAOImplementation rolDAOImplementation;

    @Autowired
    private PaisDAOImplementation paisDAOImplementation;
    @Autowired
    private EstadoDAOImplementation estadoDAOImplementation;
    @Autowired
    private MunicipioDAOImplementation municipioDAOImplementation;
    @Autowired
    private ColoniaDAOImplementation coloniaDAOImplementation;

    @Autowired
    private DireccionDAOImplementation direccionDAOImplementation;

    @GetMapping
    public String Index(Model model) {

        Result result = usuarioDAOImplementation.GetAll();

        UsuarioML usuario = new UsuarioML();

        if (result.correct) {
            model.addAttribute("usuarios", result.objects);
        } else {
            model.addAttribute("usuarios", null);
        }

        try {
            model.addAttribute("Roles", rolDAOImplementation.GetAll().objects);
            model.addAttribute("Paises", paisDAOImplementation.GetAll().objects);
        } catch (Exception ex) {
            result.ex = ex;
            return "Exception";
        }

        model.addAttribute("usuario", usuario);

        return "UsuarioIndex";
    }

    @PostMapping("/add")
    public String Add(
            @Valid
            @RequestParam("imagenFile") MultipartFile imagen,
            @ModelAttribute("usuario") UsuarioML usuario,
            BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("usuarios", usuarioDAOImplementation.GetAll().objects);
            model.addAttribute("usuario", usuario);
            model.addAttribute("showAddModal", true);
            try {
                model.addAttribute("Roles", rolDAOImplementation.GetAll().objects);
                model.addAttribute("Paises", paisDAOImplementation.GetAll().objects);
            } catch (Exception ex) {
                return "Exception";
            }
            return "Form";
        } else {
            try {
                if (imagen != null) {
                    byte[] bytes = imagen.getBytes();
                    String base64Img = Base64.getEncoder().encodeToString(bytes);
                    usuario.setImg(base64Img);
                }
            } catch (Exception ex) {

            }
            usuarioDAOImplementation.Add(usuario);

            return "redirect:/usuario";
        }

    }

    @GetMapping("/form")
    public String Form(
            @RequestParam(name = "IdUsuario", required = false, defaultValue = "0") int IdUsuario,
            @RequestParam(name = "IdDireccion", required = false, defaultValue = "0") int IdDireccion,
            @ModelAttribute("usuario") UsuarioML usuario,
            Model model
    ) {
        try {
            model.addAttribute("Roles", rolDAOImplementation.GetAll().objects);
            model.addAttribute("Paises", paisDAOImplementation.GetAll().objects);
            model.addAttribute("Colonias", coloniaDAOImplementation.GetAll().objects);
            model.addAttribute("Estados", estadoDAOImplementation.GetAll().objects);
            model.addAttribute("Municipios", municipioDAOImplementation.GetAll().objects);

        } catch (Exception ex) {

            return "Exception";
        }

        usuario.setIdUser(IdUsuario);
        usuario.setDireccion(new DireccionML(IdDireccion));

        if (IdUsuario == 0 && IdDireccion == 0) { //Crear Usuario

            return "Form";// Formulario Completo 

        } else if (IdUsuario != 0 && IdDireccion == 0) { //detallesUsuario
            Result result = usuarioDAOImplementation.GetDetail(IdUsuario);

            if (result.correct) {
                model.addAttribute("usuario", result.object);
            } else {
                model.addAttribute("usuario", null);
            }
            return "UsuarioDetail"; //Pagina de Detalles
        } else if (IdUsuario != 0 && IdDireccion == -1) { //Editar Usuario

            return "Form";//Formulario con solo datos de usuario para editar
        } else if (IdUsuario != 0 && IdDireccion == -2) { //Crear direccion

            return "Form"; //Solo para crear direccion

        } else if (IdUsuario != 0 && IdDireccion != 0) {

            Result result = direccionDAOImplementation.GetDireccion(IdDireccion);//EditarDireccion
            if (result.correct) {
                usuario.setDireccion((DireccionML) result.object);
                model.addAttribute("usuario", usuario);
            } else {
                model.addAttribute("direccion", null);
            }
            return "Form"; //Solo parte de direccion editar
        }

        return "EROR";
    }
}
