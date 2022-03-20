package com.master.demo.Entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity // This tells Hibernate to make a table out of this class
@Data
@Table(name = "OBJETO")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Objeto {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name= "ID_OBJETO")
    private Integer idObjeto;

    @ManyToOne
    @JoinColumn(name= "ID_VERSION")
    private Version idVersion;

    @Column(name= "NOMBRE")
    private String nombre;

}
