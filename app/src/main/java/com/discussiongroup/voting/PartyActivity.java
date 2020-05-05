package com.discussiongroup.voting;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class PartyActivity extends AppCompatActivity {
    final OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_party);
        Intent intent = getIntent();
        int electionId = intent.getIntExtra("electionId", 0);
        String logo = intent.getStringExtra("logo");
        String slogan = intent.getStringExtra("slogan");
        String partyName = intent.getStringExtra("name");
        int partyId = intent.getIntExtra("partyId", 0);

        ImageView logoView = findViewById(R.id.partyLogo);
        logoView.setImageBitmap(decodeBase64(logo));
        TextView partyNameView = findViewById(R.id.partyName);
        partyNameView.setText(partyName);
        TextView sloganView = findViewById(R.id.partySlogan);
        sloganView.setText(slogan);

        try {
            fetchTeam(partyId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void fetchTeam(final int teamId) throws Exception {
        Request request = new Request.Builder()
                .url("https://votingtest.herokuapp.com/api/team/" + teamId)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.e("hey", e.toString());
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                try {
                    final JSONObject responseObj = new JSONObject(Objects.requireNonNull(Objects.requireNonNull(response.body()).string()));
                    final JSONObject dataObj = responseObj.getJSONObject("data");

                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            TextView chairmanNameView = findViewById(R.id.chairmanName);
                            ImageView chairmanPhoto = findViewById(R.id.chairmanPhoto);
                            ConstraintLayout chairmanGroup = findViewById(R.id.chairmanGroup);

                            TextView treasurerNameView = findViewById(R.id.treasurerName);
                            ImageView treasurerPhoto = findViewById(R.id.treasurerPhoto);
                            ConstraintLayout treasurerGroup = findViewById(R.id.treasurerGroup);

                            TextView secGenNameView = findViewById(R.id.secGenName);
                            ImageView secGenPhoto = findViewById(R.id.secGenPhoto);
                            ConstraintLayout secGenGroup = findViewById(R.id.secGenGroup);

                            try {
                                chairmanNameView.setText(dataObj.getString("chairman"));
                                chairmanPhoto.setImageBitmap(decodeBase64(dataObj.getString("chairman_photo")));
                                chairmanGroup.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent intent = new Intent(PartyActivity.this, CandidateActivity.class);
                                        try {
                                            intent.putExtra("party", dataObj.getString("name"));
                                            intent.putExtra("name", dataObj.getString("chairman"));
                                            intent.putExtra("photo", dataObj.getInt("chairman_photo"));
                                            intent.putExtra("position", "Chairman");
                                            intent.putExtra("id", dataObj.getInt("chairman_id"));
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                        startActivity(intent);
                                    }
                                });

                                treasurerNameView.setText(dataObj.getString("treasurer"));
                                treasurerPhoto.setImageBitmap(decodeBase64(dataObj.getString("treasurer_photo")));
                                treasurerGroup.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent intent = new Intent(PartyActivity.this, CandidateActivity.class);
                                        try {
                                            intent.putExtra("party", dataObj.getString("name"));
                                            intent.putExtra("name", dataObj.getString("treasurer"));
                                            intent.putExtra("photo", dataObj.getInt("treasurer_photo"));
                                            intent.putExtra("position", "Treasurer");
                                            intent.putExtra("id", dataObj.getInt("treasurer_id"));
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                        startActivity(intent);
                                    }
                                });

                                secGenNameView.setText(dataObj.getString("sec_gen"));
                                secGenPhoto.setImageBitmap(decodeBase64(dataObj.getString("sec_gen_photo")));
                                secGenGroup.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent intent = new Intent(PartyActivity.this, CandidateActivity.class);
                                        try {
                                            intent.putExtra("party", dataObj.getString("name"));
                                            intent.putExtra("name", dataObj.getString("sec_gen"));
                                            intent.putExtra("photo", dataObj.getInt("sec_gen_photo"));
                                            intent.putExtra("position", "Secretary General");
                                            intent.putExtra("id", dataObj.getInt("sec_gen_id"));
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                        startActivity(intent);
                                    }
                                });
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                } catch (JSONException e) {
                    Log.e("hey", e.toString());
                    e.printStackTrace();
                }
            }
        });
    }

    public static Bitmap decodeBase64(String base64String) {
        base64String = base64String.substring(base64String.indexOf(",") + 1);
        final byte[] decodedBytes = Base64.decode(base64String, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
    }
}
