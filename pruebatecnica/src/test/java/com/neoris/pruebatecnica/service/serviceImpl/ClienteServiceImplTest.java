package com.neoris.pruebatecnica.service.serviceImpl;

import com.neoris.pruebatecnica.dto.ClienteDto;
import com.neoris.pruebatecnica.dto.ClientePatchDto;
import com.neoris.pruebatecnica.dto.PersonaDto;
import com.neoris.pruebatecnica.entity.ClienteEntity;
import com.neoris.pruebatecnica.entity.PersonaEntity;
import com.neoris.pruebatecnica.enums.GeneroEnum;
import com.neoris.pruebatecnica.exception.NeorisException;
import com.neoris.pruebatecnica.repository.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class ClienteServiceImplTest {

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ClienteServiceImpl clienteServiceImpl;

    private PersonaDto personaDto;
    private ClienteDto clienteDto;
    private PersonaEntity personaEntity;
    private ClienteEntity clienteEntity;


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
    }

    @Test
    void validar_crear_cliente_cuando_respuesta_es_200() {
        when(clienteRepository.findByIdentificacion(clienteDto.getPersona().getIdentificacion())).thenReturn(Optional.empty());
        when(modelMapper.map(clienteDto, ClienteEntity.class)).thenReturn(clienteEntity);
        clienteEntity.setId(100);
        when(clienteRepository.save(clienteEntity)).thenReturn(clienteEntity);
        clienteDto.setId(100);
        when(modelMapper.map(clienteEntity, ClienteDto.class)).thenReturn(clienteDto);

        ClienteDto clienteRespuesta = clienteServiceImpl.crearCliente(clienteDto);

        assertEquals(clienteRespuesta.toString(), clienteDto.toString());
    }

    @Test
    void validar_crear_cliente_cuando_ya_existe_usuario() {
        when(clienteRepository.findByIdentificacion(clienteDto.getPersona().getIdentificacion())).thenReturn(Optional.of(clienteEntity));
        assertThrows(NeorisException.class, () ->{
            ClienteDto cliente =  clienteServiceImpl.crearCliente(clienteDto);
        });
    }
}