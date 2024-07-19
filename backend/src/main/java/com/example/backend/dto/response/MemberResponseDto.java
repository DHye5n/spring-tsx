package com.example.backend.dto.response;

import com.example.backend.domain.Member;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberResponseDto {

    private final String email;
    private final String nickname;

    @Builder
    public MemberResponseDto(String email, String nickname) {
        this.email = email;
        this.nickname = nickname;
    }

    public static MemberResponseDto fromEntity(Member member) {
        return MemberResponseDto.builder()
                .email(member.getEmail())
                .nickname(member.getNickname())
                .build();
    }
}
