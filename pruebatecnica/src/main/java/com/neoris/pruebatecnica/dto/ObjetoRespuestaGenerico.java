package com.neoris.pruebatecnica.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ObjetoRespuestaGenerico<T>{
    private T objectDto;

    private List<T> listObjectDto;

    public ObjetoRespuestaGenerico(T objectDto){
        this.objectDto = objectDto;
    }

    public ObjetoRespuestaGenerico(List<T> listObjectDto){
        this.listObjectDto = listObjectDto;
    }

    public T getObjetoRespuestaDto(){
        return this.objectDto;
    }

    public List<T> getListObjetoRespuestaDto(){
        return this.listObjectDto;
    }
}
