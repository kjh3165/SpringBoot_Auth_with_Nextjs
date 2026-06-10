package com.restapi.domain.member.member.dto;

import org.springframework.lang.NonNull;

public record MemberLoginResBody(
        @NonNull MemberDto item,
        @NonNull String apiKey,
        @NonNull String accessToken
) {
}
