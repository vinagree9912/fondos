package com.btg.pactual.gestionfondos.service;

import com.btg.pactual.gestionfondos.model.Suscripcion;
import com.btg.pactual.gestionfondos.model.Transaccion;
import com.btg.pactual.gestionfondos.utils.enums.TipoTransaccion;

import java.util.List;

public interface TransaccionService {
    void guardarTransaccion(Suscripcion suscripcion, TipoTransaccion tipoTransaccion);
    List<Transaccion> obtenerHistorialTransacciones(String clienteId);
}
