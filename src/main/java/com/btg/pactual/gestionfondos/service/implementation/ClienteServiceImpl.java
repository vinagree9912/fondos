package com.btg.pactual.gestionfondos.service.implementation;

import com.btg.pactual.gestionfondos.dto.ClienteRequestDTO;
import com.btg.pactual.gestionfondos.model.Cliente;
import com.btg.pactual.gestionfondos.repository.ClienteRepository;
import com.btg.pactual.gestionfondos.service.ClienteService;
import com.btg.pactual.gestionfondos.utils.Utils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
@Slf4j
@Service
@AllArgsConstructor
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    @Override
    public Cliente crearCliente(ClienteRequestDTO clienteRequestDTO) {
        Cliente cliente = new Cliente();
        cliente.setId(Utils.generateId());
        cliente.setNombre(clienteRequestDTO.getNombre());
        cliente.setApellido(clienteRequestDTO.getApellido());
        cliente.setEmail(clienteRequestDTO.getEmail());
        cliente.setTelefono(clienteRequestDTO.getTelefono());
        cliente.setDireccion(clienteRequestDTO.getDireccion());
        cliente.setFechaRegistro(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        cliente.setActivo(true);
        cliente.setPlata(new BigDecimal("500000"));

        clienteRepository.save(cliente);
        return cliente;

    }
}
