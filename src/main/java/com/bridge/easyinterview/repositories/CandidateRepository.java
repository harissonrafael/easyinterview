package com.bridge.easyinterview.repositories;

import com.bridge.easyinterview.configs.querydsl.CustomQuerydslBinderCustomizer;
import com.bridge.easyinterview.domains.entities.Candidate;
import com.bridge.easyinterview.domains.entities.QCandidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CandidateRepository extends JpaRepository<Candidate, Long>
        , QuerydslPredicateExecutor<Candidate>, CustomQuerydslBinderCustomizer<QCandidate> {
}
