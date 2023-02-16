package com.neoris.pruebatecnica.task.taskImpl;

import com.neoris.pruebatecnica.dto.ClienteDto;
import com.neoris.pruebatecnica.dto.ClientePatchDto;
import com.neoris.pruebatecnica.dto.ObjetoRespuestaGenerico;
import com.neoris.pruebatecnica.dto.RespuestaServicio;
import com.neoris.pruebatecnica.exception.NeorisException;
import com.neoris.pruebatecnica.service.ClienteService;
import com.neoris.pruebatecnica.task.ClienteTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ClienteTaskImpl implements ClienteTask {

    @Autowired
    private ClienteService clienteService;
    @Override
    public ResponseEntity crearCliente(ClienteDto cliente) {
        try {
            ClienteDto clienteDto = clienteService.crearCliente(cliente);
            return ResponseEntity.status(HttpStatus.OK).body(
                    RespuestaServicio.builder()
                            .codigo(HttpStatus.OK.value())
                            .detalle(HttpStatus.OK.getReasonPhrase())
                            .objetoRespuesta(new ObjetoRespuestaGenerico(clienteDto))
                            .build()
            );
        } catch (NeorisException e) {
            return ResponseEntity.status(e.getStatus())
                    .body(
                            RespuestaServicio.builder().codigo(e.getStatus().value())
                                    .detalle(e.getStatus().getReasonPhrase())
                                    .mensajeError(e.getMessage())
                                    .objetoRespuesta(new ObjetoRespuestaGenerico(cliente))
                                    .build()
                    );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(
                            RespuestaServicio.builder()
                                    .codigo(HttpStatus.INTERNAL_SERVER_ERROR.value())
                                    .detalle(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                                    .objetoRespuesta(new ObjetoRespuestaGenerico(cliente))
                    );
        }
    }

    @Override
    public ResponseEntity obtenerClientePorId(Integer idCliente) {
        try {
            ClienteDto clienteDto = clienteService.obtenerClientePorId(idCliente);
            return ResponseEntity.status(HttpStatus.OK).body(
                    RespuestaServicio.builder()
                            .codigo(HttpStatus.OK.value())
                            .detalle(HttpStatus.OK.getReasonPhrase())
                            .objetoRespuesta(new ObjetoRespuestaGenerico(clienteDto))
                            .build()
            );
        } catch (NeorisException e) {
            return ResponseEntity.status(e.getStatus())
                    .body(
                            RespuestaServicio.builder().codigo(e.getStatus().value())
                                    .detalle(e.getStatus().getReasonPhrase())
                                    .mensajeError(e.getMessage())
                                    .build()
                    );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(
                            RespuestaServicio.builder()
                                    .codigo(HttpStatus.INTERNAL_SERVER_ERROR.value())
                                    .detalle(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                                    .build()
                    );
        }
    }


    @Override
    public ResponseEntity editarCliente(ClienteDto cliente) {
        try {
            ClienteDto clienteDto = clienteService.editarCliente(cliente);
            return ResponseEntity.status(HttpStatus.OK).body(
                    RespuestaServicio.builder()
                            .codigo(HttpStatus.OK.value())
                            .detalle(HttpStatus.OK.getReasonPhrase())
                            .objetoRespuesta(new ObjetoRespuestaGenerico(clienteDto))
                            .build()
            );
        } catch (NeorisException e) {
            return ResponseEntity.status(e.getStatus())
                    .body(
                            RespuestaServicio.builder().codigo(e.getStatus().value())
                                    .detalle(e.getStatus().getReasonPhrase())
                                    .mensajeError(e.getMessage())
                                    .build()
                    );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(
                            RespuestaServicio.builder()
                                    .codigo(HttpStatus.INTERNAL_SERVER_ERROR.value())
                                    .detalle(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                                    .build()
                    );
        }
    }

    @Override
    public ResponseEntity actualizarCliente (Integer idCliente, ClientePatchDto cliente){
        try {
            ClienteDto clienteDto = clienteService.actualizarCliente(idCliente,cliente);
            return ResponseEntity.status(HttpStatus.OK).body(
                    RespuestaServicio.builder()
                            .codigo(HttpStatus.OK.value())
                            .detalle(HttpStatus.OK.getReasonPhrase())
                            .objetoRespuesta(new ObjetoRespuestaGenerico(clienteDto))
                            .build()
            );
        } catch (NeorisException e) {
            return ResponseEntity.status(e.getStatus())
                    .body(
                            RespuestaServicio.builder().codigo(e.getStatus().value())
                                    .detalle(e.getStatus().getReasonPhrase())
                                    .mensajeError(e.getMessage())
                                    .build()
                    );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(
                            RespuestaServicio.builder()
                                    .codigo(HttpStatus.INTERNAL_SERVER_ERROR.value())
                                    .detalle(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                                    .build()
                    );
        }
    }

    @Override
    public ResponseEntity eliminarCliente(Integer idCliente){
        try {
            ClienteDto clienteDto = clienteService.eliminarCliente(idCliente);
            return ResponseEntity.status(HttpStatus.OK).body(
                    RespuestaServicio.builder()
                            .codigo(HttpStatus.OK.value())
                            .detalle(HttpStatus.OK.getReasonPhrase())
                            .objetoRespuesta(new ObjetoRespuestaGenerico(clienteDto))
                            .build()
            );
        } catch (NeorisException e) {
            return ResponseEntity.status(e.getStatus())
                    .body(
                            RespuestaServicio.builder().codigo(e.getStatus().value())
                                    .detalle(e.getStatus().getReasonPhrase())
                                    .mensajeError(e.getMessage())
                                    .build()
                    );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(
                            RespuestaServicio.builder()
                                    .codigo(HttpStatus.INTERNAL_SERVER_ERROR.value())
                                    .detalle(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                                    .build()
                    );
        }
    }

}
