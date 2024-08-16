package com.schedule.service;

import com.schedule.dao.StaffDaoImpl;
import com.schedule.dto.StaffDto;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
class StaffServiceImplTest {
    @Autowired StaffDaoImpl staffDao;
    @Autowired StaffServiceImpl staffService;

    // 테스트 전 DB 테이블에 더미데이터 추가
//    @BeforeEach
    @Test
    void setUpDB() throws Exception {
        for (int i = 1; i <= 10; i++) {
            StaffDto testDto = new StaffDto("id" + i, "name", "test" + i + "@spring.co.kr", "Password1@", "CO01", "DE01", "3", "Staff", "N", "WS0001", "Y", "2022-03-02", "2000-04-03", "F", "010-1234-5678", "서울시 강남구 영동대로");
            staffDao.insert(testDto);
        }
        for (int i = 11; i <= 20; i++) {
            StaffDto testDto = new StaffDto("id" + i, "name", "test" + i + "@spring.co.kr", "Password1@", "CO01", "DE02", "4", "Staff", "N", "WS0001", "Y", "2021-03-02", "1999-04-03", "F", "010-1234-5678", "서울시 강남구 영동대로");
            staffDao.insert(testDto);
        }
        for (int i = 21; i <= 30; i++) {
            StaffDto testDto = new StaffDto("id" + i, "name", "test" + i + "@spring.co.kr", "Password1@", "CO02", "DE03", "5", "Staff", "N", "WS0001", "Y", "2020-03-02", "1998-04-03", "M", "010-1234-5678", "서울시 강남구 영동대로");
            staffDao.insert(testDto);
        }
        for (int i = 31; i <= 40; i++) {
            StaffDto testDto = new StaffDto("id" + i, "name", "test" + i + "@spring.co.kr", "Password1@", "CO02", "DE04", "7", "Charge", "N", "WS0001", "Y", "2018-03-02", "1998-04-03", "M", "010-1234-5678", "서울시 강남구 영동대로");
            staffDao.insert(testDto);
        }
    }

    // 테스트 종료 후 더미데이터 삭제
//    @AfterEach
    @Test
    void cleanDB() throws Exception {
        staffDao.deleteAll();
    }

    @Test
    @DisplayName("아이디 조회 성공")
    void successToCheckExistOfId() {
        String existId = "1"; // 더미데이터로 넣은 ID
        assertTrue(staffService.checkExistOfId(existId));
    }

    @Test
    @DisplayName("아이디 조회 실패")
    void failToCheckExistOfId() {
        String nonExistId = "nonExistId";
        assertFalse(staffService.checkExistOfId(nonExistId));
    }

    @Test
    @DisplayName("회원가입 시 입력데이터 저장 성공")
    void successToSaveStaffJoinInfo() {
        String testId = "20180302";
        StaffDto testDto = new StaffDto(testId, "name", "test" + "@spring.co.kr", "password", "CO01", "DE01", "3", "Staff", "N", "WS0001", "Y", "2022-03-02", "2000-04-03", "F", "010-1234-5678", "서울시 강남구 영동대로");
        // 데이터 저장 성공 시 true 반환
        assertTrue(staffService.saveStaffJoinInfo(testDto));
        // 저장 시도한 데이터(ID)가 조회되면 true 반환
        assertTrue(staffService.checkExistOfId(testId));
    }

    @Test
    @DisplayName("회원가입 시 중복된 ID 입력 시 데이터 저장 실패")
    void failToSaveStaffJoinInfo() {
        String duplicatedId = "1"; // 이미 DB에 저장된 ID
        assertTrue(staffService.checkExistOfId(duplicatedId));

        StaffDto testDto = new StaffDto(duplicatedId, "name", "test" + "@spring.co.kr", "password", "CO01", "DE01", "3", "Staff", "N", "WS0001", "Y", "2022-03-02", "2000-04-03", "F", "010-1234-5678", "서울시 강남구 영동대로");
        assertFalse(staffService.saveStaffJoinInfo(testDto));
    }

    @Test
    @DisplayName("중복된 아이디 존재하는 경우 확인 성공")
    void successToCheckDuplicatedId() {
        String duplicatedId = "1";
//        StaffDto testDto = new StaffDto();
//        testDto.setId(duplicatedId);
        assertTrue(staffService.checkDuplicatedId(duplicatedId));
    }

    @Test
    @DisplayName("중복된 아이디 존재하지 않는 경우 확인 성공")
    void successToCheckNonDuplicatedId() {
        String nonDuplicatedId = "newID";
//        StaffDto testDto = new StaffDto();
//        testDto.setId(nonDuplicatedId);
        assertFalse(staffService.checkDuplicatedId(nonDuplicatedId));
    }

    @Test
    @DisplayName("로그인 성공")
    void successToValidateStaffLogin() {
        // DB에 저장된 ID, PWD 값과 동일한 ID, PWD 값으로 초기화
        String inputId = "1";
        String inputPwd = "password1";
        assertTrue(staffService.checkExistOfId(inputId));

        StaffDto testDto = new StaffDto();
        testDto.setId(inputId);
        testDto.setPwd(inputPwd);

        assertTrue(staffService.validateStaffLogin(testDto));
    }

    @Test
    @DisplayName("존재하지 않는 아이디 입력 시 로그인 실패")
    void failToValidateStaffLogin_inputNonExistId() {
        String inputId = "wrongId";
        String inputPwd = "password1";
        assertFalse(staffService.checkExistOfId(inputId));

        StaffDto testDto = new StaffDto();
        testDto.setId(inputId);
        testDto.setPwd(inputPwd);

        assertFalse(staffService.validateStaffLogin(testDto));
    }

    @Test
    @DisplayName("잘못된 비밀번호 입력 시 로그인 실패")
    void failToValidateStaffLogin_inputWrongPwd() {
        String inputId = "1";
        String inputPwd = "wrongPwd";
        assertTrue(staffService.checkExistOfId(inputId));

        StaffDto testDto = new StaffDto();
        testDto.setId(inputId);
        testDto.setPwd(inputPwd);

        assertFalse(staffService.validateStaffLogin(testDto));
    }

    @Test
    @DisplayName("비밀번호 암호화 성공")
    void successToEncodePwd() {
        StaffDto testDto = new StaffDto("TestID", "name", "test" + "@spring.co.kr", "password", "CO01", "DE01", "3", "Staff", "N", "WS0001", "Y", "2022-03-02", "2000-04-03", "F", "010-1234-5678", "서울시 강남구 영동대로");
        assertTrue(staffService.saveStaffJoinInfo(testDto));
    }

    @Test
    @DisplayName("기존 비밀번호와 암호화된 비밀번호 비교 성공")
    void successToAuthenticatePwd() {
        String inputId = "TestID";
        String inputPwd = "password";

        StaffDto testDto = new StaffDto();
        testDto.setId(inputId);
        testDto.setPwd(inputPwd);

        assertTrue(staffService.validateStaffLogin(testDto));
    }
}