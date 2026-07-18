package principal;

import excepciones.DatosInvalidosException;
import excepciones.UsuarioNoEncontradoException;

import java.util.InputMismatchException;
import java.util.Scanner;

public class SistemaUsuarios {

    private final GestorUsuarios gestor;
    private final Scanner scanner;
    private Usuario usuarioActual;

    public SistemaUsuarios() {
        gestor = GestorUsuarios.getInstancia();
        scanner = new Scanner(System.in);
        usuarioActual = null;
    }

    public void mostrarMenu() {
        int opcion = 0;
        do {
            System.out.println("\n===== MENÚ PRINCIPAL =====");
            if (usuarioActual != null) {
                System.out.println("(Sesión activa: " + usuarioActual.getEmail() + ")");
            }
            System.out.println("1. Iniciar sesión");
            System.out.println("2. Registrar administrador");
            System.out.println("3. Alta usuario tester");
            System.out.println("4. Listar usuarios");
            System.out.println("5. Buscar usuario por email");
            System.out.println("6. Cerrar sesión");
            System.out.println("7. Salir");
            System.out.print("Seleccione una opción válida: ");

            try {
                opcion = leerOpcion();
                switch (opcion) {
                    case 1 -> loginUsuario();
                    case 2 -> registrarAdmin();
                    case 3 -> registrarTester();
                    case 4 -> listarUsuarios();
                    case 5 -> buscarUsuario();
                    case 6 -> cerrarSesion();
                    case 7 -> System.out.println("Salida exitosa...");
                    default -> System.out.println("Opción inválida. Elija un número entre 1 y 7.");
                }
            } catch (DatosInvalidosException | UsuarioNoEncontradoException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (opcion != 7);
    }

    private int leerOpcion() {
        try {
            int opcion = scanner.nextInt();
            scanner.nextLine();
            return opcion;
        } catch (InputMismatchException e) {
            scanner.nextLine();
            System.out.println("Debe ingresar un número.");
            return -1;
        }
    }

    private void loginUsuario() throws UsuarioNoEncontradoException, DatosInvalidosException {
        System.out.println("=== Iniciar sesión ===");
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Contraseña: ");
        String password = scanner.nextLine();

        usuarioActual = gestor.login(email, password);
        System.out.println("Login exitoso.");
        usuarioActual.mostrarInfo();
        usuarioActual.accionEspecial();
    }

    private void cerrarSesion() {
        if (usuarioActual == null) {
            System.out.println("No hay ninguna sesión activa.");
        } else {
            System.out.println("Sesión de " + usuarioActual.getEmail() + " cerrada.");
            usuarioActual = null;
        }
    }

    private void registrarAdmin() throws DatosInvalidosException {
        System.out.println("=== Registro de administrador ===");
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Apellido: ");
        String apellido = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Contraseña: ");
        String password = scanner.nextLine();
        System.out.print("País: ");
        String pais = scanner.nextLine();

        gestor.registrarAdmin(nombre, apellido, email, pais, password);
        System.out.println("Administrador registrado correctamente.");
    }

    private void registrarTester() throws DatosInvalidosException {
        if (!(usuarioActual instanceof Admin)) {
            System.out.println("Solo un administrador con sesión iniciada puede dar de alta testers.");
            return;
        }

        System.out.println("=== Alta de usuario tester ===");
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Apellido: ");
        String apellido = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Contraseña: ");
        String password = scanner.nextLine();
        System.out.print("País: ");
        String pais = scanner.nextLine();
        String nivel = elegirNivelTester();

        gestor.registrarTester(nombre, apellido, email, pais, password, nivel);
        System.out.println("Tester registrado correctamente.");
    }

    private String elegirNivelTester() {
        int opcion;
        do {
            System.out.println("Nivel de tester: 1. Junior  2. Senior  3. Líder");
            System.out.print("Seleccione: ");
            opcion = leerOpcion();
        } while (opcion < 1 || opcion > 3);

        return switch (opcion) {
            case 1 -> "Junior";
            case 2 -> "Senior";
            default -> "Líder";
        };
    }

    private void listarUsuarios() {
        System.out.println("=== Lista de usuarios ===");
        gestor.listarUsuarios().forEach(Usuario::mostrarInfo);
    }

    private void buscarUsuario() throws UsuarioNoEncontradoException {
        System.out.print("Ingrese email a buscar: ");
        String email = scanner.nextLine();
        Usuario usuario = gestor.buscarPorEmail(email);
        System.out.println("Usuario encontrado:");
        usuario.mostrarInfo();
    }
}