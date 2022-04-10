package com.master.demo.Repositories;

import com.master.demo.Entities.Partida;
import com.master.demo.Entities.RegistroPeticion;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RegistroPeticionRepository extends CrudRepository<RegistroPeticion, Integer> {
    @Query(value = "SELECT * FROM registro_peticion rp\n" +
            "WHERE rp.usuario = ?1", nativeQuery = true)
    List<RegistroPeticion> findRegistroByUsuario(String usuario);
}
