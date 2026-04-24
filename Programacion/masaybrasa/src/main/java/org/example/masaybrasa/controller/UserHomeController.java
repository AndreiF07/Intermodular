package org.example.masaybrasa.controller;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.masaybrasa.Main;
import org.example.masaybrasa.Session;
import org.example.masaybrasa.dao.EmpleadoDAO;
import org.example.masaybrasa.dao.MesaDAO;
import org.example.masaybrasa.dao.ReservaDAO;
import org.example.masaybrasa.dao.ReservaMesaDAO;
import org.example.masaybrasa.model.Empleado;
import org.example.masaybrasa.model.Mesa;
import org.example.masaybrasa.model.Usuario;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class UserHomeController {

    @FXML private Label bienvenidaLabel;
    @FXML private DatePicker fechaPicker;
    @FXML private TableView<Mesa> mesasTable;
    @FXML private TableColumn<Mesa, Integer> idCol;
    @FXML private TableColumn<Mesa, Integer> personasCol;
    @FXML private Button reservarBtn;
    @FXML private Label statusLabel;

    private final MesaDAO mesaDAO = new MesaDAO();
    private final ReservaDAO reservaDAO = new ReservaDAO();
    private final ReservaMesaDAO reservaMesaDAO = new ReservaMesaDAO();
    private final EmpleadoDAO empleadoDAO = new EmpleadoDAO();

    @FXML
    private void initialize() {
        Usuario u = Session.getUsuarioActual();
        if (u != null) {
            bienvenidaLabel.setText("Hola, " + u.getNombre() + " " + u.getApellido());
        }
        idCol.setCellValueFactory(c ->
                new SimpleIntegerProperty(c.getValue().getIdMesa()).asObject());
        personasCol.setCellValueFactory(new PropertyValueFactory<>("cantidadPersonas"));
        fechaPicker.setValue(LocalDate.now());
        cargarMesas();
    }

    @FXML
    private void onBuscar() {
        cargarMesas();
    }

    private void cargarMesas() {
        statusLabel.setText("");
        LocalDate fecha = fechaPicker.getValue();
        if (fecha == null) {
            mesasTable.setItems(FXCollections.observableArrayList());
            return;
        }
        try {
            List<Mesa> mesas = mesaDAO.findDisponiblesPorFecha(fecha);
            ObservableList<Mesa> data = FXCollections.observableArrayList(mesas);
            mesasTable.setItems(data);
            if (mesas.isEmpty()) {
                statusLabel.setText("No hay mesas disponibles para esa fecha");
            }
        } catch (SQLException ex) {
            error(ex.getMessage());
        }
    }

    @FXML
    private void onReservar() {
        Mesa mesa = mesasTable.getSelectionModel().getSelectedItem();
        LocalDate fecha = fechaPicker.getValue();
        Usuario usuario = Session.getUsuarioActual();

        if (mesa == null) {
            statusLabel.setText("Selecciona una mesa");
            return;
        }
        if (fecha == null || usuario == null) {
            statusLabel.setText("Datos incompletos");
            return;
        }

        try {
            // 1. Buscar un camarero. Primero uno libre ese dia,
            //    y si no hay ninguno libre, el que tenga menos reservas.
            Empleado camarero = empleadoDAO.findCamareroParaFecha(fecha);
            if (camarero == null) {
                statusLabel.setText("No hay camareros dados de alta");
                return;
            }

            // 2. Crear la reserva.
            LocalDateTime fechaHora = fecha.atTime(LocalTime.of(20, 0));
            int idReserva = reservaDAO.insert(usuario.getIdUsuario(), fechaHora, "activa");

            // 3. Asociar la mesa y el camarero a la reserva.
            reservaMesaDAO.insert(idReserva, mesa.getIdMesa(), camarero.getIdEmpleado());

            String msg = "Reserva creada para la mesa " + mesa.getIdMesa()
                    + " el " + fecha
                    + ". Camarero asignado: "
                    + camarero.getNombre() + " " + camarero.getApellido();
            new Alert(Alert.AlertType.INFORMATION, msg).showAndWait();

            cargarMesas();
        } catch (SQLException ex) {
            error(ex.getMessage());
        }
    }

    @FXML
    private void onMisReservas() {
        try {
            Main.loadView("mis-reservas-view.fxml");
        } catch (IOException ex) {
            error(ex.getMessage());
        }
    }

    @FXML
    private void onCerrarSesion() {
        Session.cerrarSesion();
        try {
            Main.loadView("login-view.fxml");
        } catch (IOException ex) {
            error(ex.getMessage());
        }
    }

    private void error(String msg) {
        Alert a = new Alert(Alert.AlertType.ERROR, msg);
        a.showAndWait();
    }
}
