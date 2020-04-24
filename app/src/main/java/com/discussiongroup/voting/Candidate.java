package com.discussiongroup.voting;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Candidate extends AppCompatActivity implements CandidateClick {
    static String party_frag;
    TextView postitle;
    String pos;
    RecyclerView candidates_rv;
    AspirantsAdapter aspAdapt;
    List<Aspirants> aspirants;
    Fragment aspirantVote;
    FragmentManager manageasp, manage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidates);
        postitle = findViewById(R.id.txt_position);
        candidates_rv = findViewById(R.id.candidatesrv);


        manage = getSupportFragmentManager();
        aspirantVote = manage.findFragmentById(R.id.aspfrag);
        manage.beginTransaction().setCustomAnimations(R.anim.topdown, R.anim.out).hide(aspirantVote).commit();


        postitle.setText(getIntent().getExtras().getString("Party"));
        candidates_rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        aspirants = new ArrayList<>();
        aspirants.add(new Aspirants(R.drawable.person, "Cand1", "Party1"));
        aspirants.add(new Aspirants(R.drawable.person, "Cand2", "Party2"));
        aspirants.add(new Aspirants(R.drawable.person, "Cand3", "Party3"));
        aspirants.add(new Aspirants(R.drawable.person, "Cand4", "Party4"));
        aspAdapt = new AspirantsAdapter(Candidate.this, aspirants, this);
        candidates_rv.setAdapter(aspAdapt);


    }

    @Override
    public void onBackPressed() {
        if (!(aspirantVote.isHidden())) {
            manage.beginTransaction().setCustomAnimations(R.anim.topdown, R.anim.out).hide(aspirantVote).commit();
            candidates_rv.setVisibility(View.VISIBLE);
        } else {
            super.onBackPressed();
        }


    }

    @Override
    public void onAspirantClick(Aspirants aspirante, ImageView postimg) {
        candidates_rv.setVisibility(View.INVISIBLE);
        manage.beginTransaction().setCustomAnimations(R.anim.topdown, R.anim.out).show(aspirantVote).commit();


    }
}
