package com.restapi.domain.post.post.dto;

import com.restapi.domain.post.post.entity.Post;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;

public record PostWithAuthorDto(
        @NonNull long id,
        @NonNull LocalDateTime createDate,
        @NonNull LocalDateTime modifyDate,
        @NonNull Long authorId,
        @NonNull String authorName,
        @NonNull String title,
        @NonNull String content,
        @NonNull String author
) {
    public PostWithAuthorDto(Post post) {
        this(
                post.getId(),
                post.getCreateDate(),
                post.getModifyDate(),
                post.getAuthor().getId(),
                post.getAuthor().getNickname(),
                post.getTitle(),
                post.getContent(),
                post.getAuthor().getUsername()
        );
    }
}
