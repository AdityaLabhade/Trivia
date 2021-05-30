package com.appscripdemo.triviaapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.appscripdemo.triviaapp.BR;

public class QuizResult extends BaseObservable {

    private long quizId;
    private String question;
    private String answer;


    @Bindable
    public long getQuizId() {
        return quizId;
    }

    public void setQuizId(long quizId) {
        this.quizId = quizId;
    }

    @Bindable
    public String getQuestion() {
        return question!=null ? question : "";
    }

    public void setQuestion(String question) {
        this.question = question;
        notifyPropertyChanged(BR.question);
    }

    @Bindable
    public String getAnswer() {
        return answer!=null ? answer : "";
    }

    public void setAnswer(String answer) {
        this.answer = answer;
        notifyPropertyChanged(BR.answer);
    }

}
