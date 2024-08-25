package com.schedule.service;

import com.schedule.dto.StaffDto;

import javax.servlet.http.HttpServletRequest;

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
    boolean checkDuplicatedId(String id);

    // 2. 로그인
    // 2.1. 아이디, 비밀번호 일치여부 확인
    // 2.1.1. 입력된 ID 존재하는지 확인
    // 2.1.1.1. 해당 ID 존재하면, PWD 비교하여 일치하면 true 반환
    // 2.1.1.2. 해당 ID 존재하지 않거나, PWD 일치하지 않으면 false 반환
    boolean validateStaffLogin(StaffDto staffDto);

    // 특정 직원 정보 조회
    // 특정 ID로 조회된 Staff 정보
    StaffDto getStaffInfo(String id);

    // 4. 이메일 본인 인증
    // 4.1. 메일 전송
    // 4.1.1. 사용자가 입력한 이메일 중복 여부 확인
    // 4.1.1.1. 중복되지 않은 이메일인 경우, 메일 전송 및 true 반환
    // 4.1.1.2. 중복된 이메일인 경우, false 반환
    boolean sendVerificationEmail(String email, String savedMailKey);
    // 중복되지 않은 인증번호 생성하여 반환
    String makeVerificationCode(String savedMailKey, String newMailKey);

    // 5. 아이디 찾기
    // 5.1. 사용자가 입력한 아이디로 조회된 정보 확인
    String findIdByEmail(String email);

    // 5.2. 비밀번호 찾기
    // 5.2.1. 사용자가 입력한 아이디와 이메일로 조회한 정보를 비교 및 확인
    // 5.2.2. 새로운 비밀번호로 변경
    boolean modifyPassword(String id, String pwd);
}
