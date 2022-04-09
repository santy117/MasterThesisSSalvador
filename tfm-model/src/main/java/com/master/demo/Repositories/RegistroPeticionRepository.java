package com.master.demo.Repositories;

import com.master.demo.Entities.RegistroPeticion;
import org.springframework.data.repository.CrudRepository;

public interface RegistroPeticionRepository extends CrudRepository<RegistroPeticion, Integer> {
}
