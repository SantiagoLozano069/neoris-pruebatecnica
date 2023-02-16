package com.neoris.pruebatecnica.mapper;

import com.neoris.pruebatecnica.dto.ClientePatchDto;
import com.neoris.pruebatecnica.dto.MovimientoPatchDto;
import com.neoris.pruebatecnica.entity.ClienteEntity;
import com.neoris.pruebatecnica.entity.CuentaEntity;
import com.neoris.pruebatecnica.entity.MovimientoEntity;
import lombok.NoArgsConstructor;
import org.springframework.util.ObjectUtils;

import java.util.Optional;

@NoArgsConstructor
public class MapMovimientoPatchDtoToMovimientoEntity {

    public void mapMovimientoPatchDtoToMovimientoEntity(MovimientoPatchDto movimientoPatchDto,
                                                        MovimientoEntity movimientoEntity, Optional<CuentaEntity> cuentaEntity){

        if(!ObjectUtils.isEmpty(movimientoPatchDto.getFecha())){
            movimientoEntity.setFecha(movimientoPatchDto.getFecha());
        }
        if(!ObjectUtils.isEmpty(movimientoPatchDto.getTipoMovimiento())){
            movimientoEntity.setTipoMovimiento(movimientoPatchDto.getTipoMovimiento().getMovimiento());
        }
        if(!ObjectUtils.isEmpty(movimientoPatchDto.getValor())){
            movimientoEntity.setValor(movimientoPatchDto.getValor());
        }
        if(!ObjectUtils.isEmpty(movimientoPatchDto.getSaldo())){
            movimientoEntity.setSaldo(movimientoPatchDto.getSaldo());
        }
        if(cuentaEntity.isPresent()){
            movimientoEntity.setCuenta(cuentaEntity.get());
        }
    }
}
