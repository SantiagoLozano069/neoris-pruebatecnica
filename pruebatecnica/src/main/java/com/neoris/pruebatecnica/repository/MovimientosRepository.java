package com.neoris.pruebatecnica.repository;

import com.neoris.pruebatecnica.entity.MovimientoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MovimientosRepository extends JpaRepository<MovimientoEntity, Integer> {

    @Query("SELECT M FROM MovimientoEntity M WHERE M.fecha BETWEEN :fechaInicial AND :fechaFinal")
    List<MovimientoEntity> getMovimientoEntityByFechas(LocalDate fechaInicial, LocalDate fechaFinal);
}
