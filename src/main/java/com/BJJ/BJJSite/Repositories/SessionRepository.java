package com.BJJ.BJJSite.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.BJJ.BJJSite.Classes.Session;

@Repository
public interface SessionRepository extends JpaRepository<Session, Integer> {
    Session getSessionById(Integer id);
}
