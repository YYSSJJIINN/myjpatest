package com.example.jpa_test.service;

import com.example.jpa_test.domain.MemberEntity;
import com.example.jpa_test.dto.MemberDTO;
import com.example.jpa_test.repo.MemberRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
}
