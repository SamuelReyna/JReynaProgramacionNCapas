package com.programacionNCapas.SReynaProgramacionNCapas.DAOJPA;

import com.programacionNCapas.SReynaProgramacionNCapas.JPA.UsuarioJPA;
import com.programacionNCapas.SReynaProgramacionNCapas.ML.Result;
import com.programacionNCapas.SReynaProgramacionNCapas.ML.UsuarioML;
import com.programacionNCapas.SReynaProgramacionNCapas.Service.EmailService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioDAOJPAImplementation implements IUsuarioDAOJPA {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private EmailService emailService;

    @Override
    public Result GetAll() {
        Result result = new Result();

        try {
            TypedQuery<UsuarioJPA> queryUsuario
                    = entityManager.createQuery("FROM Usuario", UsuarioJPA.class);
            List<UsuarioJPA> usuarios = queryUsuario.getResultList();

            result.objects = new ArrayList<>();

            for (UsuarioJPA usuario : usuarios) {
                result.objects.add(new UsuarioML(usuario));
            }
            result.correct = true;
        } catch (Exception ex) {
            result.ex = ex;
            result.errMessage = ex.getLocalizedMessage();
            result.correct = false;
        }

        return result;
    }

    @Override
    public Result GetOne(int IdUser) {
        Result result = new Result();
        try {
            UsuarioJPA usuarioJPA = entityManager.find(UsuarioJPA.class, IdUser);
            result.object = new UsuarioML(usuarioJPA);
            result.correct = true;

        } catch (Exception ex) {
            result.correct = false;
            result.errMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        return result;
    }

    @Transactional
    @Override
    public Result Add(UsuarioML usuario) {
        Result result = new Result();

        try {
            UsuarioJPA usuarioJPA = new UsuarioJPA(usuario);
            usuarioJPA.Direcciones.get(0).setIdDireccion(0);
            String passwordTemp = usuario.getPassword();
            usuarioJPA.setPassword(passwordEncoder.encode(usuario.getPassword()));
            entityManager.persist(usuarioJPA);
            result.correct = true;

            String linkRestablecer = "http://192.167.0.63:8080/login";

            String html = ""
                    + "<!DOCTYPE html>\n"
                    + "<html lang=\"es\">\n"
                    + "<head>\n"
                    + "    <meta charset=\"UTF-8\">\n"
                    + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                    + "    <title>Restablecer Contraseña</title>\n"
                    + "    <style>\n"
                    + "        body {\n"
                    + "            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\n"
                    + "            background-color: #f4f4f4;\n"
                    + "            margin: 0;\n"
                    + "            padding: 0;\n"
                    + "        }\n"
                    + "        .container {\n"
                    + "            max-width: 600px;\n"
                    + "            margin: 40px auto;\n"
                    + "            background-color: #ffffff;\n"
                    + "            border-radius: 10px;\n"
                    + "            box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);\n"
                    + "            overflow: hidden;\n"
                    + "        }\n"
                    + "        .header {\n"
                    + "            background-color: #007bff;\n"
                    + "            color: #ffffff;\n"
                    + "            padding: 20px;\n"
                    + "            text-align: center;\n"
                    + "        }\n"
                    + "        .content {\n"
                    + "            padding: 30px;\n"
                    + "            color: #333333;\n"
                    + "            line-height: 1.6;\n"
                    + "        }\n"
                    + "        .button {\n"
                    + "            display: inline-block;\n"
                    + "            padding: 12px 24px;\n"
                    + "            margin: 20px 0;\n"
                    + "            background-color: #007bff;\n"
                    + "            color: #ffffff;\n"
                    + "            text-decoration: none;\n"
                    + "            border-radius: 6px;\n"
                    + "            font-weight: bold;\n"
                    + "            transition: background-color 0.3s ease;\n"
                    + "        }\n"
                    + "        .button:hover {\n"
                    + "            background-color: #0056b3;\n"
                    + "        }\n"
                    + "        .footer {\n"
                    + "            text-align: center;\n"
                    + "            font-size: 12px;\n"
                    + "            color: #999999;\n"
                    + "            padding: 20px;\n"
                    + "            border-top: 1px solid #eeeeee;\n"
                    + "        }\n"
                    + "    </style>\n"
                    + "</head>\n"
                    + "<body>\n"
                    + "    <div class=\"container\">\n"
                    + "        <div class=\"header\">\n"
                    + "            <h1>¡Hola " + usuario.getNombreUsuario() + " " + usuario.getApellidoPaterno() + " " + usuario.getApellidoMaterno() + "!</h1>\n"
                    + "        </div>\n"
                    + "        <div class=\"content\">\n"
                    + "            <p>Estas son las credenciales de acceso.</p>\n"
                    + "<p>" + usuario.getUsername() + ".</p>\n"
                    + "<p>" + passwordTemp + "</p>\n"
                    + "            <p>Haz clic en el botón de abajo para iniciar sesión y verificar tu cuenta:</p>\n"
                    + "            <a href=\"" + linkRestablecer + "\" class=\"button\">Inicia sesión ahora</a>\n"
                    + "        </div>\n"
                    + "        <div class=\"footer\">\n"
                    + "            &copy; 2025 TuAplicación. Todos los derechos reservados.\n"
                    + "        </div>\n"
                    + "    </div>\n"
                    + "</body>\n"
                    + "</html>";

            emailService.sendEmail(usuario.getEmail(), "Creación de cuenta nueva", html);

        } catch (Exception ex) {
            result.ex = ex;
            result.errMessage = ex.getLocalizedMessage();
            result.correct = false;
        }
        return result;
    }

    @Transactional
    @Override
    public Result Update(UsuarioML usuario) {
        Result result = new Result();
        try {
            UsuarioJPA usuarioJPA = new UsuarioJPA(usuario);
            UsuarioJPA usuarioBD = entityManager.find(UsuarioJPA.class, usuario.getIdUser());
            usuarioJPA.Direcciones = usuarioBD.Direcciones;
            if (usuario.getPassword() != null) {
                usuarioJPA.setPassword(passwordEncoder.encode(usuario.getPassword()));
            }   
            entityManager.merge(usuarioJPA);
            result.correct = true;
        } catch (Exception ex) {
            result.correct = false;
            result.ex = ex;
            result.errMessage = ex.getLocalizedMessage();
        }

        return result;
    }

    @Transactional
    @Override
    public Result Delete(int IdUser) {
        Result result = new Result();
        try {
            UsuarioJPA usuario = entityManager.find(UsuarioJPA.class, IdUser);
            entityManager.remove(usuario);
            result.correct = true;
        } catch (Exception ex) {
            result.correct = false;
            result.errMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        return result;
    }

    @Transactional
    @Override
    public Result LogicalDelete(int IdUser) {
        Result result = new Result();

        try {
            UsuarioJPA usuarioJPA = entityManager.find(UsuarioJPA.class, IdUser);
            usuarioJPA.setEstatus(usuarioJPA.getEstatus() == 1 ? 0 : 1);
            entityManager.merge(usuarioJPA);
            result.correct = true;
        } catch (Exception ex) {
            result.correct = false;
            result.errMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        return result;
    }

    @Override
    public Result loadUserByUsername(String Username) {
        Result result = new Result();
        try {
            TypedQuery<UsuarioJPA> queryUsuario = entityManager.createQuery(
                    "SELECT u FROM Usuario u WHERE u.Username = :username", UsuarioJPA.class);
            queryUsuario.setParameter("username", Username);
            UsuarioJPA usuario = queryUsuario.getSingleResult();
            result.object = usuario;
            result.correct = true;

        } catch (Exception ex) {
            result.correct = false;
            result.errMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        return result;
    }

    @Override
    public Result loadUserByEmail(String Email) {
        Result result = new Result();
        try {
            TypedQuery<UsuarioJPA> queryUser
                    = entityManager.createQuery(
                            "SELECT u.Username, u.IdUser, u.NombreUsuario, u.Estatus, u.Email "
                            + "FROM Usuario u"
                            + " WHERE u.Email = :email", UsuarioJPA.class);
            queryUser.setParameter("email", Email);
            UsuarioJPA usuario = queryUser.getSingleResult();
            result.object = usuario;
            result.correct = true;

        } catch (Exception ex) {
            result.correct = false;
            result.errMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        return result;
    }

}
