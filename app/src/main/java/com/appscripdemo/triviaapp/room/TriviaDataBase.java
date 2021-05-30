package com.appscripdemo.triviaapp.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.appscripdemo.triviaapp.room.daos.QuizResultDao;
import com.appscripdemo.triviaapp.room.entities.QuizDetailsEntity;
import com.appscripdemo.triviaapp.room.daos.QuizDetailsDao;
import com.appscripdemo.triviaapp.room.entities.QuizResultEntity;

@Database(entities = {QuizDetailsEntity.class, QuizResultEntity.class}, version = 1, exportSchema = false)
public abstract class TriviaDataBase extends RoomDatabase {
    public abstract QuizDetailsDao quizDetailsDao();
    public abstract QuizResultDao quizResultDao();
}
