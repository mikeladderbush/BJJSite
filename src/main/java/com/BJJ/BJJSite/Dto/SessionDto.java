package com.BJJ.BJJSite.Dto;

import java.sql.Time;
import java.time.DayOfWeek;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SessionDto {

    private Integer sessionId;

    private DayOfWeek dayOfWeek;

    private Time timeOfDay;

    private String typeOfSession;
}
