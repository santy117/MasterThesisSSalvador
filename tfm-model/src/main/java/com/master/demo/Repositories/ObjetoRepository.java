package com.master.demo.Repositories;


import com.master.demo.Entities.Objeto;
import org.springframework.data.jpa.repository.Modifying;
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

    @Modifying
    @Query(value = "UPDATE tfm.objeto\n" +
            "SET objeto.id_version=?2 \n" +
            "WHERE objeto.id_objeto=?1", nativeQuery = true)
    void updateVersion(Integer objectId, Integer idVersion);
}
