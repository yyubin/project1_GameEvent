package com.smhrd.js;

public class MemberDTO {

        private String id;
        private String pw;
        private String name;
        private String tel;
        private String lol_name;
        private String email;

    public MemberDTO(String id, String pw, String name, String tel, String lol_name, String email) {
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.tel = tel;
        this.lol_name = lol_name;
        this.email = email;
    }

    public MemberDTO(String name, String tel, String lol_name, String email) {
        this.name = name;
        this.tel = tel;
        this.lol_name = lol_name;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getLol_name() {
        return lol_name;
    }

    public void setLol_name(String lol_name) {
        this.lol_name = lol_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
