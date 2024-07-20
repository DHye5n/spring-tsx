package com.example.backend.dto.response;

import com.example.backend.domain.Member;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberResponseDto {

    private final String email;
    private final String nickname;
    private final String phone;

    @Builder
    public MemberResponseDto(String email, String nickname, String phone) {
        this.email = email;
        this.nickname = nickname;
        this.phone = phone;
    }

    public static MemberResponseDto fromEntity(Member member) {
        return MemberResponseDto.builder()
                .email(member.getEmail())
                .nickname(member.getNickname())
                .phone(member.getPhone())
                .build();
    }
}
