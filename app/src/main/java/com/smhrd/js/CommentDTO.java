package com.smhrd.js;

public class CommentDTO {

    String lol_name;
    String comment;

    public CommentDTO(String lol_name, String comment) {
        this.lol_name = lol_name;
        this.comment = comment;
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
