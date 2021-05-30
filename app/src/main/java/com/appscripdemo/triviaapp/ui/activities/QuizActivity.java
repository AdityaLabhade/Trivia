package com.appscripdemo.triviaapp.ui.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.appscripdemo.triviaapp.R;
import com.appscripdemo.triviaapp.databinding.ActivityQuizBinding;
import com.appscripdemo.triviaapp.ui.fragments.QuizFragment1;

import dagger.hilt.android.AndroidEntryPoint;

import static com.appscripdemo.triviaapp.ui.activities.MainActivity.QUIZ_ID_KEY;

@AndroidEntryPoint
public class QuizActivity extends AppCompatActivity {

    ActivityQuizBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_quiz);
        binding.executePendingBindings();

        long quizId =   getIntent().getExtras() != null ? getIntent().getExtras().getLong(QUIZ_ID_KEY): 0;

        Bundle bundle = new Bundle();
        bundle.putLong("quizId", quizId);

        QuizFragment1 quizFragment1 = new QuizFragment1();
        quizFragment1.setArguments(bundle);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(binding.fragmentContainer.getId(),quizFragment1)
                .commit();

    }

}