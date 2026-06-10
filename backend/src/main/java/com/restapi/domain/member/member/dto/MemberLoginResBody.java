package com.restapi.domain.member.member.dto;

public record MemberLoginResBody(
        MemberDto item,
        String apiKey,
        String accessToken
) {
}
