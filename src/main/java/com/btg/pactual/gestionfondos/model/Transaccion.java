package com.btg.pactual.gestionfondos.model;

import com.btg.pactual.gestionfondos.utils.enums.TipoTransaccion;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@DynamoDbBean
public class Transaccion {

    private String id; // Identificador único de la transacción
    private String clienteId; // ID del cliente asociado a la transacción
    private String fondoId; // ID del fondo asociado
    private BigDecimal monto; // Monto de la transacción
    private LocalDateTime fecha; // Fecha de la transacción
    private TipoTransaccion tipo; // Tipo de transacción (suscripción, cancelación, etc.)

    @DynamoDbPartitionKey
    @DynamoDbAttribute("id")
    public String getId() {
        return id;
    }

    @DynamoDbAttribute("clienteId")
    public String getClienteId() {
        return clienteId;
    }

    @DynamoDbAttribute("fondoId")
    public String getFondoId() {
        return fondoId;
    }

    @DynamoDbAttribute("monto")
    public BigDecimal getMonto() {
        return monto;
    }

    @DynamoDbAttribute("fecha")
    public LocalDateTime getFecha() {
        return fecha;
    }

    @DynamoDbAttribute("tipo")
    public TipoTransaccion getTipo() {
        return tipo;
    }

    // Método para establecer la fecha actual
    public void setFechaAhora() {
        this.fecha = LocalDateTime.now();
    }
}
