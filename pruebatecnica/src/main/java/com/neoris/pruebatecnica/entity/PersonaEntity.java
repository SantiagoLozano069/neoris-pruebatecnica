package com.neoris.pruebatecnica.entity;

import com.neoris.pruebatecnica.enums.GeneroEnum;
import com.neoris.pruebatecnica.enums.converters.GeneroEnumConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "persona")
public class PersonaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", length = 8, nullable = false)
    private Integer id;

    @Column(name = "nombre", length = 50)
    private String nombre;

    @Column(name = "genero", length = 9)
    @Convert(converter = GeneroEnumConverter.class)
    private GeneroEnum genero;

    @Column(name = "edad", length = 3)
    private Integer edad;

    @Column(name = "identificacion", length = 10)
    private String identificacion;

    @Column(name = "direccion", length = 60)
    private String direccion;

    @Column(name = "telefono", length = 10)
    private String telefono;
}
