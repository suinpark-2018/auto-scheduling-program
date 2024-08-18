package com.schedule.dto;

import com.schedule.common.validation.ValidationGroups;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StaffDto {

    @NotBlank(message = "아이디는 필수입력 항목입니다.", groups = {ValidationGroups.NotBlankGroup.class, ValidationGroups.IdCheckGroup.class, ValidationGroups.LoginCheckGroup.class})
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "아이디는 영문대소문자와 숫자만 입력 가능합니다.", groups = {ValidationGroups.PatternGroup.class, ValidationGroups.IdCheckGroup.class, ValidationGroups.LoginCheckGroup.class})
    @Size(min = 3, max = 20, message = "아이디는 최소 3자 이상, 20자 이하로 입력해주세요.", groups = {ValidationGroups.SizeGroup.class, ValidationGroups.IdCheckGroup.class, ValidationGroups.LoginCheckGroup.class})
    String id; // 사번

    @NotBlank(message = "이름은 필수입력 항목입니다.", groups = ValidationGroups.NotBlankGroup.class)
    @Size(min = 1, max = 20, message = "이름은 1자 이상, 20자 이하로 입력해주세요.", groups = ValidationGroups.SizeGroup.class)
    @Pattern(regexp = "^[a-zA-Z가-힣\\s]+$", message = "이름은 영문과 한글만 입력 가능합니다.", groups = ValidationGroups.PatternGroup.class)
    String name; // 직원명

    @NotBlank(message = "이메일은 필수입력 항목입니다.", groups = {ValidationGroups.NotBlankGroup.class, ValidationGroups.EmailCheckGroup.class})
    @Pattern(regexp = "[0-9a-zA-Z]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$", message = "이메일 형식이 올바르지 않습니다.", groups = {ValidationGroups.NotBlankGroup.class, ValidationGroups.EmailCheckGroup.class})
    String email; // 이메일

    @NotBlank(message = "비밀번호는 필수입력 항목입니다.", groups = {ValidationGroups.NotBlankGroup.class, ValidationGroups.LoginCheckGroup.class})
    @ Size(min = 8, max = 20, message = "비밀번호는 최소 8자 이상, 최대 20자 이하로 입력해주세요.", groups = {ValidationGroups.SizeGroup.class, ValidationGroups.LoginCheckGroup.class})
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+=]).+$", message = "비밀번호는 공백 없이 영문 대소문자, 숫자 최소 1개 이상 포함해야 합니다.", groups = {ValidationGroups.PatternGroup.class, ValidationGroups.LoginCheckGroup.class})
    String pwd; // 비밀번호

    @NotBlank(message = "회사코드가 지정되지 않았습니다.", groups = ValidationGroups.NotBlankGroup.class)
    String corp_code; // 회사코드

    @NotBlank(message = "부서코드가 지정되지 않았습니다.", groups = ValidationGroups.NotBlankGroup.class)
    String depart_code; // 부서코드

    @NotBlank(message = "연차가 지정되지 않았습니다.", groups = ValidationGroups.NotBlankGroup.class)
    String work_years; // 연차

    @NotBlank(message = "직급이 지정되지 않았습니다.", groups = ValidationGroups.NotBlankGroup.class)
    String grade; // 직급

    String skill_level; // 업무 숙련도
    String fix_duty; // 고정 근무
    String fix_off; // 고정 휴무
    String is_preceptor; // 프리셉터 여부
    String is_preceptee; // 프리셉티 여부


    @NotBlank(message = "임신여부가 설정되지 않았습니다.", groups = ValidationGroups.NotBlankGroup.class)
    String preg_state; // 임신여부

    @NotBlank(message = "근무상태가 설정되지 않았습니다.", groups = ValidationGroups.NotBlankGroup.class)
    String work_state_code; // 근무상태 코드

    String mng_auth; // 근무 일정표 관리권한

    @NotBlank(message = "재직상태가 설정되지 않았습니다.", groups = ValidationGroups.NotBlankGroup.class)
    String is_active; // 재직상태

    @NotBlank(message = "입사년도가 설정되지 않았습니다.", groups = ValidationGroups.NotBlankGroup.class)
    String hire_date; // 입사년도

    String quit_date; // 퇴사년도

    @NotBlank(message = "생년월일이 설정되지 않았습니다.", groups = ValidationGroups.NotBlankGroup.class)
    String birth; // 생년월일

    @NotBlank(message = "생년월일이 설정되지 않았습니다.", groups = ValidationGroups.NotBlankGroup.class)
    String gender; // 성별

    @NotBlank(message = "휴대폰번호가 입력되지 않았습니다.", groups = ValidationGroups.NotBlankGroup.class)
    @Size(min = 10, max = 15, message = "휴대폰번호는 최소 10자 이상, 최대 15자 이하로 입력해주세요.", groups = ValidationGroups.SizeGroup.class)
    @Pattern(regexp = "^\\d+$", message = "'-' 제외한 숫자만 입력해주세요.", groups = ValidationGroups.PatternGroup.class)
    String phone_num; // 휴대폰 번호

    @NotBlank(message = "주소가 입력되지 않았습니다.", groups = ValidationGroups.NotBlankGroup.class)
    String address; // 주소

    // 시스템 컬럼 항목
    String reg_id; // 최초등록자
    String reg_date; // 최초 등록일
    String up_id; // 최종 수정자
    String up_date; // 최종 수정일

    // 회원가입 관련 생성자
    public StaffDto(String id, String name, String email, String pwd, String corp_code, String depart_code, String work_years, String grade, String preg_state, String work_state_code, String is_active, String hire_date, String birth, String gender, String phone_num, String address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.pwd = pwd;
        this.corp_code = corp_code;
        this.depart_code = depart_code;
        this.work_years = work_years;
        this.grade = grade;
        this.preg_state = preg_state;
        this.work_state_code = work_state_code;
        this.is_active = is_active;
        this.hire_date = hire_date;
        this.birth = birth;
        this.gender = gender;
        this.phone_num = phone_num;
        this.address = address;
    }
}
