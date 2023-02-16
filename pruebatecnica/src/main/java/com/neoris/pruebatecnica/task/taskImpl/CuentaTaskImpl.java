package com.neoris.pruebatecnica.task.taskImpl;

import com.neoris.pruebatecnica.dto.*;
import com.neoris.pruebatecnica.exception.NeorisException;
import com.neoris.pruebatecnica.service.CuentaService;
import com.neoris.pruebatecnica.task.CuentaTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class CuentaTaskImpl implements CuentaTask {

    @Autowired
    private CuentaService cuentaService;

    @Override
    public ResponseEntity crearCuenta(CuentaDto cuentaDto) {
        try {
            CuentaDto cuenta = cuentaService.crearCuenta(cuentaDto);
            return ResponseEntity.status(HttpStatus.OK).body(
                    RespuestaServicio.builder()
                            .codigo(HttpStatus.OK.value())
                            .detalle(HttpStatus.OK.getReasonPhrase())
                            .objetoRespuesta(new ObjetoRespuestaGenerico(cuenta))
                            .build()
            );
        } catch (NeorisException e) {
            return ResponseEntity.status(e.getStatus())
                    .body(
                            RespuestaServicio.builder().codigo(e.getStatus().value())
                                    .detalle(e.getStatus().getReasonPhrase())
                                    .mensajeError(e.getMessage())
                                    .objetoRespuesta(new ObjetoRespuestaGenerico(cuentaDto))
                                    .build()
                    );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(
                            RespuestaServicio.builder()
                                    .codigo(HttpStatus.INTERNAL_SERVER_ERROR.value())
                                    .detalle(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                                    .objetoRespuesta(new ObjetoRespuestaGenerico(cuentaDto))
                    );
        }
    }

    @Override
    public ResponseEntity obtenerCuentaPorId(Integer idCuenta) {
        try {
            CuentaDto cuentaDto = cuentaService.obtenerCuentaPorId(idCuenta);
            return ResponseEntity.status(HttpStatus.OK).body(
                    RespuestaServicio.builder()
                            .codigo(HttpStatus.OK.value())
                            .detalle(HttpStatus.OK.getReasonPhrase())
                            .objetoRespuesta(new ObjetoRespuestaGenerico(cuentaDto))
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
    public ResponseEntity editarCuenta(CuentaDto cuenta) {
        try {
            CuentaDto clienteDto = cuentaService.editarCuenta(cuenta);
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
    public ResponseEntity actualizarCuenta(Integer idCuenta, CuentaPatchDto cuenta) {
        try {
            CuentaDto cuentaDto = cuentaService.actualizarCuenta(idCuenta,cuenta);
            return ResponseEntity.status(HttpStatus.OK).body(
                    RespuestaServicio.builder()
                            .codigo(HttpStatus.OK.value())
                            .detalle(HttpStatus.OK.getReasonPhrase())
                            .objetoRespuesta(new ObjetoRespuestaGenerico(cuentaDto))
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
    public ResponseEntity eliminarCuenta(Integer idCuenta) {
        try {
            CuentaDto cuentaDto = cuentaService.eliminarCuenta(idCuenta);
            return ResponseEntity.status(HttpStatus.OK).body(
                    RespuestaServicio.builder()
                            .codigo(HttpStatus.OK.value())
                            .detalle(HttpStatus.OK.getReasonPhrase())
                            .objetoRespuesta(new ObjetoRespuestaGenerico(cuentaDto))
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
