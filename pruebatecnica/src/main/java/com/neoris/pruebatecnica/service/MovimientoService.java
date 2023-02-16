package com.neoris.pruebatecnica.service;

import com.neoris.pruebatecnica.dto.MovimientoDto;
import com.neoris.pruebatecnica.dto.MovimientoPatchDto;
import com.neoris.pruebatecnica.dto.ReporteDto;
import com.neoris.pruebatecnica.exception.NeorisException;

import java.time.LocalDate;
import java.util.List;

public interface MovimientoService {

    MovimientoDto crearMovimiento(MovimientoDto movimientoDto) throws NeorisException;

    MovimientoDto obtenerMovimientoPorId(Integer idMovimiento) throws NeorisException;

    MovimientoDto editarMovimiento(MovimientoDto movimientoDto) throws NeorisException;

    MovimientoDto actualizarMovimiento(Integer idMovimiento, MovimientoPatchDto movimiento) throws NeorisException;

    MovimientoDto eliminarMovimiento(Integer id) throws NeorisException;

    List<ReporteDto> obtenerReportePorFechas(LocalDate fechaInicial, LocalDate fechaFinal, Integer idCliente);
}
