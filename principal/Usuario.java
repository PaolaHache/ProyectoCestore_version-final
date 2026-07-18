package principal;

public abstract class Usuario {
    private String nombre;
    private String apellido;
    private String email;
    private String pais;
    private String password;

    public Usuario(String nombre, String apellido, String email, String pais, String password) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.pais = pais;
        this.password = password;
    }

    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public String getEmail() { return email; }
    public String getPais() { return pais; }
    public String getPassword() { return password; }

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    public void setEmail(String email) { this.email = email; }
    public void setPais(String pais) { this.pais = pais; }
    public void setPassword(String password) { this.password = password; }

    public abstract void mostrarInfo();
    public abstract void accionEspecial();
}