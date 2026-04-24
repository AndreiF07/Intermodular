package org.example.masaybrasa.controller;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.example.masaybrasa.Main;
import org.example.masaybrasa.dao.ReservaDAO;
import org.example.masaybrasa.model.Reserva;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class ReservasAdminController {

    @FXML private DatePicker fechaPicker;
    @FXML private TableView<Reserva> reservasTable;
    @FXML private TableColumn<Reserva, Integer> idCol;
    @FXML private TableColumn<Reserva, String> usuarioCol;
    @FXML private TableColumn<Reserva, LocalDateTime> fechaCol;
    @FXML private TableColumn<Reserva, String> estadoCol;
    @FXML private TableColumn<Reserva, Integer> mesaCol;

    private final ReservaDAO reservaDAO = new ReservaDAO();

    @FXML
    private void initialize() {
        idCol.setCellValueFactory(c ->
                new SimpleIntegerProperty(c.getValue().getIdReserva()).asObject());
        usuarioCol.setCellValueFactory(c -> new SimpleStringProperty(
                c.getValue().getNombreUsuario() + " " + c.getValue().getApellidoUsuario()));
        fechaCol.setCellValueFactory(c -> new SimpleObjectProperty<>(c.getValue().getFecha()));
        estadoCol.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getEstado()));
        mesaCol.setCellValueFactory(c -> new SimpleObjectProperty<>(c.getValue().getIdMesa()));

        fechaPicker.setValue(LocalDate.now());
        cargar();
    }

    @FXML
    private void onBuscar() {
        cargar();
    }

    private void cargar() {
        LocalDate fecha = fechaPicker.getValue();
        if (fecha == null) {
            reservasTable.setItems(FXCollections.observableArrayList());
            return;
        }
        try {
            List<Reserva> lista = reservaDAO.findByFecha(fecha);
            ObservableList<Reserva> data = FXCollections.observableArrayList(lista);
            reservasTable.setItems(data);
        } catch (SQLException ex) {
            error(ex.getMessage());
        }
    }

    @FXML
    private void onEditar() {
        Reserva r = reservasTable.getSelectionModel().getSelectedItem();
        if (r == null) {
            new Alert(Alert.AlertType.WARNING, "Selecciona una reserva").showAndWait();
            return;
        }
        try {
            FXMLLoader loader = new FXMLLoader(
                    Objects.requireNonNull(Main.class.getResource("reserva-form-view.fxml")));
            Parent root = loader.load();
            ReservaFormController controller = loader.getController();
            controller.setReserva(r);

            Stage dialog = new Stage();
            dialog.initOwner(Main.getPrimaryStage());
            dialog.initModality(Modality.WINDOW_MODAL);
            dialog.setTitle("Editar reserva #" + r.getIdReserva());
            dialog.setScene(new Scene(root));
            dialog.showAndWait();
            cargar();
        } catch (IOException ex) {
            error(ex.getMessage());
        }
    }

    @FXML
    private void onVolver() {
        try {
            Main.loadView("admin-home-view.fxml");
        } catch (IOException ex) {
            error(ex.getMessage());
        }
    }

    private void error(String msg) {
        new Alert(Alert.AlertType.ERROR, msg).showAndWait();
    }
}
