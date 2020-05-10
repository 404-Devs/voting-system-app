package com.discussiongroup.voting;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.discussiongroup.voting.PartyActivity.decodeBase64;

public class PartyAdapter extends RecyclerView.Adapter<PartyAdapter.MyViewHolder> {
    private Context ctx;
    private List<Party> data;
    private OkHttpClient client = new OkHttpClient();
    private int electionId;

    PartyAdapter(Context ctx, List<Party> data, int electionId) {
        this.ctx = ctx;
        this.data = data;
        this.electionId = electionId;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull PartyAdapter.MyViewHolder holder, final int position) {
        holder.title.setText(data.get(position).getName());
        holder.partyLogo.setImageBitmap(decodeBase64(data.get(position).getLogo()));
        holder.chairman.setText("Chairman: " + data.get(position).getChairman());
        holder.treasurer.setText("Treasurer: " + data.get(position).getTreasurer());
        holder.sec_gen.setText("Secretary General: " + data.get(position).getSec_gen());
        holder.slogan.setText(data.get(position).getSlogan());
        holder.voteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialAlertDialogBuilder(ctx)
                        .setTitle("Vote for " + data.get(position).getName() + "?")
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                try {
                                    castVote(data.get(position).getId());
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) { }
                        })
                        .show();
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
        TextView title, slogan, chairman, treasurer, sec_gen;
        MaterialButton voteBtn;
        ImageView partyLogo;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.partyTitle);
            partyLogo = itemView.findViewById(R.id.partyLogo);
            voteBtn = itemView.findViewById(R.id.voteBtn);
            slogan = itemView.findViewById(R.id.partySlogan);
            chairman = itemView.findViewById(R.id.chairmanName);
            treasurer = itemView.findViewById(R.id.treasurerName);
            sec_gen = itemView.findViewById(R.id.secGenName);
        }
    }

    public void castVote(int teamId) throws Exception {
        SharedPreferences sharedPref = ctx.getSharedPreferences("profile", Context. MODE_PRIVATE);
        RequestBody formBody = new FormBody.Builder()
                .add("voter_reg", sharedPref.getString("reg_no", "-1-"))
                .add("election_id", String.valueOf(electionId))
                .add("team_id", String.valueOf(teamId))
                .add("dibs", sharedPref.getString("dibs", "-1-"))
                .build();
        Request request = new Request.Builder()
                .url("https://votingtest.herokuapp.com/api/vote")
                .post(formBody)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.e("hey", e.toString());
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                try {
                    JSONObject responseObj = new JSONObject(Objects.requireNonNull(Objects.requireNonNull(response.body()).string()));
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {

                        }
                    });
                } catch (JSONException e) {
                    Log.e("hey", e.toString());
                    e.printStackTrace();
                }
            }
        });
    }
}