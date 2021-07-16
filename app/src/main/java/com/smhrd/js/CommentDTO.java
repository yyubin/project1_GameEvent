package com.smhrd.js;

public class CommentDTO {

    String lol_name;
    String comment;
    String num;

    public CommentDTO(String lol_name, String comment) {
        this.lol_name = lol_name;
        this.comment = comment;
    }

    public CommentDTO(String lol_name, String comment, String num) {
        this.lol_name = lol_name;
        this.comment = comment;
        this.num = num;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getLol_name() {
        return lol_name;
    }

    public void setLol_name(String lol_name) {
        this.lol_name = lol_name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
