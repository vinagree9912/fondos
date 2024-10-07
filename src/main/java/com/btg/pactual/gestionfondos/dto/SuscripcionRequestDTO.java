package com.btg.pactual.gestionfondos.dto;

import com.btg.pactual.gestionfondos.utils.enums.TipoNotificacion;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SuscripcionRequestDTO {
    @NotNull(message = "El ID del cliente no puede ser nulo")
    private String clienteId;

    @NotNull(message = "El ID del fondo no puede ser nulo")
    private String fondoId;

    @NotNull(message = "El monto de suscripción no puede ser nulo")
    @Min(value = 50000, message = "El monto mínimo debe ser de COP 50,000")
    private BigDecimal monto;

    @NotNull(message = "El tipo de notificacion no puede ser nulo")
    private TipoNotificacion tipoNotificacion;
}
