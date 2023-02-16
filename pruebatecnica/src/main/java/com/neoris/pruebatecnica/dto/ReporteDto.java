package com.neoris.pruebatecnica.dto;

import com.neoris.pruebatecnica.enums.TipoCuentaEnum;
import com.neoris.pruebatecnica.enums.TipoMovimientoEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ReporteDto implements Serializable {

    private LocalDate fecha;

    private String cliente;

    private String numeroCuenta;

    private TipoCuentaEnum tipoCuenta;

    private BigDecimal saldoInicial;

    private Boolean estado;

    private TipoMovimientoEnum tipoMovimiento;

    private BigDecimal saldoDisponible;

}
