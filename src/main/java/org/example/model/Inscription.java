package org.example.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Inscription {
    private String name;
    private String content;
    private LocalDateTime date;

    public Inscription(String name, String content, LocalDateTime date) {
        this.name = name;
        this.content = content;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public Inscription setName(String name) {
        this.name = name;
        return this;
    }

    public String getContent() {
        return content;
    }

    public Inscription setContent(String content) {
        this.content = content;
        return this;
    }

    public String getDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.mm.yyyy hh:mm:ss");
        return this.date.format(formatter);
    }

    public Inscription setDate(LocalDateTime date) {
        this.date = date;
        return this;
    }
}
