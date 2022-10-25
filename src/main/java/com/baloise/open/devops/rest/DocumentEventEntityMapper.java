package com.baloise.open.devops.rest;

import ch.basler.soba.document.SoBaBpType;
import ch.basler.soba.document.SoBaDocumentEvent;
import ch.basler.soba.document.SoBaDocumentType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class DocumentEventEntityMapper {

    private static final SimpleDateFormat SIGNED_AT_FORMAT = new SimpleDateFormat("yyy-MM-dd HH:mm:ss.SSS");

    public DocumentDO eventToDo(SoBaDocumentEvent event) {
        final String signedAt = event.getDocument().getSignedAt() != null ?
                SIGNED_AT_FORMAT.format(Date.from(event.getDocument().getSignedAt().atStartOfDay().toInstant(ZoneOffset.MIN)))
                : null;
        return DocumentDO.builder()
                .description(event.getDocument().getBpNr())
                .documentNo(event.getDocument().getSoBaId())
                .contentType(mapContentType(event))
                .customerNo(String.valueOf(event.getDocument().getPartnerNr()))
                .signedAt(
                        signedAt)
                .build();
    }

    private DocumentContentType mapContentType(SoBaDocumentEvent event){
        final Function<SoBaBpType, DocumentContentType> mapSigned = t -> switch (t){
            case COMPANY_ACCOUNT -> DocumentContentType.WAIVER_SOBA_COMPANY_SIGNED;
            case JOINT_ACCOUNT -> DocumentContentType.WAIVER_SOBA_JOINT_SIGNED;
            case SINGLE_ACCOUNT -> DocumentContentType.WAIVER_SOBA_SINGLE_SIGNED;
        };

        final Function<SoBaBpType, DocumentContentType> mapUnsigned = t -> switch (t){
            case COMPANY_ACCOUNT -> DocumentContentType.WAIVER_SOBA_COMPANY_UNSIGNED;
            case JOINT_ACCOUNT -> DocumentContentType.WAIVER_SOBA_JOINT_UNSIGNED;
            case SINGLE_ACCOUNT -> DocumentContentType.WAIVER_SOBA_SINGLE_UNSIGNED;
        };

        final SoBaDocumentType docType = event.getDocument().getType();
        final SoBaBpType bpType = event.getDocument().getBpType();

        return switch(docType){
            case WAIVER_VERS -> mapSigned.apply(bpType);
            case NO_WAIVER -> mapUnsigned.apply(bpType);
        };
    }
}

