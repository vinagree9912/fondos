package com.btg.pactual.gestionfondos.service.implementation;

import com.btg.pactual.gestionfondos.dto.SuscripcionRequestDTO;
import com.btg.pactual.gestionfondos.exception.BusinessException;
import com.btg.pactual.gestionfondos.model.Cliente;
import com.btg.pactual.gestionfondos.model.Fondo;
import com.btg.pactual.gestionfondos.model.Suscripcion;
import com.btg.pactual.gestionfondos.repository.ClienteRepository;
import com.btg.pactual.gestionfondos.repository.FondoRepository;
import com.btg.pactual.gestionfondos.repository.SuscripcionRepository;
import com.btg.pactual.gestionfondos.service.FondoService;
import com.btg.pactual.gestionfondos.service.TransaccionService;
import com.btg.pactual.gestionfondos.utils.Constantes;
import com.btg.pactual.gestionfondos.utils.Utils;
import com.btg.pactual.gestionfondos.utils.enums.EstadoTransaccion;
import com.btg.pactual.gestionfondos.utils.enums.TipoTransaccion;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Service
@AllArgsConstructor
public class FondoServiceImpl implements FondoService {

    private final FondoRepository fondoRepository;
    private final SuscripcionRepository suscripcionRepository;
    private final TransaccionService transaccionService;
    private final ClienteRepository clienteRepository;
    private final Utils utils;
    //Suscripción a un nuevo fondo (aperturas).

    public void suscribirFondo(SuscripcionRequestDTO suscripcionRequestDTO) {
        validateFondoDTO(suscripcionRequestDTO);
        BigDecimal saldoInicial = BigDecimal.valueOf(2);
        validacionesDeNegocio(suscripcionRequestDTO, saldoInicial);
        // Crear una nueva entidad Suscripcion
        Suscripcion suscripcion = crearSuscripcion(suscripcionRequestDTO);

        suscripcionRepository.save(suscripcion);
        //
        transaccionService.guardarTransaccion(suscripcion, TipoTransaccion.APERTURA);
        log.info(Constantes.SUSCRIPCION_EXITOSA,
                suscripcionRequestDTO.getClienteId(),
                suscripcionRequestDTO.getFondoId());
    }

    public void cancelarSuscripcion(String suscripcionId) {
        // Validar que la suscripción existe
        Suscripcion suscripcion = suscripcionRepository.findById(suscripcionId);

        if (suscripcion == null) {
            throw new BusinessException(Constantes.ERROR_SUSCRIPCION_NO_EXISTE);
        }
        // restar dinero del fondo
        Fondo fondo = fondoRepository.findById(suscripcion.getFondoId());
        BigDecimal nuevoMontoFondo = fondo.getMontoTotal().subtract(suscripcion.getMonto());
        fondo.setMontoTotal(nuevoMontoFondo);
        fondoRepository.save(fondo);


        // Obtener el cliente y sumar el monto de la suscripción a su plata
        Cliente cliente = clienteRepository.findById(suscripcion.getClienteId());
        if (cliente != null) {
            BigDecimal nuevoMontoCliente = cliente.getPlata().add(suscripcion.getMonto());
            cliente.setPlata(nuevoMontoCliente);
            clienteRepository.save(cliente);
        }

        // Guardar la transacción de cancelación
        transaccionService.guardarTransaccion(suscripcion, TipoTransaccion.CANCELACION);

        // Cancelar la suscripción
        suscripcion.setEstado(EstadoTransaccion.CANCELADO);
        suscripcionRepository.save(suscripcion);
        log.info(Constantes.CANCELACION_EXITOSA, suscripcion.getClienteId(), suscripcion.getFondoId());
    }



    private void validateFondoDTO(SuscripcionRequestDTO suscripcionRequestDTO) {
        if (suscripcionRequestDTO == null) {
            throw new IllegalArgumentException(Constantes.ERROR_FONDO_DTO_NULL);
        }

        if (suscripcionRequestDTO.getFondoId() == null) {
            throw new IllegalArgumentException(Constantes.ERROR_NOMBRE_VACIO);
        }

        if (suscripcionRequestDTO.getMonto() == null || suscripcionRequestDTO.getMonto().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(Constantes.ERROR_MONTO_MINIMO_INVALIDO);
        }

    }

    private void validacionesDeNegocio(SuscripcionRequestDTO suscripcionRequestDTO, BigDecimal saldoInicial) {
        // Obtener el fondo y el monto mínimo requerido para vincularse
        Fondo fondo = fondoRepository.findById(suscripcionRequestDTO.getFondoId());

        if (fondo == null) {
            throw new BusinessException(Constantes.ERROR_FONDO_NO_EXISTE);
        }

        BigDecimal montoMinimo = fondo.getMontoMinimo();
        BigDecimal montoSuscripcion = suscripcionRequestDTO.getMonto();

        // Verificar si el monto de la suscripción es mayor o igual al monto mínimo requerido
        if (montoSuscripcion.compareTo(montoMinimo) < 0) {
            throw new BusinessException(Constantes.ERROR_MONTO_INSUFICIENTE_PARA_EL_FONDO + fondo.getNombre());
        }

        // Verificar si el cliente tiene saldo suficiente
        if (montoSuscripcion.compareTo(saldoInicial) > 0) {
            throw new BusinessException(Constantes.ERROR_NO_SALDO_SUFICIENTE + fondo.getNombre());
        }
    }

    private Suscripcion crearSuscripcion(SuscripcionRequestDTO suscripcionRequestDTO) {
        Suscripcion suscripcion = new Suscripcion();
        suscripcion.setId(Utils.generateId());
        suscripcion.setClienteId(suscripcionRequestDTO.getClienteId());
        suscripcion.setFondoId(suscripcionRequestDTO.getFondoId());
        suscripcion.setMonto(suscripcionRequestDTO.getMonto());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String fechaActual = LocalDateTime.now().format(formatter);
        suscripcion.setFechaSuscripcion(fechaActual);

        // Obtener el fondo y actualizar su monto total
        Fondo fondo = fondoRepository.findById(suscripcionRequestDTO.getFondoId());
        fondo.setMontoTotal(fondo.getMontoTotal().add(suscripcionRequestDTO.getMonto()));
        fondoRepository.save(fondo);

        // Obtener el cliente y restar el monto de la suscripción de su plata
        Cliente cliente = clienteRepository.findById(suscripcionRequestDTO.getClienteId());
        if (cliente != null) {
            BigDecimal nuevoMonto = cliente.getPlata().subtract(suscripcionRequestDTO.getMonto());
            cliente.setPlata(nuevoMonto);
            clienteRepository.save(cliente);
        }

        return suscripcion;
    }

}
