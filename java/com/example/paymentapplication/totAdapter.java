package com.example.paymentapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class totAdapter extends RecyclerView.Adapter<totAdapter.ViewHolder> {
    private List<totlistItem> totalListItem;
    private Context context;

    public totAdapter(List<totlistItem> totalListItem, Context applicationContext) {
        this.totalListItem = totalListItem;
        this.context = applicationContext;
    }

    @NonNull
    @Override
    public totAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.tottext, parent, false);
        return new totAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull totAdapter.ViewHolder holder, int position) {
        String name, amount;
        name = totalListItem.get(position).getName();
        amount = totalListItem.get(position).getAmount();
        holder.text1.setText(name);
        holder.text2.setText(amount);
    }

    @Override
    public int getItemCount() {
        return totalListItem.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView text1, text2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            text1 = itemView.findViewById(R.id.nametext);
            text2 = itemView.findViewById(R.id.amttext);
        }
    }
}
