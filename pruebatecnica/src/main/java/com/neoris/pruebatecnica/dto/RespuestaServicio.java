package com.neoris.pruebatecnica.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@JsonInclude(Include.NON_NULL)
public class RespuestaServicio implements Serializable {
    private int codigo;
    private String detalle;
    private List<String> listaErrores;
    private String mensajeError;
    private ObjetoRespuestaGenerico objetoRespuesta;
}
