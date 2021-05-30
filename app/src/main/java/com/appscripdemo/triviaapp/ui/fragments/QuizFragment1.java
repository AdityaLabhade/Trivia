package com.appscripdemo.triviaapp.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.appscripdemo.triviaapp.R;
import com.appscripdemo.triviaapp.databinding.FragmentQuiz1Binding;
import com.appscripdemo.triviaapp.models.QuizResult;
import com.appscripdemo.triviaapp.viewmodels.QuizResultViewModel;

import static com.appscripdemo.triviaapp.ui.activities.MainActivity.QUIZ_ID_KEY;

public class QuizFragment1 extends Fragment {

    private FragmentQuiz1Binding binding;
    private QuizResult quizResult;
    long quizId;
    QuizResultViewModel quizResultViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_quiz1, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        quizResultViewModel = new ViewModelProvider(this).get(QuizResultViewModel.class);

        quizResult = new QuizResult();
        quizResult.setQuestion(binding.questionTextView.getText().toString());
        binding.setTriviaInfo(quizResult);
        binding.setHandler(new ClickHandler());

        quizId =   getArguments() != null ? getArguments().getLong(QUIZ_ID_KEY): 0;
        quizResult.setQuizId(quizId);
/*

        binding.answersRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.optionOneRadioButton:
                        quizResult.setAnswer(binding.optionOneRadioButton.getText().toString());
                        break;

                    case R.id.optionTwoRadioButton:
                        quizResult.setAnswer(binding.optionTwoRadioButton.getText().toString());
                        break;

                    case R.id.optionThreeRadioButton:
                        quizResult.setAnswer(binding.optionThreeRadioButton.getText().toString());
                        break;

                    case R.id.optionFourRadioButton:
                        quizResult.setAnswer(binding.optionFourRadioButton.getText().toString());
                        break;
                }
            }
        });*/
    }

    public class ClickHandler {
        public void onNextButtonClick(View view) {
            int selectedId = binding.answersRadioGroup.getCheckedRadioButtonId();
            // find the radiobutton by returned id
            RadioButton selectedRadioButton =  binding.getRoot().findViewById(selectedId);
            if(selectedRadioButton != null) {
                quizResult.setAnswer(selectedRadioButton.getText().toString());
            }
            if (quizResult != null && quizResult.getAnswer().equals("")) {
                Toast.makeText(getActivity(), getResources().getString(R.string.error_select_answer), Toast.LENGTH_SHORT).show();
                return;
            }
            quizResultViewModel.insertQuizResult(quizResult);

            Bundle bundle = new Bundle();
            bundle.putLong(QUIZ_ID_KEY, quizId);

            QuizFragment2 quizFragment2 = new QuizFragment2();
            quizFragment2.setArguments(bundle);
            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentContainer,quizFragment2)
                    .commit();
        }
    }
}
