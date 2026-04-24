package org.example.masaybrasa.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.example.masaybrasa.Main;
import org.example.masaybrasa.dao.UsuarioDAO;
import org.example.masaybrasa.model.Usuario;

import java.io.IOException;
import java.sql.SQLException;

public class RegisterController {

    @FXML private TextField nombreField;
    @FXML private TextField apellidoField;
    @FXML private TextField correoField;
    @FXML private TextField telefonoField;
    @FXML private Label errorLabel;

    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    @FXML
    private void onRegistrar() {
        errorLabel.setText("");

        String nombre = nombreField.getText();
        String apellido = apellidoField.getText();
        String correo = correoField.getText();
        String telefonoTxt = telefonoField.getText();

        if (nombre == null || nombre.trim().isEmpty()
                || apellido == null || apellido.trim().isEmpty()
                || correo == null || correo.trim().isEmpty()
                || telefonoTxt == null || telefonoTxt.trim().isEmpty()) {
            errorLabel.setText("Completa todos los campos");
            return;
        }

        long telefono;
        try {
            telefono = Long.parseLong(telefonoTxt.trim());
        } catch (NumberFormatException ex) {
            errorLabel.setText("El telefono debe ser numerico");
            return;
        }

        Usuario u = new Usuario();
        u.setNombre(nombre.trim());
        u.setApellido(apellido.trim());
        u.setRol("user");
        u.setCorreo(correo.trim());
        u.setTelefono(telefono);

        try {
            usuarioDAO.insert(u);
            new Alert(Alert.AlertType.INFORMATION, "Usuario registrado correctamente").showAndWait();
            Main.loadView("login-view.fxml");
        } catch (SQLException ex) {
            errorLabel.setText("No se pudo registrar: " + ex.getMessage());
        } catch (IOException ex) {
            errorLabel.setText("Error abriendo la vista: " + ex.getMessage());
        }
    }

    @FXML
    private void onVolver() {
        try {
            Main.loadView("login-view.fxml");
        } catch (IOException ex) {
            new Alert(Alert.AlertType.ERROR, ex.getMessage()).showAndWait();
        }
    }
}
