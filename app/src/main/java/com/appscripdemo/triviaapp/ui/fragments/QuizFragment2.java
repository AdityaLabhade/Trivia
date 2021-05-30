package com.appscripdemo.triviaapp.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.appscripdemo.triviaapp.R;
import com.appscripdemo.triviaapp.adapters.QuizResultAdapter;
import com.appscripdemo.triviaapp.databinding.FragmentQuiz2Binding;
import com.appscripdemo.triviaapp.models.QuizResult;
import com.appscripdemo.triviaapp.room.entities.QuizResultEntity;
import com.appscripdemo.triviaapp.viewmodels.QuizResultViewModel;

import okhttp3.internal.Util;

import static com.appscripdemo.triviaapp.ui.activities.MainActivity.QUIZ_ID_KEY;

public class QuizFragment2 extends Fragment {

    private FragmentQuiz2Binding binding;
    private QuizResult quizResult;
    long quizId;
    QuizResultViewModel quizResultViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_quiz2, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        quizResultViewModel = new ViewModelProvider(this).get(QuizResultViewModel.class);
        quizResult = new QuizResult();
        quizResult.setQuestion(binding.questionTextView.getText().toString());
        quizId = getArguments() != null ? getArguments().getLong(QUIZ_ID_KEY) : 0;
        quizResult.setQuizId(quizId);

        binding.setQuizResult(quizResult);
        binding.setHandler(new ClickHandler());
    }


    public class ClickHandler {
        public void onNextButtonClick(View view) {
            CheckBox[] checkBoxArray = {binding.optionOneCheckBox, binding.optionTwoCheckbox, binding.optionThreeCheckbox, binding.optionFourCheckbox};
            String answer = getCommaSeparatedString(checkBoxArray);


            if (answer.equals("")) {
                Toast.makeText(getActivity(), getResources().getString(R.string.error_select_answer), Toast.LENGTH_SHORT).show();
                return;
            } else {
                quizResult.setAnswer(answer);
            }

            quizResultViewModel.insertQuizResult(quizResult);

            Bundle bundle = new Bundle();
            bundle.putLong(QUIZ_ID_KEY, quizId);

            SummaryFragment summaryFragment = new SummaryFragment();
            summaryFragment.setArguments(bundle);
            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentContainer, summaryFragment)
                    .commit();
        }

        private String getCommaSeparatedString(CheckBox[] checkBoxArray) {
            String output = "";
            for (CheckBox checkBox : checkBoxArray) {
                if (checkBox.isChecked()) {
                    output = output.trim().length() > 0 ? output + ", " + checkBox.getText().toString() : checkBox.getText().toString();
                }
            }
            return output;
        }
    }
}