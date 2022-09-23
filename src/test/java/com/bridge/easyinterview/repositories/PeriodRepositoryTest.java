package com.bridge.easyinterview.repositories;

import com.bridge.easyinterview.commons.BaseControllerIntegrationTest;
import com.bridge.easyinterview.domains.entities.Period;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import static com.bridge.easyinterview.utils.PathUtils.RESOURCE_PERIODS;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Period integration test")
class PeriodRepositoryTest extends BaseControllerIntegrationTest<Period> {

    @BeforeEach
    void init() {
        RESOURCE = RESOURCE_PERIODS;
        setBasePath();

        this.sampleEntity = Period.builder()
                .candidateName("Candidate 3")
                .interviewerName("Interview 3")
                .build();

        this.sampleEntityGet = Period.builder()
                .id("2022-01-06T16:00:00_2_2")
                .dateTime(LocalDateTime.of(2022, 01, 06, 16, 00, 00))
                .candidateId(2L)
                .interviewerId(2L)
                .candidateName("Candidate 2")
                .interviewerName("Interview 2")
                .build();
    }

    @Test
    void test_get_all() {
        List<Period> periods = super.test_request_get_all();

        assertNotNull(periods);
    }

    @Test
    void test_getByParameter() {
        MultiValueMap<String, String> pathParams = new LinkedMultiValueMap<>();
        pathParams.set("projection", "period");
        pathParams.set("candidateName", this.sampleEntityGet.getCandidateName());

        List<Period> retrieveEntityList = super.test_request_getWithParams(pathParams);

        assertTrue(retrieveEntityList.stream().allMatch(p -> p.getCandidateName().equals(this.sampleEntityGet.getCandidateName())));
    }

    @Test
    void test_getByDate() {
        MultiValueMap<String, String> pathParams = new LinkedMultiValueMap<>();
        pathParams.set("dateTime", "2022-01-06T16:00:00");

        List<Period> retrieveEntityList = super.test_request_getWithParams(pathParams);

        assertTrue(retrieveEntityList.stream().allMatch(p -> p.getDateTime().equals(this.sampleEntityGet.getDateTime())));
    }

    @Test
    void test_getByDateRange() {
        MultiValueMap<String, String> pathParams = new LinkedMultiValueMap<>();
        pathParams.set("dateTime", "2022-01-06T15:00:00");
        pathParams.set("dateTime", "2022-01-06T17:00:00");

        List<Period> retrieveEntityList = super.test_request_getWithParams(pathParams);

        assertTrue(retrieveEntityList.stream().allMatch(p -> p.getDateTime().equals(this.sampleEntityGet.getDateTime())));
    }

    @Test
    void test_post() throws IOException {
        Response response = given().
                contentType(ContentType.JSON).
                body(this.getMockJson()).
                when().
                post().
                then().
                log().
                all(true).
                extract().
                response();

        Assertions.assertEquals(HttpStatus.SC_METHOD_NOT_ALLOWED, response.statusCode());
    }

    @Test
    void test_put() throws IOException {
        Response response = given().
                contentType(ContentType.JSON).
                body(this.getMockJson()).
                when().
                put(RandomStringUtils.randomAlphanumeric(10)).
                then().extract().response();

        Assertions.assertEquals(HttpStatus.SC_METHOD_NOT_ALLOWED, response.statusCode());
    }

    @Test
    void test_patch() throws IOException {
        Response response = given().
                contentType(ContentType.JSON).
                body(this.getMockJson()).
                when().
                patch(RandomStringUtils.randomAlphanumeric(10)).
                then().extract().response();

        Assertions.assertEquals(HttpStatus.SC_NOT_FOUND, response.statusCode());
    }

    @Test
    void test_delete() throws IOException {
        Response response = given().
                contentType(ContentType.JSON).
                body(this.getMockJson()).
                when().
                delete(RandomStringUtils.randomAlphanumeric(10)).
                then().extract().response();

        Assertions.assertEquals(HttpStatus.SC_METHOD_NOT_ALLOWED, response.statusCode());
    }

}
