package com.schedule.dao;

import com.schedule.dto.StaffDto;

import java.util.List;

public interface StaffDao {

    // 현재 시간 반환
    String now() throws Exception;

    // 직원 수
    int count() throws Exception;

    // 1.1. 직원 정보 입력
    int insert(StaffDto staffDto) throws Exception;

    // 1.2. 특정 직원 정보 조회
    StaffDto select(String id) throws Exception;

    // 1.2. 전체 직원 정보 조회
    List<StaffDto> selectAll() throws Exception;

    int update(StaffDto staffDto) throws Exception;

    // 4.1. 특정 직원 정보 삭제
    int delete(String id) throws Exception;

    // 4.2. 전체 직원 정보 삭제
    int deleteAll() throws Exception;
}
