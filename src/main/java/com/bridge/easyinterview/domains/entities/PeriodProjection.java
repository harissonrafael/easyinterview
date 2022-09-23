package com.bridge.easyinterview.domains.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.rest.core.config.Projection;

import java.time.LocalDateTime;

@Projection(name = "period", types = {Period.class})
public interface PeriodProjection {

    @JsonIgnore
    String getId();

    LocalDateTime getDateTime();

    @JsonIgnore
    Long getCandidateId();

    @JsonIgnore
    Long getInterviewerId();

    String getCandidateName();

    String getInterviewerName();

}
