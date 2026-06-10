package com.restapi.domain.post.post.controller;

import com.restapi.domain.member.member.entity.Member;
import com.restapi.domain.post.post.dto.AdmPostCountResBody;
import com.restapi.domain.post.post.service.PostService;
import com.restapi.global.exception.ServiceException;
import com.restapi.global.rq.Rq;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // @Controller + @ResponseBody
@RequestMapping("/api/v1/adm/posts")
@RequiredArgsConstructor
@Tag(name="ApiV1PostAdmController", description = "관리자용 API 글 컨트롤러")
@SecurityRequirement(name = "bearerAuth")
public class ApiV1AdmPostController {
    private final PostService postService;
    private final Rq rq;

    @GetMapping("/count")
    public AdmPostCountResBody count() {
        Member actor = rq.getActor();

        if (!actor.isAdmin()) {
            throw new ServiceException("403-1", "권한이 없습니다.");
        }

        return new AdmPostCountResBody(postService.count());
    }
}
