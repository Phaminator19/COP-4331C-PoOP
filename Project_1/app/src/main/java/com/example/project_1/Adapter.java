package com.example.project_1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    Context cl;
    private GroupList gr;

    public Adapter(Context c, GroupList GroupList) {
        this.cl = c;
        gr = GroupList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.gName.setText(gr.get(position).getName());
        holder.gInterest.setText(gr.get(position).getInterest());
        holder.gBios.setText(gr.get(position).getBios());

    }

    @Override
    public int getItemCount() {
        return gr.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public TextView gName;
        public TextView gInterest;
        public TextView gBios;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            gName = itemView.findViewById(R.id.gName);
            gBios = itemView.findViewById(R.id.gBios2);
            gInterest = itemView.findViewById(R.id.gInterest2);
        }
    }
}
