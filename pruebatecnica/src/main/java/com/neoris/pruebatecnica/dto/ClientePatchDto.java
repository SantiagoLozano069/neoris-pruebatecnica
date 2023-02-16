package com.neoris.pruebatecnica.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClientePatchDto {

    private String contrasena;

    private Boolean estado;

    private PersonaPatchDto persona;
}
