package com.master.demo.Entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity // This tells Hibernate to make a table out of this class
@Data
@Table(name = "REGISTRO_PETICION")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegistroPeticion {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name= "ID_PETICION")
    private Integer idPeticion;

    @OneToOne
    @JoinColumn(name= "ID_VERSION")
    private Version idVersion;

    @OneToOne
    @JoinColumn(name= "ID_OBJETO")
    private Objeto idObjeto;

    @Column(name= "TIPO_PETICION")
    private String tipoPeticion;

    @Column(name= "USUARIO")
    private String usuario;

}