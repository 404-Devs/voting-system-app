package com.discussiongroup.voting;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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
        holder.btn.setText(data.get(position).getElectionName());
        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ctx, data.get(position).getElectionName(), Toast.LENGTH_LONG).show();
                Intent next = new Intent(ctx, PartyActivity.class);
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
            return new ElectionsAdapter.MyViewHolder(view);
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        Button btn;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            btn = itemView.findViewById(R.id.electionBtn);
        }
    }

}
