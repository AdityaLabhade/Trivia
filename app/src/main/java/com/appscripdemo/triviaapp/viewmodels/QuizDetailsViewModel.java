package com.appscripdemo.triviaapp.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.appscripdemo.triviaapp.models.QuizDetails;
import com.appscripdemo.triviaapp.room.entities.QuizDetailsEntity;
import com.appscripdemo.triviaapp.room.entities.QuizResultEntity;
import com.appscripdemo.triviaapp.room.repos.QuizRepo;
import com.appscripdemo.triviaapp.utils.DateTimeUtils;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class QuizDetailsViewModel extends AndroidViewModel {

    @Inject
    QuizRepo quizRepo;

    @Inject
    public QuizDetailsViewModel(@NonNull Application application) {
        super(application);
        quizRepo = new QuizRepo(application.getApplicationContext());
    }

    public LiveData<Long>  insertQuizDetails(QuizDetails quizDetails) {
        QuizDetailsEntity entityQuizDetailsEntity = new QuizDetailsEntity();
        entityQuizDetailsEntity.setQuizTime(DateTimeUtils.getCurrentDateTime());
        entityQuizDetailsEntity.setUserName(quizDetails.getUserName());
        return quizRepo.insertQuizDetails(entityQuizDetailsEntity);
    }

    public LiveData<List<QuizDetailsEntity>> getQuizDetailsList() {
        return quizRepo.getQuizDetailsList();
    }

    public LiveData<List<QuizDetailsEntity>> getSingleQuizDetails(long quizId) {
        return (quizRepo.getSingleQuizDetails(quizId));
    }

}
