package com.bridge.easyinterview.domains.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

import static com.bridge.easyinterview.utils.Constants.DATABASE_SCHEMA;
import static javax.persistence.GenerationType.SEQUENCE;
import static javax.persistence.InheritanceType.SINGLE_TABLE;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Data
@SuperBuilder
@Entity
@Table(name = "SLOT", schema = DATABASE_SCHEMA)
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(ignoreUnknown = true)
@Inheritance(strategy = SINGLE_TABLE)
public class Slot implements Serializable {

    private static final long serialVersionUID = 2771532790655262799L;

    @Id
    @Column(name = "ID", nullable = false)
    @SequenceGenerator(name = "SLOT_SEQ", schema = DATABASE_SCHEMA, sequenceName = "SLOT_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = SEQUENCE, generator = "SLOT_SEQ")
    private Long id;

    @Column(name = "DATETIME")
    @NotNull(message = "Date time field cannot be null")
    private LocalDateTime dateTime;

}
