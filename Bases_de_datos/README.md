# Bases de datos
La empresa para la que está hecha la base de datos y el proyecto es un restaurante "Masa & Brasa".

Se dispone de las siguientes entidades :
- Usuarios (id_usuario (PK), nombre, apellido, rol, correo, telefono). Son los perfiles que utilizan la aplicación del restaurante, ya sea para hacer reservas, si se tiene rol de user, o hacer más funciones internas, como cancelar o modificar reservas, si es admin.

- Reservas (id_reserva (PK), id_usuario(FK), fecha, estado). Son las reservas que crean los usuarios a traves de la aplicación. 

- Mesas (id_mesa(PK), cantidad_personas). Son las mesas disponibles en el restaurante.

- Reservas_mesas (id_reserva(PK, FK), id_mesa(PK, FK), id_empleado). Tabla intermedia entre Reservas y Mesas, para permitir la alocación de ciertas mesas a: reservas y al camarero.

- Empleados (id_empleado (PK), nombre, apellido, dni, correo, telefono, desempeño). Son los trabajadores del restaurante, pudiendo ser camameros, cocineros, personal de limpieza, o gerente.

Relaciones entre entidades
Un usuario puede realizar muchas reservas
- 1:N entre Usuario y Reserva

Una reserva pertenece a un único usuario
- N:1 hacia Usuario

Una reserva puede estar asociada a una o varias mesas
- N:M entre Reserva y Mesa (resuelta con Reserva_mesa)

Una mesa puede aparecer en muchas reservas
- N:M con Reserva

Un empleado puede gestionar varias asignaciones de reservas
- 1:N con Reserva_mesa

En este módulo hay dos directorios, uno, con los diagramas, tanto el Entidad / Relación, como el Modelo relacional, de todas las tablas / entidades de esta base de datos.