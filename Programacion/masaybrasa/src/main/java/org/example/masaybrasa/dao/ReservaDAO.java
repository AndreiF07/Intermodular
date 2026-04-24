package org.example.masaybrasa.dao;

import org.example.masaybrasa.database.DatabaseConnection;
import org.example.masaybrasa.model.Reserva;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReservaDAO {

    public int insert(int idUsuario, LocalDateTime fecha, String estado) throws SQLException {
        String sql = "INSERT INTO Reserva (id_usuario, fecha, estado) VALUES (?, ?, ?)";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, idUsuario);
            ps.setTimestamp(2, Timestamp.valueOf(fecha));
            ps.setString(3, estado == null ? "activa" : estado);
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    return keys.getInt(1);
                }
            }
            return -1;
        }
    }

    public List<Reserva> findByUsuario(int idUsuario) throws SQLException {
        String sql = "SELECT r.id_reserva, r.id_usuario, r.fecha, r.estado, " +
                "u.nombre, u.apellido, rm.id_mesa " +
                "FROM Reserva r " +
                "JOIN Usuario u ON u.id_usuario = r.id_usuario " +
                "LEFT JOIN Reserva_mesa rm ON rm.id_reserva = r.id_reserva " +
                "WHERE r.id_usuario = ? " +
                "ORDER BY r.fecha DESC";
        List<Reserva> lista = new ArrayList<>();
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idUsuario);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapRow(rs));
                }
            }
        }
        return lista;
    }

    public List<Reserva> findByFecha(LocalDate fecha) throws SQLException {
        String sql = "SELECT r.id_reserva, r.id_usuario, r.fecha, r.estado, " +
                "u.nombre, u.apellido, rm.id_mesa " +
                "FROM Reserva r " +
                "JOIN Usuario u ON u.id_usuario = r.id_usuario " +
                "LEFT JOIN Reserva_mesa rm ON rm.id_reserva = r.id_reserva " +
                "WHERE DATE(r.fecha) = ? " +
                "ORDER BY r.fecha";
        List<Reserva> lista = new ArrayList<>();
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setDate(1, Date.valueOf(fecha));
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapRow(rs));
                }
            }
        }
        return lista;
    }

    public void updateEstado(int idReserva, String estado) throws SQLException {
        String sql = "UPDATE Reserva SET estado = ? WHERE id_reserva = ?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, estado);
            ps.setInt(2, idReserva);
            ps.executeUpdate();
        }
    }

    public void updateFecha(int idReserva, LocalDateTime fecha) throws SQLException {
        String sql = "UPDATE Reserva SET fecha = ? WHERE id_reserva = ?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setTimestamp(1, Timestamp.valueOf(fecha));
            ps.setInt(2, idReserva);
            ps.executeUpdate();
        }
    }

    private Reserva mapRow(ResultSet rs) throws SQLException {
        Reserva r = new Reserva();
        r.setIdReserva(rs.getInt("id_reserva"));
        r.setIdUsuario(rs.getInt("id_usuario"));
        Timestamp ts = rs.getTimestamp("fecha");
        r.setFecha(ts == null ? null : ts.toLocalDateTime());
        r.setEstado(rs.getString("estado"));
        r.setNombreUsuario(rs.getString("nombre"));
        r.setApellidoUsuario(rs.getString("apellido"));
        int idMesa = rs.getInt("id_mesa");
        r.setIdMesa(rs.wasNull() ? null : idMesa);
        return r;
    }
}
