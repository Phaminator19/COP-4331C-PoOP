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
    private ArrayList<String> GroupList;
    Context cl;

    public Adapter(Context c, ArrayList<String> GroupList) {
        this.cl = c;
        this.GroupList = GroupList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(cl).inflate(R.layout.row, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.gName.setText(GroupList.get(position));
    }

    @Override
    public int getItemCount() {
        return GroupList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public TextView gName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            gName = (TextView) itemView.findViewById(R.id.gName);
        }
    }
}
