package principal;

import excepciones.DatosInvalidosException;
import java.util.regex.Pattern;

/**
 * Centraliza las reglas de validación de datos de entrada.
 * Separada del resto de la lógica para respetar el principio
 * de responsabilidad única.
 */
public class Validaciones {

    private static final int LONGITUD_MINIMA_PASSWORD = 6;
    private static final Pattern PATRON_EMAIL =
            Pattern.compile("^[\\w.+-]+@[\\w-]+\\.[a-zA-Z]{2,}$");

    private Validaciones() {
        // Clase utilitaria, no se instancia
    }

    public static void validarCampoObligatorio(String valor, String nombreCampo)
            throws DatosInvalidosException {
        if (valor == null || valor.trim().isEmpty()) {
            throw new DatosInvalidosException("El campo '" + nombreCampo + "' es obligatorio.");
        }
    }

    public static void validarEmail(String email) throws DatosInvalidosException {
        validarCampoObligatorio(email, "email");
        if (!PATRON_EMAIL.matcher(email).matches()) {
            throw new DatosInvalidosException("El email '" + email + "' no tiene un formato válido.");
        }
    }

    public static void validarPassword(String password) throws DatosInvalidosException {
        validarCampoObligatorio(password, "contraseña");
        if (password.length() < LONGITUD_MINIMA_PASSWORD) {
            throw new DatosInvalidosException(
                    "La contraseña debe tener al menos " + LONGITUD_MINIMA_PASSWORD + " caracteres.");
        }
    }
}