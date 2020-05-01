package com.discussiongroup.voting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.ncorti.slidetoact.SlideToActView;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import www.sanju.motiontoast.MotionToast;

public class LoginActivity extends AppCompatActivity {
    Animation topdown, left;
    TextInputEditText regn, pass;
    SlideToActView login;
    ImageView logo;
    TextView slogan;
    final OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setTitle("LOGIN");

        topdown = AnimationUtils.loadAnimation(this, R.anim.topdown);
        left = AnimationUtils.loadAnimation(this, R.anim.txtanim);
        regn = findViewById(R.id.regno);
        pass = findViewById(R.id.password);
        login = findViewById(R.id.login);
        logo = findViewById(R.id.logoimg);
        slogan = findViewById(R.id.logtxt);
        logo.setAnimation(left);
        slogan.setAnimation(left);

        login.setOnSlideCompleteListener(new SlideToActView.OnSlideCompleteListener() {
            @Override
            public void onSlideComplete(SlideToActView slideToActView) {
                final String reg = regn.getText().toString();
                final String pswd = pass.getText().toString();
                if (reg.length() > 0 && pswd.length() > 0) {
                    // login bit goes 'ere

                    try {
                        loginPost(reg, pswd);
                    } catch (Exception e) {
                        Log.e("hey", e.toString());
                        e.printStackTrace();
                    }
                } else {
                    MotionToast.Companion.createToast(LoginActivity.this, "FAILED",
                            MotionToast.Companion.getTOAST_ERROR(),
                            MotionToast.Companion.getGRAVITY_BOTTOM(),
                            MotionToast.Companion.getSHORT_DURATION(),
                            ResourcesCompat.getFont(LoginActivity.this, R.font.alegreya_sc_italic));
//                    MotionToast.Companion.createToast(LoginActivity.this, "FAILED",
//                            MotionToast.Companion.getTOAST_ERROR(),
//                            MotionToast.Companion.getGRAVITY_BOTTOM(),
//                            MotionToast.Companion.getSHORT_DURATION(),
//                            ResourcesCompat.getFont(LoginActivity.this, R.font.alegreya_sc_italic));
                    Toast.makeText(LoginActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                    Handler wait = new Handler();
                    Runnable runner = new Runnable() {
                        @Override
                        public void run() {
                            login.resetSlider();
                        }
                    };
                    wait.postDelayed(runner, 1000);
                }
            }
        });
    }

    public void loginPost(final String reg_no, String password) throws Exception {
        RequestBody formBody = new FormBody.Builder()
                .add("reg_no", reg_no)
                .add("password", password)
                .build();
        Request request = new Request.Builder()
                .url("https://votingtest.herokuapp.com/api/voter/login")
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
                    JSONObject responseObj = new JSONObject(Objects.requireNonNull(response.body()).string());
                    Log.e("hey", responseObj.toString());

                    if (responseObj.get("status").equals("success")) {
                        JSONObject data = responseObj.getJSONObject("data");

                        SharedPreferences pref = getApplicationContext().getSharedPreferences("profile", MODE_PRIVATE);
                        @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor = pref.edit();
                        editor.putString("reg_no", data.getString("reg_no"));
                        editor.putInt("id", data.getInt("id"));
                        editor.putString("email", data.getString("email"));
                        editor.putInt("school_id", data.getInt("school_id"));
                        editor.apply();

                        Intent next = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(next);
                        finish();
                    } else {
//                        login.resetSlider();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

//                MotionToast.Companion.createToast(LoginActivity.this, reg_no,
//                        MotionToast.Companion.getTOAST_SUCCESS(),
//                        MotionToast.Companion.getGRAVITY_BOTTOM(),
//                        MotionToast.Companion.getSHORT_DURATION(),
//                        ResourcesCompat.getFont(LoginActivity.this, R.font.alegreya_sc_italic));
//                    MotionToast.Companion.createToast(LoginActivity.this, reg,
//                            MotionToast.Companion.getTOAST_SUCCESS(),
//                            MotionToast.Companion.getGRAVITY_BOTTOM(),
//                            MotionToast.Companion.getSHORT_DURATION(),
//                            ResourcesCompat.getFont(LoginActivity.this, R.font.alegreya_sc_italic));
//                Toast.makeText(LoginActivity.this, "Success", Toast.LENGTH_SHORT).show();

//                MotionToast.Companion.createToast(LoginActivity.this, "FAILED",
//                        MotionToast.Companion.getTOAST_ERROR(),
//                        MotionToast.Companion.getGRAVITY_BOTTOM(),
//                        MotionToast.Companion.getSHORT_DURATION(),
//                        ResourcesCompat.getFont(LoginActivity.this, R.font.alegreya_sc_italic));
            }
        });
    }
}
