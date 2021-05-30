package com.appscripdemo.triviaapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.appscripdemo.triviaapp.R;
import com.appscripdemo.triviaapp.databinding.ListItemQuizResultBinding;
import com.appscripdemo.triviaapp.models.QuizResult;

import java.util.List;

public class QuizResultAdapter extends RecyclerView.Adapter<QuizResultAdapter.MyViewHolder> {
    private Context context;
    private List<QuizResult> list;

    public QuizResultAdapter(Context context, List<QuizResult> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListItemQuizResultBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.list_item_quiz_result,parent,false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        QuizResult quizResult = list.get(position);
        holder.binding.setQuizResult(quizResult);
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ListItemQuizResultBinding binding;
        public MyViewHolder(@NonNull ListItemQuizResultBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
