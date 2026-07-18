package principal;

import excepciones.DatosInvalidosException;
import excepciones.UsuarioNoEncontradoException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


public class GestorUsuarios {

    private static GestorUsuarios instancia;

    private final Map<String, Usuario> usuariosPorEmail;

    private GestorUsuarios() {
        usuariosPorEmail = new HashMap<>();
        cargarUsuariosIniciales();
    }

    public static GestorUsuarios getInstancia() {
        if (instancia == null) {
            instancia = new GestorUsuarios();
        }
        return instancia;
    }

    private void cargarUsuariosIniciales() {
        usuariosPorEmail.put("yaniscorrea@gmail.com",
                new Admin("Yanis", "Correa", "yaniscorrea@gmail.com", "Uruguay", "12345"));
        usuariosPorEmail.put("leonardoperez@gmail.com",
                new Admin("Leonardo", "Pérez", "leonardoperez@gmail.com", "Uruguay", "12345"));
        usuariosPorEmail.put("paola291187@gmail.com",
                new Tester("Paola", "Holzmann", "paola291187@gmail.com", "Uruguay", "Abcde1"));
    }

    public void registrarAdmin(String nombre, String apellido, String email, String pais, String password)
            throws DatosInvalidosException {

        validarDatosBasicos(nombre, apellido, email, password);
        verificarEmailDisponible(email);

        usuariosPorEmail.put(email, new Admin(nombre, apellido, email, pais, password));
    }

    public void registrarTester(String nombre, String apellido, String email, String pais,
                                String password, String nivel)
            throws DatosInvalidosException {

        validarDatosBasicos(nombre, apellido, email, password);
        verificarEmailDisponible(email);

        Tester nuevo = new Tester(nombre, apellido, email, pais, password);
        nuevo.setNivelTester(nivel);
        usuariosPorEmail.put(email, nuevo);
    }

    public Usuario login(String email, String password)
            throws UsuarioNoEncontradoException, DatosInvalidosException {

        Validaciones.validarCampoObligatorio(email, "email");
        Validaciones.validarCampoObligatorio(password, "contraseña");

        Usuario usuario = buscarPorEmail(email);
        if (!usuario.getPassword().equals(password)) {
            throw new DatosInvalidosException("Contraseña incorrecta.");
        }
        return usuario;
    }

    public Usuario buscarPorEmail(String email) throws UsuarioNoEncontradoException {
        Usuario usuario = usuariosPorEmail.get(email);
        if (usuario == null) {
            throw new UsuarioNoEncontradoException(email);
        }
        return usuario;
    }

    public Collection<Usuario> listarUsuarios() {
        return usuariosPorEmail.values();
    }

    private void verificarEmailDisponible(String email) throws DatosInvalidosException {
        if (usuariosPorEmail.containsKey(email)) {
            throw new DatosInvalidosException("Ya existe un usuario registrado con el email: " + email);
        }
    }

    private void validarDatosBasicos(String nombre, String apellido, String email, String password)
            throws DatosInvalidosException {
        Validaciones.validarCampoObligatorio(nombre, "nombre");
        Validaciones.validarCampoObligatorio(apellido, "apellido");
        Validaciones.validarEmail(email);
        Validaciones.validarPassword(password);
    }
}