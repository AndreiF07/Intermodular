package org.example.masaybrasa.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.masaybrasa.dao.MesaDAO;
import org.example.masaybrasa.dao.ReservaDAO;
import org.example.masaybrasa.dao.ReservaMesaDAO;
import org.example.masaybrasa.model.Mesa;
import org.example.masaybrasa.model.Reserva;
import org.example.masaybrasa.model.ReservaMesa;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class ReservaFormController {

    @FXML private Label tituloLabel;
    @FXML private DatePicker fechaPicker;
    @FXML private TextField horaField;
    @FXML private ChoiceBox<String> estadoChoice;
    @FXML private ChoiceBox<Mesa> mesaChoice;
    @FXML private Label errorLabel;

    private final ReservaDAO reservaDAO = new ReservaDAO();
    private final ReservaMesaDAO reservaMesaDAO = new ReservaMesaDAO();
    private final MesaDAO mesaDAO = new MesaDAO();

    private Reserva reserva;

    @FXML
    private void initialize() {
        estadoChoice.setItems(FXCollections.observableArrayList("activa", "cancelada"));
        try {
            mesaChoice.setItems(FXCollections.observableArrayList(mesaDAO.findAll()));
        } catch (SQLException ex) {
            errorLabel.setText(ex.getMessage());
        }
    }

    public void setReserva(Reserva r) {
        this.reserva = r;
        tituloLabel.setText("Reserva #" + r.getIdReserva() + " - " +
                r.getNombreUsuario() + " " + r.getApellidoUsuario());
        if (r.getFecha() != null) {
            fechaPicker.setValue(r.getFecha().toLocalDate());
            horaField.setText(r.getFecha().toLocalTime().toString());
        }
        estadoChoice.setValue(r.getEstado());

        if (r.getIdMesa() != null) {
            for (Mesa m : mesaChoice.getItems()) {
                if (m.getIdMesa() == r.getIdMesa()) {
                    mesaChoice.setValue(m);
                    break;
                }
            }
        }
    }

    @FXML
    private void onGuardar() {
        errorLabel.setText("");
        LocalDate fecha = fechaPicker.getValue();
        String horaTxt = horaField.getText() == null ? "" : horaField.getText().trim();
        String estado = estadoChoice.getValue();
        Mesa mesa = mesaChoice.getValue();

        if (fecha == null || horaTxt.isEmpty() || estado == null) {
            errorLabel.setText("Completa fecha, hora y estado");
            return;
        }
        LocalTime hora;
        try {
            hora = LocalTime.parse(horaTxt);
        } catch (Exception ex) {
            errorLabel.setText("Hora invalida (HH:mm)");
            return;
        }

        try {
            LocalDateTime nueva = fecha.atTime(hora);
            reservaDAO.updateFecha(reserva.getIdReserva(), nueva);
            reservaDAO.updateEstado(reserva.getIdReserva(), estado);

            if (mesa != null) {
                List<ReservaMesa> existente = reservaMesaDAO.findByReserva(reserva.getIdReserva());
                if (existente.isEmpty()) {
                    reservaMesaDAO.insert(reserva.getIdReserva(), mesa.getIdMesa(), null);
                } else if (existente.get(0).getIdMesa() != mesa.getIdMesa()) {
                    reservaMesaDAO.updateMesa(reserva.getIdReserva(), mesa.getIdMesa());
                }
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
        Stage stage = (Stage) fechaPicker.getScene().getWindow();
        stage.close();
    }
}
