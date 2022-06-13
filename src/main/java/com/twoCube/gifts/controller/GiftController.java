package com.twoCube.gifts.controller;

import com.twoCube.common.annotation.CurrentUser;
import com.twoCube.gifts.domain.GiftNote;
import com.twoCube.gifts.dto.*;
import com.twoCube.gifts.service.GiftService;
import com.twoCube.members.domain.Member;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/gifts")
@RequiredArgsConstructor
@Api(tags = {"선물 API"})
public class GiftController {

    private final GiftService giftService;

    @PostMapping("/notes")
    @ApiOperation(value = "쪽지 선물하기 api")
    public ResponseEntity<Long> createNote(@RequestBody NoteRequest noteRequest,
                                           @ApiIgnore @CurrentUser Member member
                                           ){
        Long giftNoteId = giftService.createNote(noteRequest, member);
        return ResponseEntity.ok(giftNoteId);
    }

    @PostMapping("/polaroids")
    @ApiOperation(value = "폴라로이드 선물하기 api", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Long> createPolaroid(@RequestPart(required = false) MultipartFile polaroid,
                                               @RequestPart(required = false) String content
//                                           @ApiIgnore @CurrentUser User user
    ){
        return ResponseEntity.ok().build();
    }

    @PostMapping("/pills")
    @ApiOperation(value = "영양제 선물하기 api")
    public ResponseEntity<Long> createPill(@RequestBody PillRequest pillRequest
//                                           @ApiIgnore @CurrentUser User user
    ){
        return ResponseEntity.ok().build();
    }

    @GetMapping("/pills")
    @ApiOperation(value = "영양제 list 호출 api")
    public ResponseEntity<List<PillListResponse>> getPillList(
//                                           @ApiIgnore @CurrentUser User user
    ){
        List<PillListResponse> pillListResponse = new ArrayList<>();
        return ResponseEntity.ok(pillListResponse);
    }

//    @GetMapping("/flowers")
//    @ApiOperation(value = "랜덤으로 꽃 가져오기 api")
//    public ResponseEntity<FlowerResponse> getFlower(
//                                           @ApiIgnore @CurrentUser Member memer
//    ){
//        FlowerResponse flowerResponse = giftService.getTodayFlower();
//        return ResponseEntity.ok(flowerResponse);
//    }

    @PostMapping("/flowers")
    @ApiOperation(value = "꽃 선물하기 api")
    public ResponseEntity<Long> createFlower(@RequestBody FlowerRequest flowerRequest
//                                           @ApiIgnore @CurrentUser User user
    ){
        return ResponseEntity.ok().build();
    }

    @GetMapping("/main")
    @ApiOperation(value = "선물 메인 api")
    public ResponseEntity<MainRequest> getMain(
//                                           @ApiIgnore @CurrentUser User user
    ){
        System.out.println("print");
        MainRequest mainRequest = new MainRequest();
        return ResponseEntity.ok().build();
    }
}
