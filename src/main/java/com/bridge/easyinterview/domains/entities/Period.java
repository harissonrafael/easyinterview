package com.bridge.easyinterview.domains.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Immutable;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.io.Serializable;
import java.time.LocalDateTime;

import static com.bridge.easyinterview.utils.Constants.DATABASE_SCHEMA;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Data
@Builder
@Entity
@Immutable
@Table(name = "PERIOD_V", schema = DATABASE_SCHEMA,
        uniqueConstraints = {@UniqueConstraint(name = "PERIOD_V", columnNames = {"DATETIME", "CANDIDATE_ID", "INTERVIEWER_ID"})})
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Period implements Serializable {

    private static final long serialVersionUID = 7487164693162502259L;

    @Id
    private String id;

    @Column(name = "DATETIME")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime dateTime;

    @Column(name = "CANDIDATE_ID")
    private Long candidateId;

    @Column(name = "INTERVIEWER_ID")
    private Long interviewerId;

    @Column(name = "CANDIDATE_NAME")
    private String candidateName;

    @Column(name = "INTERVIEWER_NAME")
    private String interviewerName;

}
