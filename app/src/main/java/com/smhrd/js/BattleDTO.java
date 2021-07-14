package com.smhrd.js;

public class BattleDTO {

    private String team_name;
    private String top;
    private String jug;
    private String mid;
    private String adc;
    private String sup;

    public BattleDTO(String team_name, String top, String jug, String mid, String adc, String sup) {
        this.team_name = team_name;
        this.top = top;
        this.jug = jug;
        this.mid = mid;
        this.adc = adc;
        this.sup = sup;
    }
    public BattleDTO(String team_name) {
        this.team_name = team_name;
    }

    public String getTop() {
        return top;
    }

    public void setTop(String top) {
        this.top = top;
    }

    public String getJug() {
        return jug;
    }

    public void setJug(String jug) {
        this.jug = jug;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getAdc() {
        return adc;
    }

    public void setAdc(String adc) {
        this.adc = adc;
    }

    public String getSup() {
        return sup;
    }

    public void setSup(String sup) {
        this.sup = sup;
    }



    public String getTeam_name() {
        return team_name;
    }

    public void setTeam_name(String team_name) {
        this.team_name = team_name;
    }
}
