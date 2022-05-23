package com.master.demo.Repositories;

import com.master.demo.Entities.RegistroPeticion;
import com.master.demo.Entities.UsuariosCache;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UsuariosCacheRepository extends CrudRepository<UsuariosCache, Integer> {
    @Query(value = "SELECT rp.versiones_cacheadas FROM usuarios_cache rp\n" +
            "WHERE rp.usuario = ?1", nativeQuery = true)
    Optional<String> findVersionesByUsuario(String usuario);

    @Modifying
    @Query(value = "UPDATE tfm.usuarios_cache rp \n" +
            "SET rp.versiones_cacheadas=?2 \n" +
            "WHERE rp.usuario = ?1", nativeQuery = true)
    void updateVersiones(String usuario, String versiones);
}
