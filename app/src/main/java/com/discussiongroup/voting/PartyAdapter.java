package com.discussiongroup.voting;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PartyAdapter extends RecyclerView.Adapter<PartyAdapter.MyViewHolder> {
    private Context ctx;
    private List<Party> data;

    PartyAdapter(Context ctx, List<Party> data) {
        this.ctx = ctx;
        this.data = data;
    }

    @Override
    public void onBindViewHolder(@NonNull PartyAdapter.MyViewHolder holder, final int position) {
        holder.btn.setText(data.get(position).getName());
        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(ctx, PartyActivity.class);
                next.putExtra("electionId", data.get(position).getElectionId());
                next.putExtra("logo", data.get(position).getLogo());
                next.putExtra("slogan", data.get(position).getSlogan());
                next.putExtra("partyId", data.get(position).getId());
                next.putExtra("name", data.get(position).getName());
                ctx.startActivity(next);
            }
        });
    }

    @NonNull
    @Override
    public PartyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.parties_list, parent, false);
        return new MyViewHolder(view);
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        Button btn;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            btn = itemView.findViewById(R.id.partyBtn);
        }
    }
}