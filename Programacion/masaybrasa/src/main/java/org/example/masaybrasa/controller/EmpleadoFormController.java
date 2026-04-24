package org.example.masaybrasa.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.masaybrasa.dao.EmpleadoDAO;
import org.example.masaybrasa.model.Empleado;

import java.sql.SQLException;

public class EmpleadoFormController {

    @FXML private TextField nombreField;
    @FXML private TextField apellidoField;
    @FXML private TextField dniField;
    @FXML private TextField correoField;
    @FXML private TextField telefonoField;
    @FXML private ChoiceBox<String> desempenoChoice;
    @FXML private Label errorLabel;

    private final EmpleadoDAO empleadoDAO = new EmpleadoDAO();
    private Empleado empleado;

    @FXML
    private void initialize() {
        desempenoChoice.setItems(FXCollections.observableArrayList(
                "camarero", "cocinero", "limpieza", "gerente"));
        desempenoChoice.setValue("camarero");
    }

    public void setEmpleado(Empleado e) {
        this.empleado = e;
        if (e != null) {
            nombreField.setText(e.getNombre());
            apellidoField.setText(e.getApellido());
            dniField.setText(e.getDni());
            correoField.setText(e.getCorreo());
            telefonoField.setText(String.valueOf(e.getTelefono()));
            desempenoChoice.setValue(e.getDesempeno());
        }
    }

    @FXML
    private void onGuardar() {
        errorLabel.setText("");
        String nombre = nombreField.getText() == null ? "" : nombreField.getText().trim();
        String apellido = apellidoField.getText() == null ? "" : apellidoField.getText().trim();
        String dni = dniField.getText() == null ? "" : dniField.getText().trim();
        String correo = correoField.getText() == null ? "" : correoField.getText().trim();
        String telefonoTxt = telefonoField.getText() == null ? "" : telefonoField.getText().trim();
        String desempeno = desempenoChoice.getValue();

        if (nombre.isEmpty() || apellido.isEmpty() || dni.isEmpty() || correo.isEmpty()
                || telefonoTxt.isEmpty() || desempeno == null) {
            errorLabel.setText("Completa todos los campos");
            return;
        }
        int telefono;
        try {
            telefono = Integer.parseInt(telefonoTxt);
        } catch (NumberFormatException ex) {
            errorLabel.setText("Telefono invalido");
            return;
        }

        try {
            if (empleado == null) {
                empleadoDAO.insert(new Empleado(0, nombre, apellido, dni, correo, telefono, desempeno));
            } else {
                empleado.setNombre(nombre);
                empleado.setApellido(apellido);
                empleado.setDni(dni);
                empleado.setCorreo(correo);
                empleado.setTelefono(telefono);
                empleado.setDesempeno(desempeno);
                empleadoDAO.update(empleado);
            }
            cerrar();
        } catch (SQLException ex) {
            errorLabel.setText("Error: " + ex.getMessage());
        }
    }

    @FXML
    private void onCancelar() {
        cerrar();
    }

    private void cerrar() {
        Stage stage = (Stage) nombreField.getScene().getWindow();
        stage.close();
    }
}
