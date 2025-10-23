package com.programacionNCapas.SReynaProgramacionNCapas.Controller;

import com.programacionNCapas.SReynaProgramacionNCapas.DAOJPA.UsuarioDAOJPAImplementation;
import com.programacionNCapas.SReynaProgramacionNCapas.JPA.UsuarioJPA;
import com.programacionNCapas.SReynaProgramacionNCapas.ML.Result;
import com.programacionNCapas.SReynaProgramacionNCapas.Service.EmailService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.security.MessageDigest;
import java.util.concurrent.ConcurrentHashMap;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {

    @Autowired
    private EmailService emailService;

    private static final ConcurrentHashMap<String, Long> tokens = new ConcurrentHashMap<>();

    private static final long EXPIRATION_TIME_MS = 180000; // 3 minutos

    @Autowired
    private UsuarioDAOJPAImplementation usuarioDAOJPAImplementation;

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
    public String sendEmail(@RequestParam("email") String email, Model model, RedirectAttributes redirectAttributes) {

        Result result = usuarioDAOJPAImplementation.loadUserByEmail(email);
        if (result.correct) {
            if (result.object != null) {
                UsuarioJPA usuario = (UsuarioJPA) result.object;
                long expirationTime = System.currentTimeMillis() + EXPIRATION_TIME_MS;

                String token = usuario.getIdUser() + "/" + usuario.getNombreUsuario() + "/" + usuario.getEmail();

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
                        + "            <p>Gracias por confiar en <strong>TuAplicación</strong>.</p>\n"
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
    public String changePass() {
        return "redirect:/login";
    }

    @GetMapping("/changePassword")
    public String changePassword(@RequestParam(name = "token", required = true) String token) {
        return "changePassword";
    }

    public byte[] cifra(String sinCifrar) throws Exception {
        final byte[] bytes = sinCifrar.getBytes("UTF-8");
        final Cipher aes = obtieneCipher(true);
        final byte[] cifrado = aes.doFinal(bytes);
        return cifrado;
    }

    public String descifra(byte[] cifrado) throws Exception {
        final Cipher aes = obtieneCipher(false);
        final byte[] bytes = aes.doFinal(cifrado);
        final String sinCifrar = new String(bytes, "UTF-8");
        return sinCifrar;
    }

    private Cipher obtieneCipher(boolean paraCifrar) throws Exception {
        final String frase = "FraselargapARACifrarTextoyGenera123rtokene$%#$%#$%&%&$";
        final MessageDigest digest = MessageDigest.getInstance("SHA");
        digest.update(frase.getBytes("UTF-8"));
        final SecretKeySpec key = new SecretKeySpec(digest.digest(), 0, 16, "AES");

        final Cipher aes = Cipher.getInstance("AES/ECB/PKCS5Padding");
        if (paraCifrar) {
            aes.init(Cipher.ENCRYPT_MODE, key);
        } else {
            aes.init(Cipher.DECRYPT_MODE, key);
        }

        return aes;
    }
}
