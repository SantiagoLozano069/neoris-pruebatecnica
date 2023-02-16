package com.neoris.pruebatecnica.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neoris.pruebatecnica.dto.CuentaDto;
import com.neoris.pruebatecnica.dto.CuentaPatchDto;
import com.neoris.pruebatecnica.entity.ClienteEntity;
import com.neoris.pruebatecnica.entity.CuentaEntity;
import com.neoris.pruebatecnica.entity.PersonaEntity;
import com.neoris.pruebatecnica.enums.GeneroEnum;
import com.neoris.pruebatecnica.enums.TipoCuentaEnum;
import com.neoris.pruebatecnica.task.CuentaTask;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(CuentaController.class)
class CuentaControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CuentaTask cuentaTask;


    private CuentaDto cuentaDto;

    private PersonaEntity personaEntity;
    private ClienteEntity clienteEntity;

    private CuentaEntity cuentaEntity;

    private CuentaPatchDto cuentaPatchDto;


    @BeforeEach
    void init() {

        cuentaDto = new CuentaDto();
        cuentaDto.setNumeroCuenta("1234");
        cuentaDto.setTipoCuenta(TipoCuentaEnum.AHORRO);
        cuentaDto.setSaldoInicial(BigDecimal.ZERO);
        cuentaDto.setIdCliente(1);
        cuentaDto.setEstado(true);

        personaEntity = new PersonaEntity();
        personaEntity.setId(1);
        personaEntity.setNombre("Santiago Lozano");
        personaEntity.setIdentificacion("1007445");
        personaEntity.setEdad(22);
        personaEntity.setDireccion("Manzana 7 Casa 8");
        personaEntity.setGenero(GeneroEnum.MASCULINO);
        personaEntity.setTelefono("3126550636");

        clienteEntity = new ClienteEntity();
        clienteEntity.setPersona(personaEntity);
        clienteEntity.setContrasena("1234");
        clienteEntity.setEstado(true);

        cuentaEntity = new CuentaEntity();
        cuentaEntity.setNumeroCuenta("1234");
        cuentaEntity.setTipoCuenta(TipoCuentaEnum.AHORRO.getTipoCuenta());
        cuentaEntity.setSaldoInicial(BigDecimal.ZERO);
        cuentaEntity.setCliente(clienteEntity);

        cuentaPatchDto = new CuentaPatchDto();
        cuentaPatchDto.setNumeroCuenta("8788");
    }

    @Test
    void validar_crear_cuenta_cuando_retorna_200() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/cuentas")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(cuentaDto))
                )
                .andExpect(status().isOk());

        //se verifica la llamada a la logica de negocio
        ArgumentCaptor<CuentaDto> cuentaCaptor = ArgumentCaptor.forClass(CuentaDto.class);
        Mockito.verify(cuentaTask, Mockito.times(1)).crearCuenta(cuentaCaptor.capture());
        assertEquals(cuentaCaptor.getValue().getNumeroCuenta() , cuentaDto.getNumeroCuenta());
        assertEquals(cuentaCaptor.getValue().getIdCliente(), cuentaDto.getIdCliente());
    }

    @Test
    void validar_crear_cuenta_cuando_retorna_400() throws Exception {
        cuentaDto.setNumeroCuenta(null);
        mockMvc.perform(MockMvcRequestBuilders.post("/cuentas")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(cuentaDto))
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    void validar_consulta_cuenta_por_id_retorna_200() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/cuentas/1")
                        .contentType("application/json")
                )
                .andExpect(status().isOk());
    }

    @Test
    void validar_consulta_cuenta_por_id_retorna_400() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/cuentas/uno")
                        .contentType("application/json")
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    void validar_editar_cuenta_cuando_retorna_200() throws Exception {
        cuentaDto.setId(1);
        cuentaDto.setNumeroCuenta("44453");
        mockMvc.perform(MockMvcRequestBuilders.put("/cuentas")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(cuentaDto))
                )
                .andExpect(status().isOk());

        //se verifica la llamada a la logica de negocio
        ArgumentCaptor<CuentaDto> cuentaCaptor = ArgumentCaptor.forClass(CuentaDto.class);
        Mockito.verify(cuentaTask, Mockito.times(1)).editarCuenta(cuentaCaptor.capture());
        assertEquals(cuentaCaptor.getValue().getNumeroCuenta(), cuentaDto.getNumeroCuenta());
        assertEquals(cuentaCaptor.getValue().getIdCliente() , cuentaDto.getIdCliente());
        assertEquals(cuentaCaptor.getValue().getSaldoInicial() , cuentaDto.getSaldoInicial());
    }

    @Test
    void validar_editar_cuenta_cuando_retorna_400() throws Exception {
        cuentaDto.setNumeroCuenta(null);
        mockMvc.perform(MockMvcRequestBuilders.put("/cuentas")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(cuentaDto))
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    void validar_actualizar_cuenta_cuando_retorna_200() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.patch("/cuentas/1")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(cuentaPatchDto))
                )
                .andExpect(status().isOk());
    }

    @Test
    void validar_actualizar_cuenta_cuando_retorna_400() throws Exception {
        cuentaPatchDto = null;
        mockMvc.perform(MockMvcRequestBuilders.patch("/cuentas/1")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(cuentaPatchDto))
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    void validar_eliminar_cuenta_cuando_retorna_200() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/cuentas/1")
                        .contentType("application/json")
                )
                .andExpect(status().isOk());
    }

    @Test
    void validar_eliminar_cuenta_cuando_retorna_400() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/cuentas/12-03-2023")
                        .contentType("application/json")
                )
                .andExpect(status().isBadRequest());
    }
}