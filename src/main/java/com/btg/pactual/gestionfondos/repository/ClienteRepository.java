package com.btg.pactual.gestionfondos.repository;


import com.btg.pactual.gestionfondos.model.Cliente;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

@Repository
@AllArgsConstructor
public class ClienteRepository {

    private final DynamoDbEnhancedClient dynamoDbEnhancedClient;
    private final DynamoDbTable<Cliente> clienteTable;

    // Constructor que inicializa el DynamoDbClient y crea la tabla
    public ClienteRepository(DynamoDbClient dynamoDbClient) {
        this.dynamoDbEnhancedClient = DynamoDbEnhancedClient.builder()
                .dynamoDbClient(dynamoDbClient)
                .build();
        this.clienteTable = dynamoDbEnhancedClient.table("Clientes", TableSchema.fromBean(Cliente.class));
    }

    // Método para guardar un cliente en DynamoDB
    public void save(Cliente cliente) {
        clienteTable.putItem(cliente);
    }

    // Método para buscar un cliente por su id
    public Cliente findById(String clienteId) {
        return clienteTable.getItem(r -> r.key(k -> k.partitionValue(clienteId)));
    }

    // Método para eliminar un cliente por su id
    public void deleteById(String clienteId) {
        clienteTable.deleteItem(r -> r.key(k -> k.partitionValue(clienteId)));
    }

    // Método para actualizar un cliente en DynamoDB
    public void update(Cliente cliente) {
        save(cliente); // Utiliza el método save para realizar la actualización
    }
}

