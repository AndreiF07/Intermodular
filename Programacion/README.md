# Masa y Brasa

Aplicación de escritorio para gestionar las reservas del restaurante Masa y Brasa.
Hecha en Java con JavaFX y conectada a una base de datos MySQL.

## Qué hace

Permite a los clientes registrarse, iniciar sesión y reservar mesa para una
fecha y hora. También hay un usuario administrador que puede ver las reservas,
las mesas y los empleados del restaurante.

## Funcionalidades

- Registro de clientes
- Inicio de sesión con correo y teléfono
- Reservar mesa indicando fecha, hora y número de personas
- Asignación automática de un camarero a cada reserva
- Ver y cancelar las reservas propias
- Vista de administrador para consultar reservas, mesas y empleados

## Entidades

- Usuario
- Empleado
- Mesa
- Reserva
- ReservaMesa

## Base de datos

Toda la información se guarda en una base de datos MySQL llamada masaybrasa.
Las consultas SQL están en el paquete dao/, que es la única parte de la
aplicación que habla con la base de datos.

## Cómo ponerla en funcionamiento

- Arrancar MySQL en XAMPP.
- Ejecutar los scripts .sql del módulo de Bases de datos
- Abrir el proyecto en IntelliJ y ejecutar la clase Main.

Datos de conexión por defecto: localhost, puerto 3306, usuario root, sin
contraseña.