package com.btg.pactual.gestionfondos.controller;

import com.btg.pactual.gestionfondos.model.Transaccion;
import com.btg.pactual.gestionfondos.service.FondoService;
import com.btg.pactual.gestionfondos.service.TransaccionService;
import com.btg.pactual.gestionfondos.utils.Constantes;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

@RestController
@RequestMapping(Constantes.PATH_TRANSACCIONES)
@AllArgsConstructor
@CrossOrigin(
        origins = "*",
        methods = {RequestMethod.OPTIONS})
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class TransaccionController {
    private final TransaccionService transaccionService;

    /**
     * Método para obtener el historial de transacciones de un cliente específico.
     *
     * @param clienteId El ID del cliente del que se quiere obtener el historial de transacciones.
     * @return Lista de transacciones asociadas al cliente.
     */
    @GetMapping(Constantes.PATH_HISTORIAL_TRANSACCIONES)
    public List<Transaccion> obtenerHistorialTransacciones(@PathVariable String clienteId) {
        return transaccionService.obtenerHistorialTransacciones(clienteId);
    }
}
