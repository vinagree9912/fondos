package com.btg.pactual.gestionfondos.utils;

import java.util.UUID;

public class Utils {

    public static String generateId() {
        // Lógica para generar un ID único
        return UUID.randomUUID().toString();
    }
}
