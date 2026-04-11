-- =========================
-- CREAR BASE DE DATOS
-- =========================
CREATE DATABASE IF NOT EXISTS masaybrasa;
USE masaybrasa;

-- =========================º
-- TABLA USUARIO
-- =========================
CREATE TABLE Usuario (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    apellido VARCHAR(100),
    rol ENUM('admin', 'user') DEFAULT 'user',
    correo VARCHAR(150) UNIQUE,
    telefono BIGINT UNIQUE
);

-- =========================
-- TABLA EMPLEADO
-- =========================
CREATE TABLE Empleado (
    id_empleado INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    apellido VARCHAR(100),
    dni VARCHAR(20) UNIQUE,
    correo VARCHAR(150) UNIQUE,
    telefono INT UNIQUE,
    desempeno ENUM('camarero', 'cocinero', 'limpieza', 'gerente')
);

-- =========================
-- TABLA MESA
-- =========================
CREATE TABLE Mesa (
    id_mesa INT AUTO_INCREMENT PRIMARY KEY,
    cantidad_personas INT NOT NULL
);

-- =========================
-- TABLA RESERVA
-- =========================
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

-- =========================
-- TABLA INTERMEDIA RESERVA_MESA
-- =========================
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

INSERT INTO Usuario (nombre, apellido, rol, correo, telefono) VALUES
('Andrei', 'García', 'admin', 'andrei@mail.com', 111222333),
('Lucía', 'Martín', 'user', 'lucia@mail.com', 444555666),
('Carlos', 'Sánchez', 'user', 'carlos@mail.com', 777888999);

INSERT INTO Empleado (nombre, apellido, dni, correo, telefono, desempeno) VALUES
('Mario', 'López', '12345678A', 'mario@masaybrasa.com', 123123123, 'camarero'),
('Juan', 'Garcia', '53847626C', 'juan@masaybrasa.com', 234234234, 'camarero'),
('Ana', 'Ruiz', '23456789B', 'ana@masaybrasa.com', 345345345, 'cocinero'),
('Pedro', 'Gómez', '34567890C', 'pedro@masaybrasa.com', 456456456, 'gerente');

INSERT INTO Mesa (cantidad_personas) VALUES
(2),
(4),
(4),
(6),
(8);
