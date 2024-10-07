package com.btg.pactual.gestionfondos.config;

import com.btg.pactual.gestionfondos.model.Cliente;
import com.btg.pactual.gestionfondos.model.Fondo;
import com.btg.pactual.gestionfondos.repository.ClienteRepository;
import com.btg.pactual.gestionfondos.repository.FondoRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Component
public class DataInitializer {

    private final FondoRepository fondoRepository;
    private final ClienteRepository clienteRepository;

    @Autowired
    public DataInitializer(FondoRepository fondoRepository, ClienteRepository clienteRepository) {
        this.fondoRepository = fondoRepository;
        this.clienteRepository = clienteRepository;
    }

    @PostConstruct
    public void init() {
        // Inicializar fondos
        List<Fondo> fondos = Arrays.asList(
                new Fondo("1", "FPV_BTG_PACTUAL_RECAUDADORA", new BigDecimal("75000"), "FPV", new BigDecimal("0")),
                new Fondo("2", "FPV_BTG_PACTUAL_ECOPETROL", new BigDecimal("125000"), "FPV", new BigDecimal("0")),
                new Fondo("3", "DEUDAPRIVADA", new BigDecimal("50000"), "FIC", new BigDecimal("0")),
                new Fondo("4", "FDO-ACCIONES", new BigDecimal("250000"), "FIC", new BigDecimal("0")),
                new Fondo("5", "FPV_BTG_PACTUAL_DINAMICA", new BigDecimal("100000"), "FPV", new BigDecimal("0"))
        );

        fondos.forEach(fondo -> {
            if (fondoRepository.findById(fondo.getId()) == null) {
                fondoRepository.save(fondo);
                System.out.println("Fondo " + fondo.getNombre() + " creado.");
            }
        });

        // Inicializar clientes
        List<Cliente> clientes = Arrays.asList(
                new Cliente("1", "Juan", "Perez", "juan.perez@example.com", "1234567890", "Calle 123", "2024-01-01", true, new BigDecimal("500000")),
                new Cliente("2", "Maria", "Lopez", "maria.lopez@example.com", "0987654321", "Avenida 456", "2024-02-01", true, new BigDecimal("500000")),
                new Cliente("3", "Carlos", "Martinez", "carlos.martinez@example.com", "1122334455", "Carrera 789", "2024-03-01", true, new BigDecimal("500000"))
        );

        clientes.forEach(cliente -> {
            if (clienteRepository.findById(cliente.getId()) == null) {
                clienteRepository.save(cliente);
                System.out.println("Cliente " + cliente.getNombre() + " " + cliente.getApellido() + " creado.");
            }
        });
    }
}

