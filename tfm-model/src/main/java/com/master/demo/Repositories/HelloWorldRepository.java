package com.master.demo.Repositories;

import com.master.demo.Entities.HelloWorld;
import org.springframework.data.repository.CrudRepository;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface HelloWorldRepository extends CrudRepository<HelloWorld, Integer> {

}
