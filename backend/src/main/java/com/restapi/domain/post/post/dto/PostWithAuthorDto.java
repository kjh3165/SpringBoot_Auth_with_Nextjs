package com.restapi.domain.post.post.dto;

import com.restapi.domain.post.post.entity.Post;

import java.time.LocalDateTime;

public record PostWithAuthorDto(
        long id,
        LocalDateTime createDate,
        LocalDateTime modifyDate,
        String authorName,
        String title,
        String content,
        String author
) {
    public PostWithAuthorDto(Post post) {
        this(
                post.getId(),
                post.getCreateDate(),
                post.getModifyDate(),
                post.getAuthor().getNickname(),
                post.getTitle(),
                post.getContent(),
                post.getAuthor().getUsername()
        );
    }
}
