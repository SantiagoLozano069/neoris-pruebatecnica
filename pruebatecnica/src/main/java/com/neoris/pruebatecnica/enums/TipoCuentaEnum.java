package com.neoris.pruebatecnica.enums;

import lombok.Getter;

@Getter
public enum TipoCuentaEnum {

    AHORRO("AHORRO"),
    CORRIENTE("CORRIENTE");

    private String tipoCuenta;

    TipoCuentaEnum(String tipoCuenta){
        this.tipoCuenta = tipoCuenta;
    }

    public static TipoCuentaEnum getEnumByTipoCuenta(String tipoCuenta){
        for(TipoCuentaEnum tipoCuentaEnum : TipoCuentaEnum.values()){
            if(tipoCuentaEnum.getTipoCuenta().equalsIgnoreCase(tipoCuenta)){
                return tipoCuentaEnum;
            }
        }
        throw new IllegalArgumentException("No se encontró un tipo cuenta de tipo ["+ tipoCuenta + "].");
    }
}
