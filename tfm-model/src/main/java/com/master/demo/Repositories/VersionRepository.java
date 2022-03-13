package com.master.demo.Repositories;

import com.master.demo.Entities.Version;
import org.springframework.data.repository.CrudRepository;

public interface VersionRepository extends CrudRepository<Version, Integer> {
}
