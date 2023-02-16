package com.neoris.pruebatecnica.enums.converters;

import com.fasterxml.jackson.databind.util.StdConverter;
import com.neoris.pruebatecnica.enums.TipoCuentaEnum;
import org.springframework.util.ObjectUtils;

import javax.persistence.AttributeConverter;

public class TipoCuentaEnumConverter extends StdConverter<String, TipoCuentaEnum> implements AttributeConverter<TipoCuentaEnum, String> {


    @Override
    public TipoCuentaEnum convert(String value) {
        return TipoCuentaEnum.getEnumByTipoCuenta(value);
    }

    @Override
    public String convertToDatabaseColumn(TipoCuentaEnum attribute) {
        if(!ObjectUtils.isEmpty(attribute)){
            attribute.getTipoCuenta();
        }
        return null;
    }

    @Override
    public TipoCuentaEnum convertToEntityAttribute(String dbData) {
        if(!ObjectUtils.isEmpty(dbData)){
            return TipoCuentaEnum.getEnumByTipoCuenta(dbData);
        }
        return null;
    }
}
