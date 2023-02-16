package com.neoris.pruebatecnica.controller;

import com.neoris.pruebatecnica.dto.*;
import com.neoris.pruebatecnica.exception.ArgumentosNoValidosException;
import com.neoris.pruebatecnica.task.CuentaTask;
import com.neoris.pruebatecnica.task.MovimientoTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;

@RestController
@RequestMapping(value = "/movimientos")
public class MovimientoController {

    @Autowired
    private MovimientoTask movimientoTask;

    @PostMapping
    private ResponseEntity crearMovimiento(@Valid @RequestBody MovimientoDto movimientoDto, BindingResult resultRequest){
        if (resultRequest.hasErrors()) {
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    RespuestaServicio.builder()
                            .codigo(HttpStatus.BAD_REQUEST.value())
                            .detalle(HttpStatus.BAD_REQUEST.getReasonPhrase())
                            .listaErrores(ArgumentosNoValidosException.handleValidationExceptions(resultRequest))
                            .build()
            );
        }
        return movimientoTask.crearMovimiento(movimientoDto);
    }

    @GetMapping("/{id}")
    private ResponseEntity obtenerMovimiento(@PathVariable("id") Integer idMovimiento){
        return movimientoTask.obtenerMovimientoPorId(idMovimiento);
    }

   @PutMapping
    private ResponseEntity editarmovimiento (@Valid @RequestBody MovimientoDto movimientoDto, BindingResult resultRequest){
        if (resultRequest.hasErrors()) {
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    RespuestaServicio.builder()
                            .codigo(HttpStatus.BAD_REQUEST.value())
                            .detalle(HttpStatus.BAD_REQUEST.getReasonPhrase())
                            .listaErrores(ArgumentosNoValidosException.handleValidationExceptions(resultRequest))
                            .build()
            );
        }
        return movimientoTask.editarMovimiento(movimientoDto);
    }

    @PatchMapping("/{id}")
    private ResponseEntity actualizarMovimiento (@PathVariable Integer id, @RequestBody MovimientoPatchDto movimiento){
        return movimientoTask.actualizarMovimiento(id,movimiento);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity eliminarMovimiento(@PathVariable Integer id){
        return movimientoTask.eliminarMovimiento(id);
    }

    @GetMapping()
    private ResponseEntity obtenerReportePorFechas(@RequestParam(name = "fechainicial")
                                                   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicial,
                                                   @RequestParam(name = "fechafinal")
                                                   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFinal,
                                                   @RequestParam(name = "idcliente") Integer idCliente) {
        return movimientoTask.obtenerReportePorFechas(fechaInicial, fechaFinal, idCliente);
    }
}
