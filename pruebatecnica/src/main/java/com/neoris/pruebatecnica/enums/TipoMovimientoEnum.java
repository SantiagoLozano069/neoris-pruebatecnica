package com.neoris.pruebatecnica.enums;

import lombok.Getter;

@Getter
public enum TipoMovimientoEnum {
    CREDITO("CREDITO"),
    DEBITO("DEBITO");

    private String movimiento;

    TipoMovimientoEnum(String movimiento){
        this.movimiento = movimiento;
    }

    public static TipoMovimientoEnum getEnumByMovimiento(String movimiento){
        for(TipoMovimientoEnum tipoMovimientoEnum : TipoMovimientoEnum.values()){
            if(tipoMovimientoEnum.getMovimiento().equalsIgnoreCase(movimiento)){
                return tipoMovimientoEnum;
            }
        }
        throw new IllegalArgumentException("No se encontró un movimiento de tipo ["+ movimiento + "].");
    }
}
