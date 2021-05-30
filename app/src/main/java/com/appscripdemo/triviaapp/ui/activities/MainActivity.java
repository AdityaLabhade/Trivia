package com.appscripdemo.triviaapp.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.appscripdemo.triviaapp.R;
import com.appscripdemo.triviaapp.databinding.ActivityMainBinding;
import com.appscripdemo.triviaapp.models.QuizDetails;
import com.appscripdemo.triviaapp.utils.DateTimeUtils;
import com.appscripdemo.triviaapp.viewmodels.QuizDetailsViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    public static final String QUIZ_ID_KEY = "quizId";
    ActivityMainBinding binding;
    QuizDetails quizDetails;
    QuizDetailsViewModel quizDetailsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        quizDetailsViewModel = new ViewModelProvider(this).get(QuizDetailsViewModel.class);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        quizDetails = new QuizDetails();
        binding.setQuizDetails(quizDetails);
        binding.setHandler(new ClickHandler());
        binding.executePendingBindings();
    }

    public class ClickHandler {
        public void onNextButtonClick(View view) {
            if (quizDetails.getUserName() == null || quizDetails.getUserName().trim().length() == 0) {
                binding.fullNameEditText.requestFocus();
                binding.fullNameEditText.setError(getString(R.string.error_enter_your_name));
                return;
            }
            quizDetails.setDateTime(DateTimeUtils.getCurrentDateTime());
            //long insertId = quizDetailsViewModel.insertQuizDetails(quizDetails);
            quizDetailsViewModel.insertQuizDetails(quizDetails).observe(MainActivity.this, new Observer<Long>() {
                @Override
                public void onChanged(Long aLong) {
                    if(aLong!=0){
                        Toast.makeText(getApplicationContext(), aLong + "", Toast.LENGTH_LONG).show();

                        Intent quizActivity = new Intent(MainActivity.this, QuizActivity.class);
                        quizActivity.putExtra(QUIZ_ID_KEY, aLong);
                        startActivity(quizActivity);
                    }
                }
            });

        }

        public void onHistoryButtonClick(View view) {
            startActivity(new Intent(MainActivity.this, AllQuizHistoryActivity.class));
        }
    }

}