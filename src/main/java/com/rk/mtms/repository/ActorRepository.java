package com.rk.mtms.repository;

import com.rk.mtms.entity.Actor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ActorRepository extends CrudRepository<Actor,Long> {
    @Query("SELECT u from Actor u where u.actorName=:requestedActorName")
    Actor getActorForId(@Param("requestedActorName") String requestedActorName);

    @Query("SELECT u from Actor u where u.id=:requestedActorId")
    Actor getActorForName(@Param("requestedActorId") int requestedActorId);

    @Query("SELECT u from Actor u ")
    List<Actor> getActorList();

    @Modifying
    @Query(value="insert into Actor(actor_name) VALUES(:actorName)",nativeQuery = true)
    @Transactional
    void insertActor(@Param("actorName") String actorName);


}
