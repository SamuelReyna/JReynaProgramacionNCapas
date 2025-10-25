package com.programacionNCapas.SReynaProgramacionNCapas.Controller;

import com.programacionNCapas.SReynaProgramacionNCapas.DAOJPA.UsuarioDAOJPAImplementation;
import com.programacionNCapas.SReynaProgramacionNCapas.JPA.UsuarioJPA;
import com.programacionNCapas.SReynaProgramacionNCapas.ML.DireccionML;
import com.programacionNCapas.SReynaProgramacionNCapas.ML.Password;
import com.programacionNCapas.SReynaProgramacionNCapas.ML.Result;
import com.programacionNCapas.SReynaProgramacionNCapas.ML.UsuarioML;
import com.programacionNCapas.SReynaProgramacionNCapas.Service.EmailService;
import com.programacionNCapas.SReynaProgramacionNCapas.Service.PasswordResetTokenService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {

    @Autowired
    private EmailService emailService;

    private final PasswordResetTokenService passwordResetTokenService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioDAOJPAImplementation usuarioDAOJPAImplementation;

    public AuthController(PasswordResetTokenService passwordResetTokenService) {
        this.passwordResetTokenService = passwordResetTokenService;
    }

    @GetMapping("/login")
    public String Login(@RequestParam(value = "error", required = false) String error, Model model) {
        if (null != error) {
            switch (error) {
                case "bad_credentials" ->
                    model.addAttribute("errorMessage", "Usuario o contraseña incorrectos");
                case "disabled" ->
                    model.addAttribute("errorMessage", "Tu cuenta está deshabilitada, contacta al administrador");
                case "" ->
                    model.addAttribute("errorMessage", "Error desconocido al iniciar sesión");
                default -> {
                }
            }
        }
        return "login";
    }

    SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();

    @GetMapping("/logout")
    public String performLogout(Authentication authentication, HttpServletRequest request, HttpServletResponse response) {
        this.logoutHandler.logout(request, response, authentication);
        return "redirect:/usuario";
    }

    @GetMapping("/forgot-password")
    public String forgotPassword() {
        return "forgotPassword";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/sendEmail")
    public String sendEmail(@RequestParam("email") String email, Model model, RedirectAttributes redirectAttributes) throws Exception {

        Result result = usuarioDAOJPAImplementation.loadUserByEmail(email);
        if (result.correct) {
            if (result.object != null) {
                UsuarioJPA usuario = (UsuarioJPA) result.object;

                String token = passwordResetTokenService.GenerarToken(usuario.getIdUser());

                String linkRestablecer = "http://localhost:8080/changePassword?token=" + token;

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
                        + "            <h1>¡Hola " + usuario.getUsername() + "!</h1>\n"
                        + "        </div>\n"
                        + "        <div class=\"content\">\n"
                        + "            <p>Recibimos una solicitud para restablecer tu contraseña.</p>\n"
                        + "            <p>Haz clic en el botón de abajo para crear una nueva contraseña segura:</p>\n"
                        + "            <a href=\"" + linkRestablecer + "\" class=\"button\">Restablecer Contraseña</a>\n"
                        + "            <p>Si no realizaste esta solicitud, puedes ignorar este mensaje. Tu cuenta seguirá siendo segura.</p>\n"
                        + "        </div>\n"
                        + "        <div class=\"footer\">\n"
                        + "            &copy; 2025 TuAplicación. Todos los derechos reservados.\n"
                        + "        </div>\n"
                        + "    </div>\n"
                        + "</body>\n"
                        + "</html>";

                emailService.sendEmail(usuario.getEmail(), "Solicitud de recuperación de contraseña", html);
                redirectAttributes.addFlashAttribute("message", "Correo electrónico enviado");
            }
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Correo electrónico no es válido");
        }

        return "redirect:/forgot-password";
    }

    @PostMapping("/changePass")
    public String changePass(RedirectAttributes redirectAttributes, @ModelAttribute("password") Password password, HttpSession session) {
        String token = (String) session.getAttribute("token");
        if (token != null) {
            if (passwordResetTokenService.validarToken(token)) {
                int idUser = passwordResetTokenService.getUserIdbyToken(token);

                if (idUser > 0) {
                    Result result = usuarioDAOJPAImplementation.GetOne(idUser);
                    if (result.correct) {
                        UsuarioML usuario = (UsuarioML) result.object;
                        if (password.getPassword() == null ? password.getConfirmPassword() == null : password.getPassword().equals(password.getConfirmPassword())) {
                            usuario.setPassword(passwordEncoder.encode(password.getPassword()));
                            usuario.Direccion = new DireccionML();
                            usuario.Direccion.setIdDireccion(-1);
                            Result resultUpdate = usuarioDAOJPAImplementation.Update(usuario);
                            if (!resultUpdate.correct) {
                                redirectAttributes.addFlashAttribute("errorMessage", "Ocurrio un error al actualizar su contraseña, contácte al administrador");
                            } else {
                                String html = ""
                                        + "<!DOCTYPE html>\n"
                                        + "<html lang=\"es\">\n"
                                        + "<head>\n"
                                        + "    <meta charset=\"UTF-8\">\n"
                                        + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                                        + "    <title>Contraseña Actualizada</title>\n"
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
                                        + "            background-color: #28a745;\n" // Verde éxito
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
                                        + "            background-color: #28a745;\n"
                                        + "            color: #ffffff;\n"
                                        + "            text-decoration: none;\n"
                                        + "            border-radius: 6px;\n"
                                        + "            font-weight: bold;\n"
                                        + "            transition: background-color 0.3s ease;\n"
                                        + "        }\n"
                                        + "        .button:hover {\n"
                                        + "            background-color: #218838;\n"
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
                                        + "            <h1>Contraseña Actualizada</h1>\n"
                                        + "        </div>\n"
                                        + "        <div class=\"content\">\n"
                                        + "            <p>¡Hola " + usuario.getUsername() + "!</p>\n"
                                        + "            <p>Queremos informarte que tu contraseña ha sido cambiada exitosamente.</p>\n"
                                        + "            <p>Si tú realizaste este cambio, no necesitas hacer nada más.</p>\n"
                                        + "            <p>Si <strong>no fuiste tú</strong>, te recomendamos restablecer tu contraseña de inmediato o contactar con el soporte técnico.</p>\n"
                                        + "        </div>\n"
                                        + "        <div class=\"footer\">\n"
                                        + "            &copy; 2025 TuAplicación. Todos los derechos reservados.\n"
                                        + "        </div>\n"
                                        + "    </div>\n"
                                        + "</body>\n"
                                        + "</html>";
                                emailService.sendEmail(usuario.getEmail(), "Actualización de contraseña", html);
                            }

                        }
                    }
                }

            } else {
                redirectAttributes.addFlashAttribute("errorMessage", "Token expirado, solicita otro");
                return "redirect:/forgot-password";
            }
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Token invalido");

            return "redirect:/forgot-password";
        }
        return "redirect:/login";
    }

    @GetMapping("/changePassword")
    public String changePassword(@RequestParam(name = "token", required = true) String token, HttpSession session) {
        session.setAttribute("token", token);
        return "changePassword";
    }

}
