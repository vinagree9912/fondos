package com.btg.pactual.gestionfondos.service.implementation;

import com.btg.pactual.gestionfondos.model.Suscripcion;
import com.btg.pactual.gestionfondos.model.Transaccion;
import com.btg.pactual.gestionfondos.repository.TransaccionRepository;
import com.btg.pactual.gestionfondos.service.TransaccionService;
import com.btg.pactual.gestionfondos.utils.Utils;
import com.btg.pactual.gestionfondos.utils.enums.TipoTransaccion;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Slf4j
@Service
@AllArgsConstructor
public class TransaccionServiceImpl implements TransaccionService {

    private final TransaccionRepository transaccionRepository;

    @Override
    public void guardarTransaccion(Suscripcion suscripcion, TipoTransaccion tipoTransaccion) {
        Transaccion transaccion = new Transaccion();
        transaccion.setId(Utils.generateId());
        transaccion.setClienteId(suscripcion.getClienteId());
        transaccion.setFondoId(suscripcion.getFondoId());
        transaccion.setMonto(suscripcion.getMonto());
        transaccion.setTipo(tipoTransaccion);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String fechaActual = LocalDateTime.now().format(formatter);
        transaccion.setFecha(LocalDateTime.parse(fechaActual));
        transaccionRepository.save(transaccion);
    }
    /**
     * Método para obtener el historial de transacciones de un cliente.
     *
     * @param clienteId El ID del cliente del que se quiere obtener el historial.
     * @return Lista de transacciones asociadas al cliente.
     */
    @Override
    public List<Transaccion> obtenerHistorialTransacciones(String clienteId) {
        return transaccionRepository.findByClienteId(clienteId);
    }
}
