package com.discussiongroup.voting;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AspirantsAdapter extends RecyclerView.Adapter<AspirantsAdapter.MyViewHolder> {
    Context ctx;
    List<Aspirant> candidates;
    CandidateClick clk;

    public AspirantsAdapter(Context ctx, List<Aspirant> candidates, CandidateClick clk) {
        this.ctx = ctx;
        this.candidates = candidates;
        this.clk = clk;

    }

    @NonNull
    @Override
    public AspirantsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.aspirants, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AspirantsAdapter.MyViewHolder holder, final int position) {
        holder.canName.setText(candidates.get(position).getAspirantName());
        // base 64 image goes 'ere
//        holder.prof.setImageResource(candidates.get(position).getAspirantPhoto());
        holder.morebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ctx, candidates.get(position).getAspirantName(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return candidates.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView prof;
        TextView canName, canParty;
        ImageButton morebtn;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            canName = itemView.findViewById(R.id.asp_name);
            canParty = itemView.findViewById(R.id.asp_prt);
            prof = itemView.findViewById(R.id.asp_prof_img);
            morebtn = itemView.findViewById(R.id.asp_more);

//
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    clk.onAspirantClick(candidates.get(getAdapterPosition()), prof);
//                }
//            });
        }
    }
}


