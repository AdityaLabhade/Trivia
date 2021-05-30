package com.appscripdemo.triviaapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.appscripdemo.triviaapp.R;
import com.appscripdemo.triviaapp.databinding.ListItemQuizDetailsBinding;
import com.appscripdemo.triviaapp.models.QuizDetails;

import java.util.List;

public class QuizHistoryAdapter extends RecyclerView.Adapter<QuizHistoryAdapter.MyViewHolder> {
    private Context context;
    private List<QuizDetails> list;

    public QuizHistoryAdapter(Context context, List<QuizDetails> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListItemQuizDetailsBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.list_item_quiz_details,parent,false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        QuizDetails quizDetails = list.get(position);
        holder.binding.setQuizDetails(quizDetails);
        holder.binding.quizDetailsRecyclerView .setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL, false));
        QuizResultAdapter quizResultAdapter = new QuizResultAdapter(context, quizDetails.getListQuizResult());
        holder.binding.quizDetailsRecyclerView.setAdapter(quizResultAdapter);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ListItemQuizDetailsBinding binding;
        public MyViewHolder(@NonNull ListItemQuizDetailsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
