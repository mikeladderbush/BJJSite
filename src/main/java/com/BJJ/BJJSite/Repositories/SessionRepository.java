package com.BJJ.BJJSite.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.BJJ.BJJSite.Classes.Session;

/**
 * Repository interface for managing Session entities.
 * 
 * This interface extends JpaRepository but disables the default findById method.
 */
@Repository
public interface SessionRepository extends JpaRepository<Session, Integer> {

}
