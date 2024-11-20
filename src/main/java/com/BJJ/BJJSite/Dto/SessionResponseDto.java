package com.BJJ.BJJSite.Dto;

import java.sql.Time;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SessionResponseDto {
    
    private Integer sessionId;
    private Integer dayOfWeek;
    private Time startTime;
    private Time endTime;
    private String typeOfSession;
    
}
