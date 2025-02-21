# JDBC Project

## Configuración

1. Instala MySQL y asegúrate de que esté en funcionamiento.
2. Crea una base de datos en MySQL.
3. Actualiza los valores en `DatabaseConfig.java` con tu URL, usuario y contraseña.

```java
// filepath: /C:/Users/slogive/dev/private/JDBC/DatabaseConfig.java
public class DatabaseConfig {
    public static final String URL = "jdbc:mysql://localhost:3306/fruverapp";
    public static final String USER = "root";
    public static final String PASSWORD = "3202648321";
}
```

## Compilación y Ejecución

### Usando un IDE

1. Importa el proyecto en tu IDE preferido (IntelliJ IDEA, Eclipse, etc.).
2. Compila y ejecuta el proyecto desde el IDE.

### Usando la línea de comandos

1. Navega al directorio del proyecto.

```sh
cd /C:/Users/slogive/dev/private/JDBC
```

2. Compila los archivos Java.

```sh
javac -d bin src/com/example/jdbc/*.java
```

3. Ejecuta el proyecto.

```sh
java -cp bin com.example.jdbc.Main
```

## Uso

Puedes utilizar las clases DAO para realizar operaciones CRUD en la base de datos. Aquí hay un ejemplo de cómo usar `UserDAO`:

```java
// filepath: /C:/Users/slogive/dev/private/JDBC/Main.java
package com.example.jdbc;

public class Main {
    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO();
        try {
            // Insertar un nuevo usuario
            User newUser = new User(0, "John Doe", "john.doe@example.com");
            userDAO.insertUser(newUser);

            // Obtener un usuario por ID
            User user = userDAO.getUser(1);
            System.out.println("User: " + user.getName() + ", " + user.getEmail());

            // Actualizar un usuario
            user.setName("Jane Doe");
            userDAO.updateUser(user);

            // Eliminar un usuario
            userDAO.deleteUser(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
```
