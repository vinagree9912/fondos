package com.btg.pactual.gestionfondos.controller;

import com.btg.pactual.gestionfondos.dto.FondoDTO;
import com.btg.pactual.gestionfondos.dto.SuscripcionRequestDTO;
import com.btg.pactual.gestionfondos.service.FondoService;
import com.btg.pactual.gestionfondos.utils.Constantes;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;

@RestController
@RequestMapping(Constantes.PATH_FONDO)
@AllArgsConstructor
@CrossOrigin(
        origins = "*",
        methods = {RequestMethod.OPTIONS})
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class FondoController {

    private final FondoService fondoService;


    @PostMapping(Constantes.PATH_SUSCRIBIR)
    public ResponseEntity<String> suscribirFondo(@Valid @RequestBody SuscripcionRequestDTO suscripcionDTO) {
        fondoService.suscribirFondo(suscripcionDTO);
        return ResponseEntity.ok("Suscripción exitosa");
    }

    // Endpoint para cancelar la suscripción a un fondo
    @DeleteMapping(Constantes.PATH_CANCELAR)
    public ResponseEntity<String> cancelarSuscripcion(@RequestParam String suscripcionId) {
        fondoService.cancelarSuscripcion(suscripcionId);
        return ResponseEntity.ok("Cancelación exitosa");
    }

}
