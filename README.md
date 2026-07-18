Sistema de Usuarios – Cestore

Proyecto final implementado en Java simula un sistema de gestión de usuarios (administradores y testers).

Funcionalidades

Iniciar sesión: autenticación por email y contraseña.
Registrar administrador: alta de un nuevo usuario Admin.
Alta usuario tester: alta de un nuevo usuario Tester (requiere sesión de administrador activa), con selección de nivel (Junior, Senior o Líder).
Listar usuarios: muestra todos los usuarios registrados en el sistema.
Buscar usuario por email: búsqueda puntual de un usuario.
Cerrar sesión: finaliza la sesión activa.
Salir: termina la ejecución del programa.


Todas las operaciones de alta validan campos obligatorios, formato de email, longitud mínima de contraseña y emails duplicados, lanzando excepciones propias (DatosInvalidosException, UsuarioNoEncontradoException) cuando corresponde.

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
