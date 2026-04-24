package org.example.masaybrasa.controller;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.example.masaybrasa.Main;
import org.example.masaybrasa.Session;
import org.example.masaybrasa.dao.ReservaDAO;
import org.example.masaybrasa.model.Reserva;
import org.example.masaybrasa.model.Usuario;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class MisReservasController {

    @FXML private TableView<Reserva> reservasTable;
    @FXML private TableColumn<Reserva, Integer> idCol;
    @FXML private TableColumn<Reserva, LocalDateTime> fechaCol;
    @FXML private TableColumn<Reserva, String> estadoCol;
    @FXML private TableColumn<Reserva, Integer> mesaCol;

    private final ReservaDAO reservaDAO = new ReservaDAO();

    @FXML
    private void initialize() {
        idCol.setCellValueFactory(c ->
                new SimpleIntegerProperty(c.getValue().getIdReserva()).asObject());
        fechaCol.setCellValueFactory(c -> new SimpleObjectProperty<>(c.getValue().getFecha()));
        estadoCol.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getEstado()));
        mesaCol.setCellValueFactory(c -> new SimpleObjectProperty<>(c.getValue().getIdMesa()));
        cargar();
    }

    private void cargar() {
        Usuario u = Session.getUsuarioActual();
        if (u == null) {
            return;
        }
        try {
            List<Reserva> lista = reservaDAO.findByUsuario(u.getIdUsuario());
            ObservableList<Reserva> data = FXCollections.observableArrayList(lista);
            reservasTable.setItems(data);
        } catch (SQLException ex) {
            error(ex.getMessage());
        }
    }

    @FXML
    private void onCancelar() {
        Reserva r = reservasTable.getSelectionModel().getSelectedItem();
        if (r == null) {
            new Alert(Alert.AlertType.WARNING, "Selecciona una reserva").showAndWait();
            return;
        }
        if ("cancelada".equalsIgnoreCase(r.getEstado())) {
            new Alert(Alert.AlertType.INFORMATION, "Esta reserva ya esta cancelada").showAndWait();
            return;
        }
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION,
                "Cancelar la reserva #" + r.getIdReserva() + "?",
                ButtonType.YES, ButtonType.NO);
        confirm.showAndWait().ifPresent(b -> {
            if (b == ButtonType.YES) {
                try {
                    reservaDAO.updateEstado(r.getIdReserva(), "cancelada");
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
            Main.loadView("user-home-view.fxml");
        } catch (IOException ex) {
            error(ex.getMessage());
        }
    }

    private void error(String msg) {
        new Alert(Alert.AlertType.ERROR, msg).showAndWait();
    }
}
