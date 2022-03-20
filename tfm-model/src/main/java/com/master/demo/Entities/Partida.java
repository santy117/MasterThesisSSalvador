package com.master.demo.Entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity // This tells Hibernate to make a table out of this class
@Data
@Table(name = "PARTIDA")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Partida {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name= "ID_PARTIDA")
    private Integer idPartida;

    @ManyToOne
    @JoinColumn(name= "ID_VERSION")
    Version version;

    @Column(name= "INFORMACION")
    private String informacion;

    @Column(name= "GASTOS")
    private String gastos;
}
