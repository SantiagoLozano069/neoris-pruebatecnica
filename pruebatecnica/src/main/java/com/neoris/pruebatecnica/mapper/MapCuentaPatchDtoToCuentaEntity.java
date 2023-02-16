package com.neoris.pruebatecnica.mapper;

import com.neoris.pruebatecnica.dto.CuentaPatchDto;
import com.neoris.pruebatecnica.entity.ClienteEntity;
import com.neoris.pruebatecnica.entity.CuentaEntity;
import lombok.NoArgsConstructor;
import org.springframework.util.ObjectUtils;

import java.util.Optional;

@NoArgsConstructor
public class MapCuentaPatchDtoToCuentaEntity {

    public void mapCuentaPatchDtoToCuentaEntity(CuentaPatchDto cuentaPatchDto, CuentaEntity cuentaEntity,
                                                Optional<ClienteEntity> clienteEntity){

        if(!ObjectUtils.isEmpty(cuentaPatchDto.getEstado() )){
            cuentaEntity.setEstado(cuentaPatchDto.getEstado());
        }
        if(!ObjectUtils.isEmpty(cuentaPatchDto.getNumeroCuenta())){
            cuentaEntity.setNumeroCuenta(cuentaPatchDto.getNumeroCuenta());
        }
        if(!ObjectUtils.isEmpty(cuentaPatchDto.getTipoCuenta())){
            cuentaEntity.setTipoCuenta(cuentaPatchDto.getTipoCuenta().getTipoCuenta());
        }
        if(!ObjectUtils.isEmpty(cuentaPatchDto.getSaldoInicial())){
            cuentaEntity.setSaldoInicial(cuentaPatchDto.getSaldoInicial());
        }
        if(clienteEntity.isPresent()){
            cuentaEntity.setCliente(clienteEntity.get());
        }
    }
}
