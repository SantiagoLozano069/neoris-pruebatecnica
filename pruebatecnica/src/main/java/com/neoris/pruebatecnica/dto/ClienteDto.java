package com.neoris.pruebatecnica.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClienteDto implements Serializable {

    private Integer id;

    @Valid
    @NotNull(message = "Los datos de la persona son obligatorios")
    private PersonaDto persona;

    @NotNull(message = "La contraseña es obligatoria")
    @NotEmpty(message = "La contraseña es obligatoria")
    @Size(max=4, message = "La contraseña es demasiado larga, por favor verifique la informacion")
    private String contrasena;

    @NotNull(message = "El estado del cliente es obligatorio")
    private Boolean estado;
}
