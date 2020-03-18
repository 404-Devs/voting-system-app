package com.example.voting1;

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

import com.google.android.material.textfield.TextInputEditText;
import com.ncorti.slidetoact.SlideToActView;

import www.sanju.motiontoast.MotionToast;

public class MainActivity extends AppCompatActivity {
    Animation topdown, left;
    TextInputEditText regn, pass;
    SlideToActView login;
    ImageView logo;
    TextView slogan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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


                    MotionToast.Companion.createToast(MainActivity.this, reg,
                            MotionToast.Companion.getTOAST_SUCCESS()
                            , MotionToast.Companion.getGRAVITY_BOTTOM(),
                            MotionToast.Companion.getSHORT_DURATION(),
                            ResourcesCompat.getFont(MainActivity.this, R.font.alegreya_sc_italic));

                    Intent next = new Intent(MainActivity.this, vote.class);
                    startActivity(next);
                    finish();
//                    login.resetSlider();

                } else {


                    MotionToast.Companion.createToast(MainActivity.this, "FAILED",
                            MotionToast.Companion.getTOAST_ERROR()
                            , MotionToast.Companion.getGRAVITY_BOTTOM(),
                            MotionToast.Companion.getSHORT_DURATION(),
                            ResourcesCompat.getFont(MainActivity.this, R.font.alegreya_sc_italic));
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
