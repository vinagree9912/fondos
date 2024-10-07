package com.btg.pactual.gestionfondos.repository;

import com.btg.pactual.gestionfondos.model.Transaccion;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Expression;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.ScanEnhancedRequest;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class TransaccionRepository {

    private final DynamoDbEnhancedClient dynamoDbEnhancedClient;
    private final DynamoDbTable<Transaccion> transaccionTable;

    public TransaccionRepository(DynamoDbClient dynamoDbClient) {
        this.dynamoDbEnhancedClient = DynamoDbEnhancedClient.builder()
                .dynamoDbClient(dynamoDbClient)
                .build();
        this.transaccionTable = dynamoDbEnhancedClient.table("Transacciones", TableSchema.fromBean(Transaccion.class));
    }

    public void save(Transaccion transaccion) {
        transaccionTable.putItem(transaccion);
    }

    public List<Transaccion> findByClienteId(String clienteId) {
        // Define la expresión de filtro para el scan
        Expression expression = Expression.builder()
                .expression("clienteId = :clienteId")
                .expressionValues(Map.of(":clienteId", AttributeValue.builder().s(clienteId).build()))
                .build();

        // Crea la solicitud de scan con la expresión de filtro
        ScanEnhancedRequest scanRequest = ScanEnhancedRequest.builder()
                .filterExpression(expression)
                .build();

        // Realiza el scan en la tabla de transacciones y convierte los resultados a una lista
        return transaccionTable.scan(scanRequest)
                .items()
                .stream()
                .collect(Collectors.toList());
    }
}
