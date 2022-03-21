package com.master.demo.Repositories;


import com.master.demo.Entities.Objeto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ObjetoRepository extends CrudRepository<Objeto, Integer> {
    @Override
    List<Objeto> findAll();

    @Query(value = "SELECT o.id_objeto, o.id_version, o.nombre  FROM objeto o\n" +
            "INNER JOIN version v \n" +
            "WHERE v.id_objeto = o.id_objeto \n" +
            "AND v.id_version = ?1", nativeQuery = true)
    Objeto findByIdVersion(Integer idVersion);
}
