package com.example.demo.model.service;
import lombok.*; // 어노테이션 자동 생성
import com.example.demo.model.domain.Member;
import jakarta.validation.constraints.*;
import org.springframework.validation.annotation.Validated;

@Validated
@NoArgsConstructor //기본 생성자 추가
@AllArgsConstructor //모든 필드 값을 파라미터로 받는 생성자 추가
@Data //getter setter equals 등 자동 생성
public class AddMemberRequest {
    @NotBlank(message = "이름은 공백이 허용되지 않습니다.")
    @Pattern(regexp = "^[a-zA-Z가-힣]+$", message = "이름에는 특수문자나 숫자가 포함될 수 없습니다.")
    private String name;

    @NotBlank(message = "이메일은 공백이 허용되지 않습니다.")
    @Email(message = "유효한 이메일 형식이 아닙니다.")
    private String email;

    @NotBlank(message = "비밀번호는 공백이 허용되지 않습니다.")
    @Size(min = 8, message = "비밀번호는 최소 8자 이상이어야 합니다.")
    @Pattern(regexp = ".*[!@#$%^&*(),.?\":{}|<>].*", message = "비밀번호에는 특수문자가 포함되어야 합니다.")
    private String password;

    @NotNull(message = "나이는 필수 입력값입니다.")
    @Min(value = 19, message = "나이는 최소 19세 이상이어야 합니다.")
    @Max(value = 90, message = "나이는 최대 90세 이하이어야 합니다.")
    private Integer age;

    private String mobile; //공백허용
    private String address; //공백허용
    
    public Member toEntity(){ // Member 생성자를 통해 객체 생성
        return Member.builder()
        .name(name)
        .email(email)
        .password(password)
        .age(String.valueOf(age))
        .mobile(mobile)
        .address(address)
        .build();
    }
}