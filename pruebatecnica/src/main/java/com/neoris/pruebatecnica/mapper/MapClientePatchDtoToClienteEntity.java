package com.neoris.pruebatecnica.mapper;

import com.neoris.pruebatecnica.dto.ClientePatchDto;
import com.neoris.pruebatecnica.entity.ClienteEntity;
import lombok.NoArgsConstructor;
import org.springframework.util.ObjectUtils;

@NoArgsConstructor
public class MapClientePatchDtoToClienteEntity {

    public void mapClientePatchDtoToClienteEntity(ClientePatchDto clientePatchDto, ClienteEntity clienteEntity){

        if(!ObjectUtils.isEmpty(clientePatchDto.getEstado())){
            clienteEntity.setEstado(clientePatchDto.getEstado());
        }
        if(!ObjectUtils.isEmpty(clientePatchDto.getContrasena())){
            clienteEntity.setContrasena(clientePatchDto.getContrasena());
        }
        if(!ObjectUtils.isEmpty(clientePatchDto.getPersona().getIdentificacion())){
            clienteEntity.getPersona().setIdentificacion(clientePatchDto.getPersona().getIdentificacion());
        }
        if(!ObjectUtils.isEmpty(clientePatchDto.getPersona().getDireccion())){
            clienteEntity.getPersona().setDireccion(clientePatchDto.getPersona().getDireccion());
        }
        if(!ObjectUtils.isEmpty(clientePatchDto.getPersona().getEdad())){
            clienteEntity.getPersona().setEdad(clientePatchDto.getPersona().getEdad());
        }
        if(!ObjectUtils.isEmpty(clientePatchDto.getPersona().getGenero())){
            clienteEntity.getPersona().setGenero(clientePatchDto.getPersona().getGenero());
        }
        if(!ObjectUtils.isEmpty(clientePatchDto.getPersona().getNombre())){
            clienteEntity.getPersona().setNombre(clientePatchDto.getPersona().getNombre());
        }
        if(!ObjectUtils.isEmpty(clientePatchDto.getPersona().getTelefono())){
            clienteEntity.getPersona().setTelefono(clientePatchDto.getPersona().getTelefono());
        }
    }
}
