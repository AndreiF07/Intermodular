package org.example.masaybrasa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reserva {
    private int idReserva;
    private int idUsuario;
    private LocalDateTime fecha;
    private String estado;

    private String nombreUsuario;
    private String apellidoUsuario;
    private Integer idMesa;
}
