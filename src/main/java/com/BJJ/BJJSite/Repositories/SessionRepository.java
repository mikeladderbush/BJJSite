package com.BJJ.BJJSite.Repositories;

import java.sql.Time;
import java.time.DayOfWeek;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.BJJ.BJJSite.Classes.Session;

@Repository
public interface SessionRepository extends JpaRepository<Session, Integer> {
    Session getSessionById(Integer id);

    @Query("SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END FROM Session s WHERE s.dayOfWeek = :dayOfWeek AND ((s.startTime <= :endTime AND s.endTime >= :startTime))")
    boolean isOverlappingSession(@Param("dayOfWeek") DayOfWeek dayOfWeek, @Param("startTime") Time startTime,
            @Param("endTime") Time endTime);

}
