package com.twoCube.couple.controller;

import com.twoCube.common.annotation.CurrentUser;
import com.twoCube.couple.dto.Code;
import com.twoCube.couple.service.CoupleService;
import com.twoCube.gifts.dto.NoteRequest;
import com.twoCube.members.domain.Member;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/api/v1/couple")
@RequiredArgsConstructor
@Api(tags = {"연인 API"})
public class CoupleController {

    private final CoupleService coupleService;

    @GetMapping("/code")
    @ApiOperation(value = "코드 발급받기 api")
    public ResponseEntity<Code> generateCode(
            @ApiIgnore @CurrentUser Member member
    ) {
        Code code = coupleService.generateCode(member);
        return ResponseEntity.ok(code);
    }

    @PostMapping("/code")
    @ApiOperation(value = "코드 검증 api")
    public ResponseEntity<String> validateCode(@RequestBody Code code,
                                               @ApiIgnore @CurrentUser Member member
    ) {
        System.out.println("controller");
        String statusMessage = coupleService.validateCode(code, member);
        return ResponseEntity.ok(statusMessage);
    }
}
