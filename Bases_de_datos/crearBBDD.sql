CREATE DATABASE IF NOT EXISTS masaybrasa;
USE masaybrasa;

CREATE TABLE Usuario (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    apellido VARCHAR(100),
    rol ENUM('admin', 'user') DEFAULT 'user',
    correo VARCHAR(150) UNIQUE,
    telefono BIGINT UNIQUE
);

CREATE TABLE Empleado (
    id_empleado INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    apellido VARCHAR(100),
    dni VARCHAR(20) UNIQUE,
    correo VARCHAR(150) UNIQUE,
    telefono INT UNIQUE,
    desempeno ENUM('camarero', 'cocinero', 'limpieza', 'gerente')
);


CREATE TABLE Mesa (
    id_mesa INT AUTO_INCREMENT PRIMARY KEY,
    cantidad_personas INT NOT NULL
);


CREATE TABLE Reserva (
    id_reserva INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT,
    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    estado ENUM('activa', 'cancelada') DEFAULT 'activa',

    CONSTRAINT fk_reserva_usuario
        FOREIGN KEY (id_usuario)
        REFERENCES Usuario(id_usuario)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE Reserva_mesa (
    id_reserva INT,
    id_mesa INT,
    id_empleado INT,

    PRIMARY KEY (id_reserva, id_mesa),

    CONSTRAINT fk_rm_reserva
        FOREIGN KEY (id_reserva)
        REFERENCES Reserva(id_reserva)
        ON DELETE CASCADE
        ON UPDATE CASCADE,

    CONSTRAINT fk_rm_mesa
        FOREIGN KEY (id_mesa)
        REFERENCES Mesa(id_mesa)
        ON DELETE CASCADE
        ON UPDATE CASCADE,

    CONSTRAINT fk_rm_empleado
        FOREIGN KEY (id_empleado)
        REFERENCES Empleado(id_empleado)
        ON DELETE SET NULL
        ON UPDATE CASCADE
);