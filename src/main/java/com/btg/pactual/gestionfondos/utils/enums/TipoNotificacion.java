package com.btg.pactual.gestionfondos.utils.enums;


import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
public enum TipoNotificacion {
    EMAIL("Email"),
    SMS("SMS");

    private final String descripcion;

}
