package com.appscrip.triviaapp.history;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.appscrip.triviaapp.R;
import com.appscrip.triviaapp.databinding.HistoryListItemBinding;
import com.appscrip.triviaapp.history.model_class.History;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.MyViewHolder> {

    ArrayList<History> historyArrayList;

    public HistoryAdapter(ArrayList<History> historyArrayList) {
        this.historyArrayList = historyArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        /*returning the view on which bindViewHolder will add elements*/
        return new MyViewHolder(HistoryListItemBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        /*add data to each of recyclerview*/
        History history = historyArrayList.get(position);
        holder.historyListItemBinding.tvGameDate.setText(String.format(holder.itemView.getResources()
                .getString(R.string.game_date_time),history.getId(),history.getDate(),history.getTime()));
        holder.historyListItemBinding.tvName.setText(String.format(holder.itemView.getResources().getString(R.string.name_value),history.getName()));
        holder.historyListItemBinding.tvCricketerAnswer.setText(String.format(holder.itemView.getResources().getString(R.string.answer),history.getCricketer()));
        holder.historyListItemBinding.tvFlagAnswer.setText(String.format(holder.itemView.getResources().getString(R.string.answers),history.getFlagColor()));
    }

    @Override
    public int getItemCount() {
        /*size of recyclerview*/
        return historyArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        /*setting up viewBinding*/
        HistoryListItemBinding historyListItemBinding;

        public MyViewHolder(HistoryListItemBinding binding){
            super(binding.getRoot());
            historyListItemBinding = binding;
        }
    }

}
