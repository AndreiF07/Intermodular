package org.example.masaybrasa.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.example.masaybrasa.Main;
import org.example.masaybrasa.Session;
import org.example.masaybrasa.dao.UsuarioDAO;
import org.example.masaybrasa.model.Usuario;

import java.io.IOException;
import java.sql.SQLException;

public class LoginController {

    @FXML private TextField correoField;
    @FXML private TextField telefonoField;
    @FXML private Label errorLabel;

    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    @FXML
    private void onLogin() {
        errorLabel.setText("");

        String correo = correoField.getText();
        String telefonoTxt = telefonoField.getText();

        if (correo == null || correo.trim().isEmpty()
                || telefonoTxt == null || telefonoTxt.trim().isEmpty()) {
            errorLabel.setText("Completa correo y telefono");
            return;
        }

        long telefono;
        try {
            telefono = Long.parseLong(telefonoTxt.trim());
        } catch (NumberFormatException ex) {
            errorLabel.setText("El telefono debe ser numerico");
            return;
        }

        try {
            Usuario u = usuarioDAO.findByCorreoYTelefono(correo.trim(), telefono);
            if (u == null) {
                errorLabel.setText("Credenciales invalidas");
                return;
            }
            Session.setUsuarioActual(u);
            if (u.getRol() != null && u.getRol().equalsIgnoreCase("admin")) {
                Main.loadView("admin-home-view.fxml");
            } else {
                Main.loadView("user-home-view.fxml");
            }
        } catch (SQLException ex) {
            mostrarError("Error de base de datos: " + ex.getMessage());
        } catch (IOException ex) {
            mostrarError("Error abriendo la vista: " + ex.getMessage());
        }
    }

    @FXML
    private void onIrRegistro() {
        try {
            Main.loadView("register-view.fxml");
        } catch (IOException ex) {
            mostrarError(ex.getMessage());
        }
    }

    private void mostrarError(String msg) {
        Alert a = new Alert(Alert.AlertType.ERROR, msg);
        a.showAndWait();
    }
}
