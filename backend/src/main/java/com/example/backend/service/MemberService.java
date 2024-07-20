package com.example.backend.service;

import com.example.backend.domain.Member;
import com.example.backend.dto.request.MemberRegisterDto;
import com.example.backend.dto.response.MemberResponseDto;
import com.example.backend.exception.MemberException;
import com.example.backend.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public MemberResponseDto register(MemberRegisterDto registerDto) {
        isExistUserEmail(registerDto.getEmail());
        checkPassword(registerDto.getPassword(), registerDto.getPasswordCheck());

        // 패스워드 암호화
        String encodePwd = passwordEncoder.encode(registerDto.getPassword());
        registerDto.setPassword(encodePwd);

        Member saveMember = memberRepository.save(
                MemberRegisterDto.ofEntity(registerDto));

        return MemberResponseDto.fromEntity(saveMember);
    }

    /**
     * 아이디(이메일) 중복 체크
     * @param email
     */

    private void isExistUserEmail(String email) {
        if (memberRepository.findByEmail(email).isPresent()) {
            throw new MemberException("이미 사용 중인 이메일입니다.", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * 비밀번호와 비밀번호 확인이 같은지 체크
     * @param password
     * @param passwordCheck
     */
    private void checkPassword(String password, String passwordCheck) {
        if (!password.equals(passwordCheck)) {
            throw new MemberException("패스워드 불일치", HttpStatus.BAD_REQUEST);
        }
    }



    public List<MemberResponseDto> getAllMembers() {
        List<Member> members = memberRepository.findAll();
        return members.stream()
                .map(member -> new MemberResponseDto(member.getNickname(), member.getEmail(), member.getPhone()))
                .collect(Collectors.toList());
    }
}
