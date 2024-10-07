package com.btg.pactual.gestionfondos.service;

import com.btg.pactual.gestionfondos.dto.ClienteRequestDTO;
import com.btg.pactual.gestionfondos.model.Cliente;

public interface ClienteService {
    Cliente crearCliente(ClienteRequestDTO clienteRequestDTO);
}
