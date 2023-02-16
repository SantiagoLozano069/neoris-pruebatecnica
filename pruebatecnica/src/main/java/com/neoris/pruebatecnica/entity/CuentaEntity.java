package com.neoris.pruebatecnica.entity;

import com.neoris.pruebatecnica.enums.TipoCuentaEnum;
import com.neoris.pruebatecnica.enums.converters.GeneroEnumConverter;
import com.neoris.pruebatecnica.enums.converters.TipoCuentaEnumConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "cuenta")
public class CuentaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", length = 8, nullable = false)
    private Integer id;

    @Column(name = "numero_cuenta", length = 6)
    private String numeroCuenta;

    @Column(name = "tipo_cuenta", length = 9)
    private String tipoCuenta;

    @Column(name = "saldo_inicial", length = 9)
    private BigDecimal saldoInicial;

    @Column(name = "estado", length = 9)
    private Boolean estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente", nullable = false)
    private ClienteEntity cliente;
}
