package com.amm.rest.greetings.model;

public class Greetings {

    private Integer id;
    private String content;

    public Greetings() {
        super();
    }

    public Greetings(Integer id, String content) {
        super();
        this.id = id;
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "Greetings{" +
                "id=" + id +
                ", content='" + content + '\'' +
                '}';
    }
}
