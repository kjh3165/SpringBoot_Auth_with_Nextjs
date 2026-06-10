package com.restapi.domain.post.post.dto;

public record PostWriteResBody (
        long totalCount,
        PostDto post
) {
}
