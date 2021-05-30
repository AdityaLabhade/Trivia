package com.appscripdemo.triviaapp.room.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.appscripdemo.triviaapp.room.entities.QuizResultEntity;

import java.util.List;

@Dao
public interface QuizResultDao {
    @Insert
    Long insert(QuizResultEntity quizResultEntity);

    @Query("SELECT * FROM tbl_quiz_result WHERE quizDetailsId = :quizId ORDER BY id ASC")
    LiveData<List<QuizResultEntity>> fetchAll(Long quizId);
}
