package org.example.masaybrasa;

import org.example.masaybrasa.model.Usuario;

public class Session {
    private static Usuario usuarioActual;

    private Session() {
    }

    public static Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public static void setUsuarioActual(Usuario u) {
        usuarioActual = u;
    }

    public static void cerrarSesion() {
        usuarioActual = null;
    }
}
