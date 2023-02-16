package com.neoris.pruebatecnica.controller;

import com.neoris.pruebatecnica.dto.CuentaDto;
import com.neoris.pruebatecnica.dto.CuentaPatchDto;
import com.neoris.pruebatecnica.dto.RespuestaServicio;
import com.neoris.pruebatecnica.exception.ArgumentosNoValidosException;
import com.neoris.pruebatecnica.task.CuentaTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/cuentas")
public class CuentaController {

    @Autowired
    private CuentaTask cuentaTask;

    @PostMapping
    private ResponseEntity crearCuenta(@Valid @RequestBody CuentaDto cuentaDto, BindingResult resultRequest){
        if (resultRequest.hasErrors()) {
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    RespuestaServicio.builder()
                            .codigo(HttpStatus.BAD_REQUEST.value())
                            .detalle(HttpStatus.BAD_REQUEST.getReasonPhrase())
                            .listaErrores(ArgumentosNoValidosException.handleValidationExceptions(resultRequest))
                            .build()
            );
        }
        return cuentaTask.crearCuenta(cuentaDto);
    }

    @GetMapping("/{id}")
    private ResponseEntity obtenerCuenta(@PathVariable("id") Integer idCuenta){
        return cuentaTask.obtenerCuentaPorId(idCuenta);
    }

    @PutMapping
    private ResponseEntity editarCuenta (@Valid @RequestBody CuentaDto cuenta, BindingResult resultRequest){
        if (resultRequest.hasErrors()) {
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    RespuestaServicio.builder()
                            .codigo(HttpStatus.BAD_REQUEST.value())
                            .detalle(HttpStatus.BAD_REQUEST.getReasonPhrase())
                            .listaErrores(ArgumentosNoValidosException.handleValidationExceptions(resultRequest))
                            .build()
            );
        }
        return cuentaTask.editarCuenta(cuenta);
    }

    @PatchMapping("/{id}")
    private ResponseEntity actualizarCuenta (@PathVariable Integer id, @RequestBody CuentaPatchDto cuenta){
        return cuentaTask.actualizarCuenta(id,cuenta);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity eliminarCuenta(@PathVariable Integer id){
        return cuentaTask.eliminarCuenta(id);
    }
}
