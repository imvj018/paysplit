package com.example.paymentapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class adapter extends RecyclerView.Adapter<adapter.ViewHolder> {
    private List<listitem> listItem;
    private Context context;

    public adapter(List<listitem> listItem, Context applicationContext) {
        this.listItem = listItem;
        this.context = applicationContext;
    }

    @NonNull
    @Override
    public adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.difftext, parent, false);
        return new ViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull adapter.ViewHolder holder, int position) {
        String id, username, amount, date, time, ex_time, note;
        id = listItem.get(position).getId();
        username = listItem.get(position).getUsername();
        amount = listItem.get(position).getAmount();
        date = listItem.get(position).getDate();
        time = listItem.get(position).getTime();
        ex_time = listItem.get(position).getEx_time();
        note = listItem.get(position).getNote();

        holder.text1.setText(id + " " + username + " " + amount + " " + note);
        holder.text2.setText(date + " " + time);
        holder.text3.setText(ex_time);

    }

    @Override
    public int getItemCount() {
        return listItem.size();

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView text1, text2, text3;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            text1 = itemView.findViewById(R.id.line1);
            text2 = itemView.findViewById(R.id.line2);
            text3 = itemView.findViewById(R.id.line3);
        }
    }
}
