package com.schedule.domain;

public class StaffDto {
    String id; // 사번
    String name; // 직원명
    String email; // 이메일
    String pwd; // 비밀번호
    String corp_code; // 회사코드
    String depart_code; // 부서코드
    String work_years; // 연차
    String grade; // 직급
    String skill_level; // 업무 숙련도
    String fix_duty; // 고정 근무
    String fix_off; // 고정 휴무
    String is_preceptor; // 프리셉터 여부
    String is_preceptee; // 프리셉티 여부
    String preg_state; // 임신여부
    String work_state_code; // 근무상태 코드
    String mng_auth; // 근무 일정표 관리권한
    String is_active; // 재직상태
    String hire_date; // 입사년도
    String quit_date; // 퇴사년도
    String birth; // 생년월일
    String gender; // 성별
    String phone_num; // 휴대폰 번호
    String address; // 주소
    String mail_auth; // 본인인증 여부

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getCorp_code() {
        return corp_code;
    }

    public void setCorp_code(String corp_code) {
        this.corp_code = corp_code;
    }

    public String getDepart_code() {
        return depart_code;
    }

    public void setDepart_code(String depart_code) {
        this.depart_code = depart_code;
    }

    public String getWork_years() {
        return work_years;
    }

    public void setWork_years(String work_years) {
        this.work_years = work_years;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getSkill_level() {
        return skill_level;
    }

    public void setSkill_level(String skill_level) {
        this.skill_level = skill_level;
    }

    public String getFix_duty() {
        return fix_duty;
    }

    public void setFix_duty(String fix_duty) {
        this.fix_duty = fix_duty;
    }

    public String getFix_off() {
        return fix_off;
    }

    public void setFix_off(String fix_off) {
        this.fix_off = fix_off;
    }

    public String getIs_preceptor() {
        return is_preceptor;
    }

    public void setIs_preceptor(String is_preceptor) {
        this.is_preceptor = is_preceptor;
    }

    public String getIs_preceptee() {
        return is_preceptee;
    }

    public void setIs_preceptee(String is_preceptee) {
        this.is_preceptee = is_preceptee;
    }

    public String getPreg_state() {
        return preg_state;
    }

    public void setPreg_state(String preg_state) {
        this.preg_state = preg_state;
    }

    public String getWork_state_code() {
        return work_state_code;
    }

    public void setWork_state_code(String work_state_code) {
        this.work_state_code = work_state_code;
    }

    public String getMng_auth() {
        return mng_auth;
    }

    public void setMng_auth(String mng_auth) {
        this.mng_auth = mng_auth;
    }

    public String getIs_active() {
        return is_active;
    }

    public void setIs_active(String is_active) {
        this.is_active = is_active;
    }

    public String getHire_date() {
        return hire_date;
    }

    public void setHire_date(String hire_date) {
        this.hire_date = hire_date;
    }

    public String getQuit_date() {
        return quit_date;
    }

    public void setQuit_date(String quit_date) {
        this.quit_date = quit_date;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone_num() {
        return phone_num;
    }

    public void setPhone_num(String phone_num) {
        this.phone_num = phone_num;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMail_auth() {
        return mail_auth;
    }

    public void setMail_auth(String mail_auth) {
        this.mail_auth = mail_auth;
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
        return "StaffDto{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", pwd='" + pwd + '\'' +
                ", corp_code='" + corp_code + '\'' +
                ", depart_code='" + depart_code + '\'' +
                ", work_years='" + work_years + '\'' +
                ", grade='" + grade + '\'' +
                ", skill_level='" + skill_level + '\'' +
                ", fix_duty='" + fix_duty + '\'' +
                ", fix_off='" + fix_off + '\'' +
                ", is_preceptor='" + is_preceptor + '\'' +
                ", is_preceptee='" + is_preceptee + '\'' +
                ", preg_state='" + preg_state + '\'' +
                ", work_state_code='" + work_state_code + '\'' +
                ", mng_auth='" + mng_auth + '\'' +
                ", is_active='" + is_active + '\'' +
                ", hire_date='" + hire_date + '\'' +
                ", quit_date='" + quit_date + '\'' +
                ", birth='" + birth + '\'' +
                ", gender='" + gender + '\'' +
                ", phone_num='" + phone_num + '\'' +
                ", address='" + address + '\'' +
                ", mail_auth='" + mail_auth + '\'' +
                ", reg_id='" + reg_id + '\'' +
                ", reg_date='" + reg_date + '\'' +
                ", up_id='" + up_id + '\'' +
                ", up_date='" + up_date + '\'' +
                '}';
    }
}
