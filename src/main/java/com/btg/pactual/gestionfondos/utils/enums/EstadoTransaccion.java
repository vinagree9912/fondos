package com.btg.pactual.gestionfondos.utils.enums;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EstadoTransaccion {
    ACTIVO("Activo"),
    CANCELADO("Cancelado");

    private final String descripcion; // Descripción legible de la transacción

    // Método opcional para obtener la descripción legible
    @Override
    public String toString() {
        return descripcion;
    }
}
