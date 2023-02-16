package com.neoris.pruebatecnica.task.taskImpl;

import com.neoris.pruebatecnica.dto.*;
import com.neoris.pruebatecnica.exception.NeorisException;
import com.neoris.pruebatecnica.service.MovimientoService;
import com.neoris.pruebatecnica.task.MovimientoTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class MovimientoTaskImpl implements MovimientoTask {

    @Autowired
    private MovimientoService movimientoService;

    @Override
    public ResponseEntity crearMovimiento(MovimientoDto movimiento) {
        try {
            MovimientoDto movimientoDto = movimientoService.crearMovimiento(movimiento);
            return ResponseEntity.status(HttpStatus.OK).body(
                    RespuestaServicio.builder()
                            .codigo(HttpStatus.OK.value())
                            .detalle(HttpStatus.OK.getReasonPhrase())
                            .objetoRespuesta(new ObjetoRespuestaGenerico(movimientoDto))
                            .build()
            );
        } catch (NeorisException e) {
            return ResponseEntity.status(e.getStatus())
                    .body(
                            RespuestaServicio.builder().codigo(e.getStatus().value())
                                    .detalle(e.getStatus().getReasonPhrase())
                                    .mensajeError(e.getMessage())
                                    .objetoRespuesta(new ObjetoRespuestaGenerico(movimiento))
                                    .build()
                    );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(
                            RespuestaServicio.builder()
                                    .codigo(HttpStatus.INTERNAL_SERVER_ERROR.value())
                                    .detalle(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                                    .objetoRespuesta(new ObjetoRespuestaGenerico(movimiento))
                    );
        }
    }

    @Override
    public ResponseEntity obtenerMovimientoPorId(Integer idMovimiento) {
        try {
            MovimientoDto movimientoDto = movimientoService.obtenerMovimientoPorId(idMovimiento);
            return ResponseEntity.status(HttpStatus.OK).body(
                    RespuestaServicio.builder()
                            .codigo(HttpStatus.OK.value())
                            .detalle(HttpStatus.OK.getReasonPhrase())
                            .objetoRespuesta(new ObjetoRespuestaGenerico(movimientoDto))
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
    public ResponseEntity editarMovimiento(MovimientoDto movimientoDto) {
        try {
            MovimientoDto movimiento = movimientoService.editarMovimiento(movimientoDto);
            return ResponseEntity.status(HttpStatus.OK).body(
                    RespuestaServicio.builder()
                            .codigo(HttpStatus.OK.value())
                            .detalle(HttpStatus.OK.getReasonPhrase())
                            .objetoRespuesta(new ObjetoRespuestaGenerico(movimiento))
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
    public ResponseEntity actualizarMovimiento(Integer idMovimiento, MovimientoPatchDto movimiento) {
        try {
            MovimientoDto movimientoDto = movimientoService.actualizarMovimiento(idMovimiento,movimiento);
            return ResponseEntity.status(HttpStatus.OK).body(
                    RespuestaServicio.builder()
                            .codigo(HttpStatus.OK.value())
                            .detalle(HttpStatus.OK.getReasonPhrase())
                            .objetoRespuesta(new ObjetoRespuestaGenerico(movimientoDto))
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
    public ResponseEntity eliminarMovimiento(Integer idMovimiento) {
        try {
            MovimientoDto movimientoDto = movimientoService.eliminarMovimiento(idMovimiento);
            return ResponseEntity.status(HttpStatus.OK).body(
                    RespuestaServicio.builder()
                            .codigo(HttpStatus.OK.value())
                            .detalle(HttpStatus.OK.getReasonPhrase())
                            .objetoRespuesta(new ObjetoRespuestaGenerico(movimientoDto))
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
    public ResponseEntity obtenerReportePorFechas(LocalDate fechaInicial, LocalDate fechaFinal,
                                                  Integer idCliente) {
        try {
            List<ReporteDto> reporteDtoList = movimientoService.obtenerReportePorFechas(fechaInicial, fechaFinal,
                    idCliente);
            return ResponseEntity.status(HttpStatus.OK).body(
                    RespuestaServicio.builder()
                            .codigo(HttpStatus.OK.value())
                            .detalle(HttpStatus.OK.getReasonPhrase())
                            .objetoRespuesta(new ObjetoRespuestaGenerico(reporteDtoList))
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
