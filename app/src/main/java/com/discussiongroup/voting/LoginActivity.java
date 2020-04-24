package com.discussiongroup.voting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
<<<<<<< HEAD
=======
import android.widget.Toast;
>>>>>>> e1a8fc8... Dashboard and Staff

import com.google.android.material.textfield.TextInputEditText;
import com.ncorti.slidetoact.SlideToActView;

import www.sanju.motiontoast.MotionToast;

public class LoginActivity extends AppCompatActivity {
    Animation topdown, left;
    TextInputEditText regn, pass;
    SlideToActView login;
    ImageView logo;
    TextView slogan;

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
<<<<<<< HEAD
                    MotionToast.Companion.createToast(LoginActivity.this, reg,
                            MotionToast.Companion.getTOAST_SUCCESS(),
                            MotionToast.Companion.getGRAVITY_BOTTOM(),
                            MotionToast.Companion.getSHORT_DURATION(),
                            ResourcesCompat.getFont(LoginActivity.this, R.font.alegreya_sc_italic));
=======
//                    MotionToast.Companion.createToast(LoginActivity.this, reg,
//                            MotionToast.Companion.getTOAST_SUCCESS(),
//                            MotionToast.Companion.getGRAVITY_BOTTOM(),
//                            MotionToast.Companion.getSHORT_DURATION(),
//                            ResourcesCompat.getFont(LoginActivity.this, R.font.alegreya_sc_italic));
                    Toast.makeText(LoginActivity.this, "Success", Toast.LENGTH_SHORT).show();
>>>>>>> e1a8fc8... Dashboard and Staff

                    Intent next = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(next);
                    finish();
                    // login.resetSlider();
                } else {
<<<<<<< HEAD
                    MotionToast.Companion.createToast(LoginActivity.this, "FAILED",
                            MotionToast.Companion.getTOAST_ERROR(),
                            MotionToast.Companion.getGRAVITY_BOTTOM(),
                            MotionToast.Companion.getSHORT_DURATION(),
                            ResourcesCompat.getFont(LoginActivity.this, R.font.alegreya_sc_italic));
=======
//                    MotionToast.Companion.createToast(LoginActivity.this, "FAILED",
//                            MotionToast.Companion.getTOAST_ERROR(),
//                            MotionToast.Companion.getGRAVITY_BOTTOM(),
//                            MotionToast.Companion.getSHORT_DURATION(),
//                            ResourcesCompat.getFont(LoginActivity.this, R.font.alegreya_sc_italic));
                    Toast.makeText(LoginActivity.this, "Failed", Toast.LENGTH_SHORT).show();
>>>>>>> e1a8fc8... Dashboard and Staff
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
}
