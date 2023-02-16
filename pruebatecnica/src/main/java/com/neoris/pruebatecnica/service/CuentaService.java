package com.neoris.pruebatecnica.service;

import com.neoris.pruebatecnica.dto.ClienteDto;
import com.neoris.pruebatecnica.dto.ClientePatchDto;
import com.neoris.pruebatecnica.dto.CuentaDto;
import com.neoris.pruebatecnica.dto.CuentaPatchDto;
import com.neoris.pruebatecnica.exception.NeorisException;

public interface CuentaService {

    CuentaDto crearCuenta(CuentaDto cuentaDto) throws NeorisException;

    CuentaDto obtenerCuentaPorId(Integer idCuenta) throws NeorisException;

    CuentaDto editarCuenta(CuentaDto cuenta) throws NeorisException;

    CuentaDto actualizarCuenta(Integer idCuenta, CuentaPatchDto cuenta) throws NeorisException;

    CuentaDto eliminarCuenta(Integer id) throws NeorisException;
}
