package org.example.masaybrasa.controller;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.example.masaybrasa.Main;
import org.example.masaybrasa.dao.EmpleadoDAO;
import org.example.masaybrasa.model.Empleado;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class EmpleadosController {

    @FXML private TableView<Empleado> empleadosTable;
    @FXML private TableColumn<Empleado, Integer> idCol;
    @FXML private TableColumn<Empleado, String> nombreCol;
    @FXML private TableColumn<Empleado, String> apellidoCol;
    @FXML private TableColumn<Empleado, String> dniCol;
    @FXML private TableColumn<Empleado, String> correoCol;
    @FXML private TableColumn<Empleado, Integer> telefonoCol;
    @FXML private TableColumn<Empleado, String> desempenoCol;

    private final EmpleadoDAO empleadoDAO = new EmpleadoDAO();

    @FXML
    private void initialize() {
        idCol.setCellValueFactory(c ->
                new SimpleIntegerProperty(c.getValue().getIdEmpleado()).asObject());
        nombreCol.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        apellidoCol.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        dniCol.setCellValueFactory(new PropertyValueFactory<>("dni"));
        correoCol.setCellValueFactory(new PropertyValueFactory<>("correo"));
        telefonoCol.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        desempenoCol.setCellValueFactory(new PropertyValueFactory<>("desempeno"));
        cargar();
    }

    private void cargar() {
        try {
            List<Empleado> lista = empleadoDAO.findAll();
            ObservableList<Empleado> data = FXCollections.observableArrayList(lista);
            empleadosTable.setItems(data);
        } catch (SQLException ex) {
            error(ex.getMessage());
        }
    }

    @FXML
    private void onNuevo() {
        abrirFormulario(null);
    }

    @FXML
    private void onEditar() {
        Empleado seleccionado = empleadosTable.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            new Alert(Alert.AlertType.WARNING, "Selecciona un empleado").showAndWait();
            return;
        }
        abrirFormulario(seleccionado);
    }

    @FXML
    private void onEliminar() {
        Empleado seleccionado = empleadosTable.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            new Alert(Alert.AlertType.WARNING, "Selecciona un empleado").showAndWait();
            return;
        }
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION,
                "Eliminar al empleado " + seleccionado.getNombre() + "?");
        confirm.showAndWait().ifPresent(b -> {
            if (b.getButtonData().isDefaultButton()) {
                try {
                    empleadoDAO.delete(seleccionado.getIdEmpleado());
                    cargar();
                } catch (SQLException ex) {
                    error(ex.getMessage());
                }
            }
        });
    }

    @FXML
    private void onVolver() {
        try {
            Main.loadView("admin-home-view.fxml");
        } catch (IOException ex) {
            error(ex.getMessage());
        }
    }

    private void abrirFormulario(Empleado empleado) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    Objects.requireNonNull(Main.class.getResource("empleado-form-view.fxml")));
            Parent root = loader.load();
            EmpleadoFormController controller = loader.getController();
            controller.setEmpleado(empleado);

            Stage dialog = new Stage();
            dialog.initOwner(Main.getPrimaryStage());
            dialog.initModality(Modality.WINDOW_MODAL);
            dialog.setTitle(empleado == null ? "Nuevo empleado" : "Editar empleado");
            dialog.setScene(new Scene(root));
            dialog.showAndWait();

            cargar();
        } catch (IOException ex) {
            error(ex.getMessage());
        }
    }

    private void error(String msg) {
        new Alert(Alert.AlertType.ERROR, msg).showAndWait();
    }
}
