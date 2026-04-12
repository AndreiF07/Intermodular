-- Ver reservas de un usuario
SELECT 
    r.id_reserva,
    r.fecha,
    r.estado
FROM Reserva r
WHERE r.id_usuario = 1;

-- Mesas disponibles en una fecha
SELECT m.*
FROM Mesa m
WHERE m.id_mesa NOT IN (
    SELECT rm.id_mesa
    FROM Reserva_mesa rm
    JOIN Reserva r ON rm.id_reserva = r.id_reserva
    WHERE r.fecha = '2026-04-12 14:00:00'
    AND r.estado = 'activa'
);

-- Datos del usuario 
SELECT 
    u.nombre,
    u.apellido,
    r.id_reserva,
    r.fecha,
    r.estado
FROM Usuario u
JOIN Reserva r ON u.id_usuario = r.id_usuario;


-- Detalle de una reserva
SELECT 
    u.nombre AS cliente,
    r.id_reserva,
    r.fecha,
    r.estado,
    m.id_mesa,
    m.cantidad_personas,
    e.nombre AS empleado,
    e.desempeno
FROM Reserva r
JOIN Usuario u ON r.id_usuario = u.id_usuario
JOIN Reserva_mesa rm ON r.id_reserva = rm.id_reserva
JOIN Mesa m ON rm.id_mesa = m.id_mesa
JOIN Empleado e ON rm.id_empleado = e.id_empleado
WHERE r.id_reserva = 1;

-- Reservas de hoy
SELECT 
    r.id_reserva,
    u.nombre AS cliente,
    r.fecha,
    r.estado
FROM Reserva r
JOIN Usuario u ON r.id_usuario = u.id_usuario
WHERE DATE(r.fecha) = '2026-04-12'
ORDER BY r.fecha;