package com.restapi.domain.post.post.dto;

import org.springframework.lang.NonNull;

public record AdmPostCountResBody(
        @NonNull long all
) {
}
