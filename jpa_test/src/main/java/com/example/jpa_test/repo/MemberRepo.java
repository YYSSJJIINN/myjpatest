package com.example.jpa_test.repo;

import com.example.jpa_test.domain.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface MemberRepo extends JpaRepository<MemberEntity, Long> {

    @Query(value = "select * from member_example where user_id=:id", nativeQuery = true)
    MemberEntity findByUserId(@Param("id") String userId);

    @Modifying
    @Transactional
    @Query(value = "update member_example set user_name=:username, password=:password, age=:age, email=:email where user_id=:id", nativeQuery = true)
    int updateMember(@Param("id") String id,
                    @Param("username") String username,
                    @Param("password") String password,
                    @Param("age") int age,
                    @Param("email") String email);

}
