package com.btg.pactual.gestionfondos.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FondoDTO {

    @NotBlank(message = "El nombre del fondo es obligatorio.")
    private String nombre;

    @NotNull(message = "El monto mínimo es obligatorio.")
    @Positive(message = "El monto mínimo debe ser un número positivo.")
    private BigDecimal montoMinimo;

    @NotBlank(message = "La categoría es obligatoria.")
    private String categoria;
}
