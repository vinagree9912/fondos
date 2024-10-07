package com.btg.pactual.gestionfondos.service;

import com.btg.pactual.gestionfondos.dto.FondoDTO;
import com.btg.pactual.gestionfondos.dto.SuscripcionRequestDTO;

public interface FondoService {
    public void suscribirFondo(SuscripcionRequestDTO suscripcionRequestDTO);
    void cancelarSuscripcion(String suscripcionId);
}
