package com.neoris.pruebatecnica.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.neoris.pruebatecnica.enums.GeneroEnum;
import com.neoris.pruebatecnica.enums.converters.GeneroEnumConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class PersonaPatchDto {

    private String nombre;

    @JsonDeserialize(converter = GeneroEnumConverter.class)
    private GeneroEnum genero;

    private Integer edad;

    private String identificacion;

    private String direccion;

    private String telefono;
}
