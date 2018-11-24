package com.hm.groupchat.Models;

import com.stfalcon.chatkit.commons.models.IMessage;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Message implements IMessage {

    private String id;
    private String text;
    private Author author;
    private Date createdAt;

    public Message() {


    }

    public Message(String  id, String text, Author author, Date createdAt) {

        this.id = id;
        this.text = text;
        this.author = author;
        this.createdAt = createdAt;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public Author getUser() {
        return author;
    }

    @Override
    public Date getCreatedAt() {
        return createdAt;
    }

    public Map<String, Object> toMap() {

        HashMap<String, Object> result = new HashMap<>();
        result.put("id", id);
        result.put("text", text);
        result.put("author", author.getId());
        result.put("createdAt", createdAt.getTime());

        return result;
    }
}
