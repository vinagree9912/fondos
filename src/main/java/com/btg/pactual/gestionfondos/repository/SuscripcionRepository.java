package com.btg.pactual.gestionfondos.repository;

import com.btg.pactual.gestionfondos.model.Suscripcion;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.GetItemEnhancedRequest;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

@Repository
@AllArgsConstructor
public class SuscripcionRepository {

    private final DynamoDbEnhancedClient dynamoDbEnhancedClient;
    private final DynamoDbTable<Suscripcion> suscripcionTable;

    public SuscripcionRepository(DynamoDbClient dynamoDbClient) {
        this.dynamoDbEnhancedClient = DynamoDbEnhancedClient.builder()
                .dynamoDbClient(dynamoDbClient)
                .build();
        this.suscripcionTable = dynamoDbEnhancedClient.table("Suscripciones", TableSchema.fromBean(Suscripcion.class));
    }

    public void save(Suscripcion suscripcion) {
        suscripcionTable.putItem(suscripcion);
    }

    public Suscripcion findById(String id) {
        return suscripcionTable.getItem(GetItemEnhancedRequest.builder()
                .key(k -> k.partitionValue(id)) // Asumiendo que "id" es la clave de partición
                .build());
    }
}
