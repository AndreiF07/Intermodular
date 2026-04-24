package org.example.masaybrasa.dao;

import org.example.masaybrasa.database.DatabaseConnection;
import org.example.masaybrasa.model.Empleado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoDAO {

    public Empleado findCamareroParaFecha(LocalDate fecha) throws SQLException {
        // Devuelve el camarero con menos reservas activas ese dia.
        // Si hay alguno con 0 reservas (libre), saldra el primero.
        String sql = "SELECT e.id_empleado, e.nombre, e.apellido, e.dni, e.correo, " +
                "e.telefono, e.desempeno, " +
                "( SELECT COUNT(*) FROM Reserva_mesa rm " +
                "  JOIN Reserva r ON r.id_reserva = rm.id_reserva " +
                "  WHERE rm.id_empleado = e.id_empleado " +
                "    AND DATE(r.fecha) = ? " +
                "    AND r.estado = 'activa' ) AS cantidad " +
                "FROM Empleado e " +
                "WHERE e.desempeno = 'camarero' " +
                "ORDER BY cantidad ASC, e.id_empleado ASC " +
                "LIMIT 1";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setDate(1, java.sql.Date.valueOf(fecha));
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
                return null;
            }
        }
    }

    public List<Empleado> findAll() throws SQLException {
        String sql = "SELECT id_empleado, nombre, apellido, dni, correo, telefono, desempeno " +
                "FROM Empleado ORDER BY id_empleado";
        List<Empleado> lista = new ArrayList<>();
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(mapRow(rs));
            }
        }
        return lista;
    }

    public Empleado findById(int id) throws SQLException {
        String sql = "SELECT id_empleado, nombre, apellido, dni, correo, telefono, desempeno " +
                "FROM Empleado WHERE id_empleado = ?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
                return null;
            }
        }
    }

    public int insert(Empleado e) throws SQLException {
        String sql = "INSERT INTO Empleado (nombre, apellido, dni, correo, telefono, desempeno) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, e.getNombre());
            ps.setString(2, e.getApellido());
            ps.setString(3, e.getDni());
            ps.setString(4, e.getCorreo());
            ps.setInt(5, e.getTelefono());
            ps.setString(6, e.getDesempeno());
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    return keys.getInt(1);
                }
            }
            return -1;
        }
    }

    public void update(Empleado e) throws SQLException {
        String sql = "UPDATE Empleado SET nombre = ?, apellido = ?, dni = ?, correo = ?, " +
                "telefono = ?, desempeno = ? WHERE id_empleado = ?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, e.getNombre());
            ps.setString(2, e.getApellido());
            ps.setString(3, e.getDni());
            ps.setString(4, e.getCorreo());
            ps.setInt(5, e.getTelefono());
            ps.setString(6, e.getDesempeno());
            ps.setInt(7, e.getIdEmpleado());
            ps.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM Empleado WHERE id_empleado = ?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    private Empleado mapRow(ResultSet rs) throws SQLException {
        return new Empleado(
                rs.getInt("id_empleado"),
                rs.getString("nombre"),
                rs.getString("apellido"),
                rs.getString("dni"),
                rs.getString("correo"),
                rs.getInt("telefono"),
                rs.getString("desempeno")
        );
    }
}
