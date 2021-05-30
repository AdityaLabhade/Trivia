package com.appscripdemo.triviaapp.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.appscripdemo.triviaapp.R;
import com.appscripdemo.triviaapp.adapters.QuizResultAdapter;
import com.appscripdemo.triviaapp.databinding.FragmentIndividualSummaryBinding;
import com.appscripdemo.triviaapp.models.QuizDetails;
import com.appscripdemo.triviaapp.models.QuizResult;
import com.appscripdemo.triviaapp.room.entities.QuizResultEntity;
import com.appscripdemo.triviaapp.ui.activities.MainActivity;
import com.appscripdemo.triviaapp.viewmodels.QuizDetailsViewModel;
import com.appscripdemo.triviaapp.viewmodels.QuizResultViewModel;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

import static com.appscripdemo.triviaapp.ui.activities.MainActivity.QUIZ_ID_KEY;

@AndroidEntryPoint
public class SummaryFragment extends Fragment {

    private FragmentIndividualSummaryBinding binding;
    private QuizDetails quizDetails;
    private ArrayList<QuizResult> quizResultArrayList;
    private RecyclerView recyclerTriviaInfo;
    private QuizResultAdapter quizResultAdapter;
    QuizDetailsViewModel quizDetailsViewModel;
    QuizResultViewModel quizResultViewModel;
    long quizId = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_individual_summary, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        quizDetailsViewModel = new ViewModelProvider(this).get(QuizDetailsViewModel.class);
        quizResultViewModel = new ViewModelProvider(this).get(QuizResultViewModel.class);

        quizId = getArguments() != null ? getArguments().getLong(QUIZ_ID_KEY) : 0;
        quizDetails = new QuizDetails();

        quizDetailsViewModel.getSingleQuizDetails(quizId).observe(getViewLifecycleOwner(), quizDetailsEntityList -> {
            if (quizDetailsEntityList != null && quizDetailsEntityList.size() > 0)
                quizDetails.setId(quizDetailsEntityList.get(0).getId());
            quizDetails.setUserName(quizDetailsEntityList.get(0).getUserName());
            quizDetails.setDateTime(quizDetailsEntityList.get(0).getQuizTime());

            quizResultViewModel.getQuizResultList(quizId)
                    .observe(getViewLifecycleOwner(), quizResultListEntity -> {

                        quizResultArrayList = new ArrayList<>();
                        if (quizResultListEntity != null && quizResultListEntity.size() > 0) {
                            for (QuizResultEntity quizResultEntity : quizResultListEntity) {
                                QuizResult quizResult = new QuizResult();
                                quizResult.setAnswer(quizResultEntity.getAnswer());
                                quizResult.setQuestion(quizResultEntity.getQuestion());
                                quizResultArrayList.add(quizResult);
                            }
                            quizDetails.setListQuizResult(quizResultArrayList);
                        }

                        binding.setQuizDetails(quizDetails);
                        binding.setHandler(new ClickHandler());

                        recyclerViewSetup();
                    });
        });
    }

    private void recyclerViewSetup() {
        recyclerTriviaInfo = binding.quizDetailsRecyclerView;
        recyclerTriviaInfo.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
        quizResultAdapter = new QuizResultAdapter(getActivity(), quizDetails.getListQuizResult());
        recyclerTriviaInfo.setAdapter(quizResultAdapter);
    }

    public class ClickHandler {
        public void onFinishButtonClick(View view) {
            startActivity(new Intent(getActivity(), MainActivity.class));
            getActivity().finish();
        }
    }
}