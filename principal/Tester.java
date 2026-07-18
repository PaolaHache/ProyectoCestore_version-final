package principal;

public class Tester extends Usuario {

    private String nivelTester; // Junior, Senior o Líder

    public Tester(String nombre, String apellido, String email, String pais, String password) {
        super(nombre, apellido, email, pais, password);
        this.nivelTester = "Junior";
    }

    public String getNivelTester() { return nivelTester; }
    public void setNivelTester(String nivelTester) { this.nivelTester = nivelTester; }

    @Override
    public void mostrarInfo() {
        System.out.println("TESTER: " + getNombre() + " " + getApellido() + " - " + getEmail() + " | Nivel: " + nivelTester);
    }

    @Override
    public void accionEspecial() {
        System.out.println("Ejecutando pruebas y reportando bugs...");
    }
}


