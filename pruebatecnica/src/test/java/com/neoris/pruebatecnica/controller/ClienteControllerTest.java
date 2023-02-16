package com.neoris.pruebatecnica.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neoris.pruebatecnica.dto.ClienteDto;
import com.neoris.pruebatecnica.dto.ClientePatchDto;
import com.neoris.pruebatecnica.dto.PersonaDto;
import com.neoris.pruebatecnica.entity.ClienteEntity;
import com.neoris.pruebatecnica.entity.PersonaEntity;
import com.neoris.pruebatecnica.enums.GeneroEnum;
import com.neoris.pruebatecnica.task.ClienteTask;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(ClienteController.class)
class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ClienteTask clienteTask;

    private PersonaDto personaDto;
    private ClienteDto clienteDto;
    private PersonaEntity personaEntity;
    private ClienteEntity clienteEntity;

    private ClientePatchDto clientePatchDto;


    @BeforeEach
    void init() {
        personaDto = new PersonaDto();
        personaDto.setNombre("Santiago Lozano");
        personaDto.setIdentificacion("1007445");
        personaDto.setEdad(22);
        personaDto.setDireccion("Manzana 7 Casa 8");
        personaDto.setGenero(GeneroEnum.MASCULINO);
        personaDto.setTelefono("3126550636");

        clienteDto = new ClienteDto();
        clienteDto.setPersona(personaDto);
        clienteDto.setContrasena("1234");
        clienteDto.setEstado(true);

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

        clientePatchDto = new ClientePatchDto();
        clientePatchDto.setEstado(false);
    }

    @Test
    void validar_crear_usuario_cuando_retorna_200() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/clientes")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(clienteDto))
                )
                .andExpect(status().isOk());

        //se verifica la llamada a la logica de negocio
        ArgumentCaptor<ClienteDto> clienteCaptor = ArgumentCaptor.forClass(ClienteDto.class);
        Mockito.verify(clienteTask, Mockito.times(1)).crearCliente(clienteCaptor.capture());
        assertEquals(clienteCaptor.getValue().getPersona().getIdentificacion(), clienteDto.getPersona().getIdentificacion());
        assertEquals(clienteCaptor.getValue().getPersona().getNombre(), clienteDto.getPersona().getNombre());
    }

    @Test
    void validar_crear_usuario_cuando_retorna_400() throws Exception {
        clienteDto.setContrasena(null);
        mockMvc.perform(MockMvcRequestBuilders.post("/clientes")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(clienteDto))
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    void validar_consulta_cliente_por_id_retorna_200() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/clientes/7")
                        .contentType("application/json")
                )
                .andExpect(status().isOk());
    }

    @Test
    void validar_consulta_cliente_por_id_retorna_400() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/clientes/uno")
                        .contentType("application/json")
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    void validar_editar_usuario_cuando_retorna_200() throws Exception {
        clienteDto.setId(1);
        clienteDto.getPersona().setTelefono("3144431908");
        mockMvc.perform(MockMvcRequestBuilders.put("/clientes")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(clienteDto))
                )
                .andExpect(status().isOk());

        //se verifica la llamada a la logica de negocio
        ArgumentCaptor<ClienteDto> clienteCaptor = ArgumentCaptor.forClass(ClienteDto.class);
        Mockito.verify(clienteTask, Mockito.times(1)).editarCliente(clienteCaptor.capture());
        assertEquals(clienteCaptor.getValue().getPersona().getIdentificacion(), clienteDto.getPersona().getIdentificacion());
        assertEquals(clienteCaptor.getValue().getPersona().getNombre(), clienteDto.getPersona().getNombre());
        assertEquals(clienteCaptor.getValue().getId(), clienteDto.getId());
    }

    @Test
    void validar_editar_usuario_cuando_retorna_400() throws Exception {
        clienteDto.setPersona(null);
        mockMvc.perform(MockMvcRequestBuilders.put("/clientes")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(clienteDto))
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    void validar_actualizar_usuario_cuando_retorna_200() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.patch("/clientes/1")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(clientePatchDto))
                )
                .andExpect(status().isOk());
    }

    @Test
    void validar_actualizar_usuario_cuando_retorna_400() throws Exception {
        clienteDto = null;
        mockMvc.perform(MockMvcRequestBuilders.patch("/clientes/1")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(clienteDto))
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    void validar_eliminar_usuario_cuando_retorna_200() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/clientes/1")
                        .contentType("application/json")
                )
                .andExpect(status().isOk());
    }

    @Test
    void validar_eliminar_usuario_cuando_retorna_400() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/clientes/12-03-2023")
                        .contentType("application/json")
                )
                .andExpect(status().isBadRequest());
    }

}