package com.schedule.dto;

public class DutyExchReqDto {
    String req_staff_id; // 신청자 사번
    String req_staff_name; // 신청자 이름
    String req_staff_bf_duty; // 신청자 교환 전 근무
    String req_staff_af_duty; // 신청자 교환 후 근무
    String exch_staff_id; // 교환대상 사번
    String exch_staff_name; // 교환대상 이름
    String exch_staff_bf_duty; // 교환대상 교환 전 근무
    String exch_staff_af_duty; // 교환대상 교환 후 근무
    String exch_rsn; // 교환사유
    String is_agreed; // 교환대상 수락여부
    String req_date; // 교환 요청일
    String req_state; // 신청 처리상태
    String approver_id; // 승인자
    String approval_date; // 승인일

    // 시스템 컬럼 항목
    String reg_id; // 최초등록자
    String reg_date; // 최초 등록일
    String up_id; // 최종 수정자
    String up_date; // 최종 수정일

    public String getReq_staff_id() {
        return req_staff_id;
    }

    public void setReq_staff_id(String req_staff_id) {
        this.req_staff_id = req_staff_id;
    }

    public String getReq_staff_name() {
        return req_staff_name;
    }

    public void setReq_staff_name(String req_staff_name) {
        this.req_staff_name = req_staff_name;
    }

    public String getReq_staff_bf_duty() {
        return req_staff_bf_duty;
    }

    public void setReq_staff_bf_duty(String req_staff_bf_duty) {
        this.req_staff_bf_duty = req_staff_bf_duty;
    }

    public String getReq_staff_af_duty() {
        return req_staff_af_duty;
    }

    public void setReq_staff_af_duty(String req_staff_af_duty) {
        this.req_staff_af_duty = req_staff_af_duty;
    }

    public String getExch_staff_id() {
        return exch_staff_id;
    }

    public void setExch_staff_id(String exch_staff_id) {
        this.exch_staff_id = exch_staff_id;
    }

    public String getExch_staff_name() {
        return exch_staff_name;
    }

    public void setExch_staff_name(String exch_staff_name) {
        this.exch_staff_name = exch_staff_name;
    }

    public String getExch_staff_bf_duty() {
        return exch_staff_bf_duty;
    }

    public void setExch_staff_bf_duty(String exch_staff_bf_duty) {
        this.exch_staff_bf_duty = exch_staff_bf_duty;
    }

    public String getExch_staff_af_duty() {
        return exch_staff_af_duty;
    }

    public void setExch_staff_af_duty(String exch_staff_af_duty) {
        this.exch_staff_af_duty = exch_staff_af_duty;
    }

    public String getExch_rsn() {
        return exch_rsn;
    }

    public void setExch_rsn(String exch_rsn) {
        this.exch_rsn = exch_rsn;
    }

    public String getIs_agreed() {
        return is_agreed;
    }

    public void setIs_agreed(String is_agreed) {
        this.is_agreed = is_agreed;
    }

    public String getReq_date() {
        return req_date;
    }

    public void setReq_date(String req_date) {
        this.req_date = req_date;
    }

    public String getReq_state() {
        return req_state;
    }

    public void setReq_state(String req_state) {
        this.req_state = req_state;
    }

    public String getApprover_id() {
        return approver_id;
    }

    public void setApprover_id(String approver_id) {
        this.approver_id = approver_id;
    }

    public String getApproval_date() {
        return approval_date;
    }

    public void setApproval_date(String approval_date) {
        this.approval_date = approval_date;
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
        return "DutyExchReqDto{" +
                "req_staff_id='" + req_staff_id + '\'' +
                ", req_staff_name='" + req_staff_name + '\'' +
                ", req_staff_bf_duty='" + req_staff_bf_duty + '\'' +
                ", req_staff_af_duty='" + req_staff_af_duty + '\'' +
                ", exch_staff_id='" + exch_staff_id + '\'' +
                ", exch_staff_name='" + exch_staff_name + '\'' +
                ", exch_staff_bf_duty='" + exch_staff_bf_duty + '\'' +
                ", exch_staff_af_duty='" + exch_staff_af_duty + '\'' +
                ", exch_rsn='" + exch_rsn + '\'' +
                ", is_agreed='" + is_agreed + '\'' +
                ", req_date='" + req_date + '\'' +
                ", req_state='" + req_state + '\'' +
                ", approver_id='" + approver_id + '\'' +
                ", approval_date='" + approval_date + '\'' +
                ", reg_id='" + reg_id + '\'' +
                ", reg_date='" + reg_date + '\'' +
                ", up_id='" + up_id + '\'' +
                ", up_date='" + up_date + '\'' +
                '}';
    }
}
