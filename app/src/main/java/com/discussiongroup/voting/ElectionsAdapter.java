package com.discussiongroup.voting;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;

import java.util.List;

public class ElectionsAdapter extends RecyclerView.Adapter<ElectionsAdapter.MyViewHolder> {
    private List<Election> data;
    private Context ctx;

    ElectionsAdapter(Context ctx, List<Election> data) {
        this.data = data;
        this.ctx = ctx;
    }

    @Override
    public void onBindViewHolder(@NonNull ElectionsAdapter.MyViewHolder holder, final int position) {
        holder.title.setText(data.get(position).getElectionName());
        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(ctx, ViewElectionActivity.class);
                next.putExtra("electionId", data.get(position).getElectionId());
                next.putExtra("electionName", data.get(position).getElectionName());
                next.putExtra("startTimestamp", data.get(position).getStartTimestamp());
                next.putExtra("endTimestamp", data.get(position).getEndTimestamp());
                ctx.startActivity(next);
            }
        });
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(ctx).inflate(R.layout.election_list, parent, false);
            return new MyViewHolder(view);
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        MaterialButton btn;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.electionTitle);
            btn = itemView.findViewById(R.id.electionBtn);
        }
    }
}
