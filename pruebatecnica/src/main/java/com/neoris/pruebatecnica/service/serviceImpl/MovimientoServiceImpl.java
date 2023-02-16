package com.neoris.pruebatecnica.service.serviceImpl;

import com.neoris.pruebatecnica.dto.MovimientoDto;
import com.neoris.pruebatecnica.dto.MovimientoPatchDto;
import com.neoris.pruebatecnica.dto.ReporteDto;
import com.neoris.pruebatecnica.entity.ClienteEntity;
import com.neoris.pruebatecnica.entity.CuentaEntity;
import com.neoris.pruebatecnica.entity.MovimientoEntity;
import com.neoris.pruebatecnica.enums.MensajesErrorNegocio;
import com.neoris.pruebatecnica.enums.TipoCuentaEnum;
import com.neoris.pruebatecnica.enums.TipoMovimientoEnum;
import com.neoris.pruebatecnica.exception.NeorisException;
import com.neoris.pruebatecnica.mapper.MapMovimientoPatchDtoToMovimientoEntity;
import com.neoris.pruebatecnica.repository.ClienteRepository;
import com.neoris.pruebatecnica.repository.CuentaRepository;
import com.neoris.pruebatecnica.repository.MovimientosRepository;
import com.neoris.pruebatecnica.service.MovimientoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovimientoServiceImpl implements MovimientoService {

    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private MovimientosRepository movimientosRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public MovimientoDto crearMovimiento(MovimientoDto movimientoDto) throws NeorisException {
        Optional<CuentaEntity> cuentaEntity = this.obtenerCuentaPorId(movimientoDto.getIdCuenta());
        if(cuentaEntity.isPresent()){
            if(movimientoDto.getTipoMovimiento().equals(TipoMovimientoEnum.CREDITO)){
                movimientoDto.setSaldo(movimientoDto.getSaldo().add(movimientoDto.getValor()));
            }else{
                if(movimientoDto.getSaldo().equals(BigDecimal.ZERO)){
                    throw new NeorisException(MensajesErrorNegocio.SALDO_NO_DISPONIBLE.getMensaje(),
                            HttpStatus.OK);
                }
                movimientoDto.setSaldo(movimientoDto.getSaldo().subtract(movimientoDto.getValor()));
            }
            MovimientoEntity movimientoEntity = modelMapper.map(movimientoDto, MovimientoEntity.class);
            movimientoEntity.setCuenta(cuentaEntity.get());
            return modelMapper.map(movimientosRepository.save(movimientoEntity), MovimientoDto.class);
        }else{
            throw new NeorisException(MensajesErrorNegocio.NO_EXISTE_CUENTA_CON_ESTE_ID.getMensaje() + movimientoDto.getIdCuenta() ,
                    HttpStatus.OK);
        }
    }

    private Optional<CuentaEntity> obtenerCuentaPorId(Integer idCuenta){
        return cuentaRepository.findById(idCuenta);
    }

    @Override
    public MovimientoDto obtenerMovimientoPorId(Integer idMovimiento) throws NeorisException {
        Optional<MovimientoEntity> movimientoEntity = movimientosRepository.findById(idMovimiento);
        if(movimientoEntity.isPresent()){
            return modelMapper.map(movimientoEntity.get(), MovimientoDto.class);
        }else{
            throw new NeorisException(MensajesErrorNegocio.NO_EXISTE_MOVIMIENTO_CON_ESTE_ID.getMensaje() + idMovimiento ,
                    HttpStatus.OK);
        }
    }

    @Override
    public MovimientoDto editarMovimiento(MovimientoDto movimientoDto) throws NeorisException {
        if(!ObjectUtils.isEmpty(movimientoDto.getId())){
            Optional<MovimientoEntity> movimientoEntity = movimientosRepository.findById(movimientoDto.getId());
            if(movimientoEntity.isPresent()){
                Optional<CuentaEntity> cuentaEntity = this.obtenerCuentaPorId(movimientoDto.getIdCuenta());
                if(cuentaEntity.isPresent()){
                    MovimientoEntity movimientoEntityPersistir = modelMapper.map(movimientoDto, MovimientoEntity.class);
                    movimientoEntityPersistir.setCuenta(cuentaEntity.get());
                    MovimientoEntity movimientoGuardado = movimientosRepository.save(movimientoEntityPersistir);
                    return modelMapper.map(movimientoGuardado, MovimientoDto.class);
                }else{
                    throw new NeorisException(MensajesErrorNegocio.NO_EXISTE_CUENTA_CON_ESTE_ID.getMensaje() + movimientoDto.getIdCuenta() ,
                            HttpStatus.OK);
                }
            }else{
                throw new NeorisException(MensajesErrorNegocio.NO_EXISTE_MOVIMIENTO_CON_ESTE_ID.getMensaje() + movimientoDto.getId(),
                        HttpStatus.OK);
            }
        }else{
            throw new NeorisException(MensajesErrorNegocio.ID_MOVIMIENTO_NULL.getMensaje(),
                    HttpStatus.OK);
        }
    }

    @Override
    public MovimientoDto actualizarMovimiento(Integer idMovimiento, MovimientoPatchDto movimientoDto) throws NeorisException {
        Optional<MovimientoEntity> movimientoEntity = movimientosRepository.findById(idMovimiento);
        if (movimientoEntity.isPresent()) {
            Optional<CuentaEntity> cuentaEntity = Optional.empty();
            if(!ObjectUtils.isEmpty(movimientoDto.getIdCuenta())){
                cuentaEntity = cuentaRepository.findById(movimientoDto.getIdCuenta());
            }
            MapMovimientoPatchDtoToMovimientoEntity mapMovimientoPatchDtoToMovimientoEntity = new MapMovimientoPatchDtoToMovimientoEntity();
            mapMovimientoPatchDtoToMovimientoEntity.mapMovimientoPatchDtoToMovimientoEntity(movimientoDto, movimientoEntity.get(), cuentaEntity);
            return modelMapper.map(movimientosRepository.save(movimientoEntity.get()), MovimientoDto.class);
        } else {
            throw new NeorisException(MensajesErrorNegocio.NO_EXISTE_MOVIMIENTO_CON_ESTE_ID.getMensaje() + idMovimiento,
                    HttpStatus.OK);
        }
    }

    @Override
    public MovimientoDto eliminarMovimiento(Integer id) throws NeorisException {
        Optional<MovimientoEntity> movimientoEntity = movimientosRepository.findById(id);
        if(movimientoEntity.isPresent()){
            movimientosRepository.deleteById(id);
            return modelMapper.map(movimientoEntity.get(), MovimientoDto.class);
        }else {
            throw new NeorisException(MensajesErrorNegocio.NO_EXISTE_MOVIMIENTO_CON_ESTE_ID.getMensaje() + id,
                    HttpStatus.OK);
        }
    }

    @Override
    public List<ReporteDto> obtenerReportePorFechas(LocalDate fechaInicial, LocalDate fechaFinal, Integer idCliente){
        Optional<ClienteEntity> clienteEntity = clienteRepository.findById(idCliente);
        if(clienteEntity.isPresent()){
           List<MovimientoEntity> movimientoEntityList = movimientosRepository.getMovimientoEntityByFechas(fechaInicial, fechaFinal);
            if(movimientoEntityList.isEmpty()){
                throw new NeorisException(MensajesErrorNegocio.NO_EXISTE_MOVIMIENTO_EN_EL_RAGO_DE_FECHAS.getMensaje()
                        + fechaInicial + " - " + fechaFinal,
                        HttpStatus.OK);
            }else{
               return this.generarListaReporte(movimientoEntityList, idCliente);
            }
        }else{
            throw new NeorisException(MensajesErrorNegocio.NO_EXISTE_USUARIO_CON_ESTE_ID.getMensaje() + idCliente,
                    HttpStatus.OK);
        }
    }

    private List<ReporteDto> generarListaReporte( List<MovimientoEntity> movimientoEntityList, Integer idCliente ){
        return movimientoEntityList.stream()
                .filter(movimiento -> movimiento.getCuenta().getCliente().getId().equals(idCliente))
                .map(movimiento -> ReporteDto.builder()
                        .fecha(movimiento.getFecha())
                        .cliente(movimiento.getCuenta().getCliente().getPersona().getNombre())
                        .numeroCuenta(movimiento.getCuenta().getNumeroCuenta())
                        .tipoCuenta(TipoCuentaEnum.getEnumByTipoCuenta(movimiento.getCuenta().getTipoCuenta()))
                        .saldoInicial(movimiento.getCuenta().getSaldoInicial())
                        .estado(movimiento.getCuenta().getEstado())
                        .tipoMovimiento(TipoMovimientoEnum.getEnumByMovimiento(movimiento.getTipoMovimiento()))
                        .saldoDisponible(movimiento.getSaldo())
                        .build()
                ).collect(Collectors.toList());
    }
}
