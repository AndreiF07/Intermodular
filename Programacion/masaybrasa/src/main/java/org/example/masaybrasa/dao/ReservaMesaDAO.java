package org.example.masaybrasa.dao;

import org.example.masaybrasa.database.DatabaseConnection;
import org.example.masaybrasa.model.ReservaMesa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class ReservaMesaDAO {

    public void insert(int idReserva, int idMesa, Integer idEmpleado) throws SQLException {
        String sql = "INSERT INTO Reserva_mesa (id_reserva, id_mesa, id_empleado) VALUES (?, ?, ?)";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idReserva);
            ps.setInt(2, idMesa);
            if (idEmpleado == null) {
                ps.setNull(3, Types.INTEGER);
            } else {
                ps.setInt(3, idEmpleado);
            }
            ps.executeUpdate();
        }
    }

    public void updateMesa(int idReserva, int idMesaNueva) throws SQLException {
        String sql = "UPDATE Reserva_mesa SET id_mesa = ? WHERE id_reserva = ?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idMesaNueva);
            ps.setInt(2, idReserva);
            ps.executeUpdate();
        }
    }

    public List<ReservaMesa> findByReserva(int idReserva) throws SQLException {
        String sql = "SELECT id_reserva, id_mesa, id_empleado FROM Reserva_mesa WHERE id_reserva = ?";
        List<ReservaMesa> lista = new ArrayList<>();
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idReserva);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int idEmpleado = rs.getInt("id_empleado");
                    Integer empleado = rs.wasNull() ? null : idEmpleado;
                    lista.add(new ReservaMesa(
                            rs.getInt("id_reserva"),
                            rs.getInt("id_mesa"),
                            empleado));
                }
            }
        }
        return lista;
    }
}
