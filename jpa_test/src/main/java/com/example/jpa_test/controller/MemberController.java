package com.example.jpa_test.controller;

import com.example.jpa_test.dto.MemberDTO;
import com.example.jpa_test.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

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
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("중복된 회원이 존재합니다.");
    }

//    // 데이터 상세정보 조회
//    public ResponseEntity getData() {
//        return ResponseEntity.ok().body();
//    }
//
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
//    // 페이징 처리
//    public ResponseEntity listPage() {
//        return ResponseEntity.ok().body();
//    }

}
