package com.bridge.easyinterview.repositories;

import com.bridge.easyinterview.configs.querydsl.CustomQuerydslBinderCustomizer;
import com.bridge.easyinterview.domains.entities.Period;
import com.bridge.easyinterview.domains.entities.QPeriod;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource
public interface PeriodRepository extends PagingAndSortingRepository<Period, String>, QueryByExampleExecutor<Period>
        , QuerydslPredicateExecutor<Period>, CustomQuerydslBinderCustomizer<QPeriod> {

    @RestResource(exported = false)
    @Override
    <S extends Period> S save(S entity);

    @RestResource(exported = false)
    @Override
    <S extends Period> Iterable<S> saveAll(Iterable<S> entities);

    @RestResource(exported = false)
    @Override
    void deleteById(String s);

}
