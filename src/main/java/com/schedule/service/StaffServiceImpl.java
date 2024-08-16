package com.schedule.service;

import com.schedule.dao.StaffDaoImpl;
import com.schedule.dto.StaffDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StaffServiceImpl implements StaffService {
    private final StaffDaoImpl staffDao;
    private final BCryptPasswordEncoder passwordEncoder;

    // 1. 회원가입
    // 1.1. 직원정보 저장
    // 1.1.1. 저장 성공 시 true 반환
    // 1.1.2. 저장 실패 시 false 반환
    @Override
    public boolean saveStaffJoinInfo(StaffDto staffDto) {
        boolean isSaved = true;
        try {
            String encodedPwd = passwordEncoder.encode(staffDto.getPwd());
            staffDto.setPwd(encodedPwd);
            staffDao.insert(staffDto);
        } catch (Exception e) {
            isSaved = false;
            e.printStackTrace();
            e.getMessage();
        }
        return isSaved;
    }

    // 1.2. 아이디 중복확인

    // 1.2.1. 사용자가 입력한 ID가 DB에 존재하는지 확인
    // 1.2.1.1. DB에 특정 ID가 이미 존재하면 true 반환
    // 1.2.1.2. DB에 특정 ID가 존재하지 않으면 false 반환
    @Override
    public boolean checkExistOfId(String id) {
        boolean isExist = true;
        try {
            if (staffDao.select(id) == null) {
                isExist = false;
            }
        } catch (Exception e) {
            isExist = false;
            e.printStackTrace();
            e.getMessage();
        }
        return isExist;
    }

    // 1.2.2. 특정 ID가 DB에 존재하는지 확인 (checkExistOfId 메서드 활용)
    // 1.2.2.1. 존재하는 경우 중복여부 true로 반환
    // 1.2.2.2. 존재하지 않는 경우 중복여부 false로 반환
    @Override
    public boolean checkDuplicatedId(String id) {
        boolean isDuplicated = true;
        try {
            if (!checkExistOfId(id)) {
                isDuplicated = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
        return isDuplicated;
    }

    // 2. 로그인
    // 2.1. 아이디, 비밀번호 일치여부 확인
    // 2.1.1. 입력된 ID 존재하는지 확인
    // 2.1.1.1. 해당 ID 존재하면, PWD 비교하여 일치하면 true 반환
    // 2.1.1.2. 해당 ID 존재하지 않거나, PWD 일치하지 않으면 false 반환
    @Override
    public boolean validateStaffLogin(StaffDto staffDto) {
        boolean isLoginMatch = true;
        // 사용자가 입력한 ID, PWD
        String inputId = staffDto.getId();
        String inputPwd = staffDto.getPwd();
        try {
            if (checkExistOfId(inputId)) {
                String dbPwd = staffDao.select(inputId).getPwd();
                if (!authenticatePwd(inputPwd, dbPwd)) {
                    isLoginMatch = false;
                }
            } else {
                isLoginMatch = false;
            }
        } catch (Exception e) {
            isLoginMatch = false;
            e.printStackTrace();
            e.getMessage();
        }
        return isLoginMatch;
    }

    // *** 암호화 전후 비밀번호 비교
    // 암호화 전 비밀번호(rawPwd)와 암호화 후 비밀번호(encodedPwd)
    // 일치 시 true / 불일치 시 false
    public boolean authenticatePwd(String rawPwd, String encodedPwd) {
        return passwordEncoder.matches(rawPwd, encodedPwd);
    }
}