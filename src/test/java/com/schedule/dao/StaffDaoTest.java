package com.schedule.dao;

import com.schedule.dto.StaffDto;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
class StaffDaoTest {

    @Mock StaffDaoImpl mockDao;
    @Autowired StaffDaoImpl staffDao;

    // Mock 객체 초기화
    @BeforeEach
    void setUpMock() {
        MockitoAnnotations.openMocks(this);
    }

    // 테스트 전 DB 테이블에 더미데이터 추가
    @BeforeEach
    void setUpDB() throws Exception {
        for (int i = 1; i <= 10; i++) {
            StaffDto testDto = new StaffDto(i + "", "name" + i, "test" + i + "@spring.co.kr", "password" + i, "CO01", "DE01", "3", "Staff", "N", "WS0001",  "Y", "2022-03-02", "2000-04-03", "F", "010-1234-5678", "서울시 강남구 영동대로");
            staffDao.insert(testDto);
        }
        for (int i = 11; i <= 20; i++) {
            StaffDto testDto = new StaffDto(i + "", "name" + i, "test" + i + "@spring.co.kr", "password" + i, "CO01", "DE02", "4", "Staff", "N", "WS0001",  "Y", "2021-03-02", "1999-04-03", "F", "010-1234-5678", "서울시 강남구 영동대로");
            staffDao.insert(testDto);
        }
        for (int i = 21; i <= 30; i++) {
            StaffDto testDto = new StaffDto(i + "", "name" + i, "test" + i + "@spring.co.kr", "password" + i, "CO02", "DE03", "5", "Staff", "N", "WS0001",  "Y", "2020-03-02", "1998-04-03", "M", "010-1234-5678", "서울시 강남구 영동대로");
            staffDao.insert(testDto);
        }
        for (int i = 31; i <= 40; i++) {
            StaffDto testDto = new StaffDto(i + "", "name" + i, "test" + i + "@spring.co.kr", "password" + i, "CO02", "DE04", "7", "Charge", "N", "WS0001",  "Y", "2018-03-02", "1998-04-03", "M", "010-1234-5678", "서울시 강남구 영동대로");
            staffDao.insert(testDto);
        }
    }

    // 테스트 종료 후 더미데이터 삭제
    @AfterEach
    void cleanDB() throws Exception {
        staffDao.deleteAll();
    }

    @Test
    @DisplayName("StaffDao 객체 주입 성공여부 확인")
    void successToDIOfStaffDao() {
        assertNotNull(staffDao);
    }

    // now 메서드 테스트
    @Test
    @DisplayName("현재 날짜 및 시간 오차범위 내 존재함")
    void withinRangeNow() throws Exception {
        // dao 메서드 사용하여 DB에 저장된 현재 datetime 호출하여 저장
        String now = staffDao.now();
        assertNotNull(now);

        // DB에 저장되었던 현재 datetime 값을 String -> LocalDateTime 형식으로 변환
        // 현재 서버의 datetime 결과 저장
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dbDateTime = LocalDateTime.parse(now, formatter);
        LocalDateTime currentDateTime = LocalDateTime.now();

        // DB의 datetime과 서버의 datetime의 오차를 초 단위로 계산
        long secondsDifference = ChronoUnit.SECONDS.between(dbDateTime, currentDateTime);

        // 60초 이내인지 확인
        assertTrue(Math.abs(secondsDifference) <= 60);
    }

    @Test
    @DisplayName("현재 날짜 및 시간 오차범위 벗어남")
    void outOfRangeNow() throws Exception {
        // dao 메서드 사용하여 DB에 저장된 현재 datetime 호출하여 저장
        String now = staffDao.now();
        assertNotNull(now);

        // DB에 저장되었던 현재 datetime 값을 String -> LocalDateTime 형식으로 변환
        // 현재 서버의 datetime 결과 저장
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dbDateTime = LocalDateTime.parse(now, formatter);

        // 임의로 서버시간을 2시간 앞당김
        LocalDateTime currentDateTime = LocalDateTime.now().minusHours(2);

        // DB의 datetime과 서버의 datetime의 오차를 초 단위로 계산
        long secondsDifference = ChronoUnit.SECONDS.between(dbDateTime, currentDateTime);

        // 60초 이내가 아닌지 확인
        assertFalse(Math.abs(secondsDifference) <= 60);
    }


    // count 메서드 테스트
    @Test
    @DisplayName("Row 카운트 성공")
    void successToCount() throws Exception {
        int expectedCnt = 40; // 더미데이터에 넣었던 데이터 개수
        int actualCnt = staffDao.count(); // 실제 카운팅된 row 수
        assertEquals(expectedCnt, actualCnt);

        // 임의의 staff 정보 1개 추가
        StaffDto testDto1 = new StaffDto("12345", "name12345", "test" + 12345 + "@spring.co.kr", "password" + 12345, "CO01", "DE01", "3", "Staff", "N", "WS0001", "Y", "2022-03-02", "2000-04-03", "F", "010-1234-5678", "서울시 강남구 영동대로");
        staffDao.insert(testDto1);
        assertEquals(41, staffDao.count());

        // 임의의 staff 정보 10개 추가
        for (int i = 41; i <= 50; i++) {
            StaffDto testDto2 = new StaffDto(i + "", "name" + i, "test" + i + "@spring.co.kr", "password" + i, "CO01", "DE01", "3", "Staff", "N", "WS0001", "Y", "2022-03-02", "2000-04-03", "F", "010-1234-5678", "서울시 강남구 영동대로");
            staffDao.insert(testDto2);
        }
        assertEquals(51, staffDao.count());

        // 임의의 staff 정보 100개 추가
        for (int i = 51; i <= 150; i++) {
            StaffDto testDto3 = new StaffDto(i + "", "name" + i, "test" + i + "@spring.co.kr", "password" + i, "CO01", "DE01", "3", "Staff", "N", "WS0001", "Y", "2022-03-02", "2000-04-03", "F", "010-1234-5678", "서울시 강남구 영동대로");
            staffDao.insert(testDto3);
        }
        assertEquals(151, staffDao.count());
    }

    @Test
    @DisplayName("Row 카운트 실패")
    void failToCount() throws Exception {
        int expectedCnt = 40; // 더미데이터에 넣었던 데이터 개수
        int actualCnt = staffDao.count(); // 실제 카운팅된 row 수
        assertEquals(expectedCnt, actualCnt);
        assertNotEquals(expectedCnt-1, actualCnt);
    }

    // 단위 테스트 (Mock 객체 활용)
    @Test
    @DisplayName("DB 연결 실패로 인한 Row 카운트 실패")
    void failToDBConnection_count() throws Exception {
        // count 메서드 호출 시 DB 접근 예외 발생시킴
        when(mockDao.count()).thenThrow(new DataAccessException("Database connection error!") {});
        assertThrows(DataAccessException.class, () -> mockDao.count());
    }

    // insert 메서드 테스트
    @Test
    @DisplayName("데이터 저장 성공 테스트")
    void successToInsert() throws Exception {
        // 데이터 저장 전 row 카운트 결과 확인
        int preCnt = staffDao.count();

        // 임의의 StaffDto 객체 생성
        // 데이터 저장 및 not null 확인
        // Case 1: 1개 추가 저장
        StaffDto testDto = new StaffDto("12345", "name12345", "test" + 12345 + "@spring.co.kr", "password" + 12345, "CO01", "DE01", "3", "Staff", "N", "WS0001", "Y", "2022-03-02", "2000-04-03", "F", "010-1234-5678", "서울시 강남구 영동대로");

        // insert 성공여부 확인
        assertEquals(1, staffDao.insert(testDto));
        // DB에 원하는 값 제대로 저장됐는지 확인
        assertEquals("name12345", staffDao.select("12345").getName());

        // 데이터 저장 후 row 카운트 결과 확인
        // 추가 저장한 데이터 갯수만큼 증가했는지 확인
        int postCnt1 = staffDao.count();
        assertEquals(preCnt + 1, postCnt1);

        // Case 2: 50개 추가 저장
        for (int i = 1; i <= 50; i++) {
            StaffDto addTestDto = new StaffDto("addTest" + i, "addName" + i, "addTest" + i + "@spring.co.kr", "addPassword" + i, "CO01", "DE01", "3", "Staff", "N", "WS0001", "Y", "2022-03-02", "2000-04-03", "F", "010-1234-5678", "서울시 강남구 영동대로");
            staffDao.insert(addTestDto);
        }

        // 데이터 저장 후 row 카운트 결과 확인
        // 추가 저장한 데이터 갯수만큼 증가했는지 확인
        int postCnt2 = staffDao.count();
        assertEquals(preCnt + 51, postCnt2);
        assertEquals(postCnt1 + 50, postCnt2);
    }

    // 단위 테스트 (Mock 객체 활용)
    @Test
    @DisplayName("DB 연결 실패로 인한 데이터 저장 실패")
    void failToDBConnection_insert() throws Exception {
        // 임의의 StaffDto 객체 생성
        StaffDto testDto = new StaffDto("12345", "name12345", "test" + 12345 + "@spring.co.kr", "password" + 12345, "CO01", "DE01", "3", "Staff", "N", "WS0001", "Y", "2022-03-02", "2000-04-03", "F", "010-1234-5678", "서울시 강남구 영동대로");

        // count 메서드 호출 시 DB 접근 예외 발생시킴
        when(mockDao.insert(testDto)).thenThrow(new DataAccessException("Database connection error!") {});
        assertThrows(DataAccessException.class, () -> mockDao.insert(testDto));
    }

    @Test
    @DisplayName("PK 중복인 경우 데이터 저장 실패 테스트")
    void failToInsert_DuplicatedPK() throws Exception {
        // 이미 테스트에 존재하는 정보가 저장된 테스트용 객체 testDto
        StaffDto testDto = new StaffDto("1", "name1", "test" + 1 + "@spring.co.kr", "password" + 1, "CO01", "DE01", "3", "Staff", "N", "WS0001", "Y", "2022-03-02", "2000-04-03", "F", "010-1234-5678", "서울시 강남구 영동대로");
        assertThrows(DuplicateKeyException.class, () -> staffDao.insert(testDto));
    }

    @Test
    @DisplayName("NOT NULL로 설정된 항목 저장 누락 시 데이터 저장 실패")
    void failToInsert_NotNullColumn() throws Exception {
        // Not Null 항목 누락된 테스트용 객체 testDto
        StaffDto testDto = new StaffDto("12345", null, "test" + 12345 + "@spring.co.kr", "password" + 12345, "CO01", "DE01", "3", "Staff", "N", "WS0001", "Y", "2022-03-02", "2000-04-03", "F", "010-1234-5678", "서울시 강남구 영동대로");
        assertThrows(DataIntegrityViolationException.class, () -> staffDao.insert(testDto));
    }

    @Test
    @DisplayName("타입 길이 오류로 인한 데이터 저장 실패")
    void failToInsert_TypeLengthError() throws Exception {
        // 잘못된 타입으로 설정한 항목이 포함된 테스트용 객체 testDto
        // name 컬럼의 타입 길이는 varchar(20)으로 설정되어 있으며, 그 이상의 데이터 저장 시도 시 관련 예외 발생하는지 확인
        StaffDto testDto = new StaffDto("12345", "name12345name56789name...", "test" + 12345 + "@spring.co.kr", "password" + 12345, "CO01", "DE01", "3", "Staff", "N", "WS0001", "Y", "2022-03-02", "2000-04-03", "F", "010-1234-5678", "서울시 강남구 영동대로");
        assertThrows(DataIntegrityViolationException.class, () -> staffDao.insert(testDto));
    }

    // select 메서드 테스트
    @Test
    @DisplayName("데이터 조회 성공")
    void successOfSelect() throws Exception {
        // 기존에 세팅해둔 데이터로 테스트 진행
        StaffDto testDto1 = staffDao.select("1");
        assertNotNull(testDto1);
        // DB 조회결과 상 원하는 값으로 조회된 것이 맞는지 확인
        assertEquals("name1", staffDao.select("1").getName());

        // 40개의 데이터 조회 시도
        for (int i = 1; i <= 40 ; i++) {
            StaffDto testDto2 = staffDao.select(i + "");
            // 테스트 결과 확인
            assertNotNull(testDto2); // 데이터 조회 성공 여부 확인
            assertEquals("name" + i, testDto2.getName()); // 원하는 데이터로 조회되는지 확인
        }
    }

    @Test
    @DisplayName("데이터 조회 실패")
    void failToSelect() throws Exception {
        // DB에 저장되지 않은 id로 조회 시
        // 조회되는 데이터 없음
        String wrongId = "wrongId";
        assertNull(staffDao.select(wrongId));
    }

    @Test
    @DisplayName("DB 연결 실패로 인한 데이터 조회 실패")
    void failToDBConnection_select() throws Exception {
        // 기존에 셋팅해뒀던 id 활용
        String dbId = "1";
        // select 메서드 호출 시 DB 접근 예외 발생시킴
        when(mockDao.select(dbId)).thenThrow(new DataAccessException("Database connection error!") {});
        assertThrows(DataAccessException.class, () -> mockDao.select(dbId));
    }

    // selectAll 메서드 테스트
    @Test
    @DisplayName("전체 데이터 조회 성공")
    void successOfSelectAll() throws Exception {
        // 전체 데이터 조회 여부 확인
        List<StaffDto> staffs = staffDao.selectAll();
        assertNotNull(staffs);

        // 테스트용으로 셋팅한 데이터 row 수 일치여부 확인
        assertEquals(40, staffDao.count());

        // 의도한 DB 값으로 조회되는지 확인
        for (StaffDto staff : staffs) {
            List<String> ids = new ArrayList<>();
            ids.add(staff.getId());
            assertTrue(ids.contains(staff.getId()));
        }
    }

    @Test
    @DisplayName("전체 데이터 조회 실패")
    void failToSelectAll() throws Exception {
        // 데이터 비우기
        staffDao.deleteAll();

        // 데이터 조회 시 빈문자열([]) 반환
        List<StaffDto> staffs = staffDao.selectAll();
        assertTrue(staffs.isEmpty());
    }

    @Test
    @DisplayName("DB 연결 실패로 인한 전체 데이터 조회 실패")
    void failToDBConnection_selectAll() throws Exception {
        // selectAll() 호출 시 DB 접근 예외 발생시킴
        when(mockDao.selectAll()).thenThrow(new DataAccessException("Database connection error!"){});

        // 설정된 예외 발생여부 확인
        assertThrows(DataAccessException.class, () -> mockDao.selectAll());
    }

    // update 메서드 테스트
    @Test
    @DisplayName("데이터_회사코드 수정 성공")
    void successOfUpdate_corp() throws Exception {
        // 임의의 테스트용 객체
        String testId = "1";
        StaffDto testDto = staffDao.select(testId);

        // 변경할 회사코드
        String corpCode = "CO02";
        // 회사코드 수정 전
        assertNotEquals(corpCode, staffDao.select(testId).getCorp_code());
        // 회사코드 수정
        testDto.setCorp_code(corpCode);
        assertEquals(1, staffDao.update(testDto)); // 수정 성공여부 확인
        // 회사코드 수정 후
        assertEquals(corpCode, staffDao.select(testId).getCorp_code());
    }

    @Test
    @DisplayName("데이터_부서코드 수정 성공")
    void successOfUpdate_depart() throws Exception {
        // 임의의 테스트용 객체
        String testId = "1";
        StaffDto testDto = staffDao.select(testId);

        // 변경할 부서코드
        String departCode = "DE02";
        // 부서코드 수정 전
        assertNotEquals(departCode, staffDao.select(testId).getDepart_code());
        // 부서코드 수정
        testDto.setDepart_code(departCode);
        assertEquals(1, staffDao.update(testDto)); // 수정 성공여부 확인
        // 부서코드 수정 후
        assertEquals(departCode, staffDao.select(testId).getDepart_code());
    }

    @Test
    @DisplayName("데이터_직급 수정 성공")
    void successOfUpdate_grade() throws Exception {
        // 임의의 테스트용 객체
        String testId = "1";
        StaffDto testDto = staffDao.select(testId);

        // 변경할 직급
        String grade = "Charge";
        // 직급 수정 전
        assertNotEquals(grade, staffDao.select(testId).getGrade());
        // 직급 수정
        testDto.setGrade(grade);
        assertEquals(1, staffDao.update(testDto)); // 수정 성공여부 확인
        // 직급 수정 후
        assertEquals(grade, staffDao.select(testId).getGrade());
    }

    @Test
    @DisplayName("데이터_근무상태코드 수정 성공")
    void successOfUpdate_workState() throws Exception {
        // 임의의 테스트용 객체
        String testId = "1";
        StaffDto testDto = staffDao.select(testId);

        // 변경할 근무상태코드
        String workState = "WS0204";
        // 근무상태코드 수정 전
        assertNotEquals(workState, staffDao.select(testId).getWork_state_code());
        // 근무상태코드 수정
        testDto.setWork_state_code(workState);
        assertEquals(1, staffDao.update(testDto)); // 수정 성공여부 확인
        // 근무상태코드 수정 후
        assertEquals(workState, staffDao.select(testId).getWork_state_code());
    }

    @Test
    @DisplayName("데이터_휴대폰번호 수정 성공")
    void successOfUpdate_phoneNum() throws Exception {
        // 임의의 테스트용 객체
        String testId = "1";
        StaffDto testDto = staffDao.select(testId);

        // 변경할 휴대폰번호
        String phoneNum = "01012334455";
        // 휴대폰번호 수정 전
        assertNotEquals(phoneNum, staffDao.select(testId).getPhone_num());
        // 휴대폰번호 수정
        testDto.setPhone_num(phoneNum);
        assertEquals(1, staffDao.update(testDto)); // 수정 성공여부 확인
        // 휴대폰번호 수정 후
        assertEquals(phoneNum, staffDao.select(testId).getPhone_num());
    }

    @Test
    @DisplayName("타입 길이 오류로 인한 회사코드 수정 실패")
    void failToUpdateCorpCode_TypeLengthError() throws Exception {
        // 잘못된 타입길이로 설정한 항목이 포함된 테스트용 객체 testDto
        // corp_code 컬럼의 타입 길이는 varchar(10)으로 설정되어 있으며, 그 이상의 데이터 저장 시도 시 관련 예외 발생하는지 확인
        String testId = "1";
        String testCorpCode = "CO123456789";
        StaffDto testDto = staffDao.select(testId);
        testDto.setCorp_code(testCorpCode);

        assertThrows(DataIntegrityViolationException.class, () -> staffDao.update(testDto));
    }

    @Test
    @DisplayName("타입 길이 오류로 인한 부서코드 수정 실패")
    void failToUpdateDepartCode_TypeLengthError() throws Exception {
        // 잘못된 타입길이로 설정한 항목이 포함된 테스트용 객체 testDto
        // depart_code 컬럼의 타입 길이는 varchar(10)으로 설정되어 있으며, 그 이상의 데이터 저장 시도 시 관련 예외 발생하는지 확인
        String testId = "1";
        String testDepartCode = "DE123456789";
        StaffDto testDto = staffDao.select(testId);
        testDto.setDepart_code(testDepartCode);

        assertThrows(DataIntegrityViolationException.class, () -> staffDao.update(testDto));
    }

    @Test
    @DisplayName("타입 길이 오류로 인한 직급 수정 실패")
    void failToUpdateGrade_TypeLengthError() throws Exception {
        // 잘못된 타입길이로 설정한 항목이 포함된 테스트용 객체 testDto
        // grade 컬럼의 타입 길이는 varchar(10)으로 설정되어 있으며, 그 이상의 데이터 저장 시도 시 관련 예외 발생하는지 확인
        String testId = "1";
        String testGrade = "ChargeNurse";
        StaffDto testDto = staffDao.select(testId);
        testDto.setGrade(testGrade);

        assertThrows(DataIntegrityViolationException.class, () -> staffDao.update(testDto));
    }

    @Test
    @DisplayName("타입 길이 오류로 인한 근무상태코드 수정 실패")
    void failToUpdateWorkStateCode_TypeLengthError() throws Exception {
        // 잘못된 타입길이로 설정한 항목이 포함된 테스트용 객체 testDto
        // work_state_code 컬럼의 타입 길이는 varchar(10)으로 설정되어 있으며, 그 이상의 데이터 저장 시도 시 관련 예외 발생하는지 확인
        String testId = "1";
        String testWorkStateCode = "WS020402040204";
        StaffDto testDto = staffDao.select(testId);
        testDto.setWork_state_code(testWorkStateCode);

        assertThrows(DataIntegrityViolationException.class, () -> staffDao.update(testDto));
    }

    @Test
    @DisplayName("타입 길이 오류로 인한 휴대폰 번호 수정 실패")
    void failToUpdatePhoneNum_TypeLengthError() throws Exception {
        // 잘못된 타입길이로 설정한 항목이 포함된 테스트용 객체 testDto
        // phone_num 컬럼의 타입 길이는 varchar(15)으로 설정되어 있으며, 그 이상의 데이터 저장 시도 시 관련 예외 발생하는지 확인
        String testId = "1";
        String testWorkStateCode = "010-1234-1234-5678";
        StaffDto testDto = staffDao.select(testId);
        testDto.setPhone_num(testWorkStateCode);

        assertThrows(DataIntegrityViolationException.class, () -> staffDao.update(testDto));
    }

    @Test
    @DisplayName("DB 연결 실패로 인한 데이터 수정 실패")
    void failToDBConnection_update() throws Exception {
        String testId = "1";
        String testCorp = "CO02";
        StaffDto testDto = staffDao.select(testId);

        assertNotEquals(testCorp, testDto.getCorp_code());
        testDto.setCorp_code(testCorp);
        assertEquals(testCorp, testDto.getCorp_code());

        // update() 호출 시 DB 접근 예외 발생시킴
        when(mockDao.update(testDto)).thenThrow(new DataAccessException("Database connection error!"){});
        // 설정된 예외 발생여부 확인
        assertThrows(DataAccessException.class, () -> mockDao.update(testDto));
    }

    // delete 메서드 테스트
    @Test
    @DisplayName("데이터 삭제 성공")
    void successOfDelete() throws Exception {
        // 특정 데이터 존재여부 확인
        String testId = "1";

        // 데이터 삭제 전
        assertNotNull(staffDao.select(testId));
        // 데이터 삭제
        assertEquals(1, staffDao.delete(testId));
        // 데이터 삭제 후
        assertNull(staffDao.select(testId));
    }

    @Test
    @DisplayName("데이터 삭제 잘못 시도한 경우 삭제 실패")
    void failToDelete_wrongTry() throws Exception {
        // 존재하지 않는 데이터 삭제 시도 시
        String wrongId = "wrongId";
        assertNull(staffDao.select(wrongId));
        // 데이터 삭제 실패
        assertEquals(0, staffDao.delete(wrongId));
    }

    @Test
    @DisplayName("존재하지 않는 데이터 삭제 시도한 경우 삭제 실패")
    void failToDelete_nonexistData() throws Exception {
        String testId = "1";
        assertNotNull(staffDao.select(testId));

        assertEquals(1, staffDao.delete(testId));
        assertNull(staffDao.select(testId));

        // 존재하지 않는 데이터 삭제 시도 시
        // 데이터 삭제 실패
        assertEquals(0, staffDao.delete(testId));
    }

    @Test
    @DisplayName("DB 연결 실패로 인한 데이터 삭제 실패")
    void failToDBConnection_delete() throws Exception {
        String testId = "1";

        // delete() 호출 시 DB 접근 예외 발생시킴
        when(mockDao.delete(testId)).thenThrow(new DataAccessException("Database connection error!"){});
        // 설정된 예외 발생여부 확인
        assertThrows(DataAccessException.class, () -> mockDao.delete(testId));
    }

    // deleteAll 메서드 테스트
    @Test
    @DisplayName("전체 데이터 삭제 성공")
    void successOfDeleteAll() throws Exception {
        // 현재 미리 셋팅된 데이터 존재함을 확인
        assertNotNull(staffDao.selectAll());
        assertFalse(staffDao.selectAll().isEmpty());

        // 전체 데이터 삭제
        // 셋팅해뒀던 40개의 row 삭제
        assertEquals(40, staffDao.deleteAll());
        // 전체 데이터 삭제 후
        // 전체 데이터 조회 시 빈배열([]) 반환
        assertTrue(staffDao.selectAll().isEmpty());
    }

    @Test
    @DisplayName("전체 데이터 삭제 실패")
    void failToDeleteAll() throws Exception {
        // 기존에 셋팅된 데이터 40개 전체 삭제
        assertEquals(40, staffDao.deleteAll());
        // 데이터 모두 삭제된 상태임을 확인
        assertTrue(staffDao.selectAll().isEmpty());
        // 데이터 없는 상태에서 전체 데이터 삭제 시도 시, 삭제되는 데이터 없음을 확인
        assertEquals(0, staffDao.deleteAll());
    }

    @Test
    @DisplayName("DB 연결 실패로 인한 전체 데이터 삭제 실패")
    void failToDBConnection_deleteAll() throws Exception {
        // deleteAll() 호출 시 DB 접근 예외 발생시킴
        when(mockDao.deleteAll()).thenThrow(new DataAccessException("Database connection error!"){});
        // 설정된 예외 발생여부 확인
        assertThrows(DataAccessException.class, () -> mockDao.deleteAll());
    }
}