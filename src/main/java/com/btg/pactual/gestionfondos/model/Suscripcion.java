package com.btg.pactual.gestionfondos.model;

import com.btg.pactual.gestionfondos.utils.enums.EstadoTransaccion;
import com.btg.pactual.gestionfondos.utils.enums.TipoNotificacion;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamoDbBean
public class Suscripcion {

    private String id;
    private String clienteId;
    private String fondoId;
    private BigDecimal monto;
    private String fechaSuscripcion;
    private EstadoTransaccion estado;
    private TipoNotificacion tipoNotificacion;
    @DynamoDbPartitionKey
    public String getId() {
        return id;
    }
}



