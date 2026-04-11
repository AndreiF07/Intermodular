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

INSERT INTO Reserva (id_usuario, fecha, estado) VALUES
(1, '2026-04-12 14:00:00', 'activa'),
(2, '2026-04-12 15:00:00', 'activa'),
(3, '2026-04-13 20:00:00', 'cancelada'),
(1, '2026-04-14 13:30:00', 'activa');

INSERT INTO Reserva_mesa (id_reserva, id_mesa, id_empleado) VALUES
(1, 1, 1),
(1, 2, 1),
(2, 3, 2),
(3, 4, 3),
(4, 5, 1);