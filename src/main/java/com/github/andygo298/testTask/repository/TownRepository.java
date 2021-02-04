package com.github.andygo298.testTask.repository;

import com.github.andygo298.testTask.entity.Town;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TownRepository extends CrudRepository<Town, Long> {

    @Query("select m from Town m where m.townName=:name ")
    Optional<Town> findByTownName(@Param("name") String townName);

}
