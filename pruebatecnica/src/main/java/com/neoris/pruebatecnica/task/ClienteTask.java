package com.neoris.pruebatecnica.task;

import com.neoris.pruebatecnica.dto.ClienteDto;
import com.neoris.pruebatecnica.dto.ClientePatchDto;
import org.springframework.http.ResponseEntity;

public interface ClienteTask {

    ResponseEntity crearCliente(ClienteDto cliente);
    ResponseEntity obtenerClientePorId(Integer idCliente);
    ResponseEntity editarCliente(ClienteDto cliente);

    ResponseEntity actualizarCliente(Integer idCliente, ClientePatchDto cliente);

    ResponseEntity eliminarCliente(Integer id);
}
