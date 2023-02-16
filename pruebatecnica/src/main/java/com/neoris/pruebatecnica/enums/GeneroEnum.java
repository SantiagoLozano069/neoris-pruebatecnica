package com.neoris.pruebatecnica.enums;

import lombok.Getter;

@Getter
public enum GeneroEnum {

    MASCULINO("MASCULINO"),
    FEMENINO("FEMENINO");

    private String genero;

    GeneroEnum(String genero){
        this.genero = genero;
    }

    public static GeneroEnum getEnumByGenero(String genero){
        for(GeneroEnum generoEnum : GeneroEnum.values()){
            if(generoEnum.getGenero().equalsIgnoreCase(genero)){
                return generoEnum;
            }
        }
        throw new IllegalArgumentException("No se encontró un genero de tipo ["+ genero + "].");
    }
}
