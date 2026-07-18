package excepciones;

public class UsuarioNoEncontradoException extends Exception {

    public UsuarioNoEncontradoException(String email) {
        super("No se encontró ningún usuario registrado con el email: " + email);
    }
}