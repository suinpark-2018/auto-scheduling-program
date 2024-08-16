package com.schedule.controller;

import com.schedule.common.validation.*;
import com.schedule.dto.StaffDto;
import com.schedule.service.StaffServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/register", produces = "application/json;charset=UTF-8")
@RequiredArgsConstructor
public class SignUpController {
    private final StaffServiceImpl staffService;
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/form")
    public String moveToRegisterPage() {
        return "register";
    }

    @PostMapping("/checkDuplicatedId")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> checkDuplicatedId(@RequestBody @Validated(ValidationGroups.IdCheckGroup.class) StaffDto staffDto, BindingResult bindingResult) {
        Map<String, Object> response = new HashMap<>();

        // 유효성 검증에 실패한 경우
        if (bindingResult.hasErrors()) {
            response.put("status", "error");
            response.put("message", bindingResult.getAllErrors().get(0).getDefaultMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        String id = staffDto.getId();
        try {
            // 중복 아이디가 아닌 경우
            if (!staffService.checkDuplicatedId(id)) {
                response.put("status", "success");
                response.put("message", "사용 가능한 아이디입니다.");
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                // 중복된 아이디인 경우
                response.put("status", "error");
                response.put("message", "이미 사용중인 아이디입니다.");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "서버 오류가 발생했습니다.");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/info")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getStaffRegisterInfo(@RequestBody @Validated(ValidationSequence.class) StaffDto staffDto, BindingResult bindingResult) {
        Map<String, Object> response = new HashMap<>();

        if (bindingResult.hasErrors()) {
            response.put("status", "error");
            response.put("message", bindingResult.getAllErrors().get(0).getDefaultMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } else {
            try {
                staffService.saveStaffJoinInfo(staffDto);
                response.put("status", "success");
                response.put("message", "회원가입 성공!");
                return new ResponseEntity<>(response, HttpStatus.OK);
            } catch (Exception e) {
                response.put("status", "error");
                response.put("message", "서버 오류 발생");
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }
}