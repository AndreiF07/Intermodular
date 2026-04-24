package org.example.masaybrasa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    private int idUsuario;
    private String nombre;
    private String apellido;
    private String rol;
    private String correo;
    private long telefono;
}
