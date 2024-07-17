package com.schedule.domain;

public class ShiftSchdArchDto {
    String id; // 사번
    String name; // 직원명
    String fix_duty; // 고정근무
    String work_date; // 근무일자
    String duty_code; // 근무유형 코드
    String work_range; // 근무시간대

    // 시스템 컬럼 항목
    String reg_id; // 최초등록자
    String reg_date; // 최초 등록일
    String up_id; // 최종 수정자
    String up_date; // 최종 수정일

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFix_duty() {
        return fix_duty;
    }

    public void setFix_duty(String fix_duty) {
        this.fix_duty = fix_duty;
    }

    public String getWork_date() {
        return work_date;
    }

    public void setWork_date(String work_date) {
        this.work_date = work_date;
    }

    public String getDuty_code() {
        return duty_code;
    }

    public void setDuty_code(String duty_code) {
        this.duty_code = duty_code;
    }

    public String getWork_range() {
        return work_range;
    }

    public void setWork_range(String work_range) {
        this.work_range = work_range;
    }

    public String getReg_id() {
        return reg_id;
    }

    public void setReg_id(String reg_id) {
        this.reg_id = reg_id;
    }

    public String getReg_date() {
        return reg_date;
    }

    public void setReg_date(String reg_date) {
        this.reg_date = reg_date;
    }

    public String getUp_id() {
        return up_id;
    }

    public void setUp_id(String up_id) {
        this.up_id = up_id;
    }

    public String getUp_date() {
        return up_date;
    }

    public void setUp_date(String up_date) {
        this.up_date = up_date;
    }

    @Override
    public String toString() {
        return "ShiftSchdArchDto{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", fix_duty='" + fix_duty + '\'' +
                ", work_date='" + work_date + '\'' +
                ", duty_code='" + duty_code + '\'' +
                ", work_range='" + work_range + '\'' +
                ", reg_id='" + reg_id + '\'' +
                ", reg_date='" + reg_date + '\'' +
                ", up_id='" + up_id + '\'' +
                ", up_date='" + up_date + '\'' +
                '}';
    }
}
