package com.example.jpa_test.controller;

import com.example.jpa_test.dto.MemberDTO;
import com.example.jpa_test.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    // 데이터 리스트 조회
    @GetMapping("/members")
    public ResponseEntity getList() {
        List<MemberDTO> list = memberService.getList();
        return ResponseEntity.ok().body(list);
    }

    // 데이터 저장
    @PostMapping("/members")
    public ResponseEntity insertData(@RequestBody MemberDTO dto) {
        int result = memberService.insertData(dto);
        if( result == 1 )
            return ResponseEntity.status(HttpStatus.CREATED).body(dto);
        return ResponseEntity.status(HttpStatus.CONFLICT).body("중복된 회원이 존재합니다.");
    }

    // 페이징 처리
    @GetMapping("/list_page")
    public ResponseEntity listPage(@RequestParam(defaultValue="0") int start,
                                    @RequestParam(defaultValue="5") int page) {
        List<MemberDTO> list = memberService.getListPage(start, page);
        return ResponseEntity.ok().body(list);
    }

    // 데이터 상세정보 조회
    @GetMapping("/members/{number}")
    // PK의 타입을 Long으로 해뒀으니
    public ResponseEntity getData(@PathVariable("number") long number) {

        // PK가 가지고 있는 데이터를 가져온다.
        MemberDTO dto = memberService.getData(number);
        if( dto != null)
            return ResponseEntity.status(HttpStatus.OK).body(dto);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("조회에 실패했습니다.");
    }

//    // 데이터 수정
//    public ResponseEntity updateData() {
//        return ResponseEntity.ok().body();
//    }
//
//    // 데이터 삭제
//    public ResponseEntity deleteData() {
//        return ResponseEntity.ok().body();
//    }
//

}
