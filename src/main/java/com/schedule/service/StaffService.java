package com.schedule.service;

import com.schedule.domain.StaffDto;

public interface StaffService {
    // 1. 회원가입
    // 1.1. 직원정보 저장
    // 1.1.1. 저장 성공 시 true 반환
    // 1.1.2. 저장 실패 시 false 반환
    boolean saveStaffJoinInfo(StaffDto staffDto);

    // 사용자가 입력한 ID 존재여부 확인
    // DB에 특정 ID가 이미 존재하면 true 반환
    // DB에 특정 ID가 존재하지 않으면 false 반환
    boolean checkExistOfId(String id);

    // 1.2. 아이디 중복확인
    // 1.2.1. 사용자가 입력한 ID가 이미 존재하면 true 반환
    // 1.2.2. 사용자가 입력한 ID가 존재하지 않으면 false 반환
    boolean checkDuplicatedId(StaffDto staffDto);

    // 2. 로그인
    // 2.1. 아이디, 비밀번호 일치여부 확인
    // 2.1.1. 입력된 ID 존재하는지 확인
    // 2.1.1.1. 해당 ID 존재하면, PWD 비교하여 일치하면 true 반환
    // 2.1.1.2. 해당 ID 존재하지 않거나, PWD 일치하지 않으면 false 반환
    boolean validateStaffLogin(StaffDto staffDto);
}
