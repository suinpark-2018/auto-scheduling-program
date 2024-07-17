package com.schedule.domain;

public class StaffInfoChgHistDto {
    String id; // 사번
    String name; // 직원명
    String chg_code; // 변경사항 코드
    String bef_info; // 변경 전 정보
    String af_info; // 변경 후 정보
    String chg_date; // 변경일

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

    public String getChg_code() {
        return chg_code;
    }

    public void setChg_code(String chg_code) {
        this.chg_code = chg_code;
    }

    public String getBef_info() {
        return bef_info;
    }

    public void setBef_info(String bef_info) {
        this.bef_info = bef_info;
    }

    public String getAf_info() {
        return af_info;
    }

    public void setAf_info(String af_info) {
        this.af_info = af_info;
    }

    public String getChg_date() {
        return chg_date;
    }

    public void setChg_date(String chg_date) {
        this.chg_date = chg_date;
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
        return "StaffInfoChgHistDto{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", chg_code='" + chg_code + '\'' +
                ", bef_info='" + bef_info + '\'' +
                ", af_info='" + af_info + '\'' +
                ", chg_date='" + chg_date + '\'' +
                ", reg_id='" + reg_id + '\'' +
                ", reg_date='" + reg_date + '\'' +
                ", up_id='" + up_id + '\'' +
                ", up_date='" + up_date + '\'' +
                '}';
    }
}