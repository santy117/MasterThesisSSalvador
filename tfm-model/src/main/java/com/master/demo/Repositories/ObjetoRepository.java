package com.master.demo.Repositories;


import com.master.demo.Entities.Objeto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ObjetoRepository extends CrudRepository<Objeto, Integer> {
    @Override
    List<Objeto> findAll();
}
