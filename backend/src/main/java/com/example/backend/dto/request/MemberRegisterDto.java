package com.example.backend.dto.request;

import com.example.backend.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class MemberRegisterDto {

    @NotBlank
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank
    @Size(min = 6, max = 8)
    private String password;

    @NotBlank
    private String passwordCheck;

    @NotBlank
    private String nickname;

    @NotBlank
    @Pattern(regexp = "^[0-9]{11,13}$", message = "Telephone number must be between 11 and 13 digits")
    private String phone;

    @NotBlank
    private String zonecode;

    @NotBlank
    private String address;

    @NotBlank
    private String addressDetail;

    @Builder
    public MemberRegisterDto(String email, String password, String passwordCheck, String nickname, String phone,
                             String zonecode, String address, String addressDetail) {
        this.email = email;
        this.password = password;
        this.passwordCheck = passwordCheck;
        this.nickname = nickname;
        this.phone = phone;
        this.zonecode = zonecode;
        this.address = address;
        this.addressDetail = addressDetail;
    }


    public boolean isPasswordMatch() {
        return this.password != null && this.password.equals(this.passwordCheck);
    }


    public Member toEntity() {
        return Member.builder()
                .email(this.email)
                .password(this.password)
                .nickname(this.nickname)
                .phone(this.phone)
                .zonecode(this.zonecode)
                .address(this.address)
                .addressDetail(this.addressDetail)
                .build();
    }
}

