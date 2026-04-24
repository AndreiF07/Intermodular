package org.example.masaybrasa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Empleado {
    private int idEmpleado;
    private String nombre;
    private String apellido;
    private String dni;
    private String correo;
    private int telefono;
    private String desempeno;
}
