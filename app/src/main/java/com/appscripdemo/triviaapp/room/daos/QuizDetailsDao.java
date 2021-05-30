package com.appscripdemo.triviaapp.room.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.appscripdemo.triviaapp.room.entities.QuizDetailsEntity;

import java.util.List;

@Dao
public interface QuizDetailsDao {
    @Insert
    Long insert(QuizDetailsEntity quizDetailsEntity);

    @Query("SELECT * FROM tbl_quiz_details ORDER BY id DESC")
    LiveData<List<QuizDetailsEntity>> fetchAll();

    @Query("SELECT * FROM tbl_quiz_details WHERE id = :quizId")
    LiveData<List<QuizDetailsEntity>> fetchSingle(Long quizId);
}
