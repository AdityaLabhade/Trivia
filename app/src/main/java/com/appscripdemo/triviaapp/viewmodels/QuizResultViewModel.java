package com.appscripdemo.triviaapp.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.appscripdemo.triviaapp.models.QuizDetails;
import com.appscripdemo.triviaapp.models.QuizResult;
import com.appscripdemo.triviaapp.room.entities.QuizDetailsEntity;
import com.appscripdemo.triviaapp.room.entities.QuizResultEntity;
import com.appscripdemo.triviaapp.room.repos.QuizRepo;
import com.appscripdemo.triviaapp.utils.DateTimeUtils;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class QuizResultViewModel extends AndroidViewModel {

    @Inject
    QuizRepo quizRepo;

    @Inject
    public QuizResultViewModel(@NonNull Application application) {
        super(application);
        quizRepo = new QuizRepo(application.getApplicationContext());
    }

    public void insertQuizResult(QuizResult quizResult){
        QuizResultEntity quizResultEntity = new QuizResultEntity();
        quizResultEntity.setQuizDetailsId(quizResult.getQuizId());
        quizResultEntity.setQuestion(quizResult.getQuestion());
        quizResultEntity.setAnswer(quizResult.getAnswer());
        quizRepo.insertQuizResults(quizResultEntity);
    }

    public LiveData<List<QuizResultEntity>> getQuizResultList(Long quizId){
        return quizRepo.getQuizResultsList(quizId);
    }
}
