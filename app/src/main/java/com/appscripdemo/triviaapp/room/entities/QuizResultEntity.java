package com.appscripdemo.triviaapp.room.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tbl_quiz_result")
public class QuizResultEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private Long quizDetailsId;
    private String question;
    private String answer;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Long getQuizDetailsId() {
        return quizDetailsId;
    }

    public void setQuizDetailsId(Long quizDetailsId) {
        this.quizDetailsId = quizDetailsId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
