package com.neoris.pruebatecnica.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.neoris.pruebatecnica.enums.TipoMovimientoEnum;
import com.neoris.pruebatecnica.enums.converters.MovimientoEnumConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MovimientoPatchDto {

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate fecha;

    @JsonDeserialize(converter = MovimientoEnumConverter.class)
    private TipoMovimientoEnum tipoMovimiento;

    private BigDecimal valor;

    private BigDecimal saldo;

    private Integer idCuenta;
}
