package com.neoris.pruebatecnica.controller;

import com.neoris.pruebatecnica.dto.ClienteDto;
import com.neoris.pruebatecnica.dto.ClientePatchDto;
import com.neoris.pruebatecnica.dto.RespuestaServicio;
import com.neoris.pruebatecnica.exception.ArgumentosNoValidosException;
import com.neoris.pruebatecnica.task.ClienteTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {

    @Autowired
    private ClienteTask clienteTask;

    @PostMapping
    private ResponseEntity crearCliente(@Valid @RequestBody ClienteDto cliente, BindingResult resultRequest){
        if (resultRequest.hasErrors()) {
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    RespuestaServicio.builder()
                            .codigo(HttpStatus.BAD_REQUEST.value())
                            .detalle(HttpStatus.BAD_REQUEST.getReasonPhrase())
                            .listaErrores(ArgumentosNoValidosException.handleValidationExceptions(resultRequest))
                            .build()
            );
        }
        return clienteTask.crearCliente(cliente);
    }

    @GetMapping("/{id}")
    private ResponseEntity crearCliente(@PathVariable("id") Integer idCliente){
        return clienteTask.obtenerClientePorId(idCliente);
    }

    @PutMapping
    private ResponseEntity editarCliente (@Valid @RequestBody ClienteDto cliente, BindingResult resultRequest){
        if (resultRequest.hasErrors()) {
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    RespuestaServicio.builder()
                            .codigo(HttpStatus.BAD_REQUEST.value())
                            .detalle(HttpStatus.BAD_REQUEST.getReasonPhrase())
                            .listaErrores(ArgumentosNoValidosException.handleValidationExceptions(resultRequest))
                            .build()
            );
        }
        return clienteTask.editarCliente(cliente);
    }

    @PatchMapping("/{id}")
    private ResponseEntity actualizarCliente (@PathVariable Integer id, @RequestBody ClientePatchDto cliente){
        return clienteTask.actualizarCliente(id,cliente);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity eliminarCliente(@PathVariable Integer id){
        return clienteTask.eliminarCliente(id);
    }
}
