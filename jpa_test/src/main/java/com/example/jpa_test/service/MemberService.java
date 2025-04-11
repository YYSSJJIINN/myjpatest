package com.example.jpa_test.service;

import com.example.jpa_test.domain.MemberEntity;
import com.example.jpa_test.dto.MemberDTO;
import com.example.jpa_test.repo.MemberRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepo repo;

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
}
