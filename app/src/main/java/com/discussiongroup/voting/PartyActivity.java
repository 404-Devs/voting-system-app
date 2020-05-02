package com.discussiongroup.voting;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class PartyActivity extends AppCompatActivity implements PartyClick {
    static String party_frag;
    TextView postitle;
    String pos;
    RecyclerView candidates_rv;
    AspirantsAdapter aspAdapt;
    PartyAdapter partyadapter;
    List<Aspirant> aspirants;
    List<Party> parties;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_party);
        postitle = findViewById(R.id.txt_position);
        candidates_rv = findViewById(R.id.candidatesrv);

        postitle.setText(getIntent().getExtras().getString("Position"));
        candidates_rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        parties = new ArrayList<>();
//        aspirants = new ArrayList<>();
        parties.add(new Party(R.drawable.person, "Party1", "Slogan Party1"));
        parties.add(new Party(R.drawable.person, "Party2", "Slogan Party1"));
        parties.add(new Party(R.drawable.person, "Party3", "Slogan Party1"));
        parties.add(new Party(R.drawable.person, "Party4", "Slogan Party1"));

        partyadapter = new PartyAdapter(PartyActivity.this, parties, this);
        candidates_rv.setAdapter(partyadapter);


    }


    @Override
    public void onPartClick(Party part, ImageView postimg) {
        Intent next = new Intent(PartyActivity.this, Candidate.class);
        next.putExtra("Party", part.getPartyName());
        startActivity(next);
    }
}
