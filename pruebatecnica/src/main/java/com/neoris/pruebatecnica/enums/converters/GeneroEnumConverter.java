package com.neoris.pruebatecnica.enums.converters;

import com.fasterxml.jackson.databind.util.StdConverter;
import com.neoris.pruebatecnica.enums.GeneroEnum;
import org.springframework.util.ObjectUtils;

import javax.persistence.AttributeConverter;

public class GeneroEnumConverter extends StdConverter<String, GeneroEnum> implements AttributeConverter<GeneroEnum, String>
 {
    @Override
    public String convertToDatabaseColumn(GeneroEnum attribute) {
        if(!ObjectUtils.isEmpty(attribute)) {
            return attribute.getGenero();
        }
        return null;
    }

    @Override
    public GeneroEnum convertToEntityAttribute(String dbData) {
        if(!ObjectUtils.isEmpty(dbData)) {
            return GeneroEnum.getEnumByGenero(dbData);
        }
        return null;
    }

     @Override
     public GeneroEnum convert(String value) {
        return GeneroEnum.getEnumByGenero(value);
     }
 }
