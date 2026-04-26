# Presentación del proyecto

## Qué es

Masa y Brasa es una aplicación de escritorio para gestionar las reservas de
un restaurante. Los clientes pueden registrarse, iniciar sesión y reservar
mesa, y el restaurante puede consultar todas las reservas, las mesas y los
empleados desde un panel de administrador.

## Qué problema resuelve

Muchos restaurantes pequeños siguen apuntando las reservas en una libreta o
en hojas de papel, con el riesgo de perderlas, duplicar mesas o no saber
qué camarero atiende cada reserva. Esta aplicación centraliza todo en un
único sitio: las reservas quedan guardadas en una base de datos, no se
pueden duplicar mesas a la misma hora y se asigna automáticamente un
camarero a cada reserva.

## Para quién está pensado

- Restaurantes pequeños o medianos que quieren llevar sus reservas de forma
  ordenada sin pagar una herramienta cara.
- El encargado o el personal de sala, que la usa día a día.
- Los clientes habituales, que se registran y hacen sus reservas ellos
  mismos desde la aplicación.

## Tecnologías utilizadas

- Java 23 como lenguaje principal.
- JavaFX para la interfaz de escritorio.
- FXML para definir las vistas.
- MySQL como base de datos, servida con XAMPP.
- JDBC para la conexión con la base de datos.
- Lombok para reducir código repetitivo en los modelos.
- Maven como gestor de dependencias.
- IntelliJ IDEA como entorno de desarrollo.

## Qué sé hacer gracias a este proyecto

- Diseñar una aplicación de escritorio completa con JavaFX y FXML.
- Aplicar el patrón MVC, separando vistas, controladores y modelos.
- Aplicar el patrón DAO, dejando todo el SQL aislado en su propio paquete.
- Diseñar una base de datos relacional con varias tablas relacionadas
  (usuarios, empleados, mesas, reservas).
- Conectar una aplicación Java con MySQL usando JDBC.
- Gestionar dependencias con Maven.
- Trabajar con Lombok para escribir código más limpio.
- Pensar en el usuario final: validaciones, mensajes de error claros y un
  flujo sencillo para registrarse y reservar.
