package com.discussiongroup.voting;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Base64;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
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
        String logo = intent.getStringExtra("logo");
        String slogan = intent.getStringExtra("slogan");
        String partyName = intent.getStringExtra("name");
        int partyId = intent.getIntExtra("partyId", 0);

        ImageView logoView = findViewById(R.id.partyLogo);
        logoView.setImageBitmap(decodeBase64(logo));
        TextView partyNameView = findViewById(R.id.partyTitle);
        TextView partyTitleView = findViewById(R.id.partyName);
        partyTitleView.setText(partyName);
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
                        @SuppressLint("SetTextI18n")
                        @Override
                        public void run() {
                            TextView chairmanNameView = findViewById(R.id.chairmanName);
                            TextView treasurerNameView = findViewById(R.id.treasurerName);
                            TextView secGenNameView = findViewById(R.id.secGenName);

                            try {
                                chairmanNameView.setText("Chairman: " + dataObj.getString("chairman"));
                                treasurerNameView.setText("Treasurer: " + dataObj.getString("treasurer"));
                                secGenNameView.setText("Secretary General: " + dataObj.getString("sec_gen"));
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
