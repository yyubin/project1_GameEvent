package com.smhrd.js;

public class ChatDTO {

    private String id;
    private String chat;

    public ChatDTO(String id, String chat) {
        this.id = id;
        this.chat = chat;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getChat() {
        return chat;
    }

    public void setChat(String chat) {
        this.chat = chat;
    }
}