package com.smhrd.js;

public class BoardDTO {

    private String board_num;
    private String name;
    private String title;
    private String text;
    private String time;
    private String num;

    public BoardDTO(String board_num, String name, String title, String text, String time, String num) {
        this.board_num = board_num;
        this.name = name;
        this.title = title;
        this.text = text;
        this.time = time;
        this.num = num;
    }

    public BoardDTO(String name, String title, String text, String time) {
        this.name = name;
        this.title = title;
        this.text = text;
        this.time = time;
    }

    public BoardDTO(String name, String title, String text, String time, String num) {
        this.name = name;
        this.title = title;
        this.text = text;
        this.time = time;
        this.num = num;
    }


    public String getBoard_num() {
        return board_num;
    }

    public void setBoard_num(String board_num) {
        this.board_num = board_num;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
