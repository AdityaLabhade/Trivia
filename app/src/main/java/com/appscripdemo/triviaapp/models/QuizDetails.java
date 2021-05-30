package com.appscripdemo.triviaapp.models;


import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.appscripdemo.triviaapp.BR;

import java.util.List;

public class QuizDetails extends BaseObservable {

    private long id;
    private String userName;
    private List<QuizResult> listQuizResult;
    private String dateTime;

    @Bindable
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
        notifyPropertyChanged(BR.id);
    }
    @Bindable
    public String getUserName() {
        return userName!=null ? userName : "";
    }

    public void setUserName(String userName) {
        this.userName = userName;
        notifyPropertyChanged(BR.userName);
    }
    @Bindable
    public List<QuizResult> getListQuizResult() {
        return listQuizResult;
    }

    public void setListQuizResult(List<QuizResult> listQuizResult) {
        this.listQuizResult = listQuizResult;
        notifyPropertyChanged(BR.listQuizResult);
    }

    @Bindable
    public String getDateTime() {
        return dateTime!=null ? dateTime : "";
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
        notifyPropertyChanged(BR.dateTime);
    }

}
