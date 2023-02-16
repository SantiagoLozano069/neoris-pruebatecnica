package com.neoris.pruebatecnica.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.neoris.pruebatecnica.enums.TipoCuentaEnum;
import com.neoris.pruebatecnica.enums.converters.TipoCuentaEnumConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CuentaPatchDto {

    private String numeroCuenta;

    @JsonDeserialize(converter = TipoCuentaEnumConverter.class)
    private TipoCuentaEnum tipoCuenta;

    private BigDecimal saldoInicial;

    private Boolean estado;

    private Integer idCliente;
}
