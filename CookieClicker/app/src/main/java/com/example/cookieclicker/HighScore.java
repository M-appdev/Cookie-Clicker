package com.example.cookieclicker;

public class HighScore {
    private String nickName;
    private int score;
    private int modePlayed;

    public HighScore(String nickName, int score, int modePlayed) {
        this.nickName = nickName;
        this.score = score;
        this.modePlayed = modePlayed;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setModePlayed(int modePlayed) {
        this.modePlayed = modePlayed;
    }

    public String getNickName() {
        return nickName;
    }

    public int getScore() {
        return score;
    }

    public int getModePlayed() {
        return modePlayed;
    }
}
