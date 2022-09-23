package com.bridge.easyinterview.repositories;

import com.bridge.easyinterview.configs.querydsl.CustomQuerydslBinderCustomizer;
import com.bridge.easyinterview.domains.entities.Interviewer;
import com.bridge.easyinterview.domains.entities.QInterviewer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface InterviewerRepository extends JpaRepository<Interviewer, Long>
        , QuerydslPredicateExecutor<Interviewer>, CustomQuerydslBinderCustomizer<QInterviewer> {
}
