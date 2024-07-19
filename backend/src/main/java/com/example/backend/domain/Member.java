package com.example.backend.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(length = 30)
    private String email;

    @Column(length = 20)
    private String password;

    @Column(length = 10)
    private String nickname;

    @Column(length = 11)
    private String phone;

    @Column(length = 10)
    private String zonecode;

    @Column(length = 30)
    private String address;

    @Column(length = 30)
    private String addressDetail;

    @Builder
    public Member(String email, String password, String nickname, String phone, String zonecode, String address, String addressDetail) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.phone = phone;
        this.zonecode = zonecode;
        this.address = address;
        this.addressDetail = addressDetail;
    }

}
