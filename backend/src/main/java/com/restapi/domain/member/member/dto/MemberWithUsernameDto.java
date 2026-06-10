package com.restapi.domain.member.member.dto;

import com.restapi.domain.member.member.entity.Member;

import java.time.LocalDateTime;

public record MemberWithUsernameDto(
        long id,
        LocalDateTime createDate,
        LocalDateTime modifyDate,
        String nickname,
        String username
)  {
    public MemberWithUsernameDto(Member member) {
        this(
                member.getId(),
                member.getCreateDate(),
                member.getModifyDate(),
                member.getNickname(),
                member.getUsername()
        );
    }
}
