package excepciones;

/**
 * Se aplica cuando los datos ingresados por el usuario no cumplen
 * las reglas de validación del sistema (campos vacíos, formato
 * de email inválido, contraseña demasiado corta, etc).
 */
public class DatosInvalidosException extends Exception {

    public DatosInvalidosException(String mensaje) {
        super(mensaje);
    }
}