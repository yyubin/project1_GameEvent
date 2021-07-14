package com.smhrd.js;

public class MemberDTO {

    private String id;
    private String pw;
    private String name;
    private String tel;
    private String lol_name;
    private String email;
    private String team_name;
    private String member_code;
    private String team_code;
    private String team_rogo;
    private String leader_code;
    private String team_score;
    private String team_intro;




    public String getLeader_code() {
        return leader_code;
    }

    public void setLeader_code(String leader_code) {
        this.leader_code = leader_code;
    }

    public String getTeam_score() {
        return team_score;
    }

    public void setTeam_score(String team_score) {
        this.team_score = team_score;
    }

    public String getTeam_intro() {
        return team_intro;
    }

    public void setTeam_intro(String team_intro) {
        this.team_intro = team_intro;
    }

    public MemberDTO(String id, String pw, String name, String tel, String lol_name, String email, String team_name, String member_code, String team_code, String team_rogo, String leader_code, String team_score, String team_intro) {
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.tel = tel;
        this.lol_name = lol_name;
        this.email = email;
        this.team_name = team_name;
        this.member_code = member_code;
        this.team_code = team_code;
        this.team_rogo = team_rogo;
        this.leader_code = leader_code;
        this.team_score = team_score;
        this.team_intro = team_intro;
    }




    public MemberDTO(String id, String pw, String name, String tel, String lol_name, String email) {
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.tel = tel;
        this.lol_name = lol_name;
        this.email = email;
    }



    public String getTeam_code() {
        return team_code;
    }

    public void setTeam_code(String team_code) {
        this.team_code = team_code;
    }

    public String getTeam_rogo() {
        return team_rogo;
    }

    public void setTeam_rogo(String team_rogo) {
        this.team_rogo = team_rogo;
    }

    public MemberDTO(String id, String pw, String name, String tel, String lol_name, String email, String team_name, String member_code) {
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.tel = tel;
        this.lol_name = lol_name;
        this.email = email;
        this.team_name = team_name;
        this.member_code = member_code;
    }

    public String getMember_code() {
        return member_code;
    }

    public void setMember_code(String member_code) {
        this.member_code = member_code;
    }

    public MemberDTO(String id, String pw, String name, String tel, String lol_name, String email, String team_name) {
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.tel = tel;
        this.lol_name = lol_name;
        this.email = email;
        this.team_name = team_name;
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

    public String getTeam_name() {
        return team_name;
    }

    public void setTeam_name(String team_name) {
        this.team_name = team_name;
    }
}
