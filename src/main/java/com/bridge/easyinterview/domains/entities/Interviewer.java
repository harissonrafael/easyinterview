package com.bridge.easyinterview.domains.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.rest.core.annotation.RestResource;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

import static com.bridge.easyinterview.utils.Constants.DATABASE_SCHEMA;
import static javax.persistence.GenerationType.SEQUENCE;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "INTERVIEWER", schema = DATABASE_SCHEMA)
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Interviewer implements Serializable {

    private static final long serialVersionUID = -17613845502200457L;

    @Id
    @Column(name = "ID", nullable = false)
    @SequenceGenerator(name = "INTERVIEWER_SEQ", schema = DATABASE_SCHEMA, sequenceName = "INTERVIEWER_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = SEQUENCE, generator = "INTERVIEWER_SEQ")
    private Long id;

    @Column(name = "CODE", unique = true)
    @NotBlank(message = "Code field cannot be null or empty")
    private String code;

    @Column(name = "NAME")
    @NotBlank(message = "Name field cannot be null or empty")
    private String name;

    @RestResource(exported = false)
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "INTERVIEWER_ID", referencedColumnName = "ID", foreignKey = @ForeignKey(name = "SLOT_INTERVIEWER_FK"))
    private List<SlotInterviewer> slots;

}
