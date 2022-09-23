package com.bridge.easyinterview.commons;

import com.bridge.easyinterview.configs.exceptions.ValidationError;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.GenericTypeResolver;
import org.springframework.util.MultiValueMap;

import java.io.IOException;
import java.util.List;

import static com.bridge.easyinterview.utils.PathUtils.joinStringsURL;
import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(OrderAnnotation.class)
public class BaseControllerIntegrationTest<T> extends BaseIntegrationTest {

    @LocalServerPort
    private int port;

    @Value("${server.servlet.context-path}")
    protected String contextPath;

    protected ObjectMapper objectMapper;

    protected String BASE_URI;

    protected String RESOURCE;

    protected T sampleEntity;

    protected T sampleEntityGet;

    protected Class<T> entityClass;

    @BeforeEach
    void initialSetup() {
        RestAssured.port = port;

        objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

        this.entityClass = (Class<T>) GenericTypeResolver.resolveTypeArgument(getClass(), BaseControllerIntegrationTest.class);

        BASE_URI = String.format("%s:%s%s", RestAssured.DEFAULT_URI, RestAssured.port, contextPath);
    }

    protected void setBasePath() {
        basePath = joinStringsURL(contextPath, RESOURCE);
    }

    protected String getMockJson() throws IOException {
        return this.objectMapper.writeValueAsString(this.sampleEntity);
    }

    protected T getResponseObject(Response response) throws IOException {
        return this.getObject(response.jsonPath().prettify());
    }

    protected T getObject(String jsonObject) throws IOException {
        return this.objectMapper.readValue(jsonObject, entityClass);
    }

    protected List<T> getListFromResponseObject(Response response) {
        return response.jsonPath().getList("_embedded." + RESOURCE, entityClass);
    }

    protected List<T> test_request_get_all() {
        Response response = given().when().
                get().
                then().
                log().all(true).
                extract().response();

        assertEquals(HttpStatus.SC_OK, response.statusCode());
        return getListFromResponseObject(response);
    }

    protected T test_request_get(String id) throws IOException {
        Response response = given().when().
                get(id).
                then().
                log().all(true).
                extract().response();

        assertEquals(HttpStatus.SC_OK, response.statusCode());
        return getResponseObject(response);
    }

    protected List<T> test_request_getWithParams(MultiValueMap pathParams) {
        Response response = given().when().
                queryParams(pathParams).
                get().
                then().
                log().all(true).
                extract().response();

        assertEquals(HttpStatus.SC_OK, response.statusCode());
        return getListFromResponseObject(response);
    }

    protected T test_request_post() throws IOException {
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

        assertEquals(HttpStatus.SC_CREATED, response.statusCode());
        return getResponseObject(response);
    }

    protected T test_request_put(String id) throws IOException {
        Response response = given().
                contentType(ContentType.JSON).
                body(this.getMockJson()).
                when().
                put(id).
                then().extract().response();

        assertEquals(HttpStatus.SC_OK, response.statusCode());
        return getResponseObject(response);
    }

    protected T test_request_patch(String id, String jsonBody) throws IOException {
        Response response = given().
                contentType(ContentType.JSON).
                body(jsonBody).
                when().
                patch(id).
                then().extract().response();

        assertEquals(HttpStatus.SC_OK, response.statusCode());
        return getResponseObject(response);
    }

    protected void test_request_delete(String id) {
        Response response = given().
                contentType(ContentType.JSON).
                when().
                delete(id).
                then().extract().response();

        assertEquals(HttpStatus.SC_NO_CONTENT, response.statusCode());
    }

    protected ValidationError test_request_post_returning_constraint_violation_exception() throws IOException {
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

        assertEquals(HttpStatus.SC_BAD_REQUEST, response.statusCode());

        return this.objectMapper.readValue(response.jsonPath().prettify(), ValidationError.class);
    }

    protected ValidationError test_request_put_returning_constraint_violation_exception(String id) throws IOException {
        Response response = given().
                contentType(ContentType.JSON).
                body(this.getMockJson()).
                when().
                put(id).
                then().extract().response();

        assertEquals(HttpStatus.SC_BAD_REQUEST, response.statusCode());

        return this.objectMapper.readValue(response.jsonPath().prettify(), ValidationError.class);

    }

}
