package com.master.demo.Entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity // This tells Hibernate to make a table out of this class
@Data
@Table(name = "USUARIOS_CACHE")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuariosCache {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name= "ID_USUARIO")
    private Integer idUsuario;

    @Column(name= "USUARIO")
    private String usuario;

    @Column(name= "VERSIONES_CACHEADAS")
    private String versionesCacheadas;

}