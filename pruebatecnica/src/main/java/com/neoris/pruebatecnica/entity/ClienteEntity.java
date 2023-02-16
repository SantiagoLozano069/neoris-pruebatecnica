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
@Table(name = "cliente")
public class ClienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", length = 8, nullable = false)
    private Integer id;

    @Column(name = "contrasena", length = 4)
    private String contrasena;

    @Column(name = "estado", length = 10 )
    private Boolean estado;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_persona", nullable = false)
    private PersonaEntity persona;

}
