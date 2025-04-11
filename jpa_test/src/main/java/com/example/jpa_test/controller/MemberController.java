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
import java.util.Map;

@Controller
@Slf4j
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    // 데이터 리스트 조회(전체 회원 목록 조회)
    @GetMapping("/members")
    public ResponseEntity getList() {
        List<MemberDTO> list = memberService.getList();
        return ResponseEntity.ok().body(list);
    }

    // 데이터 저장(회원가입)
    @PostMapping("/members")
    public ResponseEntity insertData(@RequestBody MemberDTO dto) {
        int result = memberService.insertData(dto);
        if( result == 1 )
            return ResponseEntity.status(HttpStatus.CREATED).body(dto);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("중복된 회원이 존재합니다.");
    }

    // 페이징 처리
    @GetMapping("/list_page")
    public ResponseEntity listPage(@RequestParam(defaultValue="0") int start,
                                    @RequestParam(defaultValue="5") int page) {
        List<MemberDTO> list = memberService.getListPage(start, page);
        return ResponseEntity.ok().body(list);
    }

    // 선택 데이터 조회(선택 회원 조회)
    @GetMapping("/members/{number}")
    // PK의 타입을 Long으로 해뒀으니
    public ResponseEntity getData(@PathVariable("number") long number) {

        // PK가 가지고 있는 데이터를 가져온다.
        MemberDTO dto = memberService.getData(number);
        if( dto != null)
            return ResponseEntity.status(HttpStatus.OK).body(dto);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("조회에 실패했습니다.");
    }

    // 데이터 수정(선택 회원 정보 수정)
    @PutMapping("/member/{id}")
    public ResponseEntity updateData(@PathVariable("id") String userId,
                                        @RequestBody MemberDTO dto) {

        // userId의 dto를 변경
        int result = memberService.updateData(userId, dto);
        if( result == 1 )
            return ResponseEntity.status(HttpStatus.OK).body(dto);
        return ResponseEntity.status(HttpStatus.CONFLICT).body("수정에 실패했습니다.");
    }

    // 데이터 삭제(선택 회원 삭제)
    @DeleteMapping("/members/{num}")
    public ResponseEntity deleteData(@PathVariable("num") long number) {

        // 삭제의 결과(성공이냐 실패냐 여부)를 int형인 숫자값을 반환받음
        int result = memberService.deleteData(number);
        // 반환받은 숫자로 조건문 작성
        if( result == 1 )
            return ResponseEntity.status(HttpStatus.OK).body("삭제 성공");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("해당 정보가 존재하지 않습니다.");
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody Map<String, String> map) {

        int result = memberService.login(map.get("username"), map.get("password"));

        if( result == 1 )
            return ResponseEntity.status(HttpStatus.OK).body("로그인 성공");
        else if( result == 0 )
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("틀린 비밀번호입니다.");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("존재하지 않는 id 입니다.");
    }
}
