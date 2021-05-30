package com.appscripdemo.triviaapp.room.repos;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;

import com.appscripdemo.triviaapp.room.TriviaDataBase;
import com.appscripdemo.triviaapp.room.entities.QuizDetailsEntity;
import com.appscripdemo.triviaapp.room.entities.QuizResultEntity;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Inject;

import dagger.hilt.android.qualifiers.ApplicationContext;

public class QuizRepo {
    private String DB_NAME = "db_triviaapp_appsrcip";
    private TriviaDataBase triviaDataBase;
    long insertId = 0;
    List<QuizDetailsEntity>  quizDetailsEntityList = null;

    @Inject
    public QuizRepo(@ApplicationContext Context context) {
        triviaDataBase = Room.databaseBuilder(context, TriviaDataBase.class, DB_NAME).build();
    }

    public LiveData<Long> insertQuizDetails(final QuizDetailsEntity quizDetailsEntity) {
        MutableLiveData<Long> mutableInsertId = new MutableLiveData<>();

        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> mutableInsertId.postValue(triviaDataBase.quizDetailsDao().insert(quizDetailsEntity)));

//        LiveData<Long> insertId = triviaDataBase.quizDetailsDao().insert(quizDetailsEntity);
        return mutableInsertId;
    }

    public LiveData<List<QuizDetailsEntity>> getQuizDetailsList(){
        return triviaDataBase.quizDetailsDao().fetchAll();
    }

    public LiveData<List<QuizDetailsEntity>> getSingleQuizDetails(Long quizId){
        return  triviaDataBase.quizDetailsDao().fetchSingle(quizId);
    }

    public void insertQuizResults(final QuizResultEntity quizResultEntity) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> triviaDataBase.quizResultDao().insert(quizResultEntity));
    }

    public LiveData<List<QuizResultEntity>> getQuizResultsList(Long quizId){
        return triviaDataBase.quizResultDao().fetchAll(quizId);
    }
}
