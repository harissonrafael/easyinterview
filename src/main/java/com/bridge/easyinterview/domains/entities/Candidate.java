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
@Table(name = "CANDIDATE", schema = DATABASE_SCHEMA)
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
public class Candidate implements Serializable {

    private static final long serialVersionUID = -3482931382785096341L;

    @Id
    @Column(name = "ID", nullable = false)
    @SequenceGenerator(name = "CANDIDATE_SEQ", schema = DATABASE_SCHEMA, sequenceName = "CANDIDATE_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = SEQUENCE, generator = "CANDIDATE_SEQ")
    private Long id;

    @Column(name = "NAME")
    @NotBlank(message = "Name field cannot be null or empty")
    private String name;

    @RestResource(exported = false)
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "CANDIDATE_ID", referencedColumnName = "ID", foreignKey = @ForeignKey(name = "SLOT_CANDIDATE_FK"))
    private List<SlotCandidate> slots;

}
