package com.BJJ.BJJSite.Dto;

import java.sql.Time;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SessionDto {
    @JsonProperty("dayOfWeek")
    private String dayOfWeek;
    @JsonProperty("startTime")
    private String startTime;
    @JsonProperty("endTime")
    private String endTime;
    @JsonProperty("typeOfSession")
    private String typeOfSession;
}
