package com.bridge.easyinterview.configs.querydsl;

import com.bridge.easyinterview.domains.entities.Candidate;
import com.bridge.easyinterview.domains.entities.Interviewer;
import com.bridge.easyinterview.domains.entities.Period;
import org.bitbucket.gt_tech.spring.data.querydsl.value.operators.experimental.QuerydslHttpRequestContextAwareServletFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import javax.servlet.Filter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static com.bridge.easyinterview.utils.PathUtils.RESOURCE_CANDIDATES;
import static com.bridge.easyinterview.utils.PathUtils.RESOURCE_INTERVIEWERS;
import static com.bridge.easyinterview.utils.PathUtils.RESOURCE_PERIODS;
import static com.bridge.easyinterview.utils.PathUtils.SLASH;
import static com.bridge.easyinterview.utils.PathUtils.joinStringsURL;
import static org.springframework.core.Ordered.LOWEST_PRECEDENCE;

@Configuration
@Order(LOWEST_PRECEDENCE)
public class QueryDslValueOperatorsConfig {

    @Value("${server.servlet.context-path}")
    private String contextPath;

    @Bean
    public FilterRegistrationBean<Filter> querydslHttpRequestContextAwareServletFilter() {
        String[] urlPatterns = new String[]{
                SLASH + joinStringsURL(RESOURCE_CANDIDATES, "*"),
                SLASH + joinStringsURL(RESOURCE_INTERVIEWERS, "*"),
                SLASH + joinStringsURL(RESOURCE_PERIODS, "*")

        };

        var bean = new FilterRegistrationBean<>();
        bean.setFilter(new QuerydslHttpRequestContextAwareServletFilter(querydslHttpRequestContextAwareServletFilterMappings()));
        bean.setAsyncSupported(false);
        bean.setEnabled(true);
        bean.setName("querydslHttpRequestContextAwareServletFilter");
        bean.setUrlPatterns(Arrays.asList(urlPatterns));
        bean.setOrder(LOWEST_PRECEDENCE);
        return bean;
    }

    private Map<String, Class<?>> querydslHttpRequestContextAwareServletFilterMappings() {
        Map<String, Class<?>> mappings = new HashMap<>();
        mappings.put(joinStringsURL(this.contextPath, RESOURCE_CANDIDATES), Candidate.class);
        mappings.put(joinStringsURL(this.contextPath, RESOURCE_INTERVIEWERS), Interviewer.class);
        mappings.put(joinStringsURL(this.contextPath, RESOURCE_PERIODS), Period.class);
        return mappings;
    }

}
