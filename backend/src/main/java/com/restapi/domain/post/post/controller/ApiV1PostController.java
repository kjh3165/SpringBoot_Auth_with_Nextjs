package com.restapi.domain.post.post.controller;

import com.restapi.domain.member.member.entity.Member;
import com.restapi.domain.post.post.dto.PostDto;
import com.restapi.domain.post.post.dto.PostModifyReqBody;
import com.restapi.domain.post.post.dto.PostWithAuthorDto;
import com.restapi.domain.post.post.dto.PostWriteReqBody;
import com.restapi.domain.post.post.entity.Post;
import com.restapi.domain.post.post.service.PostService;
import com.restapi.global.rq.Rq;
import com.restapi.global.rsData.RsData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // @Controller + @ResponseBody
@Validated
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
@Tag(name="ApiV1PostController", description = "API 글 컨트롤러")
@SecurityRequirement(name = "bearerAuth")
public class ApiV1PostController {
    private final PostService postService;
    private final Rq rq;

    @Transactional(readOnly = true)
    @GetMapping
    @Operation(summary = "다건 조회")
    public List<PostWithAuthorDto> getItems() {
        List<Post> items = postService.getList();

        return items
                .stream()
                .map(PostWithAuthorDto::new) // postDto 변환
                .toList();
    }

    @Transactional(readOnly = true)
    @GetMapping("/{id}")
    @Operation(summary = "단건 조회")
    public PostWithAuthorDto getItem(@PathVariable long id) {
        Post item = postService.findById(id);
        return new PostWithAuthorDto(item);
    }

    @Transactional
    @DeleteMapping("/{id}")
    @Operation(summary = "삭제")
    public RsData<PostDto> delete(
            @PathVariable Long id
    ) {
        Member actor = rq.getActor();

        Post post = postService.findById(id);

        post.checkActorCanDelete(actor);

        postService.delete(post);
        return new RsData<>("200-1", "%d번 게시글이 삭제되었습니다.".formatted(id), new PostDto(post));
    }

    @Transactional
    @PostMapping
    @Operation(summary = "작성")
    public RsData<PostDto> write(
            @Valid @RequestBody PostWriteReqBody reqBody
    ) {
        Member actor = rq.getActor();

        Post post = postService.create(actor, reqBody.title(), reqBody.content());

        return new RsData<>(
                "201-1",
                "%d번 게시글이 생성되었습니다.".formatted(post.getId()),
                new PostDto(post)
        );
    }

    @Transactional
    @PutMapping("/{id}")
    @Operation(summary = "수정")
    public RsData<Void> modify(
            @PathVariable long id,
            @Valid @RequestBody PostModifyReqBody reqBody
    ) {
        Member actor = rq.getActor();

        Post post = postService.findById(id);

        post.checkActorCanModify(actor);

        postService.update(post, reqBody.title(), reqBody.content());

        return new RsData<>("200-1",
                "%d번 게시글이 수정되었습니다.".formatted(post.getId()));
    }
}
