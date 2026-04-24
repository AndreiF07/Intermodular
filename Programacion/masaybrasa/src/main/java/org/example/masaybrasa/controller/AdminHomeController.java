package org.example.masaybrasa.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import org.example.masaybrasa.Main;
import org.example.masaybrasa.Session;
import org.example.masaybrasa.model.Usuario;

import java.io.IOException;

public class AdminHomeController {

    @FXML private Label bienvenidaLabel;

    @FXML
    private void initialize() {
        Usuario u = Session.getUsuarioActual();
        if (u != null) {
            bienvenidaLabel.setText("Administrador: " + u.getNombre() + " " + u.getApellido());
        }
    }

    @FXML
    private void onVerEmpleados() {
        cargar("empleados-view.fxml");
    }

    @FXML
    private void onVerReservas() {
        cargar("reservas-admin-view.fxml");
    }

    @FXML
    private void onCerrarSesion() {
        Session.cerrarSesion();
        cargar("login-view.fxml");
    }

    private void cargar(String fxml) {
        try {
            Main.loadView(fxml);
        } catch (IOException ex) {
            new Alert(Alert.AlertType.ERROR, ex.getMessage()).showAndWait();
        }
    }
}
