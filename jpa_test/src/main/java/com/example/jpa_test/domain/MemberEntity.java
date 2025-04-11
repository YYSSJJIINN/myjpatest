package com.example.jpa_test.domain;

import com.example.jpa_test.dto.MemberDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "member_example")
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long number;

    @NotNull
    @Column(unique = true, length = 20)
    private String userId;

    @Column(name = "user_name", nullable = false, length = 50)
    private String userName;

    @Column(name = "password", nullable = false, length = 20)
    private String password;

    @Column( name = "age" )
    private int age;

    @Column( name = "email" )
    private String email;

    public MemberEntity(MemberDTO dto) {
        this.userId = dto.getUserId();
        this.userName = dto.getUserName();
        this.password = dto.getPassword();
        this.age = dto.getAge();
        this.email = dto.getEmail();
    }
}
