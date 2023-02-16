package com.neoris.pruebatecnica.task;

import com.neoris.pruebatecnica.dto.CuentaDto;
import com.neoris.pruebatecnica.dto.CuentaPatchDto;
import org.springframework.http.ResponseEntity;

public interface CuentaTask {

    ResponseEntity crearCuenta(CuentaDto cuentaDto);

    ResponseEntity obtenerCuentaPorId(Integer idCuenta);

    ResponseEntity editarCuenta(CuentaDto cuentaDto);

    ResponseEntity actualizarCuenta(Integer idCuenta, CuentaPatchDto cuenta);

    ResponseEntity eliminarCuenta(Integer idCuenta);
}
