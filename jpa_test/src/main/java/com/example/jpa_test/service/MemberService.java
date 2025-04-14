package com.example.jpa_test.service;

import com.example.jpa_test.domain.MemberEntity;
import com.example.jpa_test.dto.MemberDTO;
import com.example.jpa_test.repo.MemberRepo;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepo repo;

    // 로그인 상태 유지를 위한 세션 객체 주입
    private final HttpSession session;

    public List<MemberDTO> getList() {

        List<MemberDTO> list = null;
        List<MemberEntity> listE = repo.findAll();

        if(listE.size() != 0) {
            list = listE.stream().map(m -> new MemberDTO(m)).toList();
        }
        return list;
    }

    public int insertData(MemberDTO dto) {
        int result = 1;
        try{
            MemberEntity entity = repo.save(new MemberEntity(dto));
            log.info("service entity : {}", entity);
        } catch (Exception e) {
            result = 0;
            throw new RuntimeException(e);
        }
        return result;
    }

    public List<MemberDTO> getListPage(int start, int page) {

        // 디폴트는 오름차순
        Pageable pageable = PageRequest.of(start, page);
        // 숫자를 기준으로 내림차순
//        Pageable pageable = PageRequest.of(start, page, Sort.by(Sort.Order.desc("number")));

        Page<MemberEntity> pageEntity = repo.findAll(pageable);
        List<MemberEntity> listE = pageEntity.getContent();
        List<MemberDTO> list = listE.stream().map(m -> new MemberDTO(m)).toList();

        return list;
    }

    public MemberDTO getData(long number) {

        // null 값 고려
        Optional<MemberEntity> opM = repo.findById(number);

        MemberEntity entity = opM.orElse(null);

        if(entity != null)
            return new MemberDTO(entity);
        return null;
    }

    public int updateData(String userId, MemberDTO dto) {

        MemberEntity entity = repo.findByUserId(userId);

        if(entity != null) {
            // id는 수정불가능이니까 작성하지 않는다.
            entity.setUserName(dto.getUserName());
            entity.setPassword(dto.getPassword());
            entity.setAge(dto.getAge());
            entity.setEmail(dto.getEmail());
            repo.save(entity);

            return 1;
        }
        // userId로 찾으려 했던 entity가 null인 경우
        return 0;
    }

    public int deleteData(long number) {

        // 삭제는 디폴트를 삭제실패로 한다
        int result = 0;

        MemberDTO dto = getData(number);

        if( dto != null ) {
            repo.deleteById(number);
            result = 1;
        }
        return result;
    }

    public int login(String username, String password) {

        int result = 1;
        result = repo.login(username, password);
        // 로그인에 성공한다면
        if( result == 1 ) {
            // 로그인 사용자 정보 저장한다.
            session.setAttribute("username", username);
        }
        return result;
    }
}
