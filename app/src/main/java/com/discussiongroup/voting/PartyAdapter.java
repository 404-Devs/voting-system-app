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

public class PartyAdapter extends RecyclerView.Adapter<PartyAdapter.MyViewHolder> {
    Context ctx;
    List<Party> prt;
    PartyClick clk;

    public PartyAdapter(Context ctx, List<Party> prt, PartyClick clk) {
        this.ctx = ctx;
        this.prt = prt;
        this.clk = clk;

    }

    @NonNull
    @Override
    public PartyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.party, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PartyAdapter.MyViewHolder holder, final int position) {
        holder.partyname.setText(prt.get(position).getPartyName());
        holder.partyslogan.setText(prt.get(position).getPartySlogan());
        holder.prof.setImageResource(prt.get(position).getPartyLogo());
    }

    @Override
    public int getItemCount() {
        return prt.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView prof;
        TextView partyname, partyslogan;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            prof = itemView.findViewById(R.id.asp_prof_img);
            partyname = itemView.findViewById(R.id.prt_name);
            partyslogan = itemView.findViewById(R.id.prt_slogan);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clk.onPartClick(prt.get(getAdapterPosition()), prof);
                }
            });
        }
    }
}
