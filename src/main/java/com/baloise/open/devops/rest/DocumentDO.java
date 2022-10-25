package com.baloise.open.devops.rest;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.springframework.lang.Nullable;

@Data
@Builder
public class DocumentDO {
    @Nullable
    private String documentNo;
    @NonNull
    private String customerNo;
    @Nullable
    private String description;
    @NonNull
    private DocumentContentType contentType;
    @Nullable
    private String signedAt;
}