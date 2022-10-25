package com.baloise.open.devops.monitor;

import ch.basler.soba.document.SoBaDocumentEvent;
import ch.basler.soba.document.configuration.KafkaConfig;
import ch.basler.soba.document.jpa.common.BpType;
import ch.basler.soba.document.jpa.common.DocumentType;
import ch.basler.soba.document.jpa.control.ControlStatus;
import ch.basler.soba.document.jpa.control.DocumentControlService;
import ch.basler.soba.document.jpa.event.ChangeType;
import ch.basler.soba.document.jpa.event.DocumentEvent;
import ch.basler.soba.document.jpa.event.DocumentEventService;
import ch.basler.soba.document.mapper.DocumentEventToKafkaDocumentEventMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.AsyncResult;

import java.util.Collections;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class KafkaServiceTest {
    @Mock
    private DocumentControlService documentControlService;

    @Mock
    private DocumentEventService documentEventService;

    @Mock
    private KafkaTemplate<String, SoBaDocumentEvent> kafkaTemplate;

    private static final DocumentEventToKafkaDocumentEventMapper soBaDocumentEventMapper = Mappers.getMapper(DocumentEventToKafkaDocumentEventMapper.class);

    @Mock
    private KafkaConfig kafkaConfig;

    private KafkaService kafkaService;

    private final static long LOAD_ID = 42;
    private final static String KAFKA_TOPIC = "TOPIC";

    private static final UUID EVENT_ID = UUID.randomUUID();
    private final static DocumentEvent DB_EVENT = DocumentEvent
            .builder()
            .eventId(EVENT_ID)
            .bpNr("FooBarBaz")
            .partnerNr(23)
            .soBaId("Foo Bar Baz")
            .loadId(LOAD_ID)
            .documentType(DocumentType.WAIVER_VERS)
            .bpType(BpType.SINGLE_ACCOUNT)
            .documentName("Document Name")
            .changeType(ChangeType.CREATED)
            .build();

    private final static SoBaDocumentEvent KAFKA_EVENT = soBaDocumentEventMapper.toKafkaDocumentEvent(DB_EVENT);

    @BeforeEach
    public void setUp() {
        kafkaService = new KafkaService(kafkaTemplate, kafkaConfig, soBaDocumentEventMapper, documentControlService, documentEventService);
        when(kafkaConfig.getTopic()).thenReturn(KAFKA_TOPIC);
    }

    @Test
    public void testSend() {
        when(kafkaTemplate.send(eq(KAFKA_TOPIC), eq(KAFKA_EVENT))).thenReturn(new AsyncResult<>(null));

        kafkaService.publishKafkaEvents(LOAD_ID, Collections.singletonList(DB_EVENT));

        verify(documentControlService).setStatus(eq(LOAD_ID), eq(ControlStatus.SENDING_KAFKA));
        verify(kafkaTemplate).send(eq(KAFKA_TOPIC), eq(KAFKA_EVENT));
        verify(documentControlService).setEventKafka(eq(LOAD_ID));
        verify(documentEventService).markSent(eq(EVENT_ID));
    }
}