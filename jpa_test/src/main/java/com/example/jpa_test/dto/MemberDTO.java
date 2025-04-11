package com.example.jpa_test.dto;

import com.example.jpa_test.domain.MemberEntity;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MemberDTO {

    private Long number;
    private String userId;
    private String userName;
    private String password;
    private int age;
    private String email;

    public MemberDTO(MemberEntity entity) {
        this.number = entity.getNumber();
        this.userId = entity.getUserId();
        this.userName = entity.getUserName();
        this.password = entity.getPassword();
        this.age = entity.getAge();
        this.email = entity.getEmail();
    }
}
