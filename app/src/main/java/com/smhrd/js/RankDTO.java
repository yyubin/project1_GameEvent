package com.smhrd.js;

public class RankDTO {

    private int rank;
    private String team_name, team_score;

    public RankDTO(int rank, String team_name, String team_score) {
        this.rank = rank;
        this.team_name = team_name;
        this.team_score = team_score;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getTeam_name() {
        return team_name;
    }

    public void setTeam_name(String team_name) {
        this.team_name = team_name;
    }

    public String getTeam_score() {
        return team_score;
    }

    public void setTeam_score(String team_score) {
        this.team_score = team_score;
    }




}
