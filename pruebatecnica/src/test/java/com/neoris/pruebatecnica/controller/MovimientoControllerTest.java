package com.neoris.pruebatecnica.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neoris.pruebatecnica.dto.CuentaDto;
import com.neoris.pruebatecnica.dto.MovimientoDto;
import com.neoris.pruebatecnica.dto.MovimientoPatchDto;
import com.neoris.pruebatecnica.entity.ClienteEntity;
import com.neoris.pruebatecnica.entity.CuentaEntity;
import com.neoris.pruebatecnica.entity.MovimientoEntity;
import com.neoris.pruebatecnica.entity.PersonaEntity;
import com.neoris.pruebatecnica.enums.GeneroEnum;
import com.neoris.pruebatecnica.enums.TipoCuentaEnum;
import com.neoris.pruebatecnica.enums.TipoMovimientoEnum;
import com.neoris.pruebatecnica.task.MovimientoTask;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.time.LocalDate;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(MovimientoController.class)
class MovimientoControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private MovimientoTask movimientoTask;



    private MovimientoDto movimientoDto;

    private MovimientoEntity movimientoEntity;

    private CuentaEntity cuentaEntity;

    private MovimientoPatchDto movimientoPatchDto;


    @BeforeEach
    void setUp() {

        movimientoDto = new MovimientoDto();
        movimientoDto.setTipoMovimiento(TipoMovimientoEnum.CREDITO);
        movimientoDto.setSaldo(BigDecimal.valueOf(300));
        movimientoDto.setValor(BigDecimal.valueOf(200));
        movimientoDto.setFecha(LocalDate.now());
        movimientoDto.setIdCuenta(1);

        cuentaEntity = new CuentaEntity();
        cuentaEntity.setNumeroCuenta("1234");
        cuentaEntity.setTipoCuenta(TipoCuentaEnum.AHORRO.getTipoCuenta());
        cuentaEntity.setSaldoInicial(BigDecimal.ZERO);

        movimientoEntity = new MovimientoEntity();
        movimientoEntity.setId(1);
        movimientoEntity.setTipoMovimiento(TipoMovimientoEnum.CREDITO.getMovimiento());
        movimientoEntity.setFecha(LocalDate.now());
        movimientoEntity.setSaldo(BigDecimal.valueOf(300));
        movimientoEntity.setValor(BigDecimal.valueOf(200));
        movimientoEntity.setCuenta(cuentaEntity);

        movimientoPatchDto = new MovimientoPatchDto();
        movimientoPatchDto.setValor(BigDecimal.valueOf(300));
    }

    @Test
    void validar_crear_movimiento_cuando_retorna_200() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/movimientos")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(movimientoDto))
                )
                .andExpect(status().isOk());

        //se verifica la llamada a la logica de negocio
        ArgumentCaptor<MovimientoDto> movimientoCaptor = ArgumentCaptor.forClass(MovimientoDto.class);
        Mockito.verify(movimientoTask, Mockito.times(1)).crearMovimiento(movimientoCaptor.capture());
        assertEquals(movimientoCaptor.getValue().getIdCuenta() , movimientoDto.getIdCuenta());
        assertEquals(movimientoCaptor.getValue().getSaldo(), movimientoDto.getSaldo());
    }

    @Test
    void validar_crear_movimiento_cuando_retorna_400() throws Exception {
        movimientoDto.setSaldo(null);
        mockMvc.perform(MockMvcRequestBuilders.post("/movimientos")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(movimientoDto))
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    void validar_consulta_movimiento_por_id_retorna_200() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/movimientos/1")
                        .contentType("application/json")
                )
                .andExpect(status().isOk());
    }

    @Test
    void validar_consulta_movimiento_por_id_retorna_400() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/movimientos/uno")
                        .contentType("application/json")
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    void validar_editar_movimiento_cuando_retorna_200() throws Exception {
        movimientoDto.setId(1);
        movimientoDto.setValor(BigDecimal.valueOf(50));
        mockMvc.perform(MockMvcRequestBuilders.put("/movimientos")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(movimientoDto))
                )
                .andExpect(status().isOk());

        //se verifica la llamada a la logica de negocio
        ArgumentCaptor<MovimientoDto> movimientosCaptor = ArgumentCaptor.forClass(MovimientoDto.class);
        Mockito.verify(movimientoTask, Mockito.times(1)).editarMovimiento(movimientosCaptor.capture());
        assertEquals(movimientosCaptor.getValue().getSaldo(), movimientoDto.getSaldo());
        assertEquals(movimientosCaptor.getValue().getIdCuenta() , movimientoDto.getIdCuenta());
        assertEquals(movimientosCaptor.getValue().getTipoMovimiento(), movimientoDto.getTipoMovimiento());
    }

    @Test
    void validar_editar_movimiento_cuando_retorna_400() throws Exception {
        movimientoDto.setIdCuenta(null);
        mockMvc.perform(MockMvcRequestBuilders.put("/movimientos")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(movimientoDto))
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    void validar_actualizar_movimiento_cuando_retorna_200() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.patch("/movimientos/1")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(movimientoPatchDto))
                )
                .andExpect(status().isOk());
    }

    @Test
    void validar_actualizar_movimiento_cuando_retorna_400() throws Exception {
        movimientoDto = null;
        mockMvc.perform(MockMvcRequestBuilders.patch("/movimientos/uno")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(movimientoPatchDto))
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    void validar_eliminar_movimiento_cuando_retorna_200() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/movimientos/1")
                        .contentType("application/json")
                )
                .andExpect(status().isOk());
    }

    @Test
    void validar_eliminar_movimiento_cuando_retorna_400() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/movimientos/12-03-2023")
                        .contentType("application/json")
                )
                .andExpect(status().isBadRequest());
    }
}