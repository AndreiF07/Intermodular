# Portfolio

## Sobre mí

Estudiante de Desarrollo de Aplicaciones Web. Este es uno de
los proyectos que he hecho durante el curso.

## Proyecto: Masa y Brasa

Aplicación de escritorio para gestionar las reservas de un restaurante.
Hecha en Java con JavaFX y conectada a una base de datos MySQL.

### Capturas

- Pantalla de login: capturas/login.png
- Registro de usuario: capturas/registro.png
- Reservar mesa: capturas/reservarMesa.png
- Mis reservas: capturas/misReservas.png
- Vista de administrador: capturas/panelDeAdmin.png

### Explicación

La aplicación permite a los clientes registrarse, iniciar sesión con su
correo y teléfono y reservar mesa para una fecha y hora. Cada reserva se
asigna automáticamente al camarero con menos reservas ese día. El usuario
administrador puede consultar todas las reservas, las mesas y los empleados
del restaurante.

El proyecto está organizado siguiendo el patrón MVC (modelos, vistas y
controladores) y el patrón DAO (todo el SQL aislado en su propio paquete).

### Enlace al repositorio

- GitHub: https://github.com/AndreiF07/Intermodular/tree/main

### Qué he aprendido

- Crear una aplicación de escritorio completa con JavaFX y FXML.
- Aplicar el patrón MVC y separar bien las responsabilidades.
- Aplicar el patrón DAO para que todo el SQL esté en un solo sitio.
- Diseñar una base de datos relacional y conectarla con Java mediante JDBC.
- Gestionar dependencias con Maven.
- Usar Lombok para escribir modelos más limpios.
- Pensar en el usuario final: validaciones, mensajes de error claros y un
  flujo sencillo para registrarse y reservar.
