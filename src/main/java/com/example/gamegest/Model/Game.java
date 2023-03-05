package com.example.gamegest.Model;

public class Game {
    public int id;
    public String name;
    public String genre;
    public String platform;
    public boolean completion_status;

    public Game() {
    }

    public Game(String name, String genre, String platform, boolean completion_status) {
        this.name = name;
        this.genre = genre;
        this.platform = platform;
        this.completion_status = completion_status;
    }

    public Game(int id, String name, String genre, String platform, boolean completion_status) {
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.platform = platform;
        this.completion_status = completion_status;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public boolean getCompletion_status() {
        return completion_status;
    }

    public void setCompletion_status(boolean completion_status) {
        this.completion_status = completion_status;
    }
}
