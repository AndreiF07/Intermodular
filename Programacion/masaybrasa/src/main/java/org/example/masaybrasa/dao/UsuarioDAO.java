package org.example.masaybrasa.dao;

import org.example.masaybrasa.database.DatabaseConnection;
import org.example.masaybrasa.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UsuarioDAO {

    public Usuario findByCorreo(String correo) throws SQLException {
        String sql = "SELECT id_usuario, nombre, apellido, rol, correo, telefono " +
                "FROM Usuario WHERE correo = ?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, correo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
                return null;
            }
        }
    }

    public Usuario findByCorreoYTelefono(String correo, long telefono) throws SQLException {
        String sql = "SELECT id_usuario, nombre, apellido, rol, correo, telefono " +
                "FROM Usuario WHERE correo = ? AND telefono = ?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, correo);
            ps.setLong(2, telefono);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
                return null;
            }
        }
    }

    public int insert(Usuario u) throws SQLException {
        String sql = "INSERT INTO Usuario (nombre, apellido, rol, correo, telefono) " +
                "VALUES (?, ?, ?, ?, ?)";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, u.getNombre());
            ps.setString(2, u.getApellido());
            ps.setString(3, u.getRol() == null ? "user" : u.getRol());
            ps.setString(4, u.getCorreo());
            ps.setLong(5, u.getTelefono());
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    return keys.getInt(1);
                }
            }
            return -1;
        }
    }

    private Usuario mapRow(ResultSet rs) throws SQLException {
        Usuario u = new Usuario();
        u.setIdUsuario(rs.getInt("id_usuario"));
        u.setNombre(rs.getString("nombre"));
        u.setApellido(rs.getString("apellido"));
        u.setRol(rs.getString("rol"));
        u.setCorreo(rs.getString("correo"));
        u.setTelefono(rs.getLong("telefono"));
        return u;
    }
}
