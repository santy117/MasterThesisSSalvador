package com.master.demo.Entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity // This tells Hibernate to make a table out of this class
@Data
@Table(name = "VERSION")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Version {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name= "ID_VERSION")
    private Integer idVersion;

    @Column(name= "ID_OBJETO")
    private Integer idObjeto;



}
