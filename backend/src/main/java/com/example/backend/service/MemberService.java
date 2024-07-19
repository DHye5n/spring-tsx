package com.example.backend.service;

import com.example.backend.domain.Member;
import com.example.backend.dto.request.MemberRegisterDto;
import com.example.backend.dto.response.MemberResponseDto;
import com.example.backend.exception.DuplicateEmailException;
import com.example.backend.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public MemberResponseDto register(MemberRegisterDto memberRegisterDto) {
        // 비밀번호 일치 검사
        if (!memberRegisterDto.isPasswordMatch()) {
            throw new IllegalArgumentException("Passwords do not match");
        }

        // 이메일 중복 검사
        if (memberRepository.existsByEmail(memberRegisterDto.getEmail())) {
            throw new DuplicateEmailException("Email is already in use");
        }

        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(memberRegisterDto.getPassword());

        // Member 엔티티로 변환
        Member member = Member.builder()
                .email(memberRegisterDto.getEmail())
                .password(encodedPassword) // 암호화된 비밀번호 설정
                .nickname(memberRegisterDto.getNickname())
                .phone(memberRegisterDto.getPhone())
                .zonecode(memberRegisterDto.getZonecode())
                .address(memberRegisterDto.getAddress())
                .addressDetail(memberRegisterDto.getAddressDetail())
                .build();

        // 회원 저장
        Member savedMember = memberRepository.save(member);

        // MemberResponseDto로 변환하여 반환
        return MemberResponseDto.fromEntity(savedMember);
    }

    // 비밀번호 확인 메소드 (예를 들어, 로그인 시 사용)
    public boolean validatePassword(Member member, String rawPassword) {
        return passwordEncoder.matches(rawPassword, member.getPassword());
    }
}
