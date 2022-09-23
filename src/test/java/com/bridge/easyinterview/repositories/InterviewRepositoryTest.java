package com.bridge.easyinterview.repositories;

import com.bridge.easyinterview.commons.BaseControllerIntegrationTest;
import com.bridge.easyinterview.configs.exceptions.ValidationError;
import com.bridge.easyinterview.domains.entities.Interviewer;
import com.bridge.easyinterview.domains.entities.SlotInterviewer;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import static com.bridge.easyinterview.utils.PathUtils.RESOURCE_INTERVIEWERS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("Interviewer integration test")
class InterviewRepositoryTest extends BaseControllerIntegrationTest<Interviewer> {

    @BeforeEach
    void init() {
        RESOURCE = RESOURCE_INTERVIEWERS;
        setBasePath();

        SlotInterviewer newSlot = SlotInterviewer.builder()
                .dateTime(LocalDateTime.of(2022, 01, 03, 10, 00, 00))
                .build();

        this.sampleEntity = Interviewer.builder()
                .name("Interviewer 3")
                .code("Code 3")
                .slots(List.of(newSlot))
                .build();

        SlotInterviewer slotInterviewer2_1 = SlotInterviewer.builder()
                .id(3L)
                .dateTime(LocalDateTime.of(2022, 01, 03, 10, 00, 00))
                .interviewerId(2L)
                .build();

        SlotInterviewer slotInterviewer2_2 = SlotInterviewer.builder()
                .id(4L)
                .dateTime(LocalDateTime.of(2022, 01, 06, 16, 00, 00))
                .interviewerId(2L)
                .build();

        this.sampleEntityGet = Interviewer.builder()
                .id(2L)
                .name("Interviewer 2")
                .code("Code 2")
                .slots(List.of(slotInterviewer2_1, slotInterviewer2_2))
                .build();
    }

    @Test
    void test_get_all() {
        List<Interviewer> interviewers = super.test_request_get_all();

        assertNotNull(interviewers);
    }

    @Test
    void test_get() throws IOException {
        Interviewer retrieveEntity = super.test_request_get(String.valueOf(this.sampleEntityGet.getId()));

        assertEquals(retrieveEntity.toString(), this.sampleEntityGet.toString());
    }

    @Test
    void test_post() throws IOException {
        Interviewer insertedEntity = super.test_request_post();

        assertNotNull(insertedEntity.getId());

        insertedEntity.setId(null);
        assertEquals(insertedEntity.toString(), this.sampleEntity.toString());
    }

    @Test
    void test_put() throws IOException {
        this.sampleEntity.setName(this.sampleEntity.getName() + RandomStringUtils.randomAlphanumeric(3));
        this.sampleEntity.setCode(this.sampleEntity.getCode() + RandomStringUtils.randomAlphanumeric(3));

        Interviewer updatedEntity = super.test_request_put("1");

        this.sampleEntity.setId(1L);
        assertEquals(updatedEntity.toString(), this.sampleEntity.toString());
    }

    @Test
    void test_patch() throws IOException {
        String newName = "Test patch 2";
        String newCode = "Test code 2";
        String body = "{\"name\" : \"" + newName + "\", \"code\" : \"" + newCode + "\"}";

        Interviewer updatedEntity = super.test_request_patch("2", body);

        assertEquals(newName, updatedEntity.getName());
        assertEquals(newCode, updatedEntity.getCode());
    }

    @Test
    void test_delete() {
        List<Interviewer> interviewersBefore = super.test_request_get_all();

        super.test_request_delete("1");

        List<Interviewer> interviewersAfter = super.test_request_get_all();

        assertEquals(interviewersBefore.size(), interviewersAfter.size() + 1);
    }

    @Test
    void test_post_returning_constraint_violation_exception() throws IOException {
        this.sampleEntity.setName(null);
        this.sampleEntity.setCode(null);

        ValidationError validationError = super.test_request_post_returning_constraint_violation_exception();

        assertEquals(2, validationError.getErrors().size());
    }

    @Test
    void test_put_returning_constraint_violation_exception() throws IOException {
        this.sampleEntity.setName(null);
        this.sampleEntity.setCode(null);

        ValidationError validationError = super.test_request_put_returning_constraint_violation_exception("1");

        assertEquals(2, validationError.getErrors().size());
    }

}
