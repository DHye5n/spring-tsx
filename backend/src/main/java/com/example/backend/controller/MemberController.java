package com.example.backend.controller;

import com.example.backend.dto.request.MemberRegisterDto;
import com.example.backend.dto.response.MemberResponseDto;
import com.example.backend.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<MemberResponseDto> register(@Valid @RequestBody MemberRegisterDto memberRegisterDto) {
        MemberResponseDto successMember = memberService.register(memberRegisterDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(successMember);
    }

    @GetMapping
    public ResponseEntity<List<MemberResponseDto>> memberList() {
        List<MemberResponseDto> members = memberService.getAllMembers();
        return ResponseEntity.ok(members);
    }
}
