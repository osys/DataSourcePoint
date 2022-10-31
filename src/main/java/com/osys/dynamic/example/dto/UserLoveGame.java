package com.osys.dynamic.example.dto;

import java.util.StringJoiner;

/**
 * <p><b>{@link UserLoveGame} Description</b>:
 * </p>
 * @author Created by osys on 2022/09/02 11:42.
 */
public class UserLoveGame {
    private int userId;

    private String gameName;

    public UserLoveGame() {
    }

    public UserLoveGame(int userId, String gameName) {
        this.userId = userId;
        this.gameName = gameName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", UserLoveGame.class.getSimpleName() + "[", "]")
                .add("userId=" + userId)
                .add("gameName='" + gameName + "'")
                .toString();
    }
}
