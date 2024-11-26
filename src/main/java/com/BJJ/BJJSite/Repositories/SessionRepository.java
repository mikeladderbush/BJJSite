package com.BJJ.BJJSite.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.BJJ.BJJSite.Classes.Session;

@Repository
public interface SessionRepository extends JpaRepository<Session, Integer> {

    Session getSessionById(Integer id);

    @Query("SELECT s FROM Session s")
    List<Session> getAllSessions();
    
}
