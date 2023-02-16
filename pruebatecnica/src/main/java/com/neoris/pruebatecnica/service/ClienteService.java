package com.neoris.pruebatecnica.service;

import ch.qos.logback.core.net.server.Client;
import com.neoris.pruebatecnica.dto.ClienteDto;
import com.neoris.pruebatecnica.dto.ClientePatchDto;
import com.neoris.pruebatecnica.exception.NeorisException;
import com.neoris.pruebatecnica.task.ClienteTask;

import java.util.Map;

public interface ClienteService {

    ClienteDto crearCliente(ClienteDto cliente) throws NeorisException;
    ClienteDto obtenerClientePorId(Integer idCliente) throws NeorisException;

    ClienteDto editarCliente(ClienteDto cliente) throws NeorisException;

    ClienteDto actualizarCliente(Integer idCliente, ClientePatchDto cliente) throws NeorisException;

    ClienteDto eliminarCliente(Integer id) throws NeorisException;
}
