package com.btg.pactual.gestionfondos.repository;

import com.btg.pactual.gestionfondos.model.Fondo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

@Repository
@AllArgsConstructor
public class FondoRepository {

    private final DynamoDbEnhancedClient dynamoDbEnhancedClient;
    private final DynamoDbTable<Fondo> fondoTable;

    // Constructor que inicializa el DynamoDbClient y crea la tabla
    public FondoRepository(DynamoDbClient dynamoDbClient) {
        this.dynamoDbEnhancedClient = DynamoDbEnhancedClient.builder()
                .dynamoDbClient(dynamoDbClient)
                .build();
        this.fondoTable = dynamoDbEnhancedClient.table("Fondos", TableSchema.fromBean(Fondo.class));
    }

    // Método para guardar un fondo en DynamoDB
    public void save(Fondo fondo) {
        fondoTable.putItem(fondo);
    }

    // Método para buscar un fondo por su id
    public Fondo findById(String fondoId) {
        return fondoTable.getItem(r -> r.key(k -> k.partitionValue(fondoId)));
    }
}
