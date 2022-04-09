package com.master.demo.Repositories;


import com.master.demo.Entities.Partida;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PartidaRepository extends CrudRepository<Partida, Integer> {

    @Query(value = "SELECT p.* FROM partida p\n" +
            "INNER JOIN version v\n" +
            "WHERE v.id_version = p.id_version\n" +
            "AND p.id_version = ?1", nativeQuery = true)
    List<Partida> findPartidaByIdVersion(Integer idVersion);

}
