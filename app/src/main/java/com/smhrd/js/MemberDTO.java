package com.smhrd.js;

import java.io.Serializable;

public class MemberDTO implements Serializable {
    private String id;
    private String pw;
    private String name;
    private String email;
    private String phone;
    private String nickname;

    public MemberDTO(String id, String pw, String name, String email, String phone, String nickname) {
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.nickname = nickname;
    }

    public MemberDTO(String id, String pw, String name, String nickname) {
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.nickname = nickname;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
