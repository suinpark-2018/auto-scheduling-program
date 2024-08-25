package com.schedule.dto;

public class DutyExchReqHistDto {
    String exch_req_seq; // 신청 식별번호
    String req_staff_id; // 신청자 아이디
    String req_state; // 교환 처리상태
    String approver_id; // 승인자 아이디
    String approval_date; // 승인일
    String comment; // 기타사항

    // 시스템 컬럼 항목
    String reg_id; // 최초등록자
    String reg_date; // 최초 등록일
    String up_id; // 최종 수정자
    String up_date; // 최종 수정일

    public String getExch_req_seq() {
        return exch_req_seq;
    }

    public void setExch_req_seq(String exch_req_seq) {
        this.exch_req_seq = exch_req_seq;
    }

    public String getReq_staff_id() {
        return req_staff_id;
    }

    public void setReq_staff_id(String req_staff_id) {
        this.req_staff_id = req_staff_id;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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
        return "DutyExchReqHistDto{" +
                "exch_req_seq='" + exch_req_seq + '\'' +
                ", req_staff_id='" + req_staff_id + '\'' +
                ", req_state='" + req_state + '\'' +
                ", approver_id='" + approver_id + '\'' +
                ", approval_date='" + approval_date + '\'' +
                ", comment='" + comment + '\'' +
                ", reg_id='" + reg_id + '\'' +
                ", reg_date='" + reg_date + '\'' +
                ", up_id='" + up_id + '\'' +
                ", up_date='" + up_date + '\'' +
                '}';
    }
}
