package org.example.masaybrasa.dao;

import org.example.masaybrasa.database.DatabaseConnection;
import org.example.masaybrasa.model.Mesa;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MesaDAO {

    public List<Mesa> findAll() throws SQLException {
        String sql = "SELECT id_mesa, cantidad_personas FROM Mesa ORDER BY id_mesa";
        List<Mesa> lista = new ArrayList<>();
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(mapRow(rs));
            }
        }
        return lista;
    }

    public List<Mesa> findDisponiblesPorFecha(LocalDate fecha) throws SQLException {
        String sql = "SELECT m.id_mesa, m.cantidad_personas " +
                "FROM Mesa m " +
                "WHERE m.id_mesa NOT IN ( " +
                "   SELECT rm.id_mesa FROM Reserva_mesa rm " +
                "   JOIN Reserva r ON r.id_reserva = rm.id_reserva " +
                "   WHERE DATE(r.fecha) = ? AND r.estado = 'activa' " +
                ") " +
                "AND m.id_mesa NOT IN ( " +
                "   SELECT rm2.id_mesa FROM Reserva_mesa rm2 " +
                "   JOIN Reserva r2 ON r2.id_reserva = rm2.id_reserva " +
                "   WHERE DATE(r2.fecha) = ? " +
                ") " +
                "ORDER BY m.id_mesa";
        List<Mesa> lista = new ArrayList<>();
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setDate(1, Date.valueOf(fecha));
            ps.setDate(2, Date.valueOf(fecha));
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapRow(rs));
                }
            }
        }
        return lista;
    }

    private Mesa mapRow(ResultSet rs) throws SQLException {
        return new Mesa(rs.getInt("id_mesa"), rs.getInt("cantidad_personas"));
    }
}
