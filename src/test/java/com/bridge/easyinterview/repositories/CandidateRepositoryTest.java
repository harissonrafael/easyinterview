package com.bridge.easyinterview.repositories;

import com.bridge.easyinterview.commons.BaseControllerIntegrationTest;
import com.bridge.easyinterview.configs.exceptions.ValidationError;
import com.bridge.easyinterview.domains.entities.Candidate;
import com.bridge.easyinterview.domains.entities.SlotCandidate;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import static com.bridge.easyinterview.utils.PathUtils.RESOURCE_CANDIDATES;
import static java.lang.String.valueOf;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("Candidate integration test")
class CandidateRepositoryTest extends BaseControllerIntegrationTest<Candidate> {

    @BeforeEach
    void init() {
        RESOURCE = RESOURCE_CANDIDATES;
        setBasePath();

        SlotCandidate newSlot = SlotCandidate.builder()
                .dateTime(LocalDateTime.of(2022, 01, 03, 10, 00, 00))
                .build();

        this.sampleEntity = Candidate.builder()
                .name("Candidate 3")
                .slots(List.of(newSlot))
                .build();

        SlotCandidate slotCandidate2_1 = SlotCandidate.builder()
                .id(3L)
                .dateTime(LocalDateTime.of(2022, 01, 02, 10, 00, 00))
                .candidateId(2L)
                .build();

        SlotCandidate slotCandidate2_2 = SlotCandidate.builder()
                .id(4L)
                .dateTime(LocalDateTime.of(2022, 01, 06, 16, 00, 00))
                .candidateId(2L)
                .build();

        this.sampleEntityGet = Candidate.builder()
                .id(2L)
                .name("Candidate 2")
                .slots(List.of(slotCandidate2_1, slotCandidate2_2))
                .build();
    }

    @Test
    void test_get_all() {
        List<Candidate> candidates = super.test_request_get_all();

        assertNotNull(candidates);
    }

    @Test
    void test_get() throws IOException {
        Candidate retrieveEntity = super.test_request_get(valueOf(this.sampleEntityGet.getId()));

        assertEquals(retrieveEntity, this.sampleEntityGet);
    }

    @Test
    void test_post() throws IOException {
        Candidate insertedEntity = super.test_request_post();

        assertNotNull(insertedEntity.getId());

        insertedEntity.setId(null);
        assertEquals(insertedEntity.toString(), this.sampleEntity.toString());
    }

    @Test
    void test_put() throws IOException {
        this.sampleEntity.setName(this.sampleEntity.getName() + RandomStringUtils.randomAlphanumeric(3));

        Candidate updatedEntity = super.test_request_put("1");

        this.sampleEntity.setId(1L);
        assertEquals(updatedEntity.toString(), this.sampleEntity.toString());
    }

    @Test
    void test_patch() throws IOException {
        String newName = "Test patch 2";
        String body = "{\"name\" : \"" + newName + "\"}";

        Candidate updatedEntity = super.test_request_patch("2", body);

        assertEquals(newName, updatedEntity.getName());
    }

    @Test
    void test_delete() {
        List<Candidate> candidatesBefore = super.test_request_get_all();

        super.test_request_delete("1");

        List<Candidate> candidatesAfter = super.test_request_get_all();

        assertEquals(candidatesBefore.size(), candidatesAfter.size() + 1);
    }

    @Test
    void test_post_returning_constraint_violation_exception() throws IOException {
        this.sampleEntity.setName(null);

        ValidationError validationError = super.test_request_post_returning_constraint_violation_exception();

        assertEquals(1, validationError.getErrors().size());
    }

    @Test
    void test_put_returning_constraint_violation_exception() throws IOException {
        this.sampleEntity.setName(null);

        ValidationError validationError = super.test_request_put_returning_constraint_violation_exception("1");

        assertEquals(1, validationError.getErrors().size());
    }

}
