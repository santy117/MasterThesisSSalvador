package com.master.demo.Entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity // This tells Hibernate to make a table out of this class
@Data
@Table(name = "NOTIFICACION")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notificacion {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name= "ID_NOTIFICACION")
    private Integer idNotificacion;

    @Column(name= "MENSAJE")
    private String mensaje;

    @OneToOne
    @JoinColumn(name= "ID_PARTIDA")
    private Partida partida;

    @Column(name= "USUARIO")
    private String usuario;

}
