package com.appscripdemo.triviaapp.room.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tbl_quiz_details")
public class QuizDetailsEntity {
    @PrimaryKey(autoGenerate = true)
    private long id;
    private String userName;
    private String quizTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getQuizTime() {
        return quizTime;
    }

    public void setQuizTime(String quizTime) {
        this.quizTime = quizTime;
    }
}
