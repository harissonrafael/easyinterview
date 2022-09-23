package com.bridge.easyinterview.configs.querydsl;

import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.dsl.DateTimePath;
import org.bitbucket.gt_tech.spring.data.querydsl.value.operators.ExpressionProviderFactory;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

public interface CustomQuerydslBinderCustomizer<T extends EntityPath<?>> extends QuerydslBinderCustomizer<T> {

    @Override
    default void customize(QuerydslBindings bindings, T t) {
        bindings.bind(String.class).all(ExpressionProviderFactory::getPredicate);
        bindings.bind(Integer.class).all(ExpressionProviderFactory::getPredicate);
        bindings.bind(Long.class).all(ExpressionProviderFactory::getPredicate);
        bindings.bind(BigDecimal.class).all(ExpressionProviderFactory::getPredicate);

        dateTimePathHandler(bindings, t);
    }

    default void dateTimePathHandler(QuerydslBindings bindings, T t) {
        bindings.bind(LocalDateTime.class).all((DateTimePath<LocalDateTime> path, Collection<? extends LocalDateTime> value) -> {
            ArrayList<LocalDateTime> dates = new ArrayList<>(value);
            Collections.sort(dates);
            if (dates.size() == 1) {
                return Optional.of(path.eq(dates.get(0)));
            } else {
                return Optional.of(path.between(dates.get(0), dates.get(1)));
            }
        });
    }

}
