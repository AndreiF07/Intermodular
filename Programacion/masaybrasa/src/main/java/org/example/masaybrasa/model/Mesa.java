package org.example.masaybrasa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mesa {
    private int idMesa;
    private int cantidadPersonas;

    @Override
    public String toString() {
        return "Mesa " + idMesa + " (" + cantidadPersonas + " personas)";
    }
}
