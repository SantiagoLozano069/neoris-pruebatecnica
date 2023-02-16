package com.neoris.pruebatecnica.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.neoris.pruebatecnica.enums.GeneroEnum;
import com.neoris.pruebatecnica.enums.converters.GeneroEnumConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PersonaDto {

    private Integer id;

    @NotNull(message = "El nombre obligatorio")
    @NotEmpty(message = "El nombre es obligatorio")
    @Size(max=50, message = "El nombre es demasiado largo, por favor verifique la informacion")
    private String nombre;

    @JsonDeserialize(converter = GeneroEnumConverter.class)
    private GeneroEnum genero;

    @NotNull(message = "La edad es obligatoria")
    private Integer edad;

    @NotNull(message = "El número de indentificacion es obligatorio")
    @NotEmpty(message = "El número de indentificacion es obligatorio")
    @Size(max=10, message = "El número de indentifiacion es demasiado largo, por favor verifique la informacion")
    private String identificacion;

    @NotNull(message = "La direccion es obligatoria")
    @NotEmpty(message = "LA direccion es obligatoria")
    @Size(max=60, message = "La dirección es demasiado larga, por favor verifique la informacion")
    private String direccion;

    @NotNull
    @NotNull(message = "El telefono es obligatorio")
    @NotEmpty(message = "El telefono es obligatorio")
    @Size(max=10, message = "El número de telefono es demasiado largo, por favor verifique la informacion")
    private String telefono;
}
