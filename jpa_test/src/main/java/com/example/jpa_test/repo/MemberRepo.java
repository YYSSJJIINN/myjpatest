package com.example.jpa_test.repo;

import com.example.jpa_test.domain.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MemberRepo extends JpaRepository<MemberEntity, Long> {

    @Query(value = "select * from member_example where number=:n", nativeQuery = true)
    MemberEntity findByUserId(String userId);
}
