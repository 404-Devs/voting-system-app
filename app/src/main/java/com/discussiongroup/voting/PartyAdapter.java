package com.discussiongroup.voting;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class PartyAdapter extends RecyclerView.Adapter<PartyAdapter.MyViewHolder> {
    private Context ctx;
    private List<Party> data;
    private OkHttpClient client = new OkHttpClient();
    private int electionId, startTimestamp, endTimestamp;
    private boolean voted;

    PartyAdapter(Context ctx, List<Party> data, int electionId, boolean voted, int startTimestamp, int endTimestamp) {
        this.ctx = ctx;
        this.data = data;
        this.electionId = electionId;
        this.voted = voted;
        this.startTimestamp = startTimestamp;
        this.endTimestamp = endTimestamp;
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
        holder.voteCount.setText("#" + data.get(position).getVotes() + " VOTES");

        if (!voted) {
            holder.voteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    long unixTime = System.currentTimeMillis() / 1000L;
                    if (unixTime < startTimestamp) {
                        Date date = new Date();
                        date.setTime((long) startTimestamp * 1000);
                        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy @ hh:mm a");
                        Toast.makeText(ctx, "Voting starts on " + dateFormat.format(date) + " ☺️", Toast.LENGTH_LONG).show();
                    } else if (unixTime > endTimestamp) {
                        Toast.makeText(ctx, "Sorry, the voting window has closed \uD83D\uDE22", Toast.LENGTH_LONG).show();
                    }
                    else {
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
                }
            });
        }
        else holder.voteBtn.setText("VOTED!");
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
        MaterialButton voteBtn, voteCount;
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
            voteCount = itemView.findViewById(R.id.voteCount);
        }
    }

    private void castVote(int teamId) throws Exception {
        SharedPreferences sharedPref = ctx.getSharedPreferences("profile", Context. MODE_PRIVATE);
        Log.e("hey", sharedPref.getString("dibs", "-1-"));
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
                    Log.e("hey", responseObj.toString());
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

    private static Bitmap decodeBase64(String base64String) {
        base64String = base64String.substring(base64String.indexOf(",") + 1);
        final byte[] decodedBytes = Base64.decode(base64String, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
    }
}