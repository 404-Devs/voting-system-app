package com.discussiongroup.voting;

import androidx.appcompat.app.AppCompatActivity;

<<<<<<< HEAD
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
=======
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button presi;
>>>>>>> e1a8fc8... Dashboard and Staff

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
<<<<<<< HEAD
=======
        presi = findViewById(R.id.president);
        presi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(MainActivity.this, PartyActivity.class);
                next.putExtra("Position", "UNSA 2020");
                startActivity(next);
            }
        });
>>>>>>> e1a8fc8... Dashboard and Staff
    }
}
