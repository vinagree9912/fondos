package com.btg.pactual.gestionfondos.model;

import lombok.*;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamoDbBean
public class Cliente {

    private String id; // ID único del cliente
    private String nombre; // Nombre del cliente
    private String apellido; // Apellido del cliente
    private String email; // Correo electrónico del cliente
    private String telefono; // Teléfono del cliente
    private String direccion; // Dirección del cliente
    private String fechaRegistro; // Fecha de registro del cliente
    private boolean activo; // Estado del cliente (activo/inactivo)
    @Setter
    @Getter
    private BigDecimal plata; // Cantidad de dinero que tiene el cliente


    @DynamoDbPartitionKey
    public String getId() {
        return id;
    }

}
