Diagrama UML

```mermaid
classDiagram
    class Usuario {
        <<abstract>>
        -String nombre
        -String apellido
        -String email
        -String pais
        -String password
        +getNombre() String
        +getApellido() String
        +getEmail() String
        +getPais() String
        +getPassword() String
        +setNombre(String) void
        +setApellido(String) void
        +setEmail(String) void
        +setPais(String) void
        +setPassword(String) void
        +mostrarInfo()* void
        +accionEspecial()* void
    }

    class Admin {
        +mostrarInfo() void
        +accionEspecial() void
    }

    class Tester {
        -String nivelTester
        +getNivelTester() String
        +setNivelTester(String) void
        +mostrarInfo() void
        +accionEspecial() void
    }

    class GestorUsuarios {
        <<Singleton>>
        -static GestorUsuarios instancia
        -Map~String, Usuario~ usuariosPorEmail
        -GestorUsuarios()
        +static getInstancia() GestorUsuarios
        +registrarAdmin(...) void
        +registrarTester(...) void
        +login(String, String) Usuario
        +buscarPorEmail(String) Usuario
        +listarUsuarios() Collection~Usuario~
    }

    class Validaciones {
        <<utility>>
        +static validarCampoObligatorio(String, String) void
        +static validarEmail(String) void
        +static validarPassword(String) void
    }

    class SistemaUsuarios {
        -GestorUsuarios gestor
        -Scanner scanner
        -Usuario usuarioActual
        +mostrarMenu() void
    }

    class Main {
        +static main(String[]) void
    }

    class DatosInvalidosException {
        +DatosInvalidosException(String)
    }

    class UsuarioNoEncontradoException {
        +UsuarioNoEncontradoException(String)
    }

    Usuario <|-- Admin
    Usuario <|-- Tester
    SistemaUsuarios --> GestorUsuarios : usa
    GestorUsuarios --> Validaciones : usa
    GestorUsuarios "1" --> "*" Usuario : gestiona
    Main --> SistemaUsuarios : crea
    GestorUsuarios ..> DatosInvalidosException : lanza
    GestorUsuarios ..> UsuarioNoEncontradoException : lanza
```


| Admin | yaniscorrea@gmail.com | 12345 |
| Admin | leonardoperez@gmail.com | 12345 |
| Tester | paola291187@gmail.com | Abcde1 |
