package com.master.demo.Repositories;

import com.master.demo.Entities.Notificacion;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface NotificacionRepository extends CrudRepository<Notificacion, Integer> {

    @Query(value = "SELECT *  FROM notificacion n " +
            "WHERE n.id_partida = ?1", nativeQuery = true)
    Notificacion findByIdPartida(Integer idPartida);
}
