package com.btg.pactual.gestionfondos.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteRequestDTO {

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(max = 50, message = "El nombre no puede exceder 50 caracteres")
    private String nombre;

    @NotBlank(message = "El apellido no puede estar vacío")
    @Size(max = 50, message = "El apellido no puede exceder 50 caracteres")
    private String apellido;

    @NotBlank(message = "El correo electrónico no puede estar vacío")
    @Email(message = "Debe proporcionar un correo electrónico válido")
    private String email;

    @NotBlank(message = "El teléfono no puede estar vacío")
    @Pattern(regexp = "^[0-9]{10}$", message = "El teléfono debe contener 10 dígitos")
    private String telefono;

    @NotBlank(message = "La dirección no puede estar vacía")
    @Size(max = 100, message = "La dirección no puede exceder 100 caracteres")
    private String direccion;
}
