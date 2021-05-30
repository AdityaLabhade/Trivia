package com.appscripdemo.triviaapp.ui.activities;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.appscripdemo.triviaapp.R;
import com.appscripdemo.triviaapp.adapters.QuizHistoryAdapter;
import com.appscripdemo.triviaapp.databinding.ActivityAllQuizHistoryBinding;
import com.appscripdemo.triviaapp.models.QuizDetails;
import com.appscripdemo.triviaapp.models.QuizResult;
import com.appscripdemo.triviaapp.room.entities.QuizDetailsEntity;
import com.appscripdemo.triviaapp.room.entities.QuizResultEntity;
import com.appscripdemo.triviaapp.viewmodels.QuizDetailsViewModel;
import com.appscripdemo.triviaapp.viewmodels.QuizResultViewModel;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class AllQuizHistoryActivity extends AppCompatActivity {

    private ActivityAllQuizHistoryBinding binding;
    private List<QuizDetails> quizDetailsList;
    ArrayList<QuizResult> quizResultList;
    private RecyclerView recyclerHistory;
    private QuizHistoryAdapter triviaHistoryAdapter;
    QuizDetailsViewModel quizDetailsViewModel;
    QuizResultViewModel quizResultViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        quizDetailsViewModel = new ViewModelProvider(this).get(QuizDetailsViewModel.class);
        quizResultViewModel = new ViewModelProvider(this).get(QuizResultViewModel.class);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_all_quiz_history);

        binding.setHandler(new ClickHandler());
        binding.executePendingBindings();

        quizDetailsViewModel = new ViewModelProvider(this).get(QuizDetailsViewModel.class);
        quizResultViewModel = new ViewModelProvider(this).get(QuizResultViewModel.class);
        getAllQuizSummery();
    }

    private void getAllQuizSummery() {
        quizDetailsViewModel.getQuizDetailsList().observe(AllQuizHistoryActivity.this, quizDetailsEntityList -> {
            if (quizDetailsEntityList != null && quizDetailsEntityList.size() > 0) {
                quizDetailsList = new ArrayList<>();
                binding.noRecordsTextView.setVisibility(View.GONE);
                binding.historyRecyclerView.setVisibility(View.VISIBLE);

                for (QuizDetailsEntity tr : quizDetailsEntityList) {
                    QuizDetails quizDetails = new QuizDetails();
                    quizResultViewModel.getQuizResultList(tr.getId())
                            .observe(AllQuizHistoryActivity.this, quizResultListEntity -> {

                        quizResultList = new ArrayList<>();
                        if (quizResultListEntity != null && quizResultListEntity.size() > 0) {
                            for (QuizResultEntity quizResultEntity : quizResultListEntity) {
                                QuizResult quizResult = new QuizResult();
                                quizResult.setAnswer(quizResultEntity.getAnswer());
                                quizResult.setQuestion(quizResultEntity.getQuestion());
                                quizResultList.add(quizResult);
                            }
                            quizDetails.setListQuizResult(quizResultList);
                            quizDetails.setDateTime(tr.getQuizTime());
                            quizDetails.setUserName(tr.getUserName());
                            quizDetails.setId(tr.getId());
                            quizDetailsList.add(quizDetails);
                            recyclerViewSetup();
                        }
                    });

                }
                recyclerViewSetup();
            } else {
                binding.noRecordsTextView.setVisibility(View.VISIBLE);
                binding.historyRecyclerView.setVisibility(View.GONE);
            }
        });
    }



    private void recyclerViewSetup() {
        recyclerHistory = binding.historyRecyclerView;
        recyclerHistory.setLayoutManager(new LinearLayoutManager(AllQuizHistoryActivity.this, RecyclerView.VERTICAL, false));

        triviaHistoryAdapter = new QuizHistoryAdapter(AllQuizHistoryActivity.this, quizDetailsList);
        recyclerHistory.setAdapter(triviaHistoryAdapter);
    }

    public class ClickHandler {
        public void onBackButtonClick(View view) {
            AllQuizHistoryActivity.this.finish();
        }
    }
}