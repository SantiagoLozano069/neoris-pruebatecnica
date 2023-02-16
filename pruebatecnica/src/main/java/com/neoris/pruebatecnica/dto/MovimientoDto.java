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
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MovimientoDto implements Serializable {

    private Integer id;

    @NotNull(message = "La fecha es obligatoria")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate fecha;

    @JsonDeserialize(converter = MovimientoEnumConverter.class)
    private TipoMovimientoEnum tipoMovimiento;

    @NotNull(message = "El valor del movimiento es obligatorio")
    private BigDecimal valor;

    @NotNull(message = "El saldo del movimiento es obligatorio")
    private BigDecimal saldo;

    @NotNull(message = "El id de la cuenta es obligatorio")
    private Integer idCuenta;
}
