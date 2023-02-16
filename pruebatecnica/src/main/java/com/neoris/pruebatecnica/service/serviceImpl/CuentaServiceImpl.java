package com.neoris.pruebatecnica.service.serviceImpl;

import com.neoris.pruebatecnica.dto.CuentaDto;
import com.neoris.pruebatecnica.dto.CuentaPatchDto;
import com.neoris.pruebatecnica.entity.ClienteEntity;
import com.neoris.pruebatecnica.entity.CuentaEntity;
import com.neoris.pruebatecnica.enums.MensajesErrorNegocio;
import com.neoris.pruebatecnica.exception.NeorisException;
import com.neoris.pruebatecnica.mapper.MapCuentaPatchDtoToCuentaEntity;
import com.neoris.pruebatecnica.repository.ClienteRepository;
import com.neoris.pruebatecnica.repository.CuentaRepository;
import com.neoris.pruebatecnica.service.CuentaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Optional;

@Service
public class CuentaServiceImpl implements CuentaService {

    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CuentaDto crearCuenta(CuentaDto cuentaDto) throws NeorisException {
       if(this.obtenerCuenta(cuentaDto.getNumeroCuenta()).isPresent()){
           throw new NeorisException(MensajesErrorNegocio.EXISTE_CUENTA_CON_MISMO_NUMERO_CUENTA.getMensaje(),
                   HttpStatus.OK);
       }else{
           Optional<ClienteEntity> clienteEntity = this.obtenerCliente(cuentaDto.getIdCliente());
           if(clienteEntity.isPresent()){
               CuentaEntity cuentaEntity = modelMapper.map(cuentaDto, CuentaEntity.class);
               cuentaEntity.setCliente(clienteEntity.get());
               CuentaEntity cuentaGuardada = cuentaRepository.save(cuentaEntity);
               return modelMapper.map(cuentaGuardada, CuentaDto.class);
           }else{
               throw new NeorisException(MensajesErrorNegocio.NO_EXISTE_USUARIO_CON_ESTE_ID.getMensaje() + cuentaDto.getIdCliente() ,
                       HttpStatus.OK);
           }
       }
    }
    private Optional<CuentaEntity> obtenerCuenta(String numeroCuenta){
        return cuentaRepository.findByNumeroCuenta(numeroCuenta);
    }

    private Optional<ClienteEntity> obtenerCliente(Integer idCliente){
        return clienteRepository.findById(idCliente);
    }

    @Override
    public CuentaDto obtenerCuentaPorId(Integer idCuenta) throws NeorisException {
        Optional<CuentaEntity> cuentaEntity = cuentaRepository.findById(idCuenta);
        if(cuentaEntity.isPresent()){
            return modelMapper.map(cuentaEntity.get(), CuentaDto.class);
        }else{
            throw new NeorisException(MensajesErrorNegocio.NO_EXISTE_CUENTA_CON_ESTE_ID.getMensaje() + idCuenta ,
                    HttpStatus.OK);
        }
    }

    @Override
    public CuentaDto editarCuenta(CuentaDto cuenta) throws NeorisException {
        if(!ObjectUtils.isEmpty(cuenta.getId())){
            Optional<CuentaEntity> cuentaEntity = cuentaRepository.findById(cuenta.getId());
            if(cuentaEntity.isPresent()){
                Optional<ClienteEntity> clienteEntity = this.obtenerCliente(cuenta.getIdCliente());
                if(clienteEntity.isPresent()){
                    CuentaEntity cuentaEntityPersistir = modelMapper.map(cuenta, CuentaEntity.class);
                    cuentaEntityPersistir.setCliente(clienteEntity.get());
                    CuentaEntity cuentaGuardada = cuentaRepository.save(cuentaEntityPersistir);
                    return modelMapper.map(cuentaGuardada, CuentaDto.class);
                }else{
                    throw new NeorisException(MensajesErrorNegocio.NO_EXISTE_USUARIO_CON_ESTE_ID.getMensaje() + cuenta.getIdCliente() ,
                            HttpStatus.OK);
                }
            }else{
                throw new NeorisException(MensajesErrorNegocio.NO_EXISTE_CUENTA_CON_ESTE_ID.getMensaje() + cuenta.getId(),
                        HttpStatus.OK);
            }
        }else{
            throw new NeorisException(MensajesErrorNegocio.ID_CUENTA_NULL.getMensaje(),
                    HttpStatus.OK);
        }
    }

    @Override
    public CuentaDto actualizarCuenta(Integer idCuenta, CuentaPatchDto cuenta) throws NeorisException {
        Optional<CuentaEntity> cuentaEntity = cuentaRepository.findById(idCuenta);
        if (cuentaEntity.isPresent()) {
            Optional<ClienteEntity> clienteEntity = Optional.empty();
            if(!ObjectUtils.isEmpty(cuenta.getIdCliente())){
                clienteEntity = this.obtenerCliente(cuenta.getIdCliente());
            }
            MapCuentaPatchDtoToCuentaEntity mapCuentaPatchDtoToCuentaEntity = new MapCuentaPatchDtoToCuentaEntity();
            mapCuentaPatchDtoToCuentaEntity.mapCuentaPatchDtoToCuentaEntity(cuenta, cuentaEntity.get(), clienteEntity);
            return modelMapper.map(cuentaRepository.save(cuentaEntity.get()), CuentaDto.class);
        } else {
            throw new NeorisException(MensajesErrorNegocio.NO_EXISTE_CUENTA_CON_ESTE_ID.getMensaje() + idCuenta,
                    HttpStatus.OK);
        }
    }

    @Override
    public CuentaDto eliminarCuenta(Integer id) throws NeorisException {
        Optional<CuentaEntity> cuentaEntity = cuentaRepository.findById(id);
        if(cuentaEntity.isPresent()){
            cuentaRepository.deleteById(id);
            return modelMapper.map(cuentaEntity.get(), CuentaDto.class);
        }else {
            throw new NeorisException(MensajesErrorNegocio.NO_EXISTE_CUENTA_CON_ESTE_ID.getMensaje() + id,
                    HttpStatus.OK);
        }
    }
}
