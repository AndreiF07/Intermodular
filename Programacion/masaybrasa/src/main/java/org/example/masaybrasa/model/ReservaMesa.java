package org.example.masaybrasa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservaMesa {
    private int idReserva;
    private int idMesa;
    private Integer idEmpleado;
}
