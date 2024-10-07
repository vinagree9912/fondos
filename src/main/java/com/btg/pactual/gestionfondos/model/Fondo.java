package com.btg.pactual.gestionfondos.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@DynamoDbBean
public class Fondo {

    private String id;
    private String nombre;
    private BigDecimal montoMinimo;
    private String categoria;
    private BigDecimal montoTotal;

    @DynamoDbPartitionKey
    @DynamoDbAttribute("id")
    public String getId() {
        return id;
    }

    @DynamoDbAttribute("nombre")
    public String getNombre() {
        return nombre;
    }

    @DynamoDbAttribute("montoMinimo")
    public BigDecimal getMontoMinimo() {
        return montoMinimo;
    }

    @DynamoDbAttribute("categoria")
    public String getCategoria() {
        return categoria;
    }
}

